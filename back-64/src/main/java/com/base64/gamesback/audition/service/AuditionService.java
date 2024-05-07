package com.base64.gamesback.audition.service;

import com.base64.gamesback.audition.dto.AuditionResult;
import org.springframework.core.io.Resource;

import java.util.List;


public interface AuditionService {

    AuditionResult submitResultsList(List<String> inputNumbers);
    Resource playAudio(int numero);
    Resource playAudioRight(int numero);
    Resource playAudioLeft(int numero);
}
