package com.controller.system;

import com.model.RoleAdmin;
import com.model.User;
import com.model.enums.Role;
import com.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.Authenticator;
import java.util.LinkedList;
import java.util.List;

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
        return new ModelAndView("../../adminDashboard");
    }


    @RequestMapping("/student")
    public ModelAndView getStudentPage(){ return new ModelAndView("../../studentDashboard"); }

    @RequestMapping("/teacher")
    public ModelAndView getTeacherPage(){
        return new ModelAndView("../../teacherDashboard");
    }


    @RequestMapping("/role")
    public String getRolePage(Authentication authentication){
        String role;
        try {
            role = authentication.getAuthorities().toArray()[0].toString();
        }catch (Exception e){

            List<SimpleGrantedAuthority> updatedAuthorities = new LinkedList<>();
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(Role.TEACHER.toString());
            updatedAuthorities.add(authority);

            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(
                            SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                            SecurityContextHolder.getContext().getAuthentication().getCredentials(),
                            updatedAuthorities)
            );
            return "redirect:/teacher";
        }

        if(role.equals(Role.ADMIN.toString()) || role.equals(Role.STUDENT.toString()) || role.equals(Role.TEACHER.toString()))
            return "redirect:/" + role.toLowerCase();

        return "redirect:/student";
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
