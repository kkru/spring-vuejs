package app.messages.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import app.messages.model.Message;
import app.messages.service.MessageService;

import org.springframework.ui.Model;

@Controller
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/messages")
    public String index() {
        return "index";
    }

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

    @GetMapping("/api/messages")
    @ResponseBody
    public ResponseEntity<List<Message>> getMessages() {
        List<Message> messages = messageService.getMessages();
        return ResponseEntity.ok(messages);
    }

    @PostMapping("/api/messages")
    @ResponseBody
    public ResponseEntity<Message> saveMessage(@RequestBody MessageData data) {
        Message saved = messageService.save(data.getText());
        if(saved == null) {
            return ResponseEntity.status(500).build();
        }
        return ResponseEntity.ok(saved);
    }
}