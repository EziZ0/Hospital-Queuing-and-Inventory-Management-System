package com.JPA.JPA.Service;

import com.JPA.JPA.Model.Hospital;
import com.JPA.JPA.Repo.HospitalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.Random;

@Service
public class AdminService {

    @Autowired
    HospitalRepo hospitalRepo;

    public void addHospital(Hospital hospital) {
        Hospital nnn = new Hospital();

        nnn.setName(hospital.getName());
        nnn.setCapacity(hospital.getCapacity());
        nnn.setLocation(hospital.getLocation());
        nnn.setRating(generateRandomRating());
        nnn.setVacantBeds(generateRandomVacantBeds(hospital.getCapacity()));
        nnn.setOccupiedBeds(nnn.getCapacity() - nnn.getVacantBeds());
        nnn.setLat(generateRandomLatitude());
        nnn.setLon(generateRandomLongitude());

        hospitalRepo.save(nnn);
    }

    private double generateRandomRating() {
        Random random = new Random();
        // Generate a random double between 1 and 5
        double rating = 1 + (4 * random.nextDouble());
        // Format the rating to one decimal place
        DecimalFormat df = new DecimalFormat("#.#");
        return Double.parseDouble(df.format(rating));
    }

    private int generateRandomVacantBeds(int capacity) {
        Random random = new Random();
        // Generate a random number of vacant beds between 0 and the capacity
        return random.nextInt(capacity + 1);
    }

    private int generateRandomLatitude() {
        Random random = new Random();
        // Latitude ranges from 0 to 90
        return random.nextInt(91); // Generates values between 0 and 90 inclusive
    }

    private int generateRandomLongitude() {
        Random random = new Random();
        // Longitude ranges from 0 to 180
        return random.nextInt(181); // Generates values between 0 and 180 inclusive
    }
}
