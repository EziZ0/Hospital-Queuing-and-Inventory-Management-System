package com.JPA.JPA.Controller;

import com.JPA.JPA.Model.Hospital;
import com.JPA.JPA.Service.HospitalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class HospitalController {

    @Autowired
    HospitalService hospitalService;

    @CrossOrigin(origins = "*")
    @GetMapping("/hospitals/list")
    public List<Hospital> getHospitals() {
        return hospitalService.getAllHospitals();
    }
}

