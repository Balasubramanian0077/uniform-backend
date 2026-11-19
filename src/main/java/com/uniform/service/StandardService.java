package com.uniform.service;

import com.uniform.entity.Standard;
import com.uniform.repository.StandardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StandardService {

    @Autowired
    private StandardRepository standardRepository;

    public List<Standard> getStandardsBySchool(Long schoolId) {
        return standardRepository.findBySchoolId(schoolId);
    }

    public Standard createStandard(Standard standard) {
        if (standardRepository.existsBySchoolIdAndStandardNameAndSectionName(
                standard.getSchool().getId(), standard.getStandardName(), standard.getSectionName())) {
            throw new RuntimeException("Class already exists!");
        }
        standard.setStatus("Pending upload");
        standard.setCreatedAt(LocalDateTime.now());
        return standardRepository.save(standard);
    }

    // Puthu function: Image upload aana data save pandrom
    public Standard updateMeasurementData(Long id, int totalStudents, int totalTops, int totalBottoms, String houseDetails) {
        Standard standard = standardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Class not found"));

        standard.setTotalStudents(totalStudents);
        standard.setTotalTops(totalTops);
        standard.setTotalBottoms(totalBottoms);
        standard.setHouseDetails(houseDetails);
        standard.setStatus("Counted"); // Status update pandrom

        return standardRepository.save(standard);
    }
}