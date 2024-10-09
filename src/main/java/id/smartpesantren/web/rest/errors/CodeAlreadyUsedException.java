package id.smartpesantren.web.rest.errors;

public class CodeAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public CodeAlreadyUsedException() {
        super(ErrorConstants.CODE_ALREADY_USED_TYPE, "Kode sudah dipakai!", "entity", "codeExists");
    }
}
