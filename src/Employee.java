public class Employee extends User {
	private String title;

	public Employee(String name, String age, String email, String id, String title) {
		super(name, age, email, id);
		this.title = title;
	}

	@Override
	public String toString() {
		return super.toString() + " " + title;
	}
}
