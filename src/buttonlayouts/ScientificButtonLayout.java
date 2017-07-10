package buttonlayouts;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import operations.ScientificOperations;

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
        degreeTypeToggleGroup.selectedToggleProperty().addListener(radioButtonToggle);
        
        scientificButtons.getChildren().add(degreeType);
    }
    
    private static String modeSelection = "Degrees";
    
    private ChangeListener<Toggle> radioButtonToggle = new ChangeListener<Toggle>()
    {
        @Override
        public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue)
        {
            RadioButton selectedType = (RadioButton)newValue.getToggleGroup().getSelectedToggle();
            modeSelection = selectedType.getText();
        }
    };
    
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
        addButton("10\u207F", buttons, 4, 4); //10 to the nth power
        
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
        String number = OutputDisplay.getDisplay();
        String result = "0";
        
        switch(buttonText)
        {
          //----------logarithmic functions----------
            case "ln":
                if(!number.equals("0"))
                {
                    result = ScientificOperations.ln(number);
                    displayResult(result);
                }
                break;
            case "e\u207F": // e ^ x
                result = ScientificOperations.eToThePowerOf(number);
                displayResult(result);
                break;    
          //----------hyperbolic functions----------
            case "sinh":
                result = ScientificOperations.sinh(number);
                displayResult(result);
                break;
            case "sinh\u207B\u00B9": // sinh^-1
                result = ScientificOperations.inverseSinh(number);
                displayResult(result);
                break;
            case "cosh":
                result = ScientificOperations.cosh(number);
                displayResult(result);
                break;
            case "cosh\u207B\u00B9": // sinh^-1
                result = ScientificOperations.inverseCosh(number);
                displayResult(result);
                break;  
          //----------trig functions----------
            case "sin":
                result = ScientificOperations.sin(number, modeSelection);
                displayResult(result);
                break;
            case "sin\u207B\u00B9": // sinh^-1
                result = ScientificOperations.inverseSin(number, modeSelection);
                displayResult(result);
                break;
            case "cos":
                result = ScientificOperations.cos(number, modeSelection);
                displayResult(result);
                break;
            case "cos\u207B\u00B9": // sinh^-1
                result = ScientificOperations.inverseCos(number, modeSelection);
                displayResult(result);
                break;
          //----------exponential functions----------
            case "x\u00B2": // x^2
                result = ScientificOperations.square(number);
                displayResult(result);
                break;
            case "n!":
                result = ScientificOperations.factorial(number);
                displayResult(result);
                break;
            case "x\u207F": // x ^ n
                break;
          //----------root functions----------
          //----------other functions----------
            case "Int":
                result = ScientificOperations.convertToInt(number);
                displayResult(result);
            case "Frac":
                break;     
            case "dms":
                break;
            case "deg":
                break;
          //----------display options----------
            case "Inv":
                invertButtons();
                break;
            case "(":
                break;
            case ")":
                break;
        }
    }
    
    private void displayResult(String result)
    {
        StandardButtonLayout.updateNumber(result);
        OutputDisplay.updateDisplay(result, false);
    }
    
    private boolean inverted = false;
    private void invertButtons()
    {
        GridPane buttons = (GridPane)scientificButtons.getChildren().get(1);
        
        if(inverted == false)
        {
            changeText(buttons, 2, "e\u207F", 12); // ln to e^x
            changeText(buttons, 5, "Frac", 9.5); // Int to Frac
            changeText(buttons, 6, "sinh\u207B\u00B9", 8.5); // sinh to sinh^-1
            changeText(buttons, 7, "sin\u207B\u00B9", 9.5); // sin to sin^-1
            changeText(buttons, 10, "deg", 10.5); // dms to deg
            changeText(buttons, 11, "cosh\u207B\u00B9", 7.8); // cosh to cosh^-1
            changeText(buttons, 12, "cos\u207B\u00B9", 8.5); // cos to cos^-1
            changeText(buttons, 15, "2*\u03c0", 11); // pi to 2*pi
            changeText(buttons, 16, "tanh\u207B\u00B9", 8); // tanh to tanh^-1
            changeText(buttons, 17, "tan\u207B\u00B9", 8.5); // tan to tan^-1
        }
        else
        {
            changeText(buttons, 2, "ln", 12);
            changeText(buttons, 5, "Int", 12);
            changeText(buttons, 6, "sinh", 10);
            changeText(buttons, 7, "sin", 12);
            changeText(buttons, 10, "dms", 10);
            changeText(buttons, 11, "cosh", 9.5);
            changeText(buttons, 12, "cos", 12);
            changeText(buttons, 15, "pi", 12);
            changeText(buttons, 16, "tanh", 10);
            changeText(buttons, 17, "tan", 12);
        }
        
        inverted = !inverted;
    }
    
    private void changeText(GridPane buttons, int index, String text, double fontSize)
    {
        ((Button)buttons.getChildren().get(index)).setText(text);
        ((Button)buttons.getChildren().get(index)).setFont(Font.font(fontSize));
    }
}
