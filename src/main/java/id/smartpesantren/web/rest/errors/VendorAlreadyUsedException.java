package id.smartpesantren.web.rest.errors;

public class VendorAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public VendorAlreadyUsedException() {
        super(ErrorConstants.EMAIL_ALREADY_USED_TYPE, "Kode vendor sudah digunakan!", "vendor", "vendorExists");
    }
}
