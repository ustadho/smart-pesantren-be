package id.smartpesantren.web.rest.errors;

public class NomorTransaksiAlreadyUsedException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public NomorTransaksiAlreadyUsedException() {
        super(ErrorConstants.TRANSACTION_NO_ALREADY_USED_TYPE, "Nomor transaksi Sudah Dipakai!", "nomor", "nomorexists");
    }
}
