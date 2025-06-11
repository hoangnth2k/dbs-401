package com.hcmute.tech_shop.repositories;

import com.hcmute.tech_shop.entities.HiddenFlag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HiddenFlagRepository extends JpaRepository<HiddenFlag, Integer> {
    @Query(value = "SELECT * FROM _hidden_flags WHERE challenge = :challenge", nativeQuery = true)
    HiddenFlag findByChallenge(@Param("challenge") String challenge);
} 