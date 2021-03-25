import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CustomerList {
    ArrayList<Customer> customerlist = new ArrayList<>();

    public CustomerList() {

        try {
            File input = new File("CustomerList");
            Scanner scan = new Scanner(input);

            while (scan.hasNext()) {
                String firstName = scan.next();
                String age = scan.next();
                String email = scan.next();
                String id = scan.next();
                String phoneNumber = scan.next();
                String gender = scan.next();
                String drivingLicense = scan.next();
                String password = scan.next();


                Customer customer = new Customer(firstName, age, email, id, phoneNumber, gender, drivingLicense, password);
                customerlist.add(customer);

            }

        } catch (FileNotFoundException x) {
            System.out.println(" File not found ");
        }

    }

    public ArrayList<Customer> getCustomerlist() {
        return customerlist;
    }

}