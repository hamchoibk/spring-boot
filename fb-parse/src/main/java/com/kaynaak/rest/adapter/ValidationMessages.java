package com.kaynaak.rest.adapter;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

/**
 * Helper to simplify accessing i18n messages in code.
 * 
 * This finds messages automatically found from src/main/resources (files named messages_*.properties)
 * 
 * This example uses hard-coded English locale.
 *
 * @author Joni Karppinen
 * @since 2015-11-02
 */
@Component
public class ValidationMessages {

    @Autowired
    private MessageSource validationMessageSource;

    private MessageSourceAccessor accessor;

    @PostConstruct
    private void init() {
        accessor = new MessageSourceAccessor(validationMessageSource, Locale.ENGLISH);
    }

    public String get(String code) {
        return accessor.getMessage(code);
    }

}