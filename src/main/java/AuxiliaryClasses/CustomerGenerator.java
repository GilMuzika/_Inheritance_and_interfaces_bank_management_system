package AuxiliaryClasses;

import org.example.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CustomerGenerator {

    private Random _rnd = new Random();

    private HashCreator _hc = new HashCreator(HashingAlgorhitms.SHA256);
    private ArrayList<String> _names;
    private ArrayList<String> _companyNames;
    private ArrayList<String> _bankNames = new ArrayList<>();

    private  String getPathFromResources(String fileName) throws URISyntaxException {
        URL fileUrl = Main.class.getResource("/" + fileName);
        return Paths.get(fileUrl.toURI()).toString();
    }

    public CustomerGenerator() {
        try {
            _names = preload(getPathFromResources("names.txt"));
            _companyNames = preload(getPathFromResources("companies.txt"));

        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        _bankNames.add(BankLeumi.class.getSimpleName());
        _bankNames.add(BankHapoalim.class.getSimpleName());
        _bankNames.add(BankDiscount.class.getSimpleName());


    }
    private ArrayList<String> preload(String absolutePath) {
        ArrayList<String> list = new ArrayList<>();
        String path = null;
        Scanner sc = null;
        try {
            File namesFile = new File(absolutePath);
            sc = new Scanner(namesFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        while (sc.hasNextLine()) {
            list.add(sc.nextLine());
        }
        return list;
    }

    private String generateCreditCardNumber() {
        StringBuilder sb = new StringBuilder();
        int[] digits  = new int[]{0,1,2,3,4,5,6,7,8,9};
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 4; j++) {
                sb.append(digits[_rnd.nextInt(digits.length)]);
            }
            if(i < 3)
                sb.append('-');
        }
        return sb.toString();
    }
    private Function1<ArrayList<String>,String> getRandomStringOfList = (list) -> list.get(_rnd.nextInt(0, list.size()-1));
    public <T extends BankPrototype> Customer getRandomCustomer() {
        String firstName = null;
        String lastName = null;
        try {
            firstName = getRandomStringOfList.func(_names);
            lastName = getRandomStringOfList.func(_names);
        } catch(Exception e) {
            throw new RuntimeException(e);
        }
        String creditcardNumber = generateCreditCardNumber();
        int amountOfMoney = _rnd.nextInt(500, 1000000);
        String id = _hc.createHash(firstName, lastName, creditcardNumber);
        return new Customer(id, firstName, lastName, creditcardNumber, amountOfMoney);
        /*this._id = _id;
        this._firstName = _firstName;
        this._lastName = _lastName;
        this._bankName = _bankName;
        this._creditCardNumber = _creditCardNumber;
        this._amountOfMoney = _amountOfMoney;*/
    }
    public CompanyCustomer getRandomCompanyCustomer() {
        /*
        this._id = id;
        this._companyName = companyName;
        this._bankName = bankName;
        this._amountOfMoney = amountOfMoney;
        */
        String companyName = getRandomStringOfList.func(_companyNames);
        String bankName = BankDiscount.class.getSimpleName();
        int amountOfMoney = _rnd.nextInt(100000, 10000000);
        String id = _hc.createHash(companyName, String.valueOf(amountOfMoney));
        return new CompanyCustomer(id, companyName, amountOfMoney);
    }

}
