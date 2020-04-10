package com.controller.user;

import com.model.*;
import com.model.enums.Role;
import com.service.SubjectService;
import com.service.UserService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Controller
public class TeacherController {

    @Autowired
    UserService userService;

    @Autowired
    SubjectService subjectService;

    @RequestMapping("/teacher/getTimeLines")
    public @ResponseBody
    JSONArray loadTeacherTimetable(@RequestParam("teacherId") String id) {

        RoleTeacher teacher = userService.getTeacherByIdWithTimelines(id);

        String startHour, startMin, finishHour, finishMin;

        JSONArray data = new JSONArray();
        for(TimeLine tl : teacher.getTimelines()){

            List<String> bookedHours = new ArrayList();
            startHour = tl.getStartingHour().split(":")[0];
            startMin = tl.getStartingHour().split(":")[1];
            finishHour = tl.getFinishingHour().split(":")[0];
            finishMin = tl.getFinishingHour().split(":")[1];

            bookedHours.add(startHour+startMin);
            // If the starting hour is an o'clock we add the half past time to both lists
            if("00".equals(startMin)) {
                bookedHours.add(startHour + "30");
            }
            // Navigation in all in between hours
            for (int index = Integer.parseInt(startHour)+1; index < Integer.parseInt(finishHour); index = index + 1) {
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

        return data;
    }

    @RequestMapping(value = "/user/teachers", method = RequestMethod.GET)
    public ModelAndView listStudentTeachers(HttpServletRequest request) {
        ModelAndView model = new ModelAndView("/user/listStudentTeachers");

        User user = (User)request.getSession().getAttribute("user");
        if(user == null){ //this is for testing, will be deleted
            user = userService.getUserById("1");
        }

        RoleStudent roleStudent;

        try{
            roleStudent = (RoleStudent) user.getRoleClass(Role.STUDENT);
        }catch(Exception e){
            return new ModelAndView("../../index");
        }

        ClassGroup group = roleStudent.getGroup();

        List<Subject> subjects = subjectService.getByGroup(group.getGroupId());

        Set<RoleTeacher> teachers = new HashSet<>();
        for (Subject subject : subjects)
            for (RoleTeacher teacher : subject.getTeachers())
                teachers.add(teacher);


        model.addObject("teachers", teachers);



        return model;
    }

    @RequestMapping("/teacher/getOtherTeachers")
    public @ResponseBody
    JSONArray loadOtherTeachers() {

        List<RoleTeacher> teachers = userService.getAllTeachers();

        JSONArray data = new JSONArray();

        for(RoleTeacher rt : teachers){
            JSONObject o = new JSONObject();
            User u = rt.getUserR();
            o.put("name", u.getName()+" "+u.getSurname()+" "+u.getSecondSurname());
            o.put("department", rt.getDepartment());
            o.put("teacherId", u.getUserId());

            data.add(o);
        }

        return data;
    }


}
