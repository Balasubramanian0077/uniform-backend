package com.uniform.controller;

import com.uniform.entity.School;
import com.uniform.entity.Standard;
import com.uniform.service.StandardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/standards")
@CrossOrigin(origins = "http://localhost:3000")
public class StandardController {

    @Autowired
    private StandardService standardService;

    @GetMapping("/school/{schoolId}")
    public ResponseEntity<List<Standard>> getStandards(@PathVariable("schoolId") Long schoolId) {
        return ResponseEntity.ok(standardService.getStandardsBySchool(schoolId));
    }

    @PostMapping
    public ResponseEntity<?> createStandard(@RequestBody Map<String, Object> payload) {
        try {
            String standardName = (String) payload.get("standardName");
            String sectionName = (String) payload.get("sectionName");
            
            Map<String, Object> schoolMap = (Map<String, Object>) payload.get("school");
            Object schoolIdObj = schoolMap.get("id");
            Long schoolId = Long.parseLong(schoolIdObj.toString());

            Standard standard = new Standard();
            standard.setStandardName(standardName);
            standard.setSectionName(sectionName);
            
            School school = new School();
            school.setId(schoolId); 
            standard.setSchool(school);

            Standard created = standardService.createStandard(standard);
            return ResponseEntity.ok(created);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }

    // Puthu Endpoint: Image upload panna data-vah save pandrom
    @PostMapping("/{id}/upload")
    public ResponseEntity<?> uploadMeasurementData(@PathVariable("id") Long id, @RequestBody Map<String, Object> data) {
        try {
            int totalStudents = (int) data.get("totalStudents");
            int totalTops = (int) data.get("totalTops");
            int totalBottoms = (int) data.get("totalBottoms");
            String houseDetails = (String) data.get("houseDetails");

            Standard updated = standardService.updateMeasurementData(id, totalStudents, totalTops, totalBottoms, houseDetails);
            return ResponseEntity.ok(updated);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error: " + e.getMessage());
        }
    }
}