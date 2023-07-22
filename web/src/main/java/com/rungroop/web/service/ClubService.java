package com.rungroop.web.service;

import com.rungroop.web.dto.ClubDto;
import com.rungroop.web.models.Club;

import java.util.List;

public interface ClubService {
    void delete(Long clubId);

    List<ClubDto> findAllClubs();

    Club saveClub(ClubDto clubDto);

    ClubDto findClubById(long clubId);

    void updateClub(ClubDto club);

    List<ClubDto> searchClubs(String query);

}
