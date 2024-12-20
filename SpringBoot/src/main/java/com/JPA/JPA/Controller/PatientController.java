package com.JPA.JPA.Controller;



import com.JPA.JPA.Model.Patient;
import com.JPA.JPA.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    // API to add a new patient
    @PostMapping("/add")
    public Patient addPatient(@RequestBody PatientRequest request) {
        return patientService.addPatient(request.getEmergencyLevel(), request.getLatitude(), request.getLongitude());
    }
}


class PatientRequest {
    private int emergencyLevel;
    private double latitude;
    private double longitude;

    // Getters and Setters
    public int getEmergencyLevel() {
        return emergencyLevel;
    }

    public void setEmergencyLevel(int emergencyLevel) {
        this.emergencyLevel = emergencyLevel;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
