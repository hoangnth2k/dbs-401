package com.hcmute.tech_shop.services;

import com.hcmute.tech_shop.entities.HiddenFlag;

public interface HiddenFlagService {
    HiddenFlag getFlagByChallenge(String challenge);
} 