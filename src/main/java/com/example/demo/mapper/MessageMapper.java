package com.example.demo.mapper;

import com.example.demo.dto.request.CreateMessageRequestDto;
import com.example.demo.model.Message;
import com.example.demo.model.User;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

    public Message toEntity(CreateMessageRequestDto messageData, User user) {
        Message message = new Message();
        message.setText(messageData.getText());
        message.setUser(user);
        return message;
    }

    public CreateMessageRequestDto toDto(Message message) {
        return new CreateMessageRequestDto(
                message.getText()
        );
    }
}
