package com.uniform.service;

import com.uniform.dto.SchoolDTO;
import com.uniform.entity.School;
import com.uniform.repository.SchoolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SchoolService {

    @Autowired
    private SchoolRepository schoolRepository;

    public SchoolDTO saveSchool(SchoolDTO schoolDTO) {
        School school = new School();
        school.setName(schoolDTO.getName());
        school.setMobile(schoolDTO.getMobile());
        school.setLocation(schoolDTO.getLocation());
        
        School savedSchool = schoolRepository.save(school);
        return convertToDTO(savedSchool);
    }

    public List<SchoolDTO> getAllSchools() {
        List<School> schools = schoolRepository.findAll();
        return schools.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public School getSchoolById(Long id) {
        return schoolRepository.findById(id).orElse(null);
    }

    private SchoolDTO convertToDTO(School school) {
        SchoolDTO dto = new SchoolDTO();
        dto.setId(school.getId());
        dto.setName(school.getName());
        dto.setMobile(school.getMobile());
        dto.setLocation(school.getLocation());
        return dto;
    }
}