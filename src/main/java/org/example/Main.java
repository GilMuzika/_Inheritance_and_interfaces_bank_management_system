package org.example;

import AuxiliaryClasses.CustomerGenerator;
import AuxiliaryClasses.HashCreator;
import AuxiliaryClasses.HashingAlgorhitms;

import java.util.Random;

public class Main {

    private static Random _rnd = new Random();
    private static CustomerGenerator _cg = new CustomerGenerator();
    public static void main(String[] args) {
        BankDiscount bankDiscount = new BankDiscount("001", 1000, 0, 54354);
        BankHapoalim bankHapoalim = new BankHapoalim("002", 2000, 0, 668);
        BankLeumi bankLeumi = new BankLeumi("003", 3000, 0, 98241);

        for(int i = 0; i < 100; i ++) {
            bankHapoalim.addCustomer(_cg.getRandomCustomer());
            bankLeumi.addCustomer(_cg.getRandomCustomer());
            //bankLeumi.addCustomer(_cg.getRandomCompanyCustomer()); // "CompanyCustomer" can be added only to "BankDiscount"
            int rn = _rnd.nextInt(1,10);
            if(rn <= 5) {
                bankDiscount.addCustomer(_cg.getRandomCustomer());
            } else {
                bankDiscount.addCustomer(_cg.getRandomCompanyCustomer());
            }
        }

        Customer hapoalimCustomer = bankHapoalim.getRandomCustomer();
        bankHapoalim.takePayment(hapoalimCustomer, 4521);

        Customer cus1 = _cg.getRandomCustomer();
        Customer cus2 = _cg.getRandomCustomer();
        CompanyCustomer comCus2 = _cg.getRandomCompanyCustomer();
        bankDiscount.addCustomer(comCus2);
        bankDiscount.takePayment(comCus2, 200);






        int hapoamRevenue = bankHapoalim.get_revenueAmount();




    }
}