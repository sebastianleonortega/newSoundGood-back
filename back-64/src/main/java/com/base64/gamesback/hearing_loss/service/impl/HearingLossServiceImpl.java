package com.base64.gamesback.hearing_loss.service.impl;

import com.base64.gamesback.auth.user.entity.Person;
import com.base64.gamesback.auth.user.repository.PersonRepository;
import com.base64.gamesback.common.exception_handler.ResourceNotFoundException;
import com.base64.gamesback.hearing_loss.dto.HearingLossDto;
import com.base64.gamesback.hearing_loss.dto.HearingLossResponse;
import com.base64.gamesback.hearing_loss.entity.HearingLoss;
import com.base64.gamesback.hearing_loss.repository.HearingLossRepository;
import com.base64.gamesback.hearing_loss.service.HearingLossService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
@Slf4j
public class HearingLossServiceImpl implements HearingLossService {

    private final HearingLossRepository hearingLossRepository;
    private final PersonRepository personRepository;

    public HearingLossServiceImpl(HearingLossRepository hearingLossRepository, PersonRepository personRepository) {
        this.hearingLossRepository = hearingLossRepository;
        this.personRepository = personRepository;
    }

    @Override
    public void registerHearingLoss(HearingLossDto hearingLossDto) {
        List<HearingLoss> hearingLosses = hearingLossRepository.getHearingLossByNameContainingIgnoreCase(hearingLossDto.getHearingLossName());

        if (!hearingLosses.isEmpty()) {
            throw new IllegalArgumentException("Ya existe una perdida auditiva con ese nombre");
        }

        HearingLoss hearingLoss = HearingLoss.create(
                hearingLossDto.getHearingLossName()
        );
        hearingLossRepository.save(hearingLoss);
    }

    @Override
    public void updateHearingLoss(HearingLossDto hearingLossDto, UUID hearingLossId) {
        HearingLoss hearingLoss = this.getHearingLossById(hearingLossId);
        hearingLoss.updateHearingLoss(hearingLossDto.getHearingLossName());
        hearingLossRepository.save(hearingLoss);
    }

    @Override
    public void deleteHearingLoss(UUID hearingLossId) {
        HearingLoss hearingLoss = this.getHearingLossById(hearingLossId);
        hearingLossRepository.delete(hearingLoss);
    }

    @Override
    public HearingLoss getHearingLossById(UUID hearingLossId) {
        return hearingLossRepository.findById(hearingLossId).orElseThrow(() -> new ResourceNotFoundException("No existe la perdida auditiva a buscar"));
    }

    @Override
    public List<HearingLossResponse> getAllHearingLoss() {
        return hearingLossRepository.getAllHearingLosses();
    }

    @Override
    public void assignHearingLosses(List<UUID> hearingLossIds, UUID personId) {

        Person person = personRepository.findById(personId).orElseThrow(() -> new ResourceNotFoundException("No existe la persona buscada"));
        Set<HearingLoss> hearingLosses = hearingLossRepository.getHearingLossSpecialtyByHearingLossIdIn(hearingLossIds);

        if (hearingLosses.isEmpty()) {
            throw new IllegalArgumentException("Las perdidas auditivas ingresadas no existen");
        }

        person.addHearingLoss(hearingLosses);
        personRepository.save(person);
    }

    @Override
    public List<HearingLossResponse> getAllHearingLossesByPersonId(UUID personId) {
        return hearingLossRepository.getAllHearingLossesByPersonId(personId);
    }
}
