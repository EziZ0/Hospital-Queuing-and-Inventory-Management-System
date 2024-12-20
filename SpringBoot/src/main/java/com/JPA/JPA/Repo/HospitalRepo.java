package com.JPA.JPA.Repo;

import com.JPA.JPA.Model.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface HospitalRepo extends JpaRepository<Hospital, Long> {
    @Query("SELECT h FROM Hospital h WHERE LOWER(h.name) = LOWER(:name)")
    Optional<Hospital> findByName(@Param("name") String name);



}
//public interface HospitalRepo extends JpaRepository<Hospital,Integer> {
//
//
//    List<Hospital> findByName(String name);
//
//    List<Hospital> findByMarksGreaterThan(int name);
//
//    List<Hospital> findByPostContainingOrPostDescContaining(String key);
//}
