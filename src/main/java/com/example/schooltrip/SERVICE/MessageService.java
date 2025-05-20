package com.example.schooltrip.SERVICE;

import org.springframework.stereotype.Service;

import com.example.schooltrip.MODEL.Message;

@Service
public class MessageService {
    public Message getHelloMessage() {
        return new Message("Ciao dal backend Spring!", "");
    }
}
