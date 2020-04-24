package com.controller.system;

import com.model.RoleAdmin;
import com.model.User;
import com.model.enums.Role;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.Authenticator;

@Controller
public class SystemController {

    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout, Model model) {
        if (error != null)
            model.addAttribute("error", "Invalid username and Password"); //dunno if it works
        if (logout != null)
            model.addAttribute("logout", "You have logged out successfully");
        return "login";
    }


    @RequestMapping("/admin")
    public ModelAndView getAdminPage(){
        return new ModelAndView("../../paginainicialadmin");
    }


    //@RequestMapping("/student")
    //public ModelAndView getStudentPage(){ return new ModelAndView("../../paginainicialadmin"); }

    @RequestMapping("/teacher")
    public ModelAndView getTeacherPage(){
        return new ModelAndView("../../teacherDashboard");
    }

    @RequestMapping("/student")
    public String getStudentPage(){ return "redirect:/"; }

    @RequestMapping("/role")
    public String getRolePage(Authentication authentication){
        String role = authentication.getAuthorities().toArray()[0].toString();

        if(role.equals(Role.ADMIN.toString()) || role.equals(Role.STUDENT.toString()))
            return "redirect:/" + role.toLowerCase();

        return "redirect:/";
    }




    /**
     *
     * After log in springSecurity redirects to this path,
     * 		used to save full information of the user as a session variable
     * @param request
     * @return it redirects to the path "/" (index)
     */
    @RequestMapping(value = "/postlogin", method = RequestMethod.GET)
    public String postLogin(HttpServletRequest request) {
        System.out.println("postlogin");
        System.out.println(request.getUserPrincipal().getName());
        User user = userService.getUserByEmail(request.getUserPrincipal().getName());
        request.getSession().setAttribute("name", user.getName());
        request.getSession().setAttribute("user", user);
        System.out.println(user);

        //customerService.addCustomer(customer);
        return "redirect:/";
    }
}
