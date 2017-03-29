package Persistence;

/**
 * Created by Calin on 20.03.2017.
 */
public class Client {
    private String name;
    private int idCardName;
    private int CNP;
    private String address;
    private ClientGateway gateway;
    public Client ()
    {
        this.gateway=new ClientGateway();
    }
    public Client (String name, int idCardName, int CNP, String address)
    {
        this.gateway= new ClientGateway();
        this.name=name;
        this.idCardName=idCardName;
        this.CNP=CNP;
        this.address=address;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIdCardName() {
        return idCardName;
    }

    public void setIdCardName(int idCardName) {
        this.idCardName = idCardName;
    }

    public int getCNP() {
        return CNP;
    }

    public void setCNP(int CNP) {
        this.CNP = CNP;
    }
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public ClientGateway getGateway() {
        return gateway;
    }

    public void setGateway(ClientGateway gateway) {
        this.gateway = gateway;
    }
}
