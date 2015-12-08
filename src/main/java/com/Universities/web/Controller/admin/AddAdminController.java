package com.Universities.web.Controller.admin;

import com.Universities.web.dto.UserDTO;
import com.Universities.web.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

/**
 * Created by andreealibotean on 12/8/2015.
 */
@Controller
@RequestMapping(value = "/admin")
public class AddAdminController {


    @Autowired
    UserService userService;

    @RequestMapping(value = "/adminForm",method = RequestMethod.GET)
    public String setModerForm(Model model){
        UserDTO userDTO = new UserDTO();
        model.addAttribute("user", userDTO);
        return "admin/adminForm";
    }

    @RequestMapping(value = "/adminForm",method = RequestMethod.POST)
    public String submitModerForm(@Valid @ModelAttribute("user") UserDTO user, BindingResult result, SessionStatus sessionStatus, final RedirectAttributes redirectAttributes){

        if (result.hasErrors()) {
            ModelAndView modelAndView = new ModelAndView("admin/adminForm");
            modelAndView.addObject("user", user);
            return modelAndView.getViewName();
        }


        if(userService.checkUsernameDuplicate(user)){
            ModelAndView modelAndView1 = new ModelAndView("admin/adminFormException");
            UserDTO user1 = new UserDTO();
            modelAndView1.addObject("user", user1);
            return modelAndView1.getViewName();
        }

        user.setIdRole(1);
        userService.addUser(user);
        redirectAttributes.addFlashAttribute("msg","User "+user.getLogin()+" has been created.");
        return "redirect:/admin/adminForm";
    }
}
