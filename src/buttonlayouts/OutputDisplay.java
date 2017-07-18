package buttonlayouts;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

public final class OutputDisplay
{
    private static StackPane outputDisplay;
            
    private static TextField output;
    private static Label equationDisplay;
    private static Label numberStoredToggle;
    
    private OutputDisplay(double windowWidth)
    {
        outputDisplay = new StackPane();
        
        output = new TextField("0");
        output.setPrefHeight(50);
        output.setMinHeight(50);
        output.setAlignment(Pos.BASELINE_RIGHT);
        output.setEditable(false);
        output.setStyle("-fx-display-caret: false;");
        output.setFocusTraversable(false);
        output.setFont(Font.font("", 20));

        equationDisplay = new Label();
        equationDisplay.setFont(Font.font(10));
        equationDisplay.setMaxWidth(windowWidth - 20);
        equationDisplay.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        equationDisplay.setTranslateY(-15);
        
        numberStoredToggle = new Label("");
        numberStoredToggle.setFont(Font.font(11));
        numberStoredToggle.setTranslateX(-windowWidth/2 + 14);
        numberStoredToggle.setTranslateY(15);
        
        outputDisplay.getChildren().addAll(output, equationDisplay, numberStoredToggle);
    }
    
    public final static StackPane getOutputDisplay(double windowWidth)
    {
        new OutputDisplay(windowWidth);
        return outputDisplay;
    }
    
    public final static void updateEquation(String addOn)
    {
        StringBuilder newEquation = new StringBuilder(equationDisplay.getText()).append(addOn);
        equationDisplay.setText(newEquation.toString());
    }
    
    public final static void clearEquation()
    {
        equationDisplay.setText("");
    }
    
    public final static String getEquation()
    {
        return equationDisplay.getText();
    }
    
    public final static boolean canFindPercentage() //for checking if percentage is applicable
    {
        String expression = getEquation();
        List<String> equation= new ArrayList<>(Arrays.asList(expression.split(" ")));
        
        return equation.contains("*") || equation.contains("/") || equation.contains("+") || equation.contains("-");
    }
    
    public final static void updateDisplay(String addOn, boolean decreaseSize)
    {
        if(decreaseSize == true)
        {
            output.setFont(Font.font(18));
        }
        else
        {
            output.setFont(Font.font(20));
        }
        
        output.setText(addOn);
    }
    
    public final static String getDisplay()
    {
        return output.getText();
    }
    
    public final static void clearDisplay()
    {
        output.setFont(Font.font(20));
        output.setText("0");
    }
        
    public final static void toggleStoredDisplay(boolean toggled)
    {        
        if(toggled == true)
        {
            numberStoredToggle.setText("M");
        }
        else
        {
            numberStoredToggle.setText("");
        }
    }
}