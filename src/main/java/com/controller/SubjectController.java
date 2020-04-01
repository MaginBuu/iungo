package com.controller;

import com.model.ClassGroup;
import com.model.Space;
import com.model.Subject;
import com.model.TimeLine;
import com.service.GroupService;
import com.service.SpaceService;
import com.service.SubjectService;
import com.service.TimeLineService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;


@Controller
public class SubjectController {

    @Autowired
    SubjectService subjectService;

    @Autowired
    SpaceService spaceService;

    @Autowired
    GroupService groupService;

    @Autowired
    TimeLineService timeLineService;

    @RequestMapping(value = "/subject/creation")
    public ModelAndView getProcedureCreationForm() {
        ModelAndView model = new ModelAndView("createSubject");
        Subject subject = new Subject();
        model.addObject("subject", subject);
        return model;
    }

    // to insert the data
    @RequestMapping(value = "/subject/creation", method = RequestMethod.POST)
    public String createProcedure(@Valid @ModelAttribute("subject") Subject subject, BindingResult result, ModelMap model) throws ParseException {
        //PER A FER
        return "redirect:/";
    }

    @RequestMapping(value = "/subject/modify/{subjectId}", method = RequestMethod.GET)
    public ModelAndView getSubjectModify(@PathVariable String subjectId) {
        Subject subject = subjectService.getByIdWithAll(subjectId);
        return new ModelAndView("updateSubject", "subject", subject);
    }

    @RequestMapping(value = "/subject/modify", method = RequestMethod.POST)
    public ModelAndView updateSubjectModify(@Valid @ModelAttribute("subject") Subject subject, @ModelAttribute("buttonName")
            String selection) {
        ClassGroup group = groupService.getClassGroupById(subject.getGroupId());
        subject.setSubjectGroup(group);
        subjectService.addSubject(subject);
        ModelAndView model;
        switch (selection) {
            case "add":
                model = new ModelAndView("addTimeline");
                List<Space> spaces = spaceService.getAll();
                TimeLine timeline = new TimeLine();
                timeline.setTimelineSubjectId(subject.getSubjectId());
                model.addObject("spaces", spaces);
                model.addObject("timeline", timeline);
                break;
            default:
                subjectService.addSubject(subject);
                model = new ModelAndView("updateSubject", "subject", subject);
                break;
        }

        return model;

    }

    @RequestMapping(value = "/subject/add/timeline", method = RequestMethod.POST)
    public String addTimeLine(@ModelAttribute("timeline") TimeLine timeLine){

        System.out.println(timeLine);
        Space space = spaceService.getById(timeLine.getTimelineSpaceId());
        Subject subject = subjectService.getById(timeLine.getTimelineSubjectId());
        timeLine.setSubjectTimeLine(subject);
        timeLine.setSpaceTimeLine(space);
        timeLineService.addTimeLine(timeLine);

        return "redirect:/subject/modify/"+timeLine.getTimelineSubjectId()+".do";
    }


    @RequestMapping("/subject/requestHours")
    public @ResponseBody
    JSONObject showAddTimeLine(@RequestParam("id") String id, @RequestParam("weekday") String weekday) {
        Space selectedSpace = spaceService.getByIdWithTimeline(id);
        List<String> bookedHours = new ArrayList();
        List<String> endHours = new ArrayList();
        if (selectedSpace != null) {
            for (TimeLine t : selectedSpace.getTimelines()) {
                if ((t.getWeekday().toString().toLowerCase()).equals(weekday.toLowerCase())) {
                    String startHour = t.getStartingHour().split(":")[0];
                    String startMin = t.getStartingHour().split(":")[1];
                    int finishHour = Integer.parseInt(t.getFinishingHour().split(":")[0]);
                    int finishMin = Integer.parseInt(t.getFinishingHour().split(":")[1]);

                    if("8".equals(startHour)) endHours.add(startHour + ":00");
                    bookedHours.add(startHour + ":" + startMin);
                    if("00".equals(startMin)) {
                        bookedHours.add(startHour + ":30");
                        endHours.add(startHour + ":30");
                    }
                    for (int index = Integer.parseInt(startHour)+1; index < finishHour; index = index + 1) {
                        bookedHours.add(index + ":00");
                        bookedHours.add(index + ":30");

                        endHours.add(index + ":00");
                        endHours.add(index + ":30");
                    }
                    if (finishMin == 30) {
                        bookedHours.add(finishHour + ":00");
                        endHours.add(finishHour + ":00");
                    }
                    endHours.add(finishHour+":"+finishMin);
                }
            }
        }
        JSONObject data = new JSONObject();
        data.put("start", bookedHours);
        data.put("end", endHours);
        return data;
    }

    @RequestMapping(value = "/subject/delete/timeline", method = RequestMethod.GET)
    public String deleteSpace(@RequestParam String timeLineId){
        System.out.println("delete " + timeLineId);
        timeLineService.deleteTimeLine(timeLineService.getById(timeLineId));
        spaceService.deleteSpace(spaceService.getById(timeLineId));

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String referer = request.getHeader("Referer");

        return "redirect:" + referer;
    }

}
