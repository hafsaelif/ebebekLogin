package jbr.springmvc.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import jbr.springmvc.model.User;
import jbr.springmvc.service.UserService;

@Controller
public class RegistrationController {
  @Autowired
  public UserService userService;

  @RequestMapping(value = "/register", method = RequestMethod.GET)
  public ModelAndView showRegister(HttpServletRequest request, HttpServletResponse response) {
    ModelAndView mav = new ModelAndView("register");
    mav.addObject("user", new User());
    return mav;
  }

  @RequestMapping(value = "/registerProcess", method = RequestMethod.POST)
  public ModelAndView addUser(HttpServletRequest request, HttpServletResponse response,
      @ModelAttribute("user") User user) {

    String ePattern = "^[A-Za-z].*?@ebebek\\.com$";
    java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
    java.util.regex.Matcher m = p.matcher(user.getEmail());
    
    boolean upperCase = false;
    boolean numberCount = false;

    for (int i = 0; i < user.getPassword().length(); i++) {
      if (Character.isUpperCase(user.getPassword().charAt(i))) {
        upperCase = true;
      }
      if (Character.isDigit(user.getPassword().charAt(i))) {
        numberCount = true;
      }
    }

    ModelAndView mav = null;
    if (user.getUsername().length() <= 3) {
      mav = new ModelAndView("register");
      mav.addObject("message", "Username lenght should be more than 3!!");
      return mav;
    } 
    else if (user.getPassword().length() < 7 || user.getConf_pass().length() < 7) {
      mav = new ModelAndView("register");
      mav.addObject("message", "Password should be more than 6 characters!!");
      return mav;
    }
    else if (!user.getPassword().equals(user.getConf_pass())) {
      mav = new ModelAndView("register");
      mav.addObject("message", "Password should be same with Confrim Password!!");
    } 
    else if(!numberCount) {
      mav = new ModelAndView("register");
      mav.addObject("message", "Password should be contain 1 number characters !!");
    }
    else if(!upperCase) {
      mav = new ModelAndView("register");
      mav.addObject("message", "Password should be contain 1 uppercase latter !!");
    }
    else if(!m.matches()) {
      mav = new ModelAndView("register");
      mav.addObject("message", "Email format should be like this m.gurle@ebebek.com !!");
    }
    else {
      userService.register(user);
      mav = new ModelAndView("welcome");
      mav.addObject("username", user.getUsername());
    }
    return mav;
  }

}
