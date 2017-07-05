/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewtypes;

import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;

/**
 *
 * @author gruenewaldjo
 */
public class OutputDisplay
{
    private static StackPane outputDisplay;
            
    private static TextField output;
    private static Label equationDisplay;
    private static Label numberStoredToggle;
    
    public OutputDisplay(double windowWidth)
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
    
    public static StackPane getOutputDisplay(double windowWidth)
    {
        new OutputDisplay(windowWidth);
        return outputDisplay;
    }
    
    public static void updateEquation(String addOn)
    {
        StringBuilder newEquation = new StringBuilder(equationDisplay.getText()).append(addOn);
        equationDisplay.setText(newEquation.toString());
    }
    
    public static void clearEquation()
    {
        equationDisplay.setText("");
    }
    
    public static String getEquation()
    {
        return equationDisplay.getText();
    }
    
    public static void updateDisplay(String addOn, boolean decreaseSize)
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
    
    public static String getDisplay()
    {
        return output.getText();
    }
    
    public static void clearDisplay()
    {
        output.setFont(Font.font(20));
        output.setText("0");
    }
        
    public static void toggleStoredDisplay(boolean toggled)
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