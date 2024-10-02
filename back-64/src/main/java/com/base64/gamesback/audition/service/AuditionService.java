package com.base64.gamesback.audition.service;

import com.base64.gamesback.audition.dto.AuditionResult;
import org.springframework.core.io.Resource;

import java.util.List;
import java.util.Set;


public interface AuditionService {

    AuditionResult submitResultsList(Set<String> inputNumbers);
    Resource playAudio(int numero);
    Resource playAudioRight(int numero);
    Resource playAudioLeft(int numero);
}
