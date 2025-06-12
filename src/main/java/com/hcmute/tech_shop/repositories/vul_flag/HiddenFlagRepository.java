package com.hcmute.tech_shop.repositories.vul_flag;

import com.hcmute.tech_shop.entities.vul_flag.HiddenFlag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HiddenFlagRepository extends JpaRepository<HiddenFlag, Long> {
    HiddenFlag findByChallenge(String challenge);


}
