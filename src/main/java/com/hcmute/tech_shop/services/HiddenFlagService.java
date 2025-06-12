package com.hcmute.tech_shop.services;

import com.hcmute.tech_shop.entities.vul_flag.HiddenFlag;
import com.hcmute.tech_shop.repositories.vul_flag.HiddenFlagRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HiddenFlagService {
    private final HiddenFlagRepository hiddenFlagRepository;

    public HiddenFlag getFlagByChallenge(String challenge) {
        return hiddenFlagRepository.findByChallenge(challenge);
    }
}
