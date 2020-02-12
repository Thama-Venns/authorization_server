package com.ntvspace.security.usecure.Common.Util;

public class Validator {

    /**
     * validate user email address
     */
    public boolean EmailIsValid(String emailAddress) {
        boolean valid = true;
        String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";

        if (!emailAddress.matches(regex))
            valid = false;

        return valid;
    };

    /**
     * Password should be less than 15 and more than 8 characters in length.
     * Password should contain at least one upper case and one lower case alphabet.
     * Password should contain at least one number.
     * Password should contain at least one special character.
     */
    public boolean PasswordIsValid(String password) {

        boolean valid = true;

        if(password.length() < 6 || password.length() > 15)
            valid = false;

        if (!password.matches(".*[A-Z].*"))
            valid = false;

        if (!password.matches(".*[a-z].*"))
            valid = false;

        if (!password.matches(".*[0-9].*"))
            valid = false;

        if (!password.matches("(.*[,~,!,@,#,$,%,^,&,*,(,),-,_,=,+,[,{,],},|,;,:,<,>,/,?].*$)"))
            valid = false;

        return valid;
    }

    public boolean IdentificationNoIsValid(String idNo) {
        return true;
    }

    // Based on user area
    public boolean TellNoIsValid(String idNo) {
        return true;
    }

}
