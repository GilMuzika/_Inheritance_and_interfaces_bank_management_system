package org.example;

import java.util.ArrayList;

public interface IBank {
     String _id = null;
     String _name = null;
     int _employeesNum = 0;
     int _revenueAmount = 0;
     int _expensesAmount = 0;
    ArrayList<Customer> _customers = new ArrayList<>();

    void takePayment(Customer customer, int payment);
    void increaseRevenue(int revenueToAdd);
    void increaseExpenses(int expensesToIncrease);
    int calculateCustomerMoney(); //updates the bank revenue
 }
