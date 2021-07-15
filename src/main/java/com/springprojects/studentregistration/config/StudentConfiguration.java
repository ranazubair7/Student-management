package com.springprojects.studentregistration.config;


import com.springprojects.studentregistration.domain.StudentDomain;
import com.springprojects.studentregistration.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class StudentConfiguration{
    private StudentRepository studentRepository;

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
        return args -> {
            StudentDomain zubair = new StudentDomain(
                    "zubair",
                    "ranamzubair7@gmail.com",
                    LocalDate.of(1998, 1, 1)
            );
            StudentDomain adil = new StudentDomain(
                    "adil",
                    "adil@gmail.com",
                    LocalDate.of(2002, 2, 2)
            );

            studentRepository.saveAll(
                    List.of(zubair, adil));
        };
    }

}
