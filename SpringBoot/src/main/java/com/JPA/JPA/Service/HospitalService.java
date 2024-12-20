package com.JPA.JPA.Service;

import com.JPA.JPA.Model.Hospital;
import com.JPA.JPA.Repo.HospitalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HospitalService {

    @Autowired
    private HospitalRepo hospitalRepo;

    public List<Hospital> getAllHospitals() {
        return hospitalRepo.findAll();
    }
}
