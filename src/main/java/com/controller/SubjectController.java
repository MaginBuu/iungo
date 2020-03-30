package com.controller;

import com.model.Space;
import com.model.Subject;
import com.model.TimeLine;
import com.service.SpaceService;
import com.service.SubjectService;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        subjectService.addSubject(subject);
        ModelAndView model;
        switch (selection) {
            case "add":
                model = new ModelAndView("addTimeline");
                List<Space> spaces = spaceService.getAll();
                TimeLine timeline = new TimeLine();
                model.addObject("subjectId", subject.getSubjectId());
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

    @RequestMapping(value = "/subject/add/timeline", method = RequestMethod.GET)
    public void addTimeline(@Valid @ModelAttribute("subject") Subject subject) {
        System.out.println("ADD");
        System.out.println(subject.getName());
    }

    @RequestMapping("/subject/ajaxdos")
    public @ResponseBody
    JSONObject peeenis(@RequestParam("var1") String var1, @RequestParam("weekday") String weekday) {
        Space selectedSpace = spaceService.getByIdWithTimeline(var1);
        List<String> bookedHours = new ArrayList();
        List<String> endHours = new ArrayList();
        if (selectedSpace != null) {
            for (TimeLine t : selectedSpace.getTimelines()) {
                if ((t.getWeekday().toString().toLowerCase()).equals(weekday.toLowerCase())) {
                    String startHour = t.getStartingHour().split(":")[0];
                    String startMin = t.getStartingHour().split(":")[1];
                    int finishHour = Integer.parseInt(t.getFinishingHour().split(":")[0]);
                    int finishMin = Integer.parseInt(t.getFinishingHour().split(":")[1]);

                    bookedHours.add(startHour + ":" + startMin);
                    if("00".equals(startMin)) bookedHours.add(startHour + ":30");
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
                }
            }
        }
        JSONObject data = new JSONObject();
        data.put("start", bookedHours);
        data.put("end", endHours);
        return data;
    }
}
