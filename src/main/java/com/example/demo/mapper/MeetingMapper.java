package com.example.demo.mapper;

import com.example.demo.dto.request.CreateMeetingRequestDto;
import com.example.demo.model.Meeting;
import org.springframework.stereotype.Component;

@Component
public class MeetingMapper {

    public Meeting toEntity(CreateMeetingRequestDto meetingData) {
        Meeting meeting = new Meeting();
        meeting.setTitle(meetingData.getTitle());
        meeting.setHostEmail(meetingData.getHostEmail());
        meeting.setMeetingLink(meetingData.getMeetingLink());
        meeting.setStartTime(meetingData.getStartTime());
        meeting.setEndTime(meetingData.getEndTime());
        return meeting;
    }

    public CreateMeetingRequestDto toDto(Meeting meeting) {
        return new CreateMeetingRequestDto(
                meeting.getTitle(),
                meeting.getHostEmail(),
                meeting.getMeetingLink(),
                meeting.getStartTime(),
                meeting.getEndTime()
        );
    }
}
