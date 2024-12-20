package com.JPA.JPA.Controller;

import com.JPA.JPA.Model.Booking;
import com.JPA.JPA.Model.BookingRequest;
import com.JPA.JPA.Model.Hospital;
import com.JPA.JPA.Repo.BookingRepo;
import com.JPA.JPA.Repo.HospitalRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private HospitalRepo hospitalRepository;

    @Autowired
    private BookingRepo bookingRepo;

    @CrossOrigin(origins = "*")
    @PostMapping("/book")
    public ResponseEntity<String> bookHospitalByName(@RequestBody BookingRequest bookingRequest) {
        String hospitalName = bookingRequest.getName(); // Hospital name
        int numberOfBedsToBook = bookingRequest.getBed(); // Number of beds
        String patientName = bookingRequest.getPatientName(); // Get patient name from request

        Booking book = new Booking();
        book.setHospitalName(hospitalName); // Set hospital name
        book.setName(patientName); // Set patient name from booking request


        bookingRepo.save(book);

        // Find hospital by name
        Optional<Hospital> optionalHospital = hospitalRepository.findByName(hospitalName);

        if (!optionalHospital.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hospital not found");
        }

        Hospital hospital = optionalHospital.get();

        // Check if there are enough vacant beds
        if (hospital.getVacantBeds() < numberOfBedsToBook) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Not enough vacant beds available");
        }

        // Update the bed counts
        hospital.setVacantBeds(hospital.getVacantBeds() - numberOfBedsToBook);
        hospital.setOccupiedBeds(hospital.getOccupiedBeds() + numberOfBedsToBook);

        // Save the updated hospital data
        hospitalRepository.save(hospital);

        return ResponseEntity.ok("Booking successful");
    }

}

