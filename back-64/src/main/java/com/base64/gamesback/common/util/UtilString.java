package com.base64.gamesback.common.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;

import java.security.SecureRandom;

@Slf4j
public class UtilString {

    public static String generateRandom(Integer size) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#%&*()_+{}.";
        return RandomStringUtils.random(size, 0, 0, true, true, characters.toCharArray(), new SecureRandom());
    }


}
