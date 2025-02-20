package com.base64.gamesback.auth.user.service.impl;

import com.base64.gamesback.auth.user.dto.DoctorScheduleRequest;
import com.base64.gamesback.auth.user.dto.DoctorScheduleResponse;
import com.base64.gamesback.auth.user.dto.DoctorScheduleUpdateRequest;
import com.base64.gamesback.auth.user.entity.Doctor;
import com.base64.gamesback.auth.user.entity.DoctorSchedule;
import com.base64.gamesback.auth.user.repository.DoctorScheduleRepository;
import com.base64.gamesback.auth.user.service.DoctorScheduleService;
import com.base64.gamesback.auth.user.service.DoctorService;
import com.base64.gamesback.common.criteria.Criteria;
import com.base64.gamesback.common.criteria.Filter;
import com.base64.gamesback.common.criteria.Filters;
import com.base64.gamesback.common.criteria.Order;
import com.base64.gamesback.common.exception_handler.ResourceNotFoundException;
import com.base64.gamesback.common.object.SearchByCriteria;
import com.base64.gamesback.common.parse.ParseFilters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Slf4j
public class DoctorScheduleServiceImpl implements DoctorScheduleService {

    private final DoctorService doctorService;
    private final DoctorScheduleRepository doctorScheduleRepository;

    public DoctorScheduleServiceImpl(DoctorService doctorService, DoctorScheduleRepository doctorScheduleRepository) {
        this.doctorService = doctorService;
        this.doctorScheduleRepository = doctorScheduleRepository;
    }

    @Override
    public void registerDoctorSchedule(UUID doctorId, DoctorScheduleRequest doctorScheduleRequest) {

        Doctor doctor = doctorService.getDoctorById(doctorId);
        if(doctorScheduleRepository.findByStartDateAndEndDateAndDoctor(doctorScheduleRequest.getStartDate(), doctorScheduleRequest.getEndDate(), doctor)){
            throw new IllegalStateException("El horario ya se encuentra registrado para este día");
        }

        DoctorSchedule doctorSchedule = DoctorSchedule.create(doctor, doctorScheduleRequest.getStartDate(), doctorScheduleRequest.getEndDate());
        doctorScheduleRepository.save(doctorSchedule);
    }

    @Override
    public void updateDoctorSchedule(DoctorScheduleUpdateRequest doctorScheduleUpdateRequest) {
        DoctorSchedule doctorSchedule = this.getDoctorScheduleById(doctorScheduleUpdateRequest.getDoctorScheduleId());
        doctorSchedule.changeAvailable(doctorScheduleUpdateRequest.isAvailable());
        doctorScheduleRepository.save(doctorSchedule);
    }

    @Override
    public DoctorSchedule getDoctorScheduleById(UUID doctorScheduleId) {
        return doctorScheduleRepository.findById(doctorScheduleId).orElseThrow(() -> new ResourceNotFoundException("No existe el horario del doctor buscado"));
    }

    @Override
    public List<DoctorScheduleResponse> getDoctorScheduleByDoctorId(UUID doctorId, SearchByCriteria search) {
        this.updateAvailabilityByDate();
        List<Filter> filters = ParseFilters.getFilters(search.filters());
        Order order = Order.fromValues(search.orderBy(), search.orderType());
        if (!order.hasOrder()) {
            order = Order.desc("start_date");
        }

        Criteria criteria = new Criteria(
                new Filters(filters),
                order,
                search.limit(),
                search.offset()
        );
        return doctorScheduleRepository.getDoctorScheduleByDoctorId(doctorId, criteria);
    }

    private void updateAvailabilityByDate() {
        doctorScheduleRepository.markUnavailablePastSchedules(LocalDateTime.now());
    }
}
