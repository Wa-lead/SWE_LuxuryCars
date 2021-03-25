import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Calendar;
import java.text.ParseException;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

// this driver is for the customer UI.
public class CustomerDriver extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("Luxury Cars: Customer Version");
		primaryStage.setScene(customerMainMenu());
		primaryStage.show();
	}

	Customer LoggedInCustomer = new Customer();

	// Prompts the main menu for the customer.
	public Scene customerMainMenu() {

		StackPane customerMainMenu = new StackPane();
		Scene scene = new Scene(customerMainMenu);

		JFXButton loginJFXButton = new JFXButton("Login");
		JFXButton createAccount = new JFXButton("Create Account");

		loginJFXButton.setOnAction(e -> {
			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
			window.setScene(loginMenu());
			window.show();

		});

		createAccount.setOnAction(e -> {

			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
			window.setScene(createAccountMenu());
			window.show();

		});

		VBox JFXButtonsBox = new VBox(20);
		JFXButtonsBox.getChildren().addAll(loginJFXButton, createAccount);

		JFXButtonsBox.setAlignment(Pos.CENTER);
		customerMainMenu.getChildren().addAll(Background(scene), JFXButtonsBox, transition("customer"));

		return scene;
	}

	// Allows the customer to login using his account.
	public Scene loginMenu() {

		CustomerList customerList = new CustomerList();
		StackPane loginMenu = new StackPane();
		Scene scene = new Scene(loginMenu);

		VBox labelsVbox = new VBox(10);
		Label yourEmail = new Label("Enter your Email :");
		yourEmail.setFont(Font.font(14));
		Label yourPassword = new Label("Enter your password :");
		yourPassword.setFont(Font.font(14));
		labelsVbox.getChildren().addAll(yourEmail, yourPassword);
		labelsVbox.setAlignment(Pos.CENTER);

		VBox JFXTextFieldsVbox = new VBox(10);
		JFXTextField emailJFXTextField = new JFXTextField();
		JFXPasswordField passwordField = new JFXPasswordField();
		JFXTextFieldsVbox.getChildren().addAll(emailJFXTextField, passwordField);
		JFXTextFieldsVbox.setAlignment(Pos.CENTER);

		HBox labelsAndJFXTextFields = new HBox(5);
		labelsAndJFXTextFields.getChildren().addAll(labelsVbox, JFXTextFieldsVbox);
		labelsAndJFXTextFields.setAlignment(Pos.CENTER);

		JFXButton loginJFXButton = new JFXButton("Login");
		HBox JFXButtonsHbox = new HBox(8);
		JFXButtonsHbox.getChildren().addAll(loginJFXButton, backToMainMenu());
		JFXButtonsHbox.setAlignment(Pos.CENTER);

		VBox allNodes = new VBox(5);
		allNodes.getChildren().addAll(labelsAndJFXTextFields, JFXButtonsHbox);
		allNodes.setAlignment(Pos.CENTER);

		loginJFXButton.setOnAction(e -> {
			String correctemail = " ";
			String Correctpassword = " ";
			for (int i = 0; i < customerList.getCustomerlist().size(); i++) {
				if (emailJFXTextField.getText().equals(customerList.getCustomerlist().get(i).getEmail())
						&& passwordField.getText().equals(customerList.getCustomerlist().get(i).getPassword())) {
					correctemail = customerList.getCustomerlist().get(i).getEmail();
					Correctpassword = customerList.getCustomerlist().get(i).getPassword();
					LoggedInCustomer = customerList.getCustomerlist().get(i);
				}
			}

			if (correctemail.equals(emailJFXTextField.getText()) && Correctpassword.equals(passwordField.getText())) {
				Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
				window.setScene(bookCar());
				AlertBox("Login successful", "Welcome !");
			} else
				AlertBox("Error", "Wrong Email or Password");
		});

		loginMenu.getChildren().addAll(Background(scene), allNodes, transition("customer"));
		return scene;

	}

	@SuppressWarnings("unchecked")
	// Allows the customer to create a new Account
	public Scene createAccountMenu() {
		CustomerList customerList = new CustomerList();
		StackPane createAccountmenu = new StackPane();

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

		JFXComboBox<String> gender = new JFXComboBox<String>();
		gender.setLayoutY(120);
		gender.getItems().add("Male");
		gender.getItems().add("Female");

		JFXButton createAccountbt = new JFXButton("Create Account");
		HBox JFXButtonsHbox = new HBox(8);
		JFXButtonsHbox.getChildren().addAll(backToMainMenu(), createAccountbt);
		JFXButtonsHbox.setAlignment(Pos.CENTER);

		HBox ageJFXComboBox = new HBox(day, month, year);
		ageJFXComboBox.setSpacing(8);

		VBox labelsVbox = new VBox(12);

		Label firstNameLabel = new Label("First Name: ");
		firstNameLabel.setFont(Font.font(14));
		Label ageLabel = new Label("Age: ");
		ageLabel.setFont(Font.font(14));
		Label eMailLabel = new Label("E-mail address: ");
		eMailLabel.setFont(Font.font(14));
		Label idOrIqamaLabel = new Label("Id or Iqama: ");
		idOrIqamaLabel.setFont(Font.font(14));
		Label phoneNumberLabel = new Label("Phone number: ");
		phoneNumberLabel.setFont(Font.font(14));
		Label genderLabel = new Label("Gender: ");
		genderLabel.setFont(Font.font(14));
		Label drivingLicenseLabel = new Label("Driving License: ");
		drivingLicenseLabel.setFont(Font.font(14));
		Label passwordLabel = new Label("Password : ");
		passwordLabel.setFont(Font.font(14));

		labelsVbox.setPadding(new Insets(20, 20, 20, 20));
		labelsVbox.getChildren().addAll(firstNameLabel, ageLabel, eMailLabel, idOrIqamaLabel, phoneNumberLabel,
				genderLabel, drivingLicenseLabel, passwordLabel);
		labelsVbox.setAlignment(Pos.CENTER);

		VBox JFXTextFieldsVbox = new VBox(8);
		JFXTextField firstNameField = new JFXTextField();
		JFXTextField emailField = new JFXTextField();
		JFXTextField idOrIqamaField = new JFXTextField();
		JFXTextField phoneNumberField = new JFXTextField();

		JFXTextField drivingLicenseField = new JFXTextField();
		JFXPasswordField passwordField = new JFXPasswordField();
		JFXTextFieldsVbox.getChildren().addAll(firstNameField, ageJFXComboBox, emailField, idOrIqamaField,
				phoneNumberField, gender, drivingLicenseField, passwordField);
		JFXTextFieldsVbox.setAlignment(Pos.CENTER);

		HBox labelsAndJFXTextFields = new HBox();
		labelsAndJFXTextFields.getChildren().addAll(labelsVbox, JFXTextFieldsVbox);
		labelsAndJFXTextFields.setAlignment(Pos.CENTER);

		Scene scene = new Scene(createAccountmenu);

		VBox allNodes = new VBox();
		allNodes.getChildren().addAll(labelsAndJFXTextFields, JFXButtonsHbox);
		allNodes.setAlignment(Pos.CENTER);
		createAccountmenu.getChildren().addAll(Background(scene), allNodes, transition("customer"));

		day.setOnAction(e1 -> {
			month.setOnAction(e2 -> {
				year.setOnAction(e4 -> {
					gender.setOnAction(e5 -> {
						createAccountbt.setOnAction(e6 -> {
							if (firstNameField.getText().isBlank() || idOrIqamaField.getText().isBlank()
									|| drivingLicenseField.getText().isBlank() || phoneNumberField.getText().isBlank()
									|| emailField.getText().isBlank() || passwordField.getText().isBlank()) {
								AlertBox("Error", "Please complete all text fields!");
							} else if (notOnlyDigits(idOrIqamaField.getText())) {
								AlertBox("Error ", "Please enter only numbers in the ID or Iqama field");

							} else if (notOnlyDigits(drivingLicenseField.getText())) {
								AlertBox("Error ", "Please enter only numbers in the Driving License field");

							} else if (notOnlyDigits(phoneNumberField.getText())) {
								AlertBox("Error ", "Please enter only numbers in the Phone number field");

							} else {

								String date = year.getValue() + "/" + month.getValue() + "/" + day.getValue();

								Customer customer = new Customer(firstNameField.getText(),
										String.valueOf(ageCalculator(date)), emailField.getText(),
										idOrIqamaField.getText(), phoneNumberField.getText(), gender.getValue(),
										drivingLicenseField.getText(), passwordField.getText());
								LoggedInCustomer = customer;
								customerList.getCustomerlist().add(customer);

								Stage window = (Stage) ((Node) e6.getSource()).getScene().getWindow();
								window.setScene(bookCar());
								AlertBox("Account created ", "Account created successfully!");
								try {
									PrintWriter pwriter = new PrintWriter((new FileWriter("CustomerList")));
									for (int i = 0; i < customerList.getCustomerlist().size(); i++) {
										pwriter.println(customerList.getCustomerlist().get(i));
									}
									pwriter.close();
								} catch (IOException e7) {
									e7.printStackTrace();
								}
							}
						});
					});
				});
			});
		});

		return scene;

	}

	// Allows the customer to book a car.
	public Scene bookCar() {
		CarsInventory carsInventory = new CarsInventory();
		ReservationList reservationList = new ReservationList();

		ObservableList<Car> carslist2 = FXCollections.observableArrayList();
		for (int i = 0; i < carsInventory.getCarList().size(); i++) {
			if (carsInventory.getCarList().get(i).getStatus().equals("Available"))
				carslist2.add(carsInventory.getCarList().get(i));
		}

		JFXComboBox<String> JFXComboBox2 = new JFXComboBox<>();
		JFXComboBox2.getItems().addAll("cash", "creditCard");
		JFXComboBox2.setPromptText("payment method");

		JFXTextField dateText = new JFXTextField();
		dateText.setFont(Font.font("Times", 20));
		dateText.setMaxSize(150, 30);
		dateText.setMinSize(150, 30);
		dateText.setPromptText("enter the date");

		JFXButton confirm = new JFXButton("Confirm");

		JFXComboBox<Car> JFXComboBox = new JFXComboBox<>();
		JFXComboBox.setPromptText("Choose The Car");
		JFXComboBox.setItems(carslist2);

		JFXComboBox.setOnAction(e -> {
			JFXComboBox2.setOnAction(e3 -> {
				confirm.setOnAction(e1 -> {

					Reservation reservation = new Reservation(JFXComboBox.getValue(), LoggedInCustomer,
							dateText.getText(), JFXComboBox2.getValue());
					reservationList.getReservationList().add(reservation);
					Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
					window.setScene(customerMainMenu());
					JFXComboBox.getValue().setStatus("Reserved");
					try {
						PrintWriter pwriter = new PrintWriter((new FileWriter("cars")));
						for (int i = 0; i < carsInventory.getCarList().size(); i++) {
							pwriter.println(carsInventory.getCarList().get(i));
						}
						pwriter.close();
					} catch (IOException e4) {
						e4.printStackTrace();
					}
					AlertBox("You Booked", "Reservation is done");

					try {
						PrintWriter pwriter = new PrintWriter((new FileWriter("ReservationList")));

						for (int i = 0; i < reservationList.getReservationList().size(); i++) {

							pwriter.println(reservationList.getReservationList().get(i));
						}
						pwriter.close();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				});
			});

		});

		HBox hbox = new HBox(10);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(confirm, backToMainMenu());

		VBox vbox = new VBox(10);
		vbox.setAlignment(Pos.CENTER);
		vbox.getChildren().addAll(JFXComboBox, dateText, JFXComboBox2, hbox);

		StackPane stackpane = new StackPane();
		Scene scene = new Scene(stackpane);
		stackpane.getChildren().addAll(Background(scene), vbox, transition("customer"));

		return scene;
	}

	// Checks the entered input, returns false if it is only digits and true
	// otherwise.
	public boolean notOnlyDigits(String field) {

		if (field.matches("[0-9]+") && field.length() > 2) {
			return false;
		} else {
			return true;
		}
	}

	// Checks the age of the customer, if they are allowed to book a car.
	public int ageCalculator(String s) {
		try {

			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			java.util.Date d = sdf.parse(s);
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
	public void AlertBox(String title, String massege) {
		Stage window = new Stage();

		window.initModality(Modality.APPLICATION_MODAL);
		window.setTitle(title);
		window.setMinWidth(450);

		Label msg = new Label(massege);

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

	// Shows the logo of the company inside the interface.
	public ImageView Background(Scene scene) {
		Image background = new Image("menu.jpg");
		ImageView viewBackground = new ImageView(background);
		viewBackground.fitHeightProperty().bind(scene.heightProperty());
		viewBackground.fitWidthProperty().bind(scene.widthProperty());
		return viewBackground;
	}

	// Returns the user to the main menu.
	public JFXButton backToMainMenu() {

		JFXButton backToMainMenu = new JFXButton("back To Main Menu");
		backToMainMenu.setOnAction(e -> {
			Stage window = (Stage) ((Node) e.getSource()).getScene().getWindow();
			window.setScene(customerMainMenu());
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