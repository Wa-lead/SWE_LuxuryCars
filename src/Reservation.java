import java.util.Date;
import java.util.Random;

public class Reservation {
    private Car car;
    private Customer customer;
    private String date;
    private String paymentMethod;

    public Reservation(Car car, Customer customer, String date, String paymentMethod) {
        this.car = car;
        this.customer = customer;
        this.date = date;
        this.paymentMethod = paymentMethod;
    }


    @Override
    public String toString() {
        return car + " " + customer + " " + date + " " + paymentMethod;
    }

}



