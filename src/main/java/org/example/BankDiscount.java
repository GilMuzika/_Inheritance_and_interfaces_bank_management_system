package org.example;

public class BankDiscount extends BankPrototype {

    public BankDiscount(String id, int employeesNum, int revenueAmount, int expensesAmount) {
        super(id, employeesNum, revenueAmount, expensesAmount);
    }

    @Override
    public int calculateCustomerMoney(){
      int newMoney = super.calculateCustomerMoney();
      this.set_revenueAmount(newMoney);
      return newMoney;
    }

    @Override
    public void takePayment(Customer customer, int payment) {
        super.takePaymentInternal(customer, payment);
    }



}
