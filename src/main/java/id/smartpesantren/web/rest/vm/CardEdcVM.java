package id.smartpesantren.web.rest.vm;


public class CardEdcVM {
    private String id;
    private String name;
    private String bankAccountNo;
    private String bankAccountId;
    private String bankAccountName;
    private Boolean suspended;

    public CardEdcVM() {
    }

    public CardEdcVM(String id, String name, String bankAccountNo, String bankAccountId, String bankAccountName, Boolean suspended) {
        this.id = id;
        this.name = name;
        this.bankAccountNo = bankAccountNo;
        this.bankAccountId = bankAccountId;
        this.bankAccountName = bankAccountName;
        this.suspended = suspended;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBankAccountNo() {
        return bankAccountNo;
    }

    public void setBankAccountNo(String bankAccountNo) {
        this.bankAccountNo = bankAccountNo;
    }

    public Boolean getSuspended() {
        return suspended;
    }

    public void setSuspended(Boolean suspended) {
        this.suspended = suspended;
    }

    public String getBankAccountId() {
        return bankAccountId;
    }

    public void setBankAccountId(String bankAccountId) {
        this.bankAccountId = bankAccountId;
    }

    public String getBankAccountName() {
        return bankAccountName;
    }

    public void setBankAccountName(String bankAccountName) {
        this.bankAccountName = bankAccountName;
    }
}
