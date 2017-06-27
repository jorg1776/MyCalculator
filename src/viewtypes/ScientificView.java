/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewtypes;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

/**
 *
 * @author gruenewaldjo
 */
public class ScientificView extends StandardView
{
    private String callerClass = "Scientific";
    
    private ScientificView()
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
        new ScientificView();
        return windowLayout;
    }
    
    private void createButtons(VBox centerVBox)
    {
        centerVBox.getChildren().add(new Button("Scientific"));
    }
}
