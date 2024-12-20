package com.JPA.JPA.Repo;

import com.JPA.JPA.Model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepo extends JpaRepository<Booking,Integer> {



}
