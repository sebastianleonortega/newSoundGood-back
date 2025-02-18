package com.base64.gamesback.auth.user.service.impl;

import com.base64.gamesback.auth.user.dto.TestRequest;
import com.base64.gamesback.auth.user.dto.TestResponse;
import com.base64.gamesback.auth.user.entity.Person;
import com.base64.gamesback.auth.user.entity.Test;
import com.base64.gamesback.auth.user.repository.TestRepository;
import com.base64.gamesback.auth.user.service.PersonService;
import com.base64.gamesback.auth.user.service.TestService;
import com.base64.gamesback.common.exception_handler.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TestServiceImpl implements TestService {

    private final TestRepository testRepository;
    private final PersonService personService;

    public TestServiceImpl(TestRepository testRepository, PersonService personService) {
        this.testRepository = testRepository;
        this.personService = personService;
    }

    @Override
    public void registerTestToPerson(TestRequest testRequest) {
        Person person = personService.getPersonById(testRequest.getPersonId());
        Test test = Test.create(person, testRequest.getTestType(), testRequest.getResultLeft(), testRequest.getResultRight(), testRequest.getResultNumeric());
        testRepository.save(test);
    }

    @Override
    public void deleteTestToPerson(UUID testId) {
        testRepository.delete(getTestById(testId));
    }

    @Override
    public Test getTestById(UUID testId) {
        return testRepository.findById(testId).orElseThrow(() -> new ResourceNotFoundException("El test buscado no existe."));
    }

    @Override
    public List<TestResponse> getTestByPerson(UUID personId) {
        return testRepository.getTestByPerson(personId);
    }
}
