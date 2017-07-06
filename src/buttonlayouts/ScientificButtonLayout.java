package buttonlayouts;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
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
        //---------------row 1-------------------
        HBox row1 = new HBox();
        row1.setPadding(new Insets(5, 0, 5, 0));
        row1.setSpacing(5);
        
        addButton("", row1);
        addButton("Inv", row1);
        addButton("ln", row1);
        addButton("(", row1);
        addButton(")", row1);
        
        //---------------row 2-------------------
        HBox row2 = new HBox();
        row2.setPadding(new Insets(0, 0, 5, 0));
        row2.setSpacing(5);
        
        addButton("Int", row2);
        addButton("sinh", row2);
        addButton("sin", row2);
        addButton("x\u00B2", row2); //x squared
        addButton("n!", row2);
        
        //---------------row 3-------------------
        HBox row3 = new HBox();
        row3.setPadding(new Insets(0, 0, 5, 0));
        row3.setSpacing(5);
        
        addButton("dms", row3);
        addButton("cosh", row3);
        addButton("cos", row3);
        addButton("x\u207F", row3); //x to the nth power
        addButton("\u207F\u221Ax", row3); //nth root
        
        //---------------row 4-------------------
        HBox row4 = new HBox();
        row4.setPadding(new Insets(0, 0, 5, 0));
        row4.setSpacing(5);
        
        addButton("\u03c0", row4); //pi
        addButton("tanh", row4);
        addButton("tan", row4);
        addButton("x\u00B3", row4); //x cubed
        addButton("\u00B3\u221Ax", row4); //cube root of x
        
        //---------------row 5-------------------
        HBox row5 = new HBox();
        row5.setSpacing(5);
        
        addButton("F-E", row5);
        addButton("Exp", row5);
        addButton("Mod", row5);
        addButton("log", row5);
        addButton("10\u207F", row5); //10 to the power of x
        
        scientificButtons.getChildren().addAll(row1, row2, row3, row4, row5);
    }
    
    public void addButton(String text, HBox buttons)
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
        
        buttons.getChildren().add(newButton);
    }
    
    private EventHandler<ActionEvent> buttonClick = new EventHandler<ActionEvent>()
    {
        @Override
        public void handle(ActionEvent event)
        {
            Button buttonClicked = (Button)event.getSource();
            String buttonText = buttonClicked.getText();
            
            //EvaluateClick(buttonText);
        }
    };
}
