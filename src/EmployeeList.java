import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeList {
	private ArrayList<Employee> employeeList = new ArrayList<>();

	public EmployeeList() {

		try {
			File input = new File("employee");
			Scanner scan = new Scanner(input);

			while (scan.hasNext()) {
				String name = scan.next();
				String age = scan.next();
				String email = scan.next();
				String id = scan.next();
				String title = scan.next();

				Employee employee = new Employee(name, age, email, id, title);
				employeeList.add(employee);

			}

		} catch (FileNotFoundException x) {
			System.out.println(" File not found ");
		}

	}

	public ArrayList<Employee> getEmployeeList() {
		return employeeList;
	}

}
