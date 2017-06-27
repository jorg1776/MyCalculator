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
        
        outputDisplay.getChildren().addAll(output, equationDisplay);
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
    
    public static String getEquation()
    {
        return equationDisplay.getText();
    }
    
    public static void updateDisplay(String addOn)
    {
        StringBuilder newNumber;
        
        if(output.getText().equals("0"))
        {
            newNumber = new StringBuilder();
        }
        else 
        {
            newNumber = new StringBuilder(output.getText());
        }
        
        switch(addOn)
        {
                case "1":
                case "2":
                case "3":
                case "4":
                case "5":
                case "6":
                case "7":
                case "8":
                case "9":
                case "0":
                case ".":
                    if(newNumber.length() < 17)
                    {
                        updateEquation(addOn);
                    }
                    break;
                case "+":
                    output.setText("0");
                    updateEquation(" " + addOn + " ");
                    return;
        }
        
        if(newNumber.length() > 12)
        {
            output.setFont(Font.font(17));
        }
        else
        {
            output.setFont(Font.font(20));
        }
        
        if(newNumber.length() < 17)
        {
            newNumber.append(addOn);
        }
        
        output.setText(newNumber.toString());
    }
}
