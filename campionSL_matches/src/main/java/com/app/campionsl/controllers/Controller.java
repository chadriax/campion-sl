package com.app.campionsl.controllers;

import com.app.campionsl.messaging.MatchResultPublisher;
import com.app.campionsl.models.Match;
import com.app.campionsl.models.MatchResultDTO;
import com.app.campionsl.repository.MatchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class Controller {

    private final MatchResultPublisher matchResultPublisher;

    public Controller(MatchResultPublisher matchResultPublisher) {
        this.matchResultPublisher = matchResultPublisher;
    }

    @Autowired
    private MatchRepository matchRepository;

    @GetMapping("/listMatches")
    public List<Match> getAllMatches(){
        return matchRepository.findAll();
    }

    @GetMapping("listMatches/{id}")
    public Optional<Match> getMatchesById(@PathVariable Long id){
        return matchRepository.findById(id);
    }

    @PostMapping("/{matchId}/result")
    public ResponseEntity<String> saveMatchResult(@PathVariable Long matchId, @RequestBody MatchResultDTO matchResultDTO){
        Optional<Match> optionalMatch = matchRepository.findById(matchId);

        if (optionalMatch.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Partida no encontrada");
        }

        Match match = optionalMatch.get();
        match.setMatchResult(matchResultDTO);
        matchRepository.save(match);

        matchResultPublisher.publishMatchResult(matchId);

        return ResponseEntity.ok("Mensaje enviado para match " + matchId);
    }

}
