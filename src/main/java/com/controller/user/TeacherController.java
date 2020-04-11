package com.controller.user;

import com.model.*;
import com.model.enums.Role;
import com.service.SubjectService;
import com.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class TeacherController {

    private static final Logger logger = LogManager.getLogger(TeacherController.class);

    @Autowired
    UserService userService;

    @Autowired
    SubjectService subjectService;

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
        List<Subject> subjects = new LinkedList<>();

        try{
            User user = (User)request.getSession().getAttribute("user");
            if(user == null){ //this is for testing, will be deleted
                user = userService.getUserById("1");
            }
            logger.info("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Session user successfully loaded: " + user.getUserId());
            roleStudent = (RoleStudent) user.getRoleClass(Role.STUDENT);
            logger.info("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] - User is an student");
            ClassGroup group = roleStudent.getGroup();
            logger.info("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] - user has group");
            subjects = subjectService.getByGroup(group.getGroupId());
            logger.info("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] - Group has subject");

        }catch(Exception e){
            logger.error("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] - failed to get subjects - User->StudentRole->group->subjects");
        }



        Set<RoleTeacher> teachers = new HashSet<>();
        try {
            for (Subject subject : subjects)
                for (RoleTeacher teacher : subject.getTeachers())
                    teachers.add(teacher);

            logger.info("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] - teachers loaded succesfully");
        }catch(Exception e){
            logger.error("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] - failed to get teachers");
        }


        model.addObject("teachers", teachers);

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


}
