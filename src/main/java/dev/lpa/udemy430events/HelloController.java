package dev.lpa.udemy430events;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class HelloController {

  @FXML
  private TextField nameField;

  @FXML
  protected void onHelloButtonClick() {
    System.out.println("Hello, " + nameField.getText());
//    nameField.setText("Welcome to JavaFX Application!");
  }

  public void onEnterEmptyTextField() {
    if (nameField.getText().equalsIgnoreCase("Enter name")) {
      nameField.setText("");
    }
  }
}