package id.smartpesantren.web.rest.errors;

public class AlreadyExistException extends BadRequestAlertException {

    private static final long serialVersionUID = 1L;

    public AlreadyExistException(String message, String entityName) {
        super(ErrorConstants.ALREADY_EXIST_TYPE, message, entityName, "alreadyExist");
    }
}
