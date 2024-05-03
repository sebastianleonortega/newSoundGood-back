package com.base64.gamesback.auth.user.service.impl;

import com.base64.gamesback.auth.user.dto.PersonDto;
import com.base64.gamesback.auth.user.dto.PersonUpdateRequest;
import com.base64.gamesback.auth.user.entity.Person;
import com.base64.gamesback.auth.user.entity.User;
import com.base64.gamesback.auth.user.repository.PersonRepository;
import com.base64.gamesback.auth.user.repository.UserRepository;
import com.base64.gamesback.auth.user.service.PersonService;
import com.base64.gamesback.common.exception_handler.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.UUID;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;


    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
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
    public void registerPerson(User user, PersonDto request) {
       Person person = Person.create(
               request.getName(),
               request.getLastname(),
               request.getDocumentNumber(),
               request.getAddress(),
               request.getPhone(),
               request.getEmail().toLowerCase(Locale.ROOT),
               request.getTypeOfHearingLoss(),
               request.getPreviousTreatments()
       );
        person.addUser(user);
        personRepository.save(person);

    }

    @Override
    public void updatePerson(PersonUpdateRequest request, User user ) {
        Person person = personRepository.findById(user.getPerson().getPersonId()).orElseThrow(() -> new ResourceNotFoundException("Person not found"));

        person.update(
                request.getPersonName(),
                request.getPersonLastName(),
                request.getPersonDocument(),
                request.getPersonAddress(),
                request.getPersonPhone(),
                request.getPersonEmail().toLowerCase(Locale.ROOT),
                request.getTypeOfHearingLoss(),
                request.getPreviousTreatments()
        );
        personRepository.save(person);
    }
}
