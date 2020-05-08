package app.messages;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/messages")
public class MessageController {

    @GetMapping("/welcome")
    public String welcome(Model model) {
        model.addAttribute("message", "Hello, Welcome to Sring Boot!");
        return "welcome";
    }

    @GetMapping("/welcome2")
    public ModelAndView welcome2() {
        ModelAndView mv = new ModelAndView("welcome");
        mv.addObject("message", "Hello, Welcome to Spring Boot! 2");
        return mv;
    }
}