package com.base64.gamesback.audition.controller;

import com.base64.gamesback.audition.dto.AuditionResult;
import com.base64.gamesback.audition.service.AuditionService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;


@RestController
@RequestMapping("/security/test-audition")
public class AuditionController {

    private final AuditionService auditionService;

    public AuditionController(AuditionService auditionService) {
        this.auditionService = auditionService;
    }

    @PostMapping("/submit-results")
    public ResponseEntity<AuditionResult> submitResults(
            @RequestBody Set<String> inputNumbers
    ) {
        return new ResponseEntity<>(auditionService.submitResultsList(inputNumbers), HttpStatus.OK);
    }

    @GetMapping(value = "/audio/{numero}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Resource getAudio(@PathVariable int numero) {
        return auditionService.playAudio(numero);
    }

    @GetMapping(value = "/audio-right/{numero}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Resource getRightAudio(@PathVariable int numero) {
        return auditionService.playAudioRight(numero);
    }

    @GetMapping(value = "/audio-left/{numero}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public Resource getLeftAudio(@PathVariable int numero) {
        return auditionService.playAudioLeft(numero);
    }

}

