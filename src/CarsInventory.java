import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class CarsInventory {

    private ArrayList<Car> carList = new ArrayList<Car>();

    public CarsInventory() {

        try {
            File input = new File("cars");
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
                carList.add(car);

            }

        } catch (FileNotFoundException x) {
            System.out.println(" File not found ");
        }

    }

    public ArrayList<Car> getCarList() {
        return carList;
    }


}
