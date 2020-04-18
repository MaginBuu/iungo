package com.controller.user;

import com.model.*;
import com.model.enums.Role;
import com.service.GroupService;
import com.service.SubjectService;
import com.service.TaskService;
import com.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.cfg.beanvalidation.GroupsPerOperation;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Controller
public class TeacherController {

    private static final Logger logger = LogManager.getLogger(TeacherController.class);

    @Autowired
    UserService userService;

    @Autowired
    SubjectService subjectService;

    @Autowired
    TaskService taskService;

    @Autowired
    GroupService groupService;

    /**
     * Looks for the timelines of a certain teacher given an id.
     *
     * @param id the id of the teacher we want to find the timelines
     * @return all the hours that the teacher is busy
     */
    @RequestMapping("/teacher/getTimeLines")
    public @ResponseBody
    JSONArray loadTeacherTimetable(@RequestParam("teacherId") String id) {

        RoleTeacher teacher = userService.getTeacherByIdWithTimelines(id);

        String startHour, startMin, finishHour, finishMin;

        JSONArray data = new JSONArray();
        try {
            for (TimeLine tl : teacher.getTimelines()) {

                List<String> bookedHours = new ArrayList();
                startHour = tl.getStartingHour().split(":")[0];
                startMin = tl.getStartingHour().split(":")[1];
                finishHour = tl.getFinishingHour().split(":")[0];
                finishMin = tl.getFinishingHour().split(":")[1];

                bookedHours.add(startHour + startMin);
                // If the starting hour is an o'clock we add the half past time to both lists
                if ("00".equals(startMin)) {
                    bookedHours.add(startHour + "30");
                }
                // Navigation in all in between hours
                for (int index = Integer.parseInt(startHour) + 1; index < Integer.parseInt(finishHour); index = index + 1) {
                    bookedHours.add(index + "00");
                    bookedHours.add(index + "30");
                }
                // Same process as starting hour in reverse
                if ("30".equals(finishMin)) {
                    bookedHours.add(finishHour + "00");
                }

                JSONObject o = new JSONObject();
                o.put("start", bookedHours);
                o.put("day", tl.getWeekday().ordinal());
                o.put("space", tl.getSpaceName());
                o.put("subject", tl.getSubjectName());

                data.add(o);
            }

            logger.info("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] -  Timelines successfully found and processed");
            return data;

        } catch (Exception e) {
            logger.error("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] -  No timelines found or error processing them: " + e);

            return null;
        }
    }

    /**
     * Lists all the teachers involved in a users education.
     *
     * @return all the teachers of the active session's user
     */
    @RequestMapping(value = "/user/teachers", method = RequestMethod.GET)
    public ModelAndView listStudentTeachers(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("/user/listStudentTeachers");

        RoleStudent roleStudent;
        List<Subject> subjects;

        try{
            User user = (User)request.getSession().getAttribute("user");
            if(user == null){ //this is for testing, will be deleted
                user = userService.getUserById("1");
            }
            logger.info("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] - Session user successfully loaded: " + user.getUserId());
            roleStudent = (RoleStudent) user.getRoleClass(Role.STUDENT);
            logger.info("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] - User is an student");
            ClassGroup group = roleStudent.getGroup();
            logger.info("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] - User has group");
            subjects = subjectService.getByGroup(group.getGroupId());
            logger.info("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] - Group has subject");
            Set<RoleTeacher> teachers = new HashSet<>();
            for (Subject subject : subjects)
                for (RoleTeacher teacher : subject.getTeachers())
                    teachers.add(teacher);

            logger.info("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] - Teachers loaded successfully");

            model.addObject("teachers", teachers);

        }catch(Exception e){
            logger.error("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] - Failed to get teachers - User->StudentRole->Group->Subjects->Teachers: " + e);
            return null;
        }

        return model;
    }

    /**
     * Lists all the teachers present in the school data base.
     *
     * @return a list of all the teachers in the data base
     */
    @RequestMapping("/teacher/getOtherTeachers")
    public @ResponseBody
    JSONArray loadOtherTeachers() {

        List<RoleTeacher> teachers = userService.getAllTeachers();

        JSONArray data = new JSONArray();

        try {

            for (RoleTeacher rt : teachers) {
                JSONObject o = new JSONObject();
                User u = rt.getUserR();
                o.put("name", u.getName() + " " + u.getSurname() + " " + u.getSecondSurname());
                o.put("department", rt.getDepartment());
                o.put("teacherId", u.getUserId());

                data.add(o);
            }
            logger.info("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] - Teachers loaded successfully");
            return data;

        } catch (Exception e) {
            logger.error("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] -  No teachers found: " + e);

            return null;
        }
    }

    @RequestMapping(value = "/teacher/subjects")
    public ModelAndView teacherSubjectsAccess() {

        //FALTA AGAFAR L'USUARI!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        try {
            logger.info("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] -  Session user successfully loaded");

            RoleTeacher teacher = userService.getTeacherByIdWithSubjects("1");
            List<Subject> subjects = new ArrayList<>();

            if (teacher != null && teacher.getSubjects() != null) {
                subjects = teacher.getSubjects();

            } else {
                logger.warn("[" + new Object() {
                }.getClass().getEnclosingMethod().getName() + "] -  Teacher has no subjects");
            }
            ModelAndView model = new ModelAndView("/subject");
            model.addObject("subjects", subjects);

            return model;
        } catch (Exception e) {
            logger.error("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] -  Error accessing the procedures: " + e);

            return null;
        }
    }

    @RequestMapping(value = "/teacher/subjects/{subjectId}")
    public ModelAndView getSubjectInfo(@PathVariable("subjectId") String subjectId) {
        Subject subject = subjectService.getByIdWithChapters(subjectId);
        ModelAndView model = new ModelAndView("/subjectInfo");
        model.addObject("subject", subject);
        return model;
    }
    
    /**
     * Processes the petition to get to the group creation page.
     *
     * @param teacherId the id of the teacher we want to access the profile of
     * @return ModelAndView with the desired .jsp file and its required model & objects
     */
    @RequestMapping(value = "/user/teacher/{teacherId}", method = RequestMethod.GET)
    public ModelAndView accessTeacherProfile(@PathVariable("teacherId") String teacherId) {
        try {
            ModelAndView model = new ModelAndView("/teacherProfile");
            RoleTeacher teacher = userService.getTeacherById(teacherId);

            logger.info("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] -  Teacher with id " + teacherId + " successfully found");

            Procedure procedure = new Procedure();
            procedure.setUserP(new User());
            model.addObject("teacher", teacher);
            model.addObject("procedure", procedure);
            model.addObject("message", new Message());
            return model;
        } catch (Exception e) {
            logger.error("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] -  Error finding the teacher with id " + teacherId + ": " + e);

            return null;
        }
    }


    /*/teacher/subjects/task/create*/

    @RequestMapping(value = "/teacher/subjects/modify/{id}")
    public ModelAndView addTask(@PathVariable("id") String subjectId) {
        ModelAndView model = new ModelAndView("/addTask");
        Task task = new Task();
        task.setChapter(new Chapter());
        model.addObject("task", task);
        model.addObject("subjectId", subjectId);
        model.addObject("chapters", subjectService.getByIdWithChapters(subjectId).getChapters());
        return model;
    }



    @RequestMapping(value = "/teacher/subjects/task/create", method= RequestMethod.POST)
    public String saveTask(@Valid @ModelAttribute("task") Task task, @ModelAttribute("subjectId") String subjectId, BindingResult bindingResult) {

        try {

            task.setCreationDate(new Date());
            taskService.addTask(task);
            ClassGroup cg = groupService.getGroupBySubjectId(subjectId);
            for (RoleStudent s : cg.getStudents()){
                UserTask ut = new UserTask();
                ut.setStudent(s);
                ut.setTask(task);
                taskService.addUserTask(ut);
            }

            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            String referer = request.getHeader("Referer");

            return "redirect:" + referer;
        } catch (Exception e) {
            logger.error("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] -  Error finding the procedure with id: " + e);

            return null;
        }
    }


}
