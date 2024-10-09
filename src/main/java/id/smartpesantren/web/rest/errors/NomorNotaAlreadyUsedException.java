package id.smartpesantren.web.rest.errors;

public class NomorNotaAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public NomorNotaAlreadyUsedException() {
        super(ErrorConstants.EMAIL_ALREADY_USED_TYPE, "Nomor Nota Sudah Dipakai!", "nota", "nomorexists");
    }
}
