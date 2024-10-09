package id.smartpesantren.config;

/**
 * Application constants.
 */
public final class Constants {

    //Regex for acceptable logins
    public static final String LOGIN_REGEX = "^[_'.@A-Za-z0-9-]*$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String ANONYMOUS_USER = "anonymoususer";
    public static final String DEFAULT_LANGUAGE = "en";

    public static final Short PERSON_TYPE_EMPLOYEE = 0;
    public static final Short PERSON_TYPE_STUDENT = 1;
    public static final Short PERSON_TYPE_PARENT = 1;

    private Constants() {
    }
}
