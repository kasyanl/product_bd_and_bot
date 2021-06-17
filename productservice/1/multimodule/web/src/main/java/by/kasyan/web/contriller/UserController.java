package by.kasyan.web.contriller;

import by.kasyan.core.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/")
public class UserController {

    private UserService userService;

    @GetMapping
    public ModelAndView findAll() {
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("index");
        modelAndView.addObject("allUsers", userService.findAll());
        return modelAndView;
    }

    @GetMapping(value = "/add")
    public ModelAndView addproduct() {
        return new ModelAndView("adduser");
    }

    @PostMapping(value = "/add")
    public ModelAndView add( @RequestParam(value = "name") String name) {
        var modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:");
        userService.save(name);
        return modelAndView;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
