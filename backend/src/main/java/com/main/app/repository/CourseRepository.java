package com.main.app.repository;

import com.main.app.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByStudent_IdOrderByScoreDesc(Integer studentId);
    List<Course> findByNameContainingIgnoreCase(String name);
    List<Course> findByScore(Float score);
    
    @Query("SELECT c FROM Course c WHERE c.student.id = ?1 ORDER BY c.score DESC")
    List<Course> findCoursesByStudentIdOrderByScoreDesc(Integer studentId);
} 