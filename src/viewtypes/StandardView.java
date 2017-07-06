package viewtypes;

import buttonlayouts.OutputDisplay;
import buttonlayouts.MenuDisplay;
import javafx.geometry.Insets;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class StandardView
{
    private static BorderPane windowLayout;
    private String callerClass = "Standard";
    public static double windowWidth = 200;
    public static double windowHeight = 275;
    
    private StandardView()
    {
        windowLayout = new BorderPane();

        windowLayout.setTop(MenuDisplay.getMenuDisplay(callerClass));

        VBox centerVBox = new VBox();
        centerVBox.setPadding(new Insets(10));
        centerVBox.setSpacing(5);

        centerVBox.getChildren().add(OutputDisplay.getOutputDisplay(windowWidth));
        centerVBox.getChildren().add(buttonlayouts.StandardButtonLayout.getStandardButtonLayout("Standard"));

        windowLayout.setCenter(centerVBox);
    }

    public static BorderPane getPane()
    { 
        new StandardView();
        return windowLayout;
    }
}
