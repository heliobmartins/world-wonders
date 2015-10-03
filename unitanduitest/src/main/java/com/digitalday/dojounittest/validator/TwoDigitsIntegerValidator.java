package com.digitalday.dojounittest.validator;

import java.util.regex.Pattern;

/**
 * Created by athila on 23/06/15.
 */
public class TwoDigitsIntegerValidator extends Validator {
    private static final String REGEX = "^[-+]?\\d{1,2}$";

    public TwoDigitsIntegerValidator() {
        super(Pattern.compile(REGEX));
    }
}
