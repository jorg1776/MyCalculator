package viewtypes;

import buttonlayouts.OutputDisplay;
import buttonlayouts.MenuDisplay;
import buttonlayouts.ScientificButtonLayout;
import buttonlayouts.StandardButtonLayout;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ScientificView
{
    private static BorderPane windowLayout;
    private String callerClass = "Scientific";
    public static double windowWidth = 400;
    public static double windowHeight = 275;
    
    private ScientificView()
    {
        windowLayout = new BorderPane();

        windowLayout.setTop(MenuDisplay.getMenuDisplay(callerClass));

        VBox centerVBox = new VBox();
        centerVBox.setPadding(new Insets(10,10,10,10));
        centerVBox.setSpacing(5);
        
        centerVBox.getChildren().add(OutputDisplay.getOutputDisplay(windowWidth));

        HBox buttonArea = new HBox();
        buttonArea.getChildren().addAll(ScientificButtonLayout.getScientificButtonLayout(), 
                                        StandardButtonLayout.getStandardButtonLayout("Scientific"));
        
        centerVBox.getChildren().add(buttonArea);
        windowLayout.setCenter(centerVBox);
    }
    
    public static BorderPane getPane()
    { 
        new ScientificView();
        return windowLayout;
    }
}
