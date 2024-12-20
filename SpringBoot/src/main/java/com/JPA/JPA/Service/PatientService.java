package com.JPA.JPA.Service;


import com.JPA.JPA.Model.Patient;
import com.JPA.JPA.Repo.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // Hardcoded hospital location (for example purposes)
    private static final double HOSPITAL_LATITUDE = 37.7749; // Example latitude
    private static final double HOSPITAL_LONGITUDE = -122.4194; // Example longitude

    // Method to calculate emergencyScore and save patient
    public Patient addPatient(int emergencyLevel, double latitude, double longitude) {
        Patient patient = new Patient();
        patient.setEmergencyLevel(emergencyLevel);

        // Calculate the distance between the patient and hospital
        double distance = calculateDistance(latitude, longitude, HOSPITAL_LATITUDE, HOSPITAL_LONGITUDE);
        patient.setDistance(distance);
        patient.setLatitude(latitude);
        patient.setLongitude(longitude);
//        int randomMinutes = generateRandomMinutes();
        patient.setTime(generateRandomMinutes());

        // Calculate emergencyScore
        double emergencyScore = calculateEmergencyScore(emergencyLevel, distance)/100;
        patient.setEmergencyScore(emergencyScore);

        // Save patient to the repository
        return patientRepository.save(patient);
    }
    private static int generateRandomMinutes() {
        Random random = new Random();
        return 5 + random.nextInt(51); // Random integer between 5 and 55 inclusive
    }

    // Method to calculate distance using Haversine formula
    private double calculateDistance(double lat1, double lon1, double lat2, double lon2) {
        final int R = 6371; // Radius of the earth in km
        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c; // Distance in km
    }

    // Updated method to calculate emergencyScore with higher distance = higher priority
    private double calculateEmergencyScore(int emergencyLevel, double distance) {
        double normalizedDistance = (distance / 100.0) * 10;  // Normalize distance
        return ((emergencyLevel / 6.0) * 8) + ((normalizedDistance / 10.0) * 2);
    }



}
