/**
 * This class is the main driver class of the application
 * @author John Juarez, Rudra Kakadia
 */
package application;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.fxml.FXMLLoader;


public class Main extends Application {
  @Override
  public void start(Stage primaryStage) {
    try {
      BorderPane root =
          (BorderPane) FXMLLoader.load(getClass().getResource("TransactionManagerFX.fxml"));
      Scene scene = new Scene(root, 600, 410);
      scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
      primaryStage.setTitle("Transaction Manager");
      primaryStage.setScene(scene);
      primaryStage.show();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    launch(args);
  }
}
