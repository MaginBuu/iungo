package com.controller.system;

import com.model.RoleAdmin;
import com.model.RoleStudent;
import com.model.Ticket;
import com.model.User;
import com.model.enums.Role;
import com.model.enums.TicketStatus;
import com.service.TicketService;
import com.service.UserService;
import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.method.P;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.Authenticator;
import java.util.*;

@Controller
public class SystemController {

    @Autowired
    TicketService ticketService;

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

    @RequestMapping("/responsible")
    public ModelAndView getResponsiblePage(HttpServletRequest request){

        User user = (User) request.getSession().getAttribute("user");
        if(user == null) user = userService.getUserById("1");
        List<RoleStudent> children = userService.getResponsibleChildList(user.getUserId());
        ModelAndView model = new ModelAndView("../../responsibleDashboard");
        model.addObject("children", children);
        return model;
    }

    @RequestMapping("/secretary")
    public ModelAndView getSecretaryPage(){
        return new ModelAndView("../../secretaryDashboard");
    }

    @RequestMapping("/role")
    public String getRolePage(Authentication authentication, HttpServletRequest request){
        String role;
        boolean testing = true;
        try {
            role = authentication.getAuthorities().toArray()[0].toString();
        }catch (Exception e){ //THIS IS FOR TESTING, WILL BE DELETED
            if(testing) {
                List<SimpleGrantedAuthority> updatedAuthorities = new LinkedList<>();
                SimpleGrantedAuthority authority = new SimpleGrantedAuthority(Role.TEACHER.toString());
                updatedAuthorities.add(authority);

                SecurityContextHolder.getContext().setAuthentication(
                        new UsernamePasswordAuthenticationToken(
                                SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                                SecurityContextHolder.getContext().getAuthentication().getCredentials(),
                                updatedAuthorities)
                );
                User user = userService.getUserById("1");
                try {
                    request.getSession().setAttribute("name", user.getFullName());
                    request.getSession().setAttribute("user", user);
                } catch (Exception e2) {
                }
            }

            return "redirect:/teacher";
        }

        if(role.equals(Role.ADMIN.toString()) || role.equals(Role.STUDENT.toString())
                || role.equals(Role.TEACHER.toString()) || role.equals(Role.RESPONSIBLE.toString())
                || role.equals(Role.SECRETARY.toString()))
            return "redirect:/" + role.toLowerCase();

        return "redirect:/student";
    }

    @RequestMapping(value = "/user/roles", method = RequestMethod.GET)
    public @ResponseBody
    JSONArray getRoles(HttpServletRequest request, Authentication authentication) {

        boolean testing = true;

        User activeUser = (User) request.getSession().getAttribute("user");
        if(activeUser == null && testing) activeUser = userService.getUserById("1");

        Set<Role> roles = new HashSet<>();
        if(activeUser != null){
                 roles = new HashSet<>(activeUser.getRoles().keySet());

            try {
                String role = authentication.getAuthorities().toArray()[0].toString();
                roles.remove(Role.valueOf(role));

            }catch (Exception e){ }
        }

        JSONArray data = new JSONArray();

        for(Role role : roles)
            data.add(role.toString());


        return data;
    }

    @RequestMapping(value = "/user/role")
    public String setRole(@RequestParam String role){

        List<SimpleGrantedAuthority> updatedAuthorities = new LinkedList<>();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role);
        updatedAuthorities.add(authority);

        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                        SecurityContextHolder.getContext().getAuthentication().getCredentials(),
                        updatedAuthorities)
        );

        return "redirect:/role";
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
        request.getSession().setAttribute("name", user.getFullName());
        request.getSession().setAttribute("user", user);
        System.out.println(user);

        //customerService.addCustomer(customer);
        return "redirect:/";
    }

    @RequestMapping(value = "/recover/password/{email}", method = RequestMethod.GET)
    public String recoverPassword(@PathVariable("email") String email, HttpServletRequest request) {
        Ticket t = new Ticket();
        t.setStatus(TicketStatus.CREATED);
        t.setCreationDate(new Date());
        t.setTitle("Password recovery");
        t.setDescription("Password recovery for user with email: "+email);
        ticketService.addTicket(t);

        return "redirect:/login";
    }
}
