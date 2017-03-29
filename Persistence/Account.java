package Persistence;

import java.util.Date;

/**
 * Created by Calin on 20.03.2017.
 */
public class Account {
    private int idNumber;
    private int idCardNumber;
    private String type;
    private int amount;
    private Date creationDate;
    private AccountGateway gateway;

    public Account()
    {
        this.gateway=new AccountGateway();
    }

    public Account(int idNumber, int idCardNumber, String type, int amount, Date creationDate) {
        this.idNumber = idNumber;
        this.idCardNumber = idCardNumber;
        this.type = type;
        this.amount = amount;
        this.creationDate = creationDate;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    public int getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(int idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public AccountGateway getGateway() {
        return gateway;
    }

    public void setGateway(AccountGateway gateway) {
        this.gateway = gateway;
    }
}
