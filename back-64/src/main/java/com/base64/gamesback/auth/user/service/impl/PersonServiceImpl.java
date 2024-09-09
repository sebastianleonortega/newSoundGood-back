package com.base64.gamesback.auth.user.service.impl;

import com.base64.gamesback.auth.user.dto.PersonDto;
import com.base64.gamesback.auth.user.dto.PersonUpdateRequest;
import com.base64.gamesback.auth.user.entity.Person;
import com.base64.gamesback.auth.user.entity.User;
import com.base64.gamesback.auth.user.repository.PersonRepository;
import com.base64.gamesback.auth.user.service.PersonService;
import com.base64.gamesback.common.exception_handler.ResourceNotFoundException;
import com.base64.gamesback.documentType.service.DocumentTypeSharedService;
import com.base64.gamesback.genderType.service.GenderTypeSharedService;
import com.base64.gamesback.hearing_loss.service.HearingLossService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final HearingLossService hearingLossService;
    private final DocumentTypeSharedService documentTypeSharedService;
    private final GenderTypeSharedService genderTypeSharedService;


    public PersonServiceImpl(PersonRepository personRepository, HearingLossService hearingLossService, DocumentTypeSharedService documentTypeSharedService, GenderTypeSharedService genderTypeSharedService) {
        this.personRepository = personRepository;
        this.hearingLossService = hearingLossService;
        this.documentTypeSharedService = documentTypeSharedService;
        this.genderTypeSharedService = genderTypeSharedService;
    }

    @Override
    public Boolean existPersonByEmail(String personEmail) {
        return personRepository.existsPersonByPersonEmail(personEmail.toLowerCase(Locale.ROOT));
    }

    @Override
    public Boolean existPersonByDocument(String personDocument) {
        return personRepository.existsPersonByPersonDocument(personDocument.toLowerCase(Locale.ROOT));
    }

    @Override
    public Person getPersonByID(UUID uuid) {
        return personRepository.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("no existe la persona"));
    }

    @Override
    public void registerPerson(User user, PersonDto request) {
        List<UUID> hearingLossesList = Arrays.asList(request.getHearingLosses());
        Set<UUID> hearingLossesSet = new HashSet<>(hearingLossesList);
       Person person = Person.create(
               request.getName(),
               request.getLastname(),
               request.getDocumentNumber(),
               request.getAddress(),
               request.getPhone(),
               request.getEmail().toLowerCase(Locale.ROOT),
               request.getPreviousTreatments()
       );
        person.addUser(user);
        person.addGenderType(genderTypeSharedService.getGenderTypeById(request.getGenderType()));
        person.addDocumentType(documentTypeSharedService.getDocumentType(request.getDocumentType()));
        personRepository.save(person);
        hearingLossService.assignHearingLosses(hearingLossesSet, person.getPersonId());
    }

    @Override
    public void updatePerson(PersonUpdateRequest request, User user ) {
        Person person = personRepository.findById(user.getPerson().getPersonId()).orElseThrow(() -> new ResourceNotFoundException("Person not found"));

        List<UUID> hearingLossesList = Arrays.asList(request.getHearingLosses());
        Set<UUID> hearingLossesSet = new HashSet<>(hearingLossesList);
        person.update(
                request.getPersonName(),
                request.getPersonLastName(),
                request.getPersonDocument(),
                request.getPersonAddress(),
                request.getPersonPhone(),
                request.getPersonEmail().toLowerCase(Locale.ROOT),
                request.getPreviousTreatments()
        );
        person.addDocumentType(documentTypeSharedService.getDocumentType(request.getDocumentType()));
        person.addGenderType(genderTypeSharedService.getGenderTypeById(request.getGenderType()));
        personRepository.save(person);
        hearingLossService.assignHearingLosses(hearingLossesSet, person.getPersonId());
    }

    @Override
    public Person getPersonById(UUID personId) {
        return personRepository.findById(personId).orElseThrow(() -> new ResourceNotFoundException("No existe la persona buscada"));
    }
}
