package mycalculator;

import viewtypes.StandardView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public final class MyCalculator extends Application
{
    private static Stage calculatorView;
    private static double windowWidth;
    
    @Override
    public final void start(Stage primaryStage)
    {
        calculatorView = primaryStage;
        calculatorView.setResizable(false);
        calculatorView.setTitle("Calculator");
        calculatorView.setScene(new Scene(StandardView.getPane(), 200, 275));
        calculatorView.show();
    }

    public final static void changeView(Scene view)
    {
        calculatorView.setResizable(true);
        calculatorView.setScene(view);
        calculatorView.setResizable(false);
    }
    
    public final static void main(String[] args)
    {
        launch(args);
    }
}
