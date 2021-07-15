package com.springprojects.studentregistration.repository;

import com.springprojects.studentregistration.domain.StudentDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface StudentRepository extends JpaRepository<StudentDomain, Long> {

    //@Query("SELECT s FROM StudentDomain s WHERE s.email = ?1")
    Optional<StudentDomain> findStudentByEmail(String email);
}
