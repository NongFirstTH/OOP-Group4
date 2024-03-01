package com.websocket.demo.Chat;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class ChatMessage {
    private String content;
    private String timestamp;
    private String sender;
    private MessageType type;
    private String count;

    public void setCount(String count) {
        this.count = count;
    }

    public static ChatMessage x(String s) {
        return new ChatMessage("",ChatController.getCount(),s,MessageType.CHAT,ChatController.getCount());
    }
}
