package org.example;

public class BankLeumi extends BankPrototype {
    public BankLeumi(String id, int employeesNum, int revenueAmount, int expensesAmount) {
        super(id, employeesNum, revenueAmount, expensesAmount);
    }

    @Override
    public int calculateCustomerMoney() {
        int moneyForRemove = super.calculateCustomerMoney();
        int currentMoney = this.get_revenueAmount();
        int newMoney = currentMoney - moneyForRemove;
        this.set_revenueAmount(newMoney);
        return newMoney;
    }

}
