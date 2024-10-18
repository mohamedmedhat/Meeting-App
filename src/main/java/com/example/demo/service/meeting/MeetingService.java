package com.example.demo.service.meeting;

import com.example.demo.dto.request.CreateMeetingRequestDto;
import com.example.demo.dto.request.CreateMessageRequestDto;
import com.example.demo.mapper.MeetingMapper;
import com.example.demo.mapper.MessageMapper;
import com.example.demo.model.Meeting;
import com.example.demo.model.Message;
import com.example.demo.model.User;
import com.example.demo.repository.MeetingRepository;
import com.example.demo.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MeetingService implements IMeetingService {
//    private final KafkaTemplate<String, String> kafkaTemplate;
    private final MeetingRepository meetingRepository;
    private final MessageRepository messageRepository;
    private final MeetingMapper meetingMapper;
    private final MessageMapper messageMapper;


    @Override
    public Meeting createMeeting(CreateMeetingRequestDto meetingData) {
        Meeting meeting = this.meetingMapper.toEntity(meetingData);
        return this.meetingRepository.save(meeting);
    }

    @Override
    public Message sendMessage(CreateMessageRequestDto messageData, User user) {
        Message message = this.messageMapper.toEntity(messageData, user);
        return this.messageRepository.save(message);
    }

    @Override
    public Boolean deleteMessage(String id) {
        Message message = this.messageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("message not found"));
        this.messageRepository.delete(message);
        return true;
    }

    @Override
    public List<Meeting> getAll() {
        return this.meetingRepository.findAll();
    }

    @Override
    public String handleAsking() {
        return "";
    }

    @Override
    public void handleAnswering() {

    }

    @Override
    public void deleteMeeting(String id) {
        Meeting meeting = this.meetingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Meeting Not found"));
        this.meetingRepository.delete(meeting);
    }
}
