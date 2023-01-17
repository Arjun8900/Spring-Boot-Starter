package com.linux.repository;

import com.linux.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface StudentDBManager extends JpaRepository<Student, Integer> {
    List<Student> findByFirstName(String firstName);

    @Query("FROM Student WHERE firstName = ?1 ORDER BY firstName DESC")
    List<Student> findByStudentSorted(String firstName);
}
