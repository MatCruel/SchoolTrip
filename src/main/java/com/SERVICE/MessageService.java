package com.SERVICE;

import com.MODEL.Message;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    public Message getHelloMessage() {
        return new Message("Ciao dal backend Spring!", "");
    }
}
