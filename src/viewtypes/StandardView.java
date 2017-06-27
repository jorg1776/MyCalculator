/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewtypes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

/**
 *
 * @author gruenewaldjo
 */
public class StandardView
{
    protected static BorderPane windowLayout;
    protected String callerClass = "Standard";
    
    protected StandardView()
    {
        windowLayout = new BorderPane();

        createMenu(callerClass);

        VBox centerVBox = new VBox();
        centerVBox.setPadding(new Insets(10,10,10,10));
        centerVBox.setSpacing(5);

        createOutputDisplay(centerVBox);
        createButtons(centerVBox);

        windowLayout.setCenter(centerVBox);
    }

    public static BorderPane getPane()
    { 
        new StandardView();
        return windowLayout;
    }
    
    protected void createMenu(String callerClass)
    {
        MenuBar options = new MenuBar();
        
        Menu view = new Menu("View");
        ToggleGroup calculatorType = new ToggleGroup();
        addMenuItem("Standard", calculatorType, view, callerClass);
        addMenuItem("Scientific", calculatorType, view, callerClass);

        options.getMenus().add(view);
        windowLayout.setTop(options);
    }
    
    private RadioMenuItem addMenuItem(String itemName, ToggleGroup menuToggleGroup, Menu menuOption, String itemToToggle)
    {
        RadioMenuItem item = new RadioMenuItem(itemName);
        item.setToggleGroup(menuToggleGroup);
        
        if(itemName.equals(itemToToggle)) { item.setSelected(true); }
        
        item.setOnAction(toggleView(menuToggleGroup, item));
        menuOption.getItems().add(item);
        
        return item;
    }
    
    protected EventHandler<ActionEvent> toggleView(ToggleGroup menuToggleGroup, RadioMenuItem item)
    {
        return new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                switch(item.getText())
                {
                    case "Standard": 
                        mycalculator.MyCalculator.changeView(new Scene(StandardView.getPane(), 200, 275));
                        break;
                    case "Scientific": 
                        mycalculator.MyCalculator.changeView(new Scene(ScientificView.getPane(), 300, 275));
                        break;
                    default: 
                        System.out.println("Something didn't get toggled properly");
                }
            }
        };
    }

    protected void createOutputDisplay(VBox centerVBox)
    {
        TextField output = new TextField("0");
        output.setPrefHeight(50);
        output.setMinHeight(50);
        output.setAlignment(Pos.CENTER_RIGHT);
        output.setEditable(false);
        output.setStyle("-fx-display-caret: false;");
        output.setFocusTraversable(false);
        output.setFont(Font.font("", 20));

        centerVBox.getChildren().add(output);
    }

    private void createButtons(VBox centerVBox)
    {
        centerVBox.getChildren().add(createStandardButtons());
    }
    
    protected VBox createStandardButtons()
    {
        VBox standardButtons = new VBox();
            
        //---------------useless row-------------------
        HBox uselessRow = new HBox();
        uselessRow.setPadding(new Insets(0, 0, 5, 0));
        uselessRow.setSpacing(5);
        
        addButton("MC", uselessRow);
        addButton("MR", uselessRow);
        addButton("MS", uselessRow);
        addButton("M+", uselessRow);
        addButton("M-", uselessRow);
        
        standardButtons.getChildren().add(uselessRow);
        
       //---------------row 1-------------------
        HBox row1 = new HBox();
        row1.setPadding(new Insets(0, 0, 5, 0));
        row1.setSpacing(5);
        
        addButton("<-", row1);
        addButton("CE", row1);
        addButton("C", row1);
        addButton("+-", row1);
        addButton("\u221A", row1);
        
        standardButtons.getChildren().add(row1);
        
        //---------------row 2-------------------
        HBox row2 = new HBox();
        row2.setPadding(new Insets(0, 0, 5, 0));
        row2.setSpacing(5);
        
        addButton("7", row2);
        addButton("8", row2);
        addButton("9", row2);
        addButton("/", row2);
        addButton("%", row2);
        
        standardButtons.getChildren().add(row2);
        
        //---------------row 3-------------------
        HBox row3 = new HBox();
        row3.setPadding(new Insets(0, 0, 5, 0));
        row3.setSpacing(5);
        
        addButton("4", row3);
        addButton("5", row3);
        addButton("6", row3);
        addButton("*", row3);
        addButton("1/x", row3);
        
        standardButtons.getChildren().add(row3);
        
        //---------------row 4-------------------
        HBox row4 = new HBox();
        row4.setPadding(new Insets(0, 0, -27, 0));
        row4.setSpacing(5);
        
        addButton("1", row4);
        addButton("2", row4);
        addButton("3", row4);
        addButton("-", row4);
        addButton("=", row4);
        
        standardButtons.getChildren().add(row4);
        
        //---------------row 5-------------------
        HBox row5 = new HBox();
        row5.setPadding(new Insets(0, 0, 0, 0));
        row5.setSpacing(5);
        
        addButton("0", row5);
        addButton(".", row5);
        addButton("+", row5);
        
        standardButtons.getChildren().add(row5);
        
        return standardButtons;
    }
    
    private void addButton(String text, Pane pane)
    {
        int standardButtonWidth = 35;
        int standardButtonHeight = 27;
        
        Button newButton = new Button(text);
        
        //modifies button size and text size
        switch(text)
        {
            case "MC":
            case "MR":
            case "MS":
            case "M+":
            case "M-":
                newButton.setFont(Font.font(11));
                newButton.setPrefSize(standardButtonWidth, standardButtonHeight);
                break;
            case "=":
                newButton.setPrefSize(35, 59);
                break;
            case "0":
                newButton.setPrefSize(72, 27);
                break;
            default:
                newButton.setPrefSize(standardButtonWidth, standardButtonHeight);
                break;
        }
        
        newButton.setFocusTraversable(false);
        pane.getChildren().add(newButton);
    }
}
