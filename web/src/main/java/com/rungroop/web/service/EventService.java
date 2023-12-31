package com.rungroop.web.service;

import com.rungroop.web.dto.EventDto;

import java.util.List;

public interface EventService {

    void createEvent(Long clubId, EventDto eventDto);

    List<EventDto> findAllEvents();

    EventDto findEventsById(long eventId);

    void updateEvent(EventDto event);

    void delete(Long eventId);
}
