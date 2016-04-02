package com.example.rodri.mypass.account;

/**
 * Created by rodri on 4/2/2016.
 */
public class Account {

    private String serviceName;
    private String password;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return serviceName + " account.";
    }

}
