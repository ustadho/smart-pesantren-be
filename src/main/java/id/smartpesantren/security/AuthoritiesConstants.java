package id.smartpesantren.security;

/**
 * Constants for Spring Security authorities.
 */
public final class AuthoritiesConstants {
    public static final String SUPERADMIN = "SUPERADMIN";
    public static final String HR = "HR";
    public static final String TU = "TU";
    public static final String STAF = "STAF";
    public static final String PENDIDIKAN = "PENDIDIKAN";
    public static final String PENGASUHAN = "PENGASUHAN";
    public static final String USTADZ = "USTADZ";
    public static final String KESEHATAN = "KESEHATAN";

    public static final String ANONYMOUS = "ROLE_ANONYMOUS";

    private AuthoritiesConstants() {
    }
}
