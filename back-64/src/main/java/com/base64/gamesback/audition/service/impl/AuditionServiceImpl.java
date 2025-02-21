package com.base64.gamesback.audition.service.impl;

import com.base64.gamesback.audition.dto.AuditionResult;
import com.base64.gamesback.audition.service.AuditionService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class AuditionServiceImpl implements AuditionService {

    private static final Set<String> VALID_NUMBERS = new HashSet<>(Arrays.asList("321", "432", "654", "765", "976", "987", "264"));

    public AuditionResult validateNumbers(Set<String> inputNumbers) {
        AtomicInteger validCount = new AtomicInteger();
        List<String> invalidNumbers = new ArrayList<>();

        inputNumbers.forEach(number -> {
            String trimmedNumber = number.trim();
            if (VALID_NUMBERS.contains(trimmedNumber)) {
                validCount.getAndIncrement();
            } else {
                invalidNumbers.add(trimmedNumber);
            }
        });
        return new AuditionResult(
                invalidNumbers.isEmpty() ? "¡Correcto! Has ingresado los números correctamente." : "¡Incorrecto! Has cometido un error. El test ha terminado.",
                validCount.get(),
                invalidNumbers.toString().replace("[", "")
                        .replace("]", "")
                        .replace(" ", ""));
    }

    @Override
    public AuditionResult submitResultsList(Set<String> inputNumbers) {
        if(inputNumbers.isEmpty()){
          throw new IllegalArgumentException("Los datos de entrada no deben de estar vacíos");
        }
        return this.validateNumbers(inputNumbers);
    }

    @Override
    public Resource playAudio(int numero) {
        String audioFileName = switch (numero) {
            case 1 -> "432_Vol1";
            case 2 -> "976_Vol2";
            case 3 -> "264_Vol3";
            case 4 -> "321_Vol4";
            case 5 -> "765_Vol5";
            case 6 -> "987_Vol6";
            case 7 -> "654_Vol7";
            default -> "audio_default";
        };

        String audioFilePath = "src/main/resources/static/audio/" + audioFileName + ".mp3";
        return new ClassPathResource(audioFilePath);
    }

    @Override
    public Resource playAudioRight(int numero) {
        String audioRight = switch (numero) {
            case 1 -> "600hz";
            case 2 -> "500hz";
            case 3 -> "400hz";
            case 4 -> "300hz";
            case 5 -> "200hz";
            case 6 -> "150hz";
            default -> "audio_default";
        };

        String audioRightPath = "src/main/resources/static/audio/" + audioRight + ".mp3";
        return new ClassPathResource(audioRightPath);
    }

    @Override
    public Resource playAudioLeft(int numero) {
        String audioLeft = switch (numero) {
            case 1 -> "600hz";
            case 2 -> "500hz";
            case 3 -> "400hz";
            case 4 -> "300hz";
            case 5 -> "200hz";
            case 6 -> "150hz";
            default -> "audio_default";
        };
        String audioLeftPath = "src/main/resources/static/audio/" + audioLeft + ".mp3";
        return new ClassPathResource(audioLeftPath);
    }
}
