package mycalculator;

import viewtypes.StandardView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author gruenewaldjo
 */
public class MyCalculator extends Application
{
    private static Stage calculatorView;
    
    @Override
    public void start(Stage primaryStage)
    {
        calculatorView = primaryStage;
        calculatorView.setResizable(false);
        calculatorView.setTitle("Calculator");
        calculatorView.setScene(new Scene(StandardView.getPane(), 200, 275));
        calculatorView.show();
    }

    public static void changeView(Scene view)
    {
        calculatorView.setResizable(true);
        calculatorView.setScene(view);
        calculatorView.setResizable(false);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        launch(args);
    }
}
