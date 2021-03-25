import java.util.ArrayList;
import java.util.Date;

public class Customer extends User {

    String drivingLicense;
    String gender;
    String phoneNumber;
    String password;

    public Customer() {

    }

    public Customer(String name, String age, String email, String id, String phoneNumber, String gender, String drivingLicense, String password) {
        super(name, age, email, id);
        this.gender = gender;
        this.phoneNumber = phoneNumber;
        this.drivingLicense = drivingLicense;
        this.password = password;
    }


    @Override
    public String toString() {
        return super.toString() + " " + phoneNumber + " " + gender + " " + drivingLicense + " " + password;
    }

    public String getPassword() {
        return password;
    }


}