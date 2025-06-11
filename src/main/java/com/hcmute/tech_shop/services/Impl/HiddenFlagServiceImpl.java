package com.hcmute.tech_shop.services.Impl;

import com.hcmute.tech_shop.entities.HiddenFlag;
import com.hcmute.tech_shop.repositories.HiddenFlagRepository;
import com.hcmute.tech_shop.services.HiddenFlagService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HiddenFlagServiceImpl implements HiddenFlagService {
    private final HiddenFlagRepository hiddenFlagRepository;

    @Override
    public HiddenFlag getFlagByChallenge(String challenge) {
        return hiddenFlagRepository.findByChallenge(challenge);
    }
} 