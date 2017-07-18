package buttonlayouts;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.ToggleGroup;
import viewtypes.ScientificView;
import viewtypes.StandardView;

public final class MenuDisplay
{
    private static MenuBar options;
    
    private MenuDisplay(String callerClass)
    {
        options = new MenuBar();
        
        Menu view = new Menu("View");
        ToggleGroup calculatorType = new ToggleGroup();
        addMenuItem("Standard", calculatorType, view, callerClass);
        addMenuItem("Scientific", calculatorType, view, callerClass);
        
        Menu help = new Menu("Help");
        ToggleGroup helpToggle = new ToggleGroup();
        addMenuItem("Help", helpToggle, help, callerClass);

        options.getMenus().addAll(view, help);
    }
    
    public final static MenuBar getMenuDisplay(String callerClass)
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
        return (ActionEvent event) ->
        {
            switch(item.getText())
            {
                case "Standard":
                    mycalculator.MyCalculator.changeView(new Scene(StandardView.getPane(), StandardView.windowWidth, StandardView.windowHeight));
                    break;
                case "Scientific":
                    mycalculator.MyCalculator.changeView(new Scene(ScientificView.getPane(), ScientificView.windowWidth, ScientificView.windowHeight));
                    break;
                case "Help":
                    if(Desktop.isDesktopSupported())
                    {
                        try
                        {
                            Desktop.getDesktop().browse(new URI("http://www.digitalcitizen.life/windows-calculator-tool-geek-you"));
                        } catch (URISyntaxException | IOException ex)
                        {
                            Logger.getLogger(MenuDisplay.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        item.setSelected(false);
                    }
                    break;
                default:
                    System.out.println("Something didn't get toggled properly");
            }
        };
    }
}