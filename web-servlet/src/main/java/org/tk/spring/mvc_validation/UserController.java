package org.tk.spring.mvc_validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.tk.spring.mvc_validation.mdl.User;
import org.tk.spring.mvc_validation.srv.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public @ResponseBody String postMethod() {
        return "post success";
    }

    @RequestMapping("/default")
    public ModelAndView validateDefault() {
        User user = new User();
        try {
            userService.validateDefault(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("failure");
        }
        return new ModelAndView("success");
    }

    @RequestMapping("/all")
    public ModelAndView validateAll() {
        User user = new User();
        try {
            userService.validateAll(user);
        } catch (Exception e) {
            e.printStackTrace();
            return new ModelAndView("failure");
        }

        return new ModelAndView("success");
    }

    @RequestMapping(value = "success")
    public @ResponseBody
    String success() {
        return "validation success";
    }

    @RequestMapping(value = "failed")
    public @ResponseBody
    String failure() {
        return "validation failed";
    }
}
