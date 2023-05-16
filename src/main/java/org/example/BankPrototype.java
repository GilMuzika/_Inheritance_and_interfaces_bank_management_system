package org.example;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public abstract class BankPrototype implements IBank {
    @Getter
    @Setter
    protected String _id;
    @Getter
    @Setter
    protected String _name;
    @Getter
    @Setter
    protected int _employeesNum;
    @Getter
    @Setter
    protected int _revenueAmount;

    @Getter
    @Setter
    protected int _expensesAmount;
    @Getter
    protected ArrayList<Customer> _customers = new ArrayList<>();

    public BankPrototype(String id, int employeesNum, int revenueAmount, int expensesAmount) {
        _id = id;
        _employeesNum = employeesNum;
        _revenueAmount = revenueAmount;
        _expensesAmount = expensesAmount;
        _name = this.getClass().getSimpleName();
    }

    public void takePayment(Customer customer, int payment) {
        if(!isDiscount()) /*{
            if(customer instanceof CompanyCustomer)
                throw new RuntimeException(String.format("You are a company customer, and %s doesn't give service to companies. Go to bank Discount instead"));
        }*/
            takePaymentInternal(customer, payment);
    }
    protected void takePaymentInternal(Customer customer, int payment){
        if(!_customers.contains(customer))
            throw new RuntimeException("The customer is not a client of this bank");

        int customerMoney = customer.get_amountOfMoney();
        int newAmountOfMoney = customerMoney - payment;
        customer.set_amountOfMoney(newAmountOfMoney);
        _revenueAmount += payment;
    }
    //Each time a bank handles customer payment the customer amount of money should decrease accordingly.
    public void increaseRevenue(int revenueToAdd) {
        _revenueAmount += revenueToAdd;
    }
    public void increaseExpenses(int expensesToIncrease) {
        _expensesAmount += expensesToIncrease;
    }
    public int calculateCustomerMoney() {
        int sum = 0;
        List<Integer> money =  _customers.stream().map(x -> x.get_amountOfMoney()).collect(Collectors.toList());
        for(int one : money){
            sum += one;
        }
        return sum;
    }


    protected boolean isDiscount() {
        return this instanceof BankDiscount;
    }

    protected void addCustomer(Customer customer) {
        if(!isDiscount()){
            if(customer instanceof CompanyCustomer)
                throw new RuntimeException(String.format("You are a company customer, and %s doesn't give service to companies. Go to bank Discount instead", this.get_name()));
        }
        if(customer.get_bankName().contains("Bank"))
            throw new RuntimeException("You already a client of a bank");

        customer.setBankName(this);
        _customers.add(customer);
    }

    public Customer getRandomCustomer() {
        Random r = new Random();
        return _customers.get(r.nextInt(0, _customers.size()-1));
    }

}
