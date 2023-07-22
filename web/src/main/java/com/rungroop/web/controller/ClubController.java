package com.rungroop.web.controller;

import com.rungroop.web.dto.ClubDto;
import com.rungroop.web.dto.EventDto;
import com.rungroop.web.models.Club;
import com.rungroop.web.service.ClubService;
import com.rungroop.web.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ClubController {
    private ClubService clubService;


    @Autowired
    public ClubController(ClubService clubService) {
        this.clubService = clubService;
    }


    //Read
    @GetMapping("/clubs")
    public String listClubs(Model model){
    List<ClubDto> clubs = clubService.findAllClubs();
    model.addAttribute("clubs",clubs);
    return "clubs-list";
    }


    //Create
    @GetMapping("/clubs/new")
    public String createClubForm(Model model){
        Club club = new Club();
        model.addAttribute("clubs",club);
        return "clubs-create";
    }

    @PostMapping("/clubs/new")
    public String saveClub(@Valid @ModelAttribute("clubs") ClubDto clubDto, BindingResult result){
        if(result.hasErrors()){
            return "clubs-create";
        }
        clubService.saveClub(clubDto);
        return "redirect:/clubs";
    }


    //Update
    @GetMapping("/clubs/{clubId}/edit")
    public String editClubForm(@PathVariable("clubId") long clubId,Model model){
        ClubDto club = clubService.findClubById(clubId);
        model.addAttribute("clubs",club);
        return "clubs-edit";
    }

    @PostMapping("/clubs/{clubId}/edit")
    public String updateClub(@PathVariable("clubId") Long clubId, @Valid @ModelAttribute("clubs") ClubDto club, BindingResult result){

        if(result.hasErrors()){
            return "clubs-edit";
        }

        club.setId(clubId);
        clubService.updateClub(club);
        return "redirect:/clubs";
    }

    //Delete
    @GetMapping("/clubs/{clubId}/delete")
    public String deleteClub(@PathVariable("clubId") Long clubId){
        clubService.delete(clubId);
        return "redirect:/clubs";
    }

    //Club Detail
    @GetMapping("/clubs/{clubId}")
    public String clubDetail(@PathVariable("clubId") long clubId, Model model){
        ClubDto clubDto = clubService.findClubById(clubId);
        model.addAttribute("clubs",clubDto);
        return "clubs-details";
    }

    //Search JPQL
    @GetMapping("/clubs/search")
    public String searchClub(@RequestParam(value = "query")String query, Model model){
        List<ClubDto> clubs = clubService.searchClubs(query);
        model.addAttribute("clubs",clubs);
        return "clubs-list";
    }






}
