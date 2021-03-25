import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ReservationList {

    private ArrayList<Reservation> reservationList = new ArrayList<>();

    public ReservationList() {

        try {
            File input = new File("ReservationList");
            Scanner scan = new Scanner(input);

            while (scan.hasNext()) {
                String year = scan.next();
                String brand = scan.next();
                String name = scan.next();
                String type = scan.next();
                String plate = scan.next();
                String price = scan.next();
                String status = scan.next();
                String color = scan.next();
                Car car = new Car(year, brand, name, type, plate, price, status, color);

                String firstName = scan.next();
                String age = scan.next();
                String email = scan.next();
                String id = scan.next();
                String phoneNumber = scan.next();
                String gender = scan.next();
                String drivingLicense = scan.next();
                String password = scan.next();
                Customer customer = new Customer(firstName, age, email, id, phoneNumber, gender, drivingLicense,
                        password);

                String date = scan.next();
                String paymentMethod = scan.next();

                Reservation reservation = new Reservation(car, customer, date, paymentMethod);
                reservationList.add(reservation);

            }

        } catch (FileNotFoundException x) {
            System.out.println(" File not found ");
        }

    }

    public ArrayList<Reservation> getReservationList() {
        return reservationList;
    }

}
