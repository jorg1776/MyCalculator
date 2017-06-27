package mycalculator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author gruenewaldjo
 */
public class MyCalculator extends Application
{
    
    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setResizable(false);
        primaryStage.setTitle("Calculator");
        primaryStage.setScene(new Scene(CalculatorPane.getPane(), 220, 280));
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}
