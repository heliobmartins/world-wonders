package com.digitalday.dojounittest.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by athila on 23/06/15.
 */
public abstract class Validator {
    protected Pattern pattern;
    private Matcher matcher;

    protected Validator(Pattern pattern) {
        this.pattern = pattern;
    }

    public boolean validate(String target) {
        return pattern != null && pattern.matcher(target).matches();
    }
}
