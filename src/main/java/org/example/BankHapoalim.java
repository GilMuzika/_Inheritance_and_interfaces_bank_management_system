package org.example;

public class BankHapoalim extends BankPrototype {
    public BankHapoalim(String id, int employeesNum, int revenueAmount, int expensesAmount) {
        super(id, employeesNum, revenueAmount, expensesAmount);
    }

    @Override
    public int calculateCustomerMoney() {
        int additionalMoney = super.calculateCustomerMoney();
        int oldMoney = this.get_revenueAmount();
        int newMoney = additionalMoney + oldMoney;
        this.set_revenueAmount(newMoney);
        return newMoney;
    }
}
