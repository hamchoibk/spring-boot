/***************************************************************************
 * Copyright 2017 by Vega - All rights reserved.                *    
 **************************************************************************/
package com.kaynaak.rest.util;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Author : Ngo Duy Kien
 * Email:kiennd@vega.com.vn
 * Nov 2, 2017
 */

public class JsonMapperUtil {

    private static Logger logger = LogManager.getLogger(JsonMapperUtil.class);
    private static ObjectMapper mapper = new ObjectMapper();

    public static <O> String writeValueAsString(O o) {
        try {
            mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            return mapper.writeValueAsString(o);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return e.getMessage();
        }
    }

    public static <T> T convertJsonToObject(String jsonStr, final Class<T> clazz) {
        try {
            return mapper.readValue(jsonStr, clazz);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }

    public static <T> T convertJsonToObject(String jsonStr, final TypeReference<T> reference) {
        try {
            return mapper.readValue(jsonStr, reference);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            return null;
        }
    }
}
