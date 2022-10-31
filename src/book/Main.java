package book;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.IOException;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    Connection connection = null;
    Statement statement = null;
    ResultSet resultSet = null;
    String addMsg = "", searchMsg = "", removeMsg = "", updateMsg = "";
    boolean checkId = false, checkName = false, checkPhone = false, checkAddress = false, checkBookID = false, checkBookTitle = false, searchBool = false;

    static TextArea txtOutput = new TextArea();

    public static void main(String[] args) {
        launch(args);

    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        } catch (ClassNotFoundException cnfex) {
            System.out.println("#### ucanaccess error");
        }
        try {
		//you file location
            //String msAccDB = "C:\\Users\\Your Name\\Documents\\NetBeansProjects\\Book\\src\\book\\dbBook.accdb";
            String dbURL = "jdbc:ucanaccess://" + msAccDB;
            connection = DriverManager.getConnection(dbURL);
            System.out.print("Database connected");
        } catch (SQLException sqlex) {
            System.out.println("###");
            sqlex.printStackTrace();
        }

        Label lbType = new Label("Customer Type:");
        Label lbID = new Label("Customer ID:");
        Label lbName = new Label("Customer Name:");
        Label lbAddress = new Label("Customer Address:");
        Label lbPhone = new Label("Customer Phone(+60):");
        Label lbBookID = new Label("Book ID:");
        Label lbBookTitle = new Label("Book Title:");
        Label lbTypeError = new Label();
        Label lbIdError = new Label();
        Label lbNameError = new Label();
        Label lbPhoneError = new Label();
        Label lbAddressError = new Label();
        Label lbBookIDError = new Label();
        Label lbBookTitleError = new Label();

        ToggleGroup typeGroup = new ToggleGroup();

        RadioButton memberRB = new RadioButton("Member");
        RadioButton NonMemberRB = new RadioButton("Non-member");

        TextField tfID = new TextField();
        TextField tfName = new TextField();
        TextField tfAddress = new TextField();
        TextField tfPhone = new TextField();
        TextField tfBookID = new TextField();
        TextField tfBookTitle = new TextField();

        Button btnAdd = new Button("Add");
        Button btnUpdate = new Button("Update");
        Button btnRemove = new Button("Remove");
        Button btnSearch = new Button("Search");
        Button btnPrint = new Button("Print");

        FlowPane btnBox = new FlowPane();
        btnBox.setHgap(50);
        btnBox.setAlignment(Pos.CENTER);
        btnBox.getChildren().addAll(btnAdd, btnUpdate, btnRemove, btnSearch, btnPrint);
        btnBox.setPadding(new Insets(0, 0, 100, 0));

        txtOutput.setEditable(false);

        GridPane pane = new GridPane();
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(15);
        pane.setVgap(15);
        pane.add(lbType, 0, 0);
        pane.add(memberRB, 1, 0);
        pane.add(NonMemberRB, 2, 0);
        pane.add(lbTypeError, 1, 1);
        pane.add(lbID, 0, 2);
        pane.add(tfID, 1, 2);
        pane.add(lbIdError, 1, 3);
        pane.add(lbName, 0, 4);
        pane.add(tfName, 1, 4);
        pane.add(lbNameError, 1, 5);
        pane.add(lbPhone, 0, 6);
        pane.add(tfPhone, 1, 6);
        pane.add(lbPhoneError, 1, 7);
        pane.add(lbAddress, 0, 8);
        pane.add(tfAddress, 1, 8);
        pane.add(lbAddressError, 1, 9);
        pane.add(lbBookID, 0, 10);
        pane.add(tfBookID, 1, 10);
        pane.add(lbBookIDError, 1, 11);
        pane.add(lbBookTitle, 0, 12);
        pane.add(tfBookTitle, 1, 12);
        pane.add(lbBookTitleError, 1, 13);

        memberRB.setToggleGroup(typeGroup);
        NonMemberRB.setToggleGroup(typeGroup);

        lbTypeError.setTextFill(Color.RED);
        lbIdError.setTextFill(Color.RED);
        lbNameError.setTextFill(Color.RED);
        lbPhoneError.setTextFill(Color.RED);
        lbAddressError.setTextFill(Color.RED);
        lbBookIDError.setTextFill(Color.RED);
        lbBookTitleError.setTextFill(Color.RED);

        tfID.setDisable(true);
        tfName.setDisable(true);
        tfPhone.setDisable(true);
        tfAddress.setDisable(true);
        tfBookID.setDisable(true);
        tfBookTitle.setDisable(true);

        tfID.textProperty().addListener((e) -> {

            if (!tfID.getText().matches("\\d*")) {
                lbIdError.setText("Please enter digits");
                checkId = false;

            } else if (tfID.getText().startsWith("0")) {
                lbIdError.setText("Cannot put 0 as first one");
                checkId = false;

            } else if (tfID.getText().length() > 10) {
                tfID.setText(tfID.getText(0, 10));
                lbIdError.setText("");
                checkId = true;

            } else if (tfID.getText().length() < 10 & tfID.getText().length() > 1) {
                lbIdError.setText("ID cannot less than 10 digits");
                checkId = false;

            } else {
                checkId = true;
                lbIdError.setText("");

            }

        });

        tfName.textProperty().addListener((e) -> {

            if (tfName.getText().length() > 0) {
                lbNameError.setText("");
            }

        });

        tfPhone.textProperty().addListener((e) -> {

            if (!tfPhone.getText().matches("\\d*")) {
                lbPhoneError.setText("Please enter digits");
                checkPhone = false;

            } else if (tfPhone.getText().startsWith("0")) {
                lbPhoneError.setText("Cannot put 0 as first one");
                checkPhone = false;

            } else if (tfPhone.getText().length() > 9) {
                tfPhone.setText(tfPhone.getText(0, 9));
                lbPhoneError.setText("");
                checkPhone = true;

            } else if (tfPhone.getText().length() < 9 & tfPhone.getText().length() > 1) {
                lbPhoneError.setText("Phone number cannot less than 9 digits");
                checkPhone = false;

            } else {
                lbPhoneError.setText("");
                checkPhone = true;

            }
        });

        tfAddress.textProperty().addListener((e) -> {
            if (tfAddress.getText().length() > 0) {
                lbAddressError.setText("");

            }

        });

        tfBookID.textProperty().addListener((e) -> {

            if (!tfBookID.getText().matches("\\d*")) {
                lbBookIDError.setText("Please enter digits");
                checkBookID = false;

            } else if (tfBookID.getText().startsWith("0")) {
                lbBookIDError.setText("Cannot put 0 as first one");
                checkBookID = false;

            } else {
                lbBookIDError.setText("");
                checkBookID = true;

            }

        });

        tfBookTitle.textProperty().addListener((e) -> {
            if (tfBookTitle.getText().length() > 0) {
                lbBookTitleError.setText("");

            }

        });

        memberRB.setOnAction((t) -> {

            tfID.setDisable(false);
            tfName.setDisable(false);
            tfPhone.setDisable(false);
            tfAddress.setDisable(false);
            tfBookID.setDisable(false);
            tfBookTitle.setDisable(false);
            lbTypeError.setText("");
            txtOutput.clear();

        });

        NonMemberRB.setOnAction((t) -> {

            tfID.setDisable(true);
            tfName.setDisable(true);
            tfPhone.setDisable(false);
            tfAddress.setDisable(false);
            tfBookID.setDisable(false);
            tfBookTitle.setDisable(false);
            tfID.clear();
            tfName.clear();
            lbTypeError.setText("");
            lbIdError.setText("");
            lbNameError.setText("");
            txtOutput.clear();

        });

        btnAdd.setOnAction((t) -> {
            String type = "", name = "", address = "", bookTitle = "";
            int bookID = 0;
            long id = 0, phoneNumber = 0;
            boolean checkType = false;

            if (memberRB.isSelected()) {
                checkType = true;

            } else if (NonMemberRB.isSelected()) {
                checkType = true;
                checkId = true;
                checkName = true;

            } else {
                lbTypeError.setText("Please select your customer type");
                checkType = false;
            }

            if (memberRB.isSelected()) {

                if (tfID.getText().isEmpty()) {
                    lbIdError.setText("Please enter ID");
                    checkId = false;

                } else {
                    lbIdError.setText("");
                    checkId = true;

                }

                if (tfName.getText().isEmpty()) {
                    lbNameError.setText("Please enter name");
                    checkName = false;

                } else {
                    lbNameError.setText("");
                    checkName = true;

                }

                if (tfPhone.getText().isEmpty()) {
                    lbPhoneError.setText("Please enter phone number");
                    checkPhone = false;

                } else {
                    lbPhoneError.setText("");
                    checkPhone = true;

                }

                if (tfAddress.getText().isEmpty()) {
                    lbAddressError.setText("Please enter address");
                    checkAddress = false;

                } else {
                    lbAddressError.setText("");
                    checkAddress = true;

                }

                if (tfBookID.getText().isEmpty()) {
                    lbBookIDError.setText("Please enter book ID");
                    checkBookID = false;

                } else {
                    lbBookIDError.setText("");
                    checkBookID = true;

                }

                if (tfBookTitle.getText().isEmpty()) {
                    lbBookTitleError.setText("Please enter book title");
                    checkBookTitle = false;

                } else {
                    lbBookTitleError.setText("");
                    checkBookTitle = true;

                }

                if (!checkType | !checkId | !checkName | !checkPhone | !checkAddress | !checkBookID | !checkBookTitle) {
                    txtOutput.setText("There are something wrong!" + "\n" + "Please check again Input.");

                } else if (memberRB.isSelected()) {
                    type = "member";

                    //add the customer
                    id = Long.parseLong(tfID.getText());
                    name = tfName.getText();
                    phoneNumber = Long.parseLong(tfPhone.getText());
                    address = tfAddress.getText();
                    bookID = Integer.parseInt(tfBookID.getText());
                    bookTitle = tfBookTitle.getText();

                    search(id, name, type, phoneNumber, address, bookID, bookTitle);

                    if (!searchBool) {
                        Member person = new Member(id, name, address, phoneNumber, bookID, bookTitle);
                        addMember(person, type);

                        txtOutput.setText(addMsg);

                        memberRB.setSelected(false);
                        lbTypeError.setText("");
                        lbIdError.setText("");
                        lbNameError.setText("");
                        lbPhoneError.setText("");
                        lbAddressError.setText("");
                        lbBookIDError.setText("");
                        lbBookTitleError.setText("");
                        tfID.clear();
                        tfName.clear();
                        tfPhone.clear();
                        tfAddress.clear();
                        tfBookID.clear();
                        tfBookTitle.clear();
                        tfID.setDisable(true);
                        tfName.setDisable(true);
                        tfPhone.setDisable(true);
                        tfAddress.setDisable(true);
                        tfBookID.setDisable(true);
                        tfBookTitle.setDisable(true);

                    } else {

                        txtOutput.setText(addMsg);

                        memberRB.setSelected(false);
                        lbTypeError.setText("");
                        lbIdError.setText("");
                        lbNameError.setText("");
                        lbPhoneError.setText("");
                        lbAddressError.setText("");
                        lbBookIDError.setText("");
                        lbBookTitleError.setText("");
                        tfID.clear();
                        tfName.clear();
                        tfPhone.clear();
                        tfAddress.clear();
                        tfBookID.clear();
                        tfBookTitle.clear();
                        tfID.setDisable(true);
                        tfName.setDisable(true);
                        tfPhone.setDisable(true);
                        tfAddress.setDisable(true);
                        tfBookID.setDisable(true);
                        tfBookTitle.setDisable(true);

                    }

                }

            } else if (NonMemberRB.isSelected()) {

                if (tfPhone.getText().isEmpty()) {
                    lbPhoneError.setText("Please enter phone number");
                    checkPhone = false;

                } else {
                    lbPhoneError.setText("");
                    checkPhone = true;

                }

                if (tfAddress.getText().isEmpty()) {
                    lbAddressError.setText("Please enter address");
                    checkAddress = false;

                } else {
                    lbAddressError.setText("");
                    checkAddress = true;

                }

                if (tfBookID.getText().isEmpty()) {
                    lbBookIDError.setText("Please enter book ID");
                    checkBookID = false;

                } else {
                    lbBookIDError.setText("");
                    checkBookID = true;

                }

                if (tfBookTitle.getText().isEmpty()) {
                    lbBookTitleError.setText("Please enter book title");
                    checkBookTitle = false;

                } else {
                    lbBookTitleError.setText("");
                    checkBookTitle = true;

                }

                if (!checkType | !checkId | !checkName | !checkPhone | !checkAddress | !checkBookID | !checkBookTitle) {
                    txtOutput.setText("There are something wrong!" + "\n" + "Please check again Input.");

                } else {
                    type = "NonMember";

                    search(id, name, type, phoneNumber, address, bookID, bookTitle);

                    if (!searchBool) {
                        //add the customer
                        phoneNumber = Long.parseLong(tfPhone.getText());
                        address = tfAddress.getText();
                        bookID = Integer.parseInt(tfBookID.getText());
                        bookTitle = tfBookTitle.getText();

                        NonMember person = new NonMember(phoneNumber, address, bookID, bookTitle);
                        addNonMember(person, type);

                        txtOutput.setText(addMsg);

                        NonMemberRB.setSelected(false);
                        lbTypeError.setText("");
                        lbIdError.setText("");
                        lbNameError.setText("");
                        lbPhoneError.setText("");
                        lbAddressError.setText("");
                        lbBookIDError.setText("");
                        lbBookTitleError.setText("");
                        tfPhone.clear();
                        tfAddress.clear();
                        tfBookID.clear();
                        tfBookTitle.clear();
                        tfPhone.setDisable(true);
                        tfAddress.setDisable(true);
                        tfBookID.setDisable(true);
                        tfBookTitle.setDisable(true);

                    } else {
                        txtOutput.setText("Customer which is Non-member exists in system and create unsuccesfully." + "\n"
                                + "Please try again.");

                        NonMemberRB.setSelected(false);
                        lbTypeError.setText("");
                        lbIdError.setText("");
                        lbNameError.setText("");
                        lbPhoneError.setText("");
                        lbAddressError.setText("");
                        lbBookIDError.setText("");
                        lbBookTitleError.setText("");
                        tfPhone.clear();
                        tfAddress.clear();
                        tfBookID.clear();
                        tfBookTitle.clear();
                        tfPhone.setDisable(true);
                        tfAddress.setDisable(true);
                        tfBookID.setDisable(true);
                        tfBookTitle.setDisable(true);

                    }

                }

            }

        });

        btnSearch.setOnAction((e) -> {
            String type = "", name = "", address = "", bookTitle = "";
            int bookID = 0;
            long id = 0, phoneNumber = 0;
            boolean checkType = false;

            if (memberRB.isSelected()) {
                checkType = true;

            } else if (NonMemberRB.isSelected()) {
                checkType = true;
                checkId = true;
                checkName = true;

            } else {
                lbTypeError.setText("Please select your customer type");
                checkType = false;
            }

            if (memberRB.isSelected()) {

                if (tfID.getText().isEmpty()) {
                    lbIdError.setText("Please enter ID");
                    checkId = false;

                } else {
                    lbIdError.setText("");
                    checkId = true;

                }

                if (!checkType || !checkId) {
                    txtOutput.setText("There are something wrong!" + "\n" + "Please check again Input.");

                } else {
                    txtOutput.clear();

                    type = "member";

                    //search the customer
                    id = Long.parseLong(tfID.getText());
                    

                    search(id, name, type, phoneNumber, address, bookID, bookTitle);
                    System.out.println("Search: " + searchBool);

                    txtOutput.setText(searchMsg);

                    memberRB.setSelected(false);
                    lbTypeError.setText("");
                    lbIdError.setText("");
                    lbNameError.setText("");
                    lbPhoneError.setText("");
                    lbAddressError.setText("");
                    lbBookIDError.setText("");
                    lbBookTitleError.setText("");
                    tfID.clear();
                    tfName.clear();
                    tfPhone.clear();
                    tfAddress.clear();
                    tfBookID.clear();
                    tfBookTitle.clear();
                    tfID.setDisable(true);
                    tfName.setDisable(true);
                    tfPhone.setDisable(true);
                    tfAddress.setDisable(true);
                    tfBookID.setDisable(true);
                    tfBookTitle.setDisable(true);

                }

            } else if (NonMemberRB.isSelected()) {

                if (tfPhone.getText().isEmpty()) {
                    lbPhoneError.setText("Please enter phone number");
                    checkPhone = false;

                } else {
                    lbPhoneError.setText("");
                    checkPhone = true;

                }

             
                if (!checkType || !checkPhone) {
                    txtOutput.setText("There are something wrong!" + "\n" + "Please check again Input.");

                } else {
                    txtOutput.clear();

                    type = "NonMember";

                    //search the customer
                    phoneNumber = Long.parseLong(tfPhone.getText());
                   

                    search(id, name, type, phoneNumber, address, bookID, bookTitle);

                    txtOutput.setText(searchMsg);

                    NonMemberRB.setSelected(false);
                    lbTypeError.setText("");
                    lbIdError.setText("");
                    lbNameError.setText("");
                    lbPhoneError.setText("");
                    lbAddressError.setText("");
                    lbBookIDError.setText("");
                    lbBookTitleError.setText("");
                    tfPhone.clear();
                    tfAddress.clear();
                    tfBookID.clear();
                    tfBookTitle.clear();
                    tfPhone.setDisable(true);
                    tfAddress.setDisable(true);
                    tfBookID.setDisable(true);
                    tfBookTitle.setDisable(true);

                }

            }

        });

        btnPrint.setOnAction((e) -> {

            memberRB.setSelected(false);
            NonMemberRB.setSelected(false);
            lbTypeError.setText("");
            lbIdError.setText("");
            lbNameError.setText("");
            lbPhoneError.setText("");
            lbAddressError.setText("");
            lbBookIDError.setText("");
            lbBookTitleError.setText("");
            tfID.clear();
            tfName.clear();
            tfPhone.clear();
            tfAddress.clear();
            tfBookID.clear();
            tfBookTitle.clear();
            tfID.setDisable(true);
            tfName.setDisable(true);
            tfPhone.setDisable(true);
            tfAddress.setDisable(true);
            tfBookID.setDisable(true);
            tfBookTitle.setDisable(true);

            displayBook();

        });
        btnRemove.setOnAction((e) -> {
            String type = "", name = "", address = "", bookTitle = "";
            int bookID = 0;
            long id = 0, phoneNumber = 0;
            boolean checkType = false;

            if (memberRB.isSelected()) {
                checkType = true;

            } else if (NonMemberRB.isSelected()) {
                checkType = true;
                checkId = true;
                checkName = true;

            } else {
                lbTypeError.setText("Please select your customer type");
                checkType = false;
            }

            if (memberRB.isSelected()) {

                if (tfID.getText().isEmpty()) {
                    lbIdError.setText("Please enter ID");
                    checkId = false;

                } else {
                    lbIdError.setText("");
                    checkId = true;

                }
                if (!checkType || !checkId) {
                    txtOutput.setText("There are something wrong!" + "\n" + "Please check again Input.");
                } else {
                    txtOutput.clear();

                    type = "member";

                    id = Long.parseLong(tfID.getText());
                    name = "";
                    phoneNumber = 0;
                    address = "";
                    bookID = 0;
                    bookTitle = "";
                        //remove the customer
                        removeBook(id, name, type, phoneNumber, address, bookID, bookTitle);
                        txtOutput.setText(removeMsg);
                        memberRB.setSelected(false);
                        lbTypeError.setText("");
                        lbIdError.setText("");
                        lbNameError.setText("");
                        lbPhoneError.setText("");
                        lbAddressError.setText("");
                        lbBookIDError.setText("");
                        lbBookTitleError.setText("");
                        tfID.clear();
                        tfName.clear();
                        tfPhone.clear();
                        tfAddress.clear();
                        tfBookID.clear();
                        tfBookTitle.clear();
                        tfID.setDisable(true);
                        tfName.setDisable(true);
                        tfPhone.setDisable(true);
                        tfAddress.setDisable(true);
                        tfBookID.setDisable(true);
                        tfBookTitle.setDisable(true);
                }
            } else if (NonMemberRB.isSelected()) {

                if (tfPhone.getText().isEmpty()) {
                    lbPhoneError.setText("Please enter phone number");
                    checkPhone = false;

                } else {
                    lbPhoneError.setText("");
                    checkPhone = true;

                }
                if (!checkType || !checkPhone) {
                    txtOutput.setText("There are something wrong!" + "\n" + "Please check again Input.");

                } else {
                    txtOutput.clear();

                    type = "NonMember";

                    //search the customer
                    phoneNumber = Long.parseLong(tfPhone.getText());
                    address = tfAddress.getText();
                    bookID = 0;
                    bookTitle = "";

                        removeBook(id, name, type, phoneNumber, address, bookID, bookTitle);
                        txtOutput.setText(removeMsg);
                        memberRB.setSelected(false);
                        lbTypeError.setText("");
                        lbIdError.setText("");
                        lbNameError.setText("");
                        lbPhoneError.setText("");
                        lbAddressError.setText("");
                        lbBookIDError.setText("");
                        lbBookTitleError.setText("");
                        tfID.clear();
                        tfName.clear();
                        tfPhone.clear();
                        tfAddress.clear();
                        tfBookID.clear();
                        tfBookTitle.clear();
                        tfID.setDisable(true);
                        tfName.setDisable(true);
                        tfPhone.setDisable(true);
                        tfAddress.setDisable(true);
                        tfBookID.setDisable(true);
                        tfBookTitle.setDisable(true);
                }

            }
        });

        btnUpdate.setOnAction((e) -> {
            String type = "", name = "", address = "", bookTitle = "";
            int bookID = 0;
            long id = 0, phoneNumber = 0;
            boolean checkType = false;

            if (memberRB.isSelected()) {
                checkType = true;

            } else if (NonMemberRB.isSelected()) {
                checkType = true;
                checkId = true;
                checkName = true;

            } else {
                lbTypeError.setText("Please select your customer type");
                checkType = false;
            }

            if (memberRB.isSelected()) {

                if (tfID.getText().isEmpty()) {
                    lbIdError.setText("Please enter ID");
                    checkId = false;

                } else {
                    lbIdError.setText("");
                    checkId = true;

                }

                if (tfName.getText().isEmpty()) {
                    lbNameError.setText("Please enter name");
                    checkName = false;

                } else {
                    lbNameError.setText("");
                    checkName = true;

                }

                if (tfPhone.getText().isEmpty()) {
                    lbPhoneError.setText("Please enter phone number");
                    checkPhone = false;

                } else {
                    lbPhoneError.setText("");
                    checkPhone = true;

                }

                if (tfAddress.getText().isEmpty()) {
                    lbAddressError.setText("Please enter address");
                    checkAddress = false;

                } else {
                    lbAddressError.setText("");
                    checkAddress = true;

                }
    

                if (!checkType || !checkId || !checkName || !checkPhone || !checkAddress) {
                    txtOutput.setText("There are something wrong!" + "\n" + "Please check again Input.");

                } else {
                    txtOutput.clear();

                    type = "member";

                    id = Long.parseLong(tfID.getText());
                    name = tfName.getText();
                    phoneNumber = Long.parseLong(tfPhone.getText());
                    address = tfAddress.getText();
                    bookID = 0;
                    bookTitle = "";
         
                        update(id, name, type, phoneNumber, address, bookID, bookTitle);
                        txtOutput.setText(updateMsg);
                        memberRB.setSelected(false);
                        lbTypeError.setText("");
                        lbIdError.setText("");
                        lbNameError.setText("");
                        lbPhoneError.setText("");
                        lbAddressError.setText("");
                        lbBookIDError.setText("");
                        lbBookTitleError.setText("");
                        tfID.clear();
                        tfName.clear();
                        tfPhone.clear();
                        tfAddress.clear();
                        tfBookID.clear();
                        tfBookTitle.clear();
                        tfID.setDisable(true);
                        tfName.setDisable(true);
                        tfPhone.setDisable(true);
                        tfAddress.setDisable(true);
                        tfBookID.setDisable(true);
                        tfBookTitle.setDisable(true);
                }
            } else if (NonMemberRB.isSelected()) {

                if (tfPhone.getText().isEmpty()) {
                    lbPhoneError.setText("Please enter phone number");
                    checkPhone = false;

                } else {
                    lbPhoneError.setText("");
                    checkPhone = true;

                }

                if (tfAddress.getText().isEmpty()) {
                    lbAddressError.setText("Please enter address");
                    checkAddress = false;

                } else {
                    lbAddressError.setText("");
                    checkAddress = true;

                }


                if (!checkType || !checkId || !checkName || !checkPhone || !checkAddress) {
                    txtOutput.setText("There are something wrong!" + "\n" + "Please check again Input.");

                } else {
                    txtOutput.clear();

                    type = "NonMember";

                    //search the customer
                    phoneNumber = Long.parseLong(tfPhone.getText());
                    address = tfAddress.getText();
                    bookID = 0;
                    bookTitle = "";

                        update(id, name, type, phoneNumber, address, bookID, bookTitle);
                        txtOutput.setText(updateMsg);
                        memberRB.setSelected(false);
                        lbTypeError.setText("");
                        lbIdError.setText("");
                        lbNameError.setText("");
                        lbPhoneError.setText("");
                        lbAddressError.setText("");
                        lbBookIDError.setText("");
                        lbBookTitleError.setText("");
                        tfID.clear();
                        tfName.clear();
                        tfPhone.clear();
                        tfAddress.clear();
                        tfBookID.clear();
                        tfBookTitle.clear();
                        tfID.setDisable(true);
                        tfName.setDisable(true);
                        tfPhone.setDisable(true);
                        tfAddress.setDisable(true);
                        tfBookID.setDisable(true);
                        tfBookTitle.setDisable(true);

                }

            }
        });

        BorderPane bp = new BorderPane();
        bp.setCenter(pane);
        bp.setBottom(btnBox);

        BorderPane bp2 = new BorderPane();
        bp2.setCenter(bp);
        bp2.setRight(txtOutput);

        Scene scene = new Scene(bp2);

        primaryStage.setWidth(1200);
        primaryStage.setHeight(900);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Book Store System");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void addMember(Member newCustomer, String type) {
        if (type.equalsIgnoreCase("Member")) {
            try {
                statement = connection.createStatement();
                statement.executeUpdate("Insert into tblMember(customerID, customerName, customerAddress, customerPhone, bookID, bookTitle, price, discount, total) "
                        + "values (" + newCustomer.getID() + ", '" + newCustomer.getName() + "', '" + newCustomer.getAddress() + "', " + newCustomer.getPhoneNumber() + ", " + newCustomer.getBookID() + ", '" + newCustomer.getBookTitle() + "', " + newCustomer.calcPrice() + ", " + newCustomer.calcDiscount() + ", " + newCustomer.calcTotal() + ")");
            } catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }
            addMsg = "Member create succefully." + "\n\n"
                    + "ID :" + newCustomer.getID() + "\n"
                    + "Name :" + newCustomer.getName();
        }
    }

    public void addNonMember(NonMember newCustomer, String type) {
        if (type.equalsIgnoreCase("NonMember")) {
            try {
                statement = connection.createStatement();
                statement.executeUpdate("Insert into tblNonMember(customerAddress, customerPhone, bookID, bookTitle, price, discount, total) "
                        + "values ('" + newCustomer.getAddress() + "', " + newCustomer.getPhoneNumber() + ", " + newCustomer.getBookID() + ", '" + newCustomer.getBookTitle() + "', " + newCustomer.calcPrice() + ", " + newCustomer.calcDiscount() + ", " + newCustomer.calcTotal() + ")");
            } catch (SQLException sqlex) {
                sqlex.printStackTrace();
            }

            addMsg = "Non-member create Succesfully";
        } else {
            addMsg = "Customer exists in system." + "\n"
                    + "Please create again";
        }
    }

    public void search(long id, String name, String type, long phoneNumber, String address, int bookID, String bookTitle) {
        searchBool = false;
        boolean flag = false;
        if (type.equalsIgnoreCase("member")) {
            Member m = new Member(id, name, address, phoneNumber, bookID, bookTitle);
            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM tblMember WHERE customerID = " + id + "");
                while (resultSet.next()) {
                    m.setMemberID((int) Long.parseLong(resultSet.getString("customerID")));
                    m.setMemberName(resultSet.getString("customerName"));
                    m.setAddress(resultSet.getString("customerAddress"));
                    m.setPhoneNumber((int) Long.parseLong(resultSet.getString("customerPhone")));
                    m.setBooID((int) Long.parseLong(resultSet.getString("bookID")));
                    m.setBookTitle(resultSet.getString("bookTitle"));
                    searchMsg = "Searching person ID and name which is " + id + " and "
                            + name + " found." + "\n\n"
                            + "ID :" + m.getMemberID() + "\n"
                            + "Name :" + m.getMemberName() + "\n"
                            + "Type : Member\n"
                            + "Address :" + m.getAddress() + "\n"
                            + "Phone Number :0" + m.getPhoneNumber() + "\n"
                            + "BookID :" + m.getBookID() + "\n"
                            + "BookTitle :" + m.getBookTitle() + "\n"
                            + "BookPrice :" + resultSet.getString("price") + "\n"
                            + "Discount :" + resultSet.getString("discount") + "\n"
                            + "Total Price :" + resultSet.getString("total");
                    flag = true;
                    searchBool = true;
                }
                if (flag == false) {
                    searchMsg = "There are not have any user in the system." + "\n" + "Please create user first";
                }
            } catch (SQLException ex) {
                ex.getMessage();
            }
        } else if (type.equalsIgnoreCase("NonMember")) {
            NonMember m = new NonMember(phoneNumber, address, bookID, bookTitle);
            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery("SELECT * FROM tblNonMember WHERE customerPhone = " + phoneNumber + "");
                while (resultSet.next()) {
                    m.setAddress(resultSet.getString("customerAddress"));
                    m.setPhoneNumber((int) Long.parseLong(resultSet.getString("customerPhone")));
                    m.setBooID((int) Long.parseLong(resultSet.getString("bookID")));
                    m.setBookTitle(resultSet.getString("bookTitle"));
                    searchMsg = "Searching non-member found." + "\n\n"
                            + "Type : Non-Member\n"
                            + "Address :" + m.getAddress() + "\n"
                            + "Phone Number :0" + m.getPhoneNumber() + "\n"
                            + "BookID :" + m.getBookID() + "\n"
                            + "BookTitle :" + m.getBookTitle() + "\n"
                            + "BookPrice :" + resultSet.getString("price") + "\n"
                            + "Discount :" + resultSet.getString("discount") + "\n"
                            + "Total Price :" + resultSet.getString("total");
                    flag = true;
                    searchBool = true;
                }
                if (flag == false) {
                    searchMsg = "There are not have any user in the system." + "\n" + "Please create user first";
                }
            } catch (SQLException ex) {
                ex.getMessage();
            }
        }
    }

    public void removeBook(long id, String name, String type, long phoneNumber, String address, int bookID, String bookTitle) {
        boolean flag = false;
        if (type.equalsIgnoreCase("member")) {
            try {
                statement = connection.createStatement();
                statement.executeUpdate("DELETE FROM tblMember WHERE customerID = " + id + "");
                removeMsg = "Remove of member user\n\n"
                        + "Remove Successfully" + "\n"
                        + "You may use print or search button to check it.";
                flag = true;
                if (flag == false) {
                    removeMsg = "There are not have any user in the system." + "\n";
                }
            } catch (SQLException ex) {
                ex.getMessage();
            }
        } else if (type.equalsIgnoreCase("NonMember")) {
            try {
                statement = connection.createStatement();
                statement.executeUpdate("DELETE FROM tblNonMember WHERE customerPhone = " + phoneNumber + "");
                removeMsg = "Remove of non-member\n\n"
                        + "Remove Successfully" + "\n"
                        + "You may use print or search button to check it.";
                flag = true;
                if (flag == false) {
                    removeMsg = "There are not have any user in the system." + "\n";
                }
            } catch (SQLException ex) {
                ex.getMessage();
            }
        }
    }

    public void displayBook() {
        txtOutput.setText("");
        boolean flag = true;
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM tblMember");
            txtOutput.appendText("//---Member---//" + "\n\n");
            while (resultSet.next()) {
                txtOutput.appendText("ID :" + resultSet.getString("customerID") + "\n"
                        + "Name :" + resultSet.getString("customerName") + "\n"
                        + "Address :" + resultSet.getString("customerAddress") + "\n"
                        + "Phone Number :0" + resultSet.getString("customerPhone") + "\n"
                        + "BookID :" + resultSet.getString("bookID") + "\n"
                        + "BookTitle :" + resultSet.getString("bookTitle") + "\n"
                        + "BookPrice :" + resultSet.getString("price") + "\n"
                        + "Discount :" + resultSet.getString("discount") + "\n"
                        + "Total Price :" + resultSet.getString("total") + "\n\n");
                flag = true;
            }
            if (flag == false) {
                txtOutput.appendText("There are not have any member in the system." + "\n" + "Please create user first");
            }
            flag = false;
            resultSet = statement.executeQuery("SELECT * FROM tblNonMember");
            txtOutput.appendText("\n\n//---Non Member---//" + "\n\n");
            while (resultSet.next()) {
                txtOutput.appendText("Address :" + resultSet.getString("customerAddress") + "\n"
                        + "Phone Number :0" + resultSet.getString("customerPhone") + "\n"
                        + "BookID :" + resultSet.getString("bookID") + "\n"
                        + "BookTitle :" + resultSet.getString("bookTitle") + "\n"
                        + "BookPrice :" + resultSet.getString("price") + "\n"
                        + "Discount :" + resultSet.getString("discount") + "\n"
                        + "Total Price :" + resultSet.getString("total") + "\n\n");
                flag = true;
            }
            if (flag == false) {
                txtOutput.appendText("There are not have any member in the system." + "\n" + "Please create user first");
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }

    public void update(long id, String name, String type, long phoneNumber, String address, int bookID, String bookTitle) {
        try {
            int count = 0;
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM tblMember WHERE customerID = " + id + "");
            while (resultSet.next()) {
                count++;
                searchBool = true;
            }
            if (count < 0) {
                updateMsg = "\"System dont have any member.\" + \"\\n\"\n" +
"                                + \"Update non-member unsuccessful.\" + \"\\n\"\n" +
"                                + \"Please create new member first.\"";
                
            } else {
                if (type.equalsIgnoreCase("member")) {
                    Member m = new Member(id, name, address, phoneNumber, bookID, bookTitle);
                    try {
                        m.setMemberID((int) (id));
                        m.setMemberName(name);
                        m.setAddress(address);
                        m.setPhoneNumber(phoneNumber);
                        statement = connection.createStatement();
                        statement.executeUpdate("UPDATE tblMember SET customerName = '" + m.getMemberName() + "', customerPhone = " + m.getPhoneNumber() + ", customerAddress='" + m.getAddress() + "' WHERE customerID = " + m.getMemberID());
                        updateMsg = "MemberID " + m.getMemberID() + " successfully updated\n\n ";
                    } catch (SQLException ex) {
                        ex.getMessage();
                    }
                } else if (type.equalsIgnoreCase("NonMember")) {
                    NonMember m = new NonMember(phoneNumber, address, bookID, bookTitle);
                    try {
                        m.setAddress(address);
                        m.setPhoneNumber(phoneNumber);
                        statement = connection.createStatement();
                        statement.executeUpdate("UPDATE tblNonMember SET customerAddress='" + m.getAddress() + "' WHERE customerPhone = " + m.getPhoneNumber());
                        updateMsg = "Non-member details successfully updated\n\n ";
                    } catch (SQLException ex) {
                        ex.getMessage();
                    }
                }
            }
        } catch (SQLException ex) {
            ex.getMessage();
        }

    }
}
