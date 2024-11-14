module dev.lpa.udemy430events {
  requires javafx.controls;
  requires javafx.fxml;


  opens dev.lpa.udemy430events to javafx.fxml;
  exports dev.lpa.udemy430events;
}