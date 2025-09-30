package com.xpto.app.controller;

import com.xpto.app.dto.TeamMemberDTO;
import com.xpto.app.model.TeamMember;
import com.xpto.app.repository.TeamMemberRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping("/members")
public class TeamMemberController {

    private final TeamMemberRepository teamMemberRepository;

    public TeamMemberController(TeamMemberRepository teamMemberRepository) {
        this.teamMemberRepository = teamMemberRepository;
    }

    @PostMapping
    public ResponseEntity<TeamMemberDTO> create(@Valid @RequestBody TeamMemberDTO teamMemberDTO) {
        TeamMember member = teamMemberDTO.toEntity();
        teamMemberRepository.save(member);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(member.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    public ResponseEntity<TeamMemberDTO> search(@PathVariable Long id) {
        Optional<TeamMember> teamMember = teamMemberRepository.findById(id);
        if(teamMember.isPresent()) {
            return ResponseEntity.ok(new TeamMemberDTO(teamMember.get()));
        }
        return ResponseEntity.notFound().build();
    }
}
