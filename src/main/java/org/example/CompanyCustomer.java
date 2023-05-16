package org.example;

public class CompanyCustomer extends Customer {

    private String _companyName;

    public CompanyCustomer(String id, String companyName,  int amountOfMoney) {
        this._id = id;
        this._companyName = companyName;
        this._amountOfMoney = amountOfMoney;
    }
}
