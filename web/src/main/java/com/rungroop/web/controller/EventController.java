package com.rungroop.web.controller;

import com.rungroop.web.dto.ClubDto;
import com.rungroop.web.dto.EventDto;
import com.rungroop.web.models.Event;
import com.rungroop.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;


@Controller
public class EventController {
    private EventService eventService;


    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping("/events/{clubId}/new")
    public String createEventForm(@PathVariable("clubId") Long clubId, Model model){
        Event event = new Event(); // pass to the view
        model.addAttribute("clubId",clubId);
        model.addAttribute("events",event);
        return "events-create";
    }

    @PostMapping("/events/{clubId}")
    public String createEvent(@PathVariable("clubId") Long clubId, @ModelAttribute("events")EventDto eventDto,Model model){
        eventService.createEvent(clubId,eventDto);
        return "redirect:/clubs/" + clubId;
    }

    //GET LIST
    @GetMapping("/events")
    public String listEvents(Model model){
        List<EventDto> events = eventService.findAllEvents();
        model.addAttribute("events",events);
        return "events-list";
    }

    // Details
    @GetMapping("/events/{eventId}")
    public String eventDetail(@PathVariable("eventId") long eventId, Model model){
        EventDto eventDto = eventService.findEventsById(eventId);
        model.addAttribute("events",eventDto);
        return "events-detail";

    }

    //Edit or Update

    @GetMapping("/events/{eventId}/edit")
    public String editEventForm(@PathVariable("eventId")Long eventId, Model model){
            EventDto event = eventService.findEventsById(eventId);
            model.addAttribute("events",event);
            return "events-edit";
    }
//Edit
    @PostMapping("/events/{eventId}/edit")
    public String editEvent(@PathVariable("eventId")Long eventId, @ModelAttribute("events") EventDto event){
        EventDto eventDto = eventService.findEventsById(eventId);
        event.setId(eventId);
        event.setClub(eventDto.getClub()); // research this
        eventService.updateEvent(eventDto);
        return "redirect:/events";
    }


    //Delete

    @GetMapping("/events/{eventId}/delete")
    public String deleteEvent(@PathVariable("eventId") Long eventId, Model model){
        eventService.delete(eventId);
        return "redirect:/events";
    }




}
