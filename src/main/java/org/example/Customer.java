package org.example;

import lombok.Getter;
import lombok.Setter;

public class Customer implements ICustomer {
    @Getter
    @Setter
    protected String _id;
    @Getter
    @Setter
    private String _firstName;
    @Getter
    @Setter
    private String _lastName;
    @Getter
    protected String _bankName = "I'm not a client of any bank yet";
    @Getter
    @Setter
    private String  _creditCardNumber;
    @Getter
    @Setter
    protected int _amountOfMoney;

    public Customer(String _id, String _firstName, String _lastName, String _creditCardNumber, int _amountOfMoney) {
        this._id = _id;
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._creditCardNumber = _creditCardNumber;
        this._amountOfMoney = _amountOfMoney;
    }
    public Customer(){}

    public <T extends BankPrototype> void setBankName(T bank){
        /*  The next two lines are a code that use Generics to find which class is calling to the function.
            You can't encapsulate them into a function because it finds a class that is calling to the specific function
            the code is in.  Calling to another function in the same class will give his same class which is calling.
            Only the class is found, not the actual object.
            Furthermore, if hte caller object is of a class which extends another class, only the base class will be found
        */
        StackWalker instance = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE);
        Class<?> callerClass = instance.getCallerClass();

        if(callerClass.equals(BankPrototype.class)) {
            this._bankName = bank.get_name();
        } else {
            throw new RuntimeException("Only bank can give bank name to a customer");
        }
    }

    private void getPayment(int salary) {
        _amountOfMoney += salary;
    }
}
