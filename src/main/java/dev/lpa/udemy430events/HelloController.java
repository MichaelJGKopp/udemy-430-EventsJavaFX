package dev.lpa.udemy430events;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {

  @FXML
  private TextField nameField;
  @FXML
  private Button helloButton;
  @FXML
  private Button byeButton;
  @FXML
  private CheckBox ourCheckBox;
  @FXML
  private Label ourLabel;

  @FXML
  public void initialize() {
    helloButton.setDisable(true);
    byeButton.setDisable(true);
  }

  @FXML
  protected void onButtonClicked(ActionEvent e) {

    ourLabel.setText("Nothing has happened");

    if (e.getSource().equals(helloButton)) {
      System.out.println("Hello, " + nameField.getText());
    } else if (e.getSource().equals(byeButton)) {
      System.out.println("Bye, " + nameField.getText());
    }

    Runnable task = new Runnable() {
      @Override
      public void run() {
        // Simulate hanging Thread
        String currentThread = Platform.isFxApplicationThread() ? "UI Thread" : "Background Thread";
        System.out.println("I'm going to sleep on the: " + currentThread);
        for (int i = 10; i >= 0; i--) {
          try {
            int j = i;
            Platform.runLater(() -> ourLabel.setText( ". ".repeat(j)));
            Thread.sleep(1_000);

            if(i == 0) {
              Platform.runLater(() -> {
                String currentThread2 = Platform.isFxApplicationThread()
                  ? "UI Thread" : "Background Thread";
                System.out.println("I'm updating the label on the: " + currentThread2);
                ourLabel.setText("Background Thread done!\nBack to UI Thread");
              });
            }
          } catch(InterruptedException ex){
            // we don't care about this
          }
        }
      }
    };
    Thread.ofVirtual().start(task);

    if (ourCheckBox.isSelected()) {
      nameField.clear();
      helloButton.setDisable(true);
      byeButton.setDisable(true);
    }
  }

  @FXML
  public void handleKeyReleased() {
    String text = nameField.getText();
    boolean disableButtons = text.isEmpty() || text.trim().isEmpty();
    helloButton.setDisable(disableButtons);
    byeButton.setDisable(disableButtons);
  }

  public void handleChange() {
    System.out.println("The checkbox is " + (ourCheckBox.isSelected() ? "checked" : "not checked"));
  }
}