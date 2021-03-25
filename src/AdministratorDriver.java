
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.util.Date;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;


// this driver is for the company and administrator to access and modify the things that are related to the system 
public class AdministratorDriver extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("luxury cars");
		primaryStage.setScene(mainMenu());
		primaryStage.show();

	}

	// Prompts the main menu for the user.
	public Scene mainMenu() {
		JFXButton receptionist = new JFXButton();
		ImageView receptionImage = new ImageView(new Image("reception.png"));
		receptionImage.setFitHeight(80);
		receptionImage.setFitWidth(80);

		receptionist.setGraphic(receptionImage);
		// This fragment of code will be seen a lot through the project, it transfers
		// the user to a specified scene.
		// starts here
		receptionist.setOnAction(e -> {
			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
			window.setScene(receptionistMenu());
		}); // ends here

		JFXButton rentalManager = new JFXButton();
		ImageView rentalImage = new ImageView(new Image("admin.png"));
		rentalImage.setFitHeight(80);
		rentalImage.setFitWidth(80);
		rentalManager.setGraphic(rentalImage);
		rentalManager.setOnAction(e -> {
			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
			window.setScene(rentalManagerMenu());
		});

		JFXButton HR = new JFXButton();
		ImageView HrImage = new ImageView(new Image("Hr.png"));
		HrImage.setFitHeight(80);
		HrImage.setFitWidth(80);
		HR.setGraphic(HrImage);
		HR.setOnAction(e -> {
			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
			window.setScene(HRMenu());
		});

		JFXButton inventoryManager = new JFXButton();
		ImageView inventoryImage = new ImageView(new Image("inventory.png"));
		inventoryImage.setFitHeight(80);
		inventoryImage.setFitWidth(80);
		inventoryManager.setGraphic(inventoryImage);
		inventoryManager.setOnAction(e -> {
			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
			window.setScene(inventoryManagerMenu());
		});
		Label receptionistLabel = new Label("Receptionist");
		Label rentalLabel = new Label("Rental Manager");
		Label HRLabel = new Label("HR");
		Label inventoryLabel = new Label("Inventory Manager");

		HBox labelsHBox = new HBox(43);
		labelsHBox.setAlignment(Pos.CENTER);
		labelsHBox.getChildren().addAll(receptionistLabel, rentalLabel, HRLabel, inventoryLabel);

		HBox buttonHbox = new HBox(10);
		buttonHbox.setPadding(new Insets(8, 8, 8, 8));
		buttonHbox.setAlignment(Pos.CENTER);
		buttonHbox.getChildren().addAll(receptionist, rentalManager, HR, inventoryManager);

		VBox AllHbox = new VBox(10);
		AllHbox.setAlignment(Pos.CENTER);

		AllHbox.getChildren().addAll(buttonHbox, labelsHBox);

		StackPane stackpane = new StackPane();
		Scene scene = new Scene(stackpane);
		stackpane.getChildren().addAll(Background(scene), AllHbox);
		return scene;
	}

	// Prompts for receptionists and transfers them to updating car status
	// interface.
	public Scene receptionistMenu() {
		JFXButton updateCarStatus = new JFXButton("update Car Status");

		updateCarStatus.setOnAction(e -> {
			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
			window.setScene(updateCarStatus());
		});

		StackPane stackpane = new StackPane();
		Scene scene = new Scene(stackpane);
		stackpane.getChildren().addAll(Background(scene), updateCarStatus);
		return scene;
	}

	// prompts for rental managers and transfers them to update cat price interface.
	public Scene rentalManagerMenu() {

		JFXButton updateCarPrice = new JFXButton("update Car price");

		updateCarPrice.setOnAction(e -> {
			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
			window.setScene(updateCarPrice());
		});

		StackPane stackpane = new StackPane();
		Scene scene = new Scene(stackpane);
		stackpane.getChildren().addAll(Background(scene), updateCarPrice);
		return scene;
	}

	// Prompts for HR and transfers them to add an employee interface.
	public Scene HRMenu() {

		JFXButton addEmployee = new JFXButton("Add employee");

		addEmployee.setOnAction(e -> {
			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
			window.setScene(addEmploye());
		});

		StackPane stackpane = new StackPane();
		Scene scene = new Scene(stackpane);
		stackpane.getChildren().addAll(Background(scene), addEmployee);
		return scene;
	}

	// Prompts for inventory managers and transfers them addcar and removecar
	// interface.
	public Scene inventoryManagerMenu() {

		JFXButton addcar = new JFXButton("add car");

		addcar.setOnAction(e -> {
			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
			window.setScene(addCar());
		});

		JFXButton removeCar = new JFXButton("remove Car");

		removeCar.setOnAction(e -> {
			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
			window.setScene(removeCar());
		});

		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(addcar, removeCar);

		StackPane stackpane = new StackPane();
		Scene scene = new Scene(stackpane);
		stackpane.getChildren().addAll(Background(scene), hbox);
		return scene;
	}

	// Allows receptionists to update cars' status
	public Scene updateCarStatus() {
		CarsInventory carsInventory = new CarsInventory();

		ObservableList<String> stat = FXCollections.observableArrayList();
		JFXComboBox status = new JFXComboBox(stat);

		JFXButton confirm = new JFXButton("CONFIRM");

		ObservableList<Car> carslist2 = FXCollections.observableArrayList();
		for (int i = 0; i < carsInventory.getCarList().size(); i++) {
			carslist2.add(carsInventory.getCarList().get(i));
		}
		JFXComboBox<Car> JFXComboBox = new JFXComboBox<>();
		JFXComboBox.setPromptText("choose the car");
		JFXComboBox.setItems(carslist2);
		JFXComboBox.setOnAction(e -> {

			confirm.setOnAction(p -> {
				JFXComboBox.getValue().setStatus((String) status.getValue());
				try {
					PrintWriter pwriter = new PrintWriter((new FileWriter("cars")));
					for (int i = 0; i < carslist2.size(); i++) {
						pwriter.println(carslist2.get(i));
					}
					pwriter.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			});
		});

		stat.add("Available");
		stat.add("Reserved");
		Text update = new Text("Update:");

		HBox hBox = new HBox(update, status);
		hBox.setSpacing(10);
		hBox.setAlignment(Pos.CENTER);

		VBox vBox = new VBox(JFXComboBox, hBox, confirm, backToMainMenu());
		vBox.setAlignment(Pos.CENTER);

		StackPane pane = new StackPane();
		Scene scene = new Scene(pane);

		vBox.setSpacing(10);

		pane.getChildren().addAll(Background(scene), vBox, transition("reception"));

		return scene;

	}

	// Allows rental managers to update cars' prices.
	public Scene updateCarPrice() {
		CarsInventory carsInventory = new CarsInventory();

		ObservableList<Car> carslist2 = FXCollections.observableArrayList();
		for (int i = 0; i < carsInventory.getCarList().size(); i++) {
			carslist2.add(carsInventory.getCarList().get(i));
		}

		Label currentprice = new Label();
		JFXTextField newpriceJFXTextField = new JFXTextField();
		JFXButton confirm = new JFXButton("confirm");

		JFXComboBox<Car> JFXComboBox = new JFXComboBox<>();
		JFXComboBox.setPromptText("choose the car");
		JFXComboBox.setItems(carslist2);
		JFXComboBox.setOnAction(e -> {
			currentprice.setText("current price: " + JFXComboBox.getValue().getPrice());

			confirm.setOnAction(p -> {
				if (newpriceJFXTextField.getText().isBlank())
					AlertBox("Blank field", "Please fill all the fields");
				else {
					JFXComboBox.getValue().setPrice(newpriceJFXTextField.getText());
					currentprice.setText("current price: " + JFXComboBox.getValue().getPrice());
					try {
						PrintWriter pwriter = new PrintWriter((new FileWriter("cars")));
						for (int i = 0; i < carslist2.size(); i++) {
							pwriter.println(carslist2.get(i));
						}
						pwriter.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			});
		});

		Label updatecarprice = new Label("update car price");

		Label newprice = new Label("new price: ");

		newpriceJFXTextField.setFont(Font.font("Times", 20));
		newpriceJFXTextField.setMaxSize(130, 30);
		newpriceJFXTextField.setMinSize(130, 30);

		HBox hbox = new HBox();
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(newprice, newpriceJFXTextField);

		VBox vbox = new VBox(10);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(updatecarprice, JFXComboBox, currentprice, hbox, confirm, backToMainMenu());

		StackPane stackpane = new StackPane();
		Scene scene = new Scene(stackpane);
		stackpane.getChildren().addAll(Background(scene), vbox, transition("admin"));

		return scene;
	}

	// Allows HR to add employees.
	public Scene addEmploye() {
		EmployeeList employeeList = new EmployeeList();
		// ----------------------------------- texts
		StackPane pane = new StackPane();

		// ----------------------------------- texts
		Text name = new Text("Name: ");
		Text iD = new Text("ID: ");
		Text title = new Text("Title: ");
		Text birth = new Text("Birth date: ");
		Text email = new Text("Email: ");
		VBox leftBox = new VBox(name, iD, title, birth, email);
		leftBox.setSpacing(21);
		// -----------------------------------------

		JFXTextField namefield = new JFXTextField();
		JFXTextField idfield = new JFXTextField();
		JFXTextField titlefield = new JFXTextField();
		JFXTextField emailfield = new JFXTextField();

		// --------------------------
		ObservableList<String> days = FXCollections.observableArrayList();
		for (int i = 1; i < 32; i++) {
			days.add(String.valueOf(i));
		}
		ObservableList<String> months = FXCollections.observableArrayList();
		for (int i = 1; i < 13; i++) {
			months.add(String.valueOf(i));
		}
		ObservableList<String> years = FXCollections.observableArrayList();
		for (int i = 1950; i < 2020; i++) {
			years.add(String.valueOf(i));
		}

		JFXComboBox day = new JFXComboBox(days);
		JFXComboBox month = new JFXComboBox(months);
		JFXComboBox year = new JFXComboBox(years);

		HBox JFXComboBox = new HBox(day, month, year);
		JFXComboBox.setSpacing(15);
		// -----------------------------------------
		JFXButton add = new JFXButton("Add");
		add.setOnAction(e -> {

			boolean b = true;

			String date = year.getValue() + "/" + month.getValue() + "/" + day.getValue();
			if (namefield.getText().isBlank() || emailfield.getText().isBlank() || idfield.getText().isBlank()
					|| titlefield.getText().isBlank() || String.valueOf(day.getValue()).isBlank()
					|| String.valueOf(month.getValue()).isBlank() || String.valueOf(year.getValue()).isBlank()) {
				AlertBox("Needed fields", " Fill All Blanks Please");
				b = false;
			}

			if (ageCalculator(date) >= 60 || ageCalculator(date) < 18) {
				AlertBox("Can't add Employee!", "Employees should be between 20 and 60 years old");
				b = false;
			}

			else {

				for (int i = 0; i < employeeList.getEmployeeList().size(); i++) {
					if (employeeList.getEmployeeList().get(i).getId().equals(idfield.getText())) {
						AlertBox("Duplicate", "The employee already exists in the system");
						b = false;
					}
				}
				if (b) {
					Employee employee = new Employee(namefield.getText(), String.valueOf(ageCalculator(date)),
							emailfield.getText(), idfield.getText(), titlefield.getText());
					employeeList.getEmployeeList().add(employee);

					try {
						PrintWriter pwriter = new PrintWriter((new FileWriter("employee")));
						for (int i = 0; i < employeeList.getEmployeeList().size(); i++) {
							pwriter.println(employeeList.getEmployeeList().get(i));
						}
						pwriter.close();
						add.setText("Done!");
						add.setTextFill(Color.GREENYELLOW);
						Timeline animation = new Timeline(new KeyFrame(Duration.millis(1000), o -> { // the animation
																										// details
							add.setText("Add");
							add.setTextFill(Color.BLACK);

						}));
						animation.play();
						animation.setCycleCount(1);

					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		HBox JFXButtonBox = new HBox(add, backToMainMenu());
		JFXButtonBox.setSpacing(130);
		// -----------------------------------------
		VBox rightBox = new VBox(namefield, idfield, titlefield, JFXComboBox, emailfield, JFXButtonBox);
		rightBox.setSpacing(10);

		FlowPane flowPane = new FlowPane(leftBox, rightBox);
		flowPane.setAlignment(Pos.CENTER);
		flowPane.setHgap(8);

		Scene scene = new Scene(pane);
		pane.getChildren().addAll(Background(scene), flowPane, transition("HR"));

		return scene;
	}

	// Allows inventory managers to add a new car to the inventory.
	public Scene addCar() {
		CarsInventory carsInventory = new CarsInventory();

		VBox labelsVbox = new VBox(10);
		Label yearLabel = new Label("Year");
		yearLabel.setFont(Font.font(12));
		Label brandLabel = new Label("Brand");
		brandLabel.setFont(Font.font(12));
		Label nameLabel = new Label("Name");
		nameLabel.setFont(Font.font(12));
		Label typeLabel = new Label("type");
		typeLabel.setFont(Font.font(12));
		Label plateLabel = new Label("Plate");
		plateLabel.setFont(Font.font(12));
		Label priceLabel = new Label("Price");
		priceLabel.setFont(Font.font(12));
		Label statusLabel = new Label("Status");
		statusLabel.setFont(Font.font(12));
		Label colorLabel = new Label("Color");
		colorLabel.setFont(Font.font(12));
		labelsVbox.setPadding(new Insets(20, 20, 20, 20));
		labelsVbox.setSpacing(14);
		labelsVbox.getChildren().addAll(yearLabel, brandLabel, nameLabel, typeLabel, plateLabel, priceLabel,
				statusLabel, colorLabel);
		labelsVbox.setAlignment(Pos.CENTER);
		VBox JFXTextFieldVbox = new VBox();
		JFXTextField yearJFXTextField = new JFXTextField();
		JFXTextField brandJFXTextField = new JFXTextField();
		JFXTextField nameJFXTextField = new JFXTextField();
		JFXTextField typeJFXTextField = new JFXTextField();
		JFXTextField plateJFXTextField = new JFXTextField();
		JFXTextField priceJFXTextField = new JFXTextField();
		JFXTextField statusJFXTextField = new JFXTextField();
		JFXTextField colorJFXTextField = new JFXTextField();
		JFXTextField message = new JFXTextField();
		JFXTextFieldVbox.getChildren().addAll(yearJFXTextField, brandJFXTextField, nameJFXTextField, typeJFXTextField,
				plateJFXTextField, priceJFXTextField, statusJFXTextField, colorJFXTextField);

		JFXTextFieldVbox.setAlignment(Pos.CENTER);
		JFXTextFieldVbox.setSpacing(6);
		HBox container = new HBox();
		container.getChildren().addAll(labelsVbox, JFXTextFieldVbox);
		container.setAlignment(Pos.CENTER);
		JFXButton addJFXButton = new JFXButton("Add car");
		VBox allNodes = new VBox(5);
		allNodes.getChildren().addAll(container, addJFXButton, backToMainMenu());
		allNodes.setAlignment(Pos.CENTER);
		addJFXButton.setOnAction(e -> {
			try {
				String year = yearJFXTextField.getText();
				String brand = brandJFXTextField.getText();
				String name = nameJFXTextField.getText();
				String type = typeJFXTextField.getText();
				String plate = plateJFXTextField.getText();
				String price = priceJFXTextField.getText();
				String status = statusJFXTextField.getText();
				String color = colorJFXTextField.getText();
				boolean b = true;
				if (year.isBlank() || brand.isBlank() || name.isBlank() || type.isBlank() || plate.isBlank()
						|| price.isBlank() || status.isBlank() || color.isBlank()) {
					AlertBox("Blank fields", "Please fill all the fields");
					b = false;
				}
				for (int i = 0; i < carsInventory.getCarList().size(); i++) {
					if (carsInventory.getCarList().get(i).getPlate().equals(plate)) {
						b = false;
						AlertBox("Duplicate", "The car with entered plate number already exists");
					}
				}
				if (b) {
					Car car = new Car(year, brand, name, type, plate, price, status, color);
					PrintWriter pwriter = new PrintWriter((new FileWriter("cars", true)));
					pwriter.println(car);
					pwriter.close();
				}

			} catch (Exception e1) {
				e1.printStackTrace();
			}
		});

		StackPane stackpane = new StackPane();
		Scene scene = new Scene(stackpane);
		stackpane.getChildren().addAll(Background(scene), allNodes, transition("inventory"));

		return scene;
	}

	// Allows inventory managers to remove a car from the inventory.
	public Scene removeCar() {
		CarsInventory carsInventory = new CarsInventory();

		VBox labelsVbox = new VBox(10);
		Label plateLabel = new Label("Plate number");
		plateLabel.setFont(Font.font(12));
		Label notesLabel = new Label("Notes");
		notesLabel.setFont(Font.font(12));
		labelsVbox.getChildren().addAll(plateLabel, notesLabel);
		labelsVbox.setAlignment(Pos.CENTER);
		labelsVbox.setPadding(new Insets(10, 10, 10, 10));
		VBox JFXTextFieldVbox = new VBox();
		JFXTextField plateJFXTextField = new JFXTextField();
		JFXTextField notesJFXTextField = new JFXTextField();
		JFXTextFieldVbox.getChildren().addAll(plateJFXTextField, notesJFXTextField);
		JFXTextFieldVbox.setAlignment(Pos.CENTER);
		JFXTextFieldVbox.setSpacing(6);
		HBox container = new HBox();
		container.getChildren().addAll(labelsVbox, JFXTextFieldVbox);
		container.setAlignment(Pos.CENTER);
		JFXButton removeJFXButton = new JFXButton("Remove car");
		VBox allNodes = new VBox(5);
		allNodes.getChildren().addAll(container, removeJFXButton, backToMainMenu());
		allNodes.setAlignment(Pos.CENTER);
		removeJFXButton.setOnAction(e -> {
			try {
				boolean b = true;
				boolean b1 = true;
				String plateCheck = plateJFXTextField.getText();
				if (plateCheck.isBlank() || notesJFXTextField.getText().isBlank()) {
					AlertBox("Blank fields", "Please fill all the fields");
					b1 = false;
				}
				for (int i = 0; i < carsInventory.getCarList().size(); i++) {
					if (carsInventory.getCarList().get(i).getPlate().equals(plateCheck) && b1) {
						carsInventory.getCarList().remove(i);
						b = false;
					}
				}
				if (b && b1) {
					AlertBox("Nonexistent car", "Car with entered plate number does not exist");
				}
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			try {
				PrintWriter pwriter = new PrintWriter((new FileWriter("cars")));

				for (int i = 0; i < carsInventory.getCarList().size(); i++) {
					pwriter.println(carsInventory.getCarList().get(i));

				}
				pwriter.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		});

		StackPane stackpane = new StackPane();
		Scene scene = new Scene(stackpane);
		stackpane.getChildren().addAll(Background(scene), allNodes, transition("inventory"));
		return scene;
	}

	// Shows the logo of the company inside the interface.
	public ImageView Background(Scene scene) {
		Image background = new Image("menu.jpg");
		ImageView viewBackground = new ImageView(background);
		viewBackground.fitHeightProperty().bind(scene.heightProperty());
		viewBackground.fitWidthProperty().bind(scene.widthProperty());
		return viewBackground;
	}

	// Calculate the age of employees to check if its valid to be hired for the
	// company.
	public int ageCalculator(String s) {
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			Date d = sdf.parse(s);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH) + 1;
			int date = c.get(Calendar.DATE);
			LocalDate l1 = LocalDate.of(year, month, date);
			LocalDate now1 = LocalDate.now();
			Period diff1 = Period.between(l1, now1);
			return diff1.getYears();

		} catch (ParseException e) {
			e.printStackTrace();
		}
		return 0;

	}

	// Prompts an alert message to users when mistakes are made.
	public void AlertBox(String title, String message) {
		Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(450);

		Label msg = new Label(message);

		JFXButton closeJFXButton = new JFXButton("close");
		closeJFXButton.setOnAction(e -> window.close());

		VBox vbox = new VBox(10);
		vbox.setAlignment(Pos.CENTER);
		vbox.setPadding(new Insets(8, 8, 8, 8));
		vbox.getChildren().addAll(msg, closeJFXButton);

		Scene scene = new Scene(vbox);
		window.setScene(scene);
		window.showAndWait();
	}

	// Returns the user to the main menu.
	public JFXButton backToMainMenu() {

		JFXButton backToMainMenu = new JFXButton("back To Main Menu");
		backToMainMenu.setOnAction(e -> {
			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
			window.setScene(mainMenu());
		});

		return backToMainMenu;
	}

	// Creates a startup transition for the user icon.
	public ImageView transition(String x) {

		ImageView hr = new ImageView(new Image(x + ".png"));
		hr.setFitWidth(200);
		hr.setFitHeight(200);

		TranslateTransition translateTransition = new TranslateTransition(); // to create an animation for
		translateTransition.setDuration(Duration.seconds(1));
		translateTransition.setToX(-300);
		translateTransition.setByY(0);
		translateTransition.setNode(hr);
		translateTransition.play();

		return hr;
	}

}
