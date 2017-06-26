/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mycalculator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 *
 * @author gruenewaldjo
 */
public class CalculatorPane
{
    private static BorderPane windowLayout;
    
    public CalculatorPane()
    {
        windowLayout = new BorderPane();
        
        VBox topDisplay = new VBox();
        topDisplay.getChildren().add(createMenu());
        
        TextField output = new TextField();
        topDisplay.getChildren().add(output);
        
        windowLayout.setTop(topDisplay);
        
        createButtons();
    }
    
    public static BorderPane getPane() 
    { 
        new CalculatorPane();
        return windowLayout; 
    }
    
    public MenuBar createMenu()
    {
        MenuBar options = new MenuBar();
        
        Menu view = new Menu("View");
        RadioMenuItem standardView = new RadioMenuItem("Standard");
        standardView.setSelected(true);
        view.getItems().add(standardView);
        options.getMenus().add(view);
        
        return options;
    }
    
    public void createButtons()
    {
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>()
//        {
//            
//            @Override
//            public void handle(ActionEvent event)
//            {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        windowLayout.setCenter(btn);
    }
}
