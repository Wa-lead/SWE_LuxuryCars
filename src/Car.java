public class Car {
	private String year;
    private String brand;
    private String name;
    private String type;
    private String plate;
    private String price;
    private String status;
    private String color;

    
    


	public Car(String year, String brand, String name, String type, String plate, String price, String status, String color) {
        this.year = year;
        this.brand = brand;
        this.name = name;
        this.type = type;
        this.plate = plate;
        this.price = price;
        this.status = status;
        this.color = color;
    }

    public String getPlate() {
        return plate;
    }

    public String getPrice() {
        return price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setPrice(String price) {
        this.price = price;
    }

	@Override
	public String toString() {
		return year +" "+ brand +" "+ name +" "+ type +" "+ plate +" "+ price +" "+ status +" "+ color;
	}


}