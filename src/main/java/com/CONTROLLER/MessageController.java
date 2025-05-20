package com.CONTROLLER;

import com.MODEL.Message;
import com.SERVICE.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class MessageController {
    @Autowired
    private MessageService messageService;

   /* @RequestMapping("/api/message")
    public Message getMessage() {
        return messageService.getHelloMessage();
    }
    
    @RequestMapping("/api/m2")
    public String getMessage2() {
        return "hello";
    }*/
    
    @PostMapping("/api/getmessage")
    public String receiveMessage(@RequestBody String message) {
        System.out.println("Ricevuto: " + message);
        return "Hai scritto: " + message;
    }
    
    @PostMapping("/api/getlongmessage")
    public String receiveLongMessage(@RequestBody Message message) {
        System.out.println("Ricevuto: " + message);
        return "Hai scritto: " + message;
    }

}
