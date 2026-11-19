package com.uniform.repository;

import com.uniform.entity.Standard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StandardRepository extends JpaRepository<Standard, Long> {
    
    // School ID vachu filter panna
    List<Standard> findBySchoolId(Long schoolId);
    
    // Duplicate check panna (Intha line-ah correct-ah maathiruken)
    boolean existsBySchoolIdAndStandardNameAndSectionName(Long schoolId, String standardName, String sectionName);
}