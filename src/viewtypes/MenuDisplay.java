/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewtypes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;

/**
 *
 * @author gruenewaldjo
 */
public class MenuDisplay
{
    private static MenuBar options;
    
    private MenuDisplay(String callerClass)
    {
        options = new MenuBar();
        
        Menu view = new Menu("View");
        ToggleGroup calculatorType = new ToggleGroup();
        addMenuItem("Standard", calculatorType, view, callerClass);
        addMenuItem("Scientific", calculatorType, view, callerClass);

        options.getMenus().add(view);
    }
    
    public static MenuBar getMenuDisplay(String callerClass)
    {
        new MenuDisplay(callerClass);
        return options;
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
    
    private EventHandler<ActionEvent> toggleView(ToggleGroup menuToggleGroup, RadioMenuItem item)
    {
        return new EventHandler<ActionEvent>()
        {
            @Override
            public void handle(ActionEvent event)
            {
                switch(item.getText())
                {
                    case "Standard": 
                        StandardView.windowWidth = 200;
                        StandardView.windowHeight = 275;
                        mycalculator.MyCalculator.changeView(new Scene(StandardView.getPane(), StandardView.windowWidth, StandardView.windowHeight));
                        break;
                    case "Scientific": 
                        ScientificView.windowWidth = 300;
                        ScientificView.windowHeight = 275;
                        mycalculator.MyCalculator.changeView(new Scene(ScientificView.getPane(), ScientificView.windowWidth, ScientificView.windowHeight));
                        break;
                    default: 
                        System.out.println("Something didn't get toggled properly");
                }
            }
        };
    }
}