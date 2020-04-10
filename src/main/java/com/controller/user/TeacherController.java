package com.controller.user;

import com.model.RoleTeacher;
import com.model.TimeLine;
import com.model.User;
import com.service.UserService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class TeacherController {

    @Autowired
    UserService userService;

    @RequestMapping("/teacher/getTimeLines")
    public @ResponseBody
    JSONArray loadTeacherTimetable(@RequestParam("teacherId") String teacherId, HttpServletRequest request) {

        //User user = (User)request.getSession().getAttribute("user");

        RoleTeacher teacher = userService.getTeacherByIdWithTimelines(teacherId);

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
}
