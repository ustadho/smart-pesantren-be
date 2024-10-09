package id.smartpesantren.web.rest.errors;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class NomorNotaNotFoundException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public NomorNotaNotFoundException() {
        super(ErrorConstants.NOMOR_NOTA_NOT_FOUND_TYPE, "Nomor nota tidak ditemukan", Status.BAD_REQUEST);
    }
}
