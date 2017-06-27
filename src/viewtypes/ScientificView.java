/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewtypes;

import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author gruenewaldjo
 */
public class ScientificView
{
    private static BorderPane windowLayout;
    private String callerClass = "Scientific";
    public static double windowWidth = 300;
    public static double windowHeight = 275;
    
    private ScientificView()
    {
        windowLayout = new BorderPane();

        windowLayout.setTop(MenuDisplay.getMenuDisplay(callerClass));

        VBox centerVBox = new VBox();
        centerVBox.setPadding(new Insets(10,10,10,10));
        centerVBox.setSpacing(5);
        
        centerVBox.getChildren().add(OutputDisplay.getOutputDisplay(windowWidth));

        windowLayout.setCenter(centerVBox);
    }
    
    public static BorderPane getPane()
    { 
        new ScientificView();
        return windowLayout;
    }
}
