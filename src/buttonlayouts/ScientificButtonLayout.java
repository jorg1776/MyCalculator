package buttonlayouts;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

public class ScientificButtonLayout
{
    private static VBox scientificButtons;
    
    private ScientificButtonLayout()
    {
        scientificButtons = new VBox();
        scientificButtons.setPadding(new Insets(0, 5, 0, 0));
        
        addDegreeTypeSelectionBox();
        addScientificButtons();
    }
    
    public static VBox getScientificButtonLayout()
    {
        new ScientificButtonLayout();
        return scientificButtons;
    }
    
    public void addDegreeTypeSelectionBox()
    {
        HBox degreeType = new HBox();
        degreeType.setStyle("-fx-padding: 5;" + 
                      "-fx-border-style: solid inside;" + 
                      "-fx-border-width: 1;" +
                      "-fx-border-color: LightGray;");
        degreeType.setPrefHeight(27);
        degreeType.getStylesheets().add(getClass().getResource("radioButton.css").toExternalForm());
        ToggleGroup degreeTypeToggleGroup = new ToggleGroup();
        
        RadioButton degrees = new RadioButton("Degrees");
        degrees.setToggleGroup(degreeTypeToggleGroup);
        degrees.setFont(Font.font(11));
        degrees.setPadding(new Insets(0, 5, 0, 0));
        degrees.setSelected(true);
        
        RadioButton radians = new RadioButton("Radians");
        radians.setToggleGroup(degreeTypeToggleGroup);
        radians.setFont(Font.font(11));
        radians.setPadding(new Insets(0, 5, 0, 0));
        
        RadioButton grads = new RadioButton("Grads");
        grads.setToggleGroup(degreeTypeToggleGroup);
        grads.setFont(Font.font(11));
        
        degreeType.getChildren().addAll(degrees, radians, grads);
        
        scientificButtons.getChildren().add(degreeType);
    }
    
    public void addScientificButtons()
    {
        GridPane buttons = new GridPane();
        buttons.setPadding(new Insets(5, 0, 0, 0));
        buttons.setHgap(5);
        buttons.setVgap(5);
        
        //---------------row 0-------------------
        addButton("", buttons, 0, 0);
        addButton("Inv", buttons, 1, 0);
        addButton("ln", buttons, 2, 0);
        addButton("(", buttons, 3, 0);
        addButton(")", buttons, 4, 0);
        
        //---------------row 1-------------------
        addButton("Int", buttons, 0, 1);
        addButton("sinh", buttons, 1, 1);
        addButton("sin", buttons, 2, 1);
        addButton("x\u00B2", buttons, 3, 1); //x squared
        addButton("n!", buttons, 4, 1);
        
        //---------------row 2-------------------
        addButton("dms", buttons, 0, 2);
        addButton("cosh", buttons, 1, 2);
        addButton("cos", buttons, 2, 2);
        addButton("x\u207F", buttons, 3, 2); //x to the nth power
        addButton("\u207F\u221Ax", buttons, 4, 2); //nth root
        
        //---------------row 3-------------------
        addButton("\u03c0", buttons, 0, 3); //pi
        addButton("tanh", buttons, 1, 3);
        addButton("tan", buttons, 2, 3);
        addButton("x\u00B3", buttons, 3, 3); //x cubed
        addButton("\u00B3\u221Ax", buttons, 4, 3); //cube root of x
        
        //---------------row 4-------------------
        addButton("F-E", buttons, 0, 4);
        addButton("Exp", buttons, 1, 4);
        addButton("Mod", buttons, 2, 4);
        addButton("log", buttons, 3, 4);
        addButton("10\u207F", buttons, 4, 4); //10 to the power of x
        
        scientificButtons.getChildren().add(buttons);
    }
    
    public void addButton(String text, GridPane buttons, int column, int row)
    {
        int standardButtonWidth = 35;
        int standardButtonHeight = 27;
        
        Button newButton = new Button(text);
        newButton.setPrefSize(standardButtonWidth, standardButtonHeight);
        newButton.setFont(Font.font(12));
        newButton.setFocusTraversable(false);
        newButton.setOnAction(buttonClick);
        
        switch(text)
        {
            case "":
                newButton.setDisable(true);
                break;
            case "cosh":
                newButton.setFont(Font.font(9.5));
                break;
            case "sinh":
            case "tanh":
            case "dms":
            case "Mod":
                newButton.setFont(Font.font(10));
                break;
            case "Exp":
                newButton.setFont(Font.font(11.5));
                break;
            default :
                newButton.setFont(Font.font(12));
                break;
        }
        
        buttons.add(newButton, column, row);
    }
    
    private EventHandler<ActionEvent> buttonClick = new EventHandler<ActionEvent>()
    {
        @Override
        public void handle(ActionEvent event)
        {
            Button buttonClicked = (Button)event.getSource();
            
            evaluateClick(buttonClicked.getText());
        }
    };
    
    private void evaluateClick(String buttonText)
    {
        switch(buttonText)
        {
            case "Inv":
                invertButtons();
                break;
            case "ln":
                break;
            case "(":
                break;
            case ")":
                break;
        }
    }
    
    private boolean inverted = false;
    private void invertButtons()
    {
        GridPane buttons = (GridPane)scientificButtons.getChildren().get(1);
        
        if(inverted == false)
        {
            changeText(buttons, 2, "e^x");
            changeText(buttons, 5, "e^x");
            changeText(buttons, 6, "e^x");
            changeText(buttons, 7, "e^x");
            changeText(buttons, 10, "e^x");
            changeText(buttons, 11, "e^x");
            changeText(buttons, 12, "e^x");
            changeText(buttons, 15, "e^x");
            changeText(buttons, 16, "e^x");
            changeText(buttons, 17, "e^x");
        }
        else
        {
            changeText(buttons, 2, "ln");
        }
        
        inverted = !inverted;
    }
    
    private void changeText(GridPane buttons, int index, String text)
    {
        ((Button)buttons.getChildren().get(index)).setText(text);
    }
}
