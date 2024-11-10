package id.smartpesantren.web.rest.errors;

import java.net.URI;

public final class ErrorConstants {

    public static final String ERR_CONCURRENCY_FAILURE = "error.concurrencyFailure";
    public static final String ERR_VALIDATION = "error.validation";
    public static final String PROBLEM_BASE_URL = "https://www.jhipster.tech/problem";
    public static final URI DEFAULT_TYPE = URI.create(PROBLEM_BASE_URL + "/problem-with-message");
    public static final URI CONSTRAINT_VIOLATION_TYPE = URI.create(PROBLEM_BASE_URL + "/constraint-violation");
    public static final URI PARAMETERIZED_TYPE = URI.create(PROBLEM_BASE_URL + "/parameterized");
    public static final URI PRECONDITION_TYPE = URI.create(PROBLEM_BASE_URL + "/precondition");
    public static final URI ENTITY_NOT_FOUND_TYPE = URI.create(PROBLEM_BASE_URL + "/entity-not-found");
    public static final URI INVALID_PASSWORD_TYPE = URI.create(PROBLEM_BASE_URL + "/invalid-password");
    public static final URI EMAIL_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/email-already-used");
    public static final URI ALREADY_EXIST_TYPE = URI.create(PROBLEM_BASE_URL + "/already-exist");

    public static final URI TRANSACTION_NO_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/transaction-no-already-used");

    public static final URI LOGIN_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/login-already-used");
    public static final URI EMAIL_NOT_FOUND_TYPE = URI.create(PROBLEM_BASE_URL + "/email-not-found");
    public static final URI NOMOR_NOTA_NOT_FOUND_TYPE = URI.create(PROBLEM_BASE_URL + "/nomor-nota-not-found");

    public static final URI VENDOR_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/vendor-already-used");

    public static final URI CODE_ALREADY_USED_TYPE = URI.create(PROBLEM_BASE_URL + "/code-already-used");

    private ErrorConstants() {
    }
}
