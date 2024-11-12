package id.smartpesantren.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {
    public static final String SUPERADMIN = "ROLE_SUPERADMIN";
    public static final String HR = "ROLE_HR";
    public static final String TU = "ROLE_TU";
    public static final String STAF = "ROLE_STAF";
    public static final String PENDIDIKAN = "ROLE_PENDIDIKAN";
    public static final String PENGASUHAN = "ROLE_PENGASUHAN";
    public static final String USTADZ = "ROLE_USTADZ";
    public static final String KESEHATAN = "ROLE_KESEHATAN";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    private AuthoritiesConstants() {
    }
}
