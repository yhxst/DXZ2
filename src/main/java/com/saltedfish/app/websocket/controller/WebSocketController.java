package com.saltedfish.app.websocket.controller;


import com.saltedfish.app.websocket.message.ClientMessage;
import com.saltedfish.app.websocket.message.ServerMessage;
import com.saltedfish.app.websocket.message.ToUserMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

/**
 * Created by lincoln on 16-10-25
 */
@Controller
public class WebSocketController {

    //订阅
    @MessageMapping("/welcome")
    @SendTo("/topic/getResponse")
    public ServerMessage say(ClientMessage clientMessage){
        System.out.println("clientMessage.getName() = " + clientMessage.getName());
        return new ServerMessage("Welcome , "+clientMessage.getName()+" !");
    }

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    //私聊
    @MessageMapping("/cheat")
    public void cheatTo(ToUserMessage toUserMessage){
        System.out.println("toUserMessage.getMessage() = " + toUserMessage.getMessage());
        System.out.println("toUserMessage.getUserId() = " + toUserMessage.getUserId());
        messagingTemplate.convertAndSendToUser(toUserMessage.getUserId(),"/message",toUserMessage.getMessage());
    }
}
