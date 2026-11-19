package com.uniform.controller;

import com.uniform.dto.SchoolDTO;
import com.uniform.entity.School;
import com.uniform.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schools")
@CrossOrigin(origins = "http://localhost:3000")
public class SchoolController {

    @Autowired
    private SchoolService schoolService;

    @PostMapping
    public SchoolDTO createSchool(@RequestBody SchoolDTO schoolDTO) {
        return schoolService.saveSchool(schoolDTO);
    }

    @GetMapping
    public List<SchoolDTO> getAllSchools() {
        return schoolService.getAllSchools();
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getSchoolById(@PathVariable("id") Long id) { // Inga "id" nu specify panniten
        try {
            School school = schoolService.getSchoolById(id);
            if (school == null) {
                return ResponseEntity.status(404).body("School not found with id: " + id);
            }
            return ResponseEntity.ok(school);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error: " + e.getMessage());
        }
    }
}