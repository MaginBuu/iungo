package com.controller.user;

import com.model.*;
import com.model.Incidence;
import com.model.encapsulators.UserSubjectEncapsulator;
import com.model.encapsulators.UserTaskEncapsulator;
import com.model.enums.FaultType;
import com.model.enums.Role;
import com.model.enums.Stage;
import com.service.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
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

    @Autowired
    CourseService courseService;

    @Autowired
    ProcedureService procedureService;

    @Autowired
    IncidenceService incidenceService;

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

    //-------------------------------------------------- INICI DE POSAR FALTES SECRETARIA

    @RequestMapping(value = "/teacher/select/group")
    public ModelAndView groupSelectAccess(HttpServletRequest request, Authentication authentication) {

        String role = authentication.getAuthorities().toArray()[0].toString();

        ModelAndView model;
        if(!"TEACHER".equals(role))
            model = new ModelAndView("/selectGroup", "group", new ClassGroup());

        else{
            User user = (User) request.getSession().getAttribute("user");
            if(user == null) user = userService.getUserById("1");
            RoleTeacher teacher = userService.getTeacherByIdWithSubjects(user.getUserId());

            List<Subject> subjects = teacher.getSubjects();

            model = new ModelAndView("/subject", "subjects", subjects);
            model.addObject("path", "/teacher/getStudentsSubject?subjectId=");


        }

        return model;

    }

    @RequestMapping(value = "/teacher/getStudentsSubject", method = RequestMethod.GET)
    public ModelAndView getStudentsSubject(@RequestParam String subjectId) {

        List<User> students;
        Subject subject = subjectService.getById(subjectId);

        logger.info("[" + new Object() {
        }.getClass().getEnclosingMethod().getName() + "] - subject loaded successfully");

        try {
             students = userService.getStudentsByGroup(subject.getSubjectGroup().getGroupId());

            logger.info("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] - students loaded successfully");
        }catch (Exception e){

            logger.error("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] -  students could not be leaded " + e);
            return null;
        }

        return new ModelAndView("/putStudentIncidences", "students", students);

    }


    @RequestMapping(value = "/teacher/getStudentsGroup", method = RequestMethod.GET)
    public ModelAndView getStudentsGroup(@Valid @ModelAttribute(value = "group") ClassGroup group) {

        Course course = courseService.findByDate(Calendar.getInstance().get(Calendar.YEAR) - 1);
        group.setCourse(course);
        List<User> studentsGroup;

        try {
            group = groupService.getGroupsByStageAndLevelAndGroupAndCourse(group);
            studentsGroup = userService.getStudentsByGroup(group.getGroupId());

            logger.info("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] - students loaded successfully");

        }catch (Exception e){

            logger.error("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] -  students could not be leaded " + e);
            return null;
        }
        return new ModelAndView("/putStudentIncidences", "students", studentsGroup);

    }

    @RequestMapping(value = "/getLevelsByStage", method = RequestMethod.GET)
    public @ResponseBody
    JSONArray getLevels(@RequestParam("stage") Stage stage) {

        logger.info("[" + new Object() {
        }.getClass().getEnclosingMethod().getName() + "] -  Session user successfully loaded");

        List<Integer> levels = groupService.getLevelsByStage(stage);

        JSONArray data = new JSONArray();

        for(Integer level : levels)
            data.add(level);


        return data;
    }

    @RequestMapping(value = "/getGroupsByStageAndLevel", method = RequestMethod.GET)
    public @ResponseBody
    JSONArray getGroups(@RequestParam("stage") Stage stage, @RequestParam("level") int level) {

        logger.info("[" + new Object() {
        }.getClass().getEnclosingMethod().getName() + "] -  Session user successfully loaded");

        List<String> groups = groupService.getGroupsByStageAndLevel(stage, level);

        JSONArray data = new JSONArray();

        for(String group : groups)
            data.add(group);


        return data;
    }


    @RequestMapping(value = "/teacher/incidence", method = RequestMethod.GET)
    public ModelAndView getIncidenceForm(@RequestParam("userId") String userId, @RequestParam("origin") String selection){

        User user = userService.getUserById(userId);

        Incidence incidence = new Incidence();
        incidence.setStudent((RoleStudent) user.getRoles().get(Role.STUDENT));

        ModelAndView model = new ModelAndView("createIncidence");
        model.addObject("incidence", incidence);
        model.addObject("origin", selection);
        return model;

    }

    @RequestMapping(value = "/incidence/creation", method = RequestMethod.POST)
    public String setIncidence(@Valid @ModelAttribute(value = "incidence") Incidence incidence
            , BindingResult result, HttpServletRequest request){

        User user = userService.getUserById(incidence.getStudent().getUserR().getUserId());
        incidence.setStudent((RoleStudent) user.getRoles().get(Role.STUDENT));

        String title = user.getFullName() + " - " + incidence.getFaultType().toString().toLowerCase() + " fault";
        String description = incidence.getDescription();


        List<RoleResponsible> responsibles = userService.getStudentResponsibles(user.getUserId());

        Calendar c = Calendar.getInstance(); // starts with today's date and time
        c.add(Calendar.DAY_OF_YEAR, 2);  // advances day by 2
        Date date = c.getTime(); // gets modified time


        Boolean online = incidence.getFaultType().equals(FaultType.ATTENDANCE) ? true : false;

        Procedure procedure = new Procedure(title, description, online, date);
        procedure.setUserP(responsibles.get(responsibles.size() - 1).getUserR());
        procedureService.addProcedure(procedure);
        incidence.setProcedure(procedure);



        /*
        for(RoleResponsible responsible : responsibles){
            procedure.setUserP(responsible.getUserR());
            procedureService.addProcedure(procedure);

            if (responsible.equals(responsibles.get(1)))
                incidence.setProcedure(procedure);

            procedure = procedure.clone();

        }*/

        incidence.setCreationDate(new Date());
        incidenceService.addIncidence(incidence);

        ClassGroup group = ((RoleStudent) user.getRoles().get(Role.STUDENT)).getGroup();

        String referer = request.getHeader("Referer");

        if(referer.contains("origin=profile")) return "redirect:/teacher/"+user.getUserId()+"/profile.do";
        else return "redirect:/teacher/getStudentsGroup?stage="+group.getStage()+"&level="+group.getLevel()+"&group="+group.getGroup();

    }

    @RequestMapping(value = "/teacher/comment", method = RequestMethod.GET)
    public ModelAndView getCommentForm(@RequestParam String userId){

        User user = userService.getUserById(userId);

        Comment comment = new Comment();
        comment.setUser(user);

        return new ModelAndView("createComment", "comment", comment);

    }

    @RequestMapping(value = "/comment/creation", method = RequestMethod.POST)
    public String setComment(@Valid @ModelAttribute(value = "comment") Comment comment, BindingResult result){

        User user = userService.getUserById(comment.getUser().getUserId());

        Calendar c = Calendar.getInstance(); // starts with today's date and time
        c.add(Calendar.DAY_OF_YEAR, 2);  // advances day by 2
        Date date = c.getTime(); // gets modified time

        comment.setCreationDate(new Date());
        incidenceService.addComment(comment);

        return "redirect:/teacher/"+user.getUserId()+"/profile.do";

    }

    //-------------------------------------------------- ACCEDIR PERFIL ESTUDIANT


    @RequestMapping(value = "/teacher/{userId}/profile", method = RequestMethod.GET)
    public ModelAndView accessStudentProfileTeacher(@PathVariable("userId") String userId){

        User user = userService.getUserById(userId);
        List<RoleResponsible> roleResponsibles = userService.getStudentResponsibles(userId);

        ModelAndView model = new ModelAndView("/user/studentProfile", "user", user);
        model.addObject("message", new Message());
        model.addObject("responsibles", roleResponsibles);
        return model;
    }

    //-------------------------------------------------- ACCEDIR PERFIL RESPONSABLE

    @RequestMapping(value = "/teacher/responsible/{userId}", method = RequestMethod.GET)
    public ModelAndView accessResponsibleProfileTeacher(@PathVariable("userId") String userId){

        User user = userService.getUserById(userId);
        List<RoleStudent> roleStudents = userService.getResponsibleChildList(userId);

        ModelAndView model = new ModelAndView("/responsibleProfile", "user", user);
        model.addObject("children", roleStudents);
        model.addObject("message", new Message());
        return model;
    }

    //-------------------------------------------------- INICI DE MODIFICAR ASSIGNATURA PROFE PER CREAR TASCA
    @RequestMapping(value = "/teacher/subjects")
    public ModelAndView teacherSubjectsAccess(HttpServletRequest request) {

        try {
            User user = (User)request.getSession().getAttribute("user");
            if(user==null) user = userService.getUserById("1");
            logger.info("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] -  Session user successfully loaded");

            RoleTeacher teacher = userService.getTeacherByIdWithSubjects(user.getUserId());
            List<Subject> subjects = new ArrayList<>();

            if (teacher != null && teacher.getSubjects() != null) {
                subjects = teacher.getSubjects();

            } else {
                logger.warn("[" + new Object() {
                }.getClass().getEnclosingMethod().getName() + "] -  Teacher has no subjects");
            }
            ModelAndView model = new ModelAndView("/subject");
            model.addObject("subjects", subjects);
            model.addObject("path", "/teacher/subjects/");

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
            task.setReports(0);
            taskService.addTask(task);
            ClassGroup cg = groupService.getGroupBySubjectId(subjectId);
            for (RoleStudent s : cg.getStudents()){
                UserTask ut = new UserTask();
                    ut.setStudent(s);
                    ut.setTask(task);
                    taskService.addUserTask(ut);
            }

            return "redirect:/teacher/subjects/"+subjectId+".do";
        } catch (Exception e) {
            logger.error("[" + new Object() {
            }.getClass().getEnclosingMethod().getName() + "] -  Error finding the procedure with id: " + e);

            return null;
        }
    }

    @RequestMapping(value = "/teacher/task/evaluate/{taskId}")
    public ModelAndView testTask(@PathVariable("taskId") String taskId) {

        ModelAndView model = new ModelAndView("/evaluateTask");
        UserTaskEncapsulator ute = new UserTaskEncapsulator();
        ute.setTasks(taskService.getUserTaskByTaskId(taskId));
        model.addObject("taskList", ute);
        model.addObject("taskInfo", ute.getTasks().get(0).getTask());
        model.addObject("subjectId", ute.getTasks().get(0).getTask().getChapter().getSubject().getSubjectId());

        return model;

    }

    @RequestMapping(value = "/teacher/task/saveEvaluation/{subjectId}/{taskId}")
    public String evaluateTask(@ModelAttribute("taskList") UserTaskEncapsulator ute, @PathVariable("subjectId") String subjectId, @PathVariable("taskId") String taskId) {

        Task t = new Task();
        RoleStudent rs = new RoleStudent();
        for(UserTask ut : ute.getTasks()){
            t.setTaskId(taskId);
            rs.setRoleId(ut.getStudent().getRoleId());
            ut.setTask(t);
            ut.setStudent(rs);
            taskService.addUserTask(ut);
        }

        return "redirect:/teacher/subjects/"+subjectId+".do";
    }

    //-------------------------------------------------- INICI D'AVALUAR ALUMNE

    @RequestMapping(value = "/teacher/evaluate")
    public ModelAndView evaluateGroup(HttpServletRequest request) {

        ModelAndView model = new ModelAndView("/evaluate");
        User user = (User) request.getSession().getAttribute("user");
        ClassGroup group = new ClassGroup();
        if(user == null){ //this is for testing, will be deleted
             group = userService.getGroupByTutor("1");
        }
        List<RoleStudent> listStudents = group.getStudents();
            List<Evaluation> evaluations = userService.getEvaluations();
            model.addObject("listStudents", listStudents);
            model.addObject("evaluationList", evaluations);
            return model;
        }

        @RequestMapping(value= "/teacher/evaluate/{studentId}")
        public ModelAndView evaluateStudent(@PathVariable("studentId") String studentId, @RequestParam String evaluationId) throws CloneNotSupportedException {
            ModelAndView model = new ModelAndView("/evaluateStudent");
            RoleStudent rs = userService.getStudentByUserId(studentId);
            List<Subject> subjectList = subjectService.getByGroupNoTeachers(rs.getGroup().getGroupId());
            UserSubject us = new UserSubject();
            List<UserSubject> userSubjectList = new ArrayList<>();
            UserSubject usAux = new UserSubject();
            for(Subject s : subjectList){
                usAux = subjectService.getUserSubjectByUserAndSubjectAndEvaluation(studentId, s.getSubjectId(), evaluationId);
                if(usAux == null) {
                    usAux = (UserSubject) us.clone();
                    usAux.setSubject(s);
                }
                userSubjectList.add(usAux);
            }
            UserSubjectEncapsulator userSubjectEncapsulator = new UserSubjectEncapsulator();
            userSubjectEncapsulator.setUserSubjects(userSubjectList);
            model.addObject("student", rs);
            model.addObject("evaluation", evaluationId);
            model.addObject("subjects", userSubjectEncapsulator);
            return model;
        }

        @RequestMapping(value= "/teacher/evaluate/{studentId}/save/{evaluationId}")
        public String saveStudentEvaluation(@ModelAttribute("subjects") UserSubjectEncapsulator use, @PathVariable("studentId") String studentId
                , @PathVariable("evaluationId") String evaluationId) {
            RoleStudent rs = new RoleStudent();
            rs.setRoleId(studentId);
            Evaluation e = new Evaluation(evaluationId);
            for(UserSubject us : use.getUserSubjects()){
                if(us.getGrade() != 0.0) {
                    us.setStudent(rs);
                    us.setEvaluation(e);
                    userService.addUserSubject(us);
                }
            }
            return "redirect:/teacher/evaluate.do";
        }



        @RequestMapping(value = "/teacher/delete/task", method = RequestMethod.GET)
        public String deleteSpace(@RequestParam String taskId){
            try{
                Task t = new Task();
                t.setTaskId(taskId);
                taskService.deleteUserTask(taskId);
                taskService.deleteTask(t);
            logger.info("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  Space sucessfully removed");
        }catch (Exception e){
            logger.error("["+new Object(){}.getClass().getEnclosingMethod().getName()+"] -  failed to delete space: " + e);
        }

        //Getting the referer page
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String referer = request.getHeader("Referer");

        return "redirect:" + referer;
    }
}
