package com.xpto.app.controller;

import com.xpto.app.dto.TeamMemberCreateDTO;
import com.xpto.app.model.TeamMember;
import com.xpto.app.repository.TeamMemberRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/members")
public class TeamMemberController {

    /*
    private final TeamMemberRepository teamMemberRepository;

    public TeamMemberController(TeamMemberRepository teamMemberRepository) {
        this.teamMemberRepository = teamMemberRepository;
    }*/

    /*
    @PostMapping
    public ResponseEntity create(@Valid @RequestBody TeamMemberCreateDTO form) {
        TeamMember member = form.toEntity();
        teamMemberRepository.save(member);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(member.getId()).toUri();

        return ResponseEntity.created().build();
    }*/
}
