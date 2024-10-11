package id.smartpesantren.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class DataNotFoundException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public DataNotFoundException() {
        super(ErrorConstants.NOMOR_NOTA_NOT_FOUND_TYPE, "Data tidak ditemukan", Status.BAD_REQUEST);
    }

    public DataNotFoundException(String s) {
        super(ErrorConstants.NOMOR_NOTA_NOT_FOUND_TYPE, s, Status.BAD_REQUEST);
    }
}
