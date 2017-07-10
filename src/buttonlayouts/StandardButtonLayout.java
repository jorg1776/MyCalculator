package buttonlayouts;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import operations.StandardOperations;

public class StandardButtonLayout
{
    private static VBox standardButtons;
    private static String viewType;
    
    private StandardButtonLayout()
    {
        standardButtons = new VBox();
        addStandardButtons();
    }
    
    public static VBox getStandardButtonLayout(String view)
    {
        viewType = view;
        new StandardButtonLayout();
        return standardButtons;
    }
    
    private void addStandardButtons()
    {
        //---------------memory row-------------------
        HBox memoryRow = new HBox();
        memoryRow.setPadding(new Insets(0, 0, 5, 0));
        memoryRow.setSpacing(5);
        
        addButton("MC", memoryRow);
        addButton("MR", memoryRow);
        addButton("MS", memoryRow);
        addButton("M+", memoryRow);
        addButton("M-", memoryRow);
        
       //---------------row 1-------------------
        HBox row1 = new HBox();
        row1.setPadding(new Insets(0, 0, 5, 0));
        row1.setSpacing(5);
        
        addButton("<-", row1);
        addButton("CE", row1);
        addButton("C", row1);
        addButton("+-", row1);
        addButton("\u221A", row1);
        
        //---------------row 2-------------------
        HBox row2 = new HBox();
        row2.setPadding(new Insets(0, 0, 5, 0));
        row2.setSpacing(5);
        
        addButton("7", row2);
        addButton("8", row2);
        addButton("9", row2);
        addButton("/", row2);
        addButton("%", row2);
        
        //---------------row 3-------------------
        HBox row3 = new HBox();
        row3.setPadding(new Insets(0, 0, 5, 0));
        row3.setSpacing(5);
        
        addButton("4", row3);
        addButton("5", row3);
        addButton("6", row3);
        addButton("*", row3);
        addButton("1/x", row3);
        
        //---------------row 4-------------------
        HBox row4 = new HBox();
        row4.setPadding(new Insets(0, 0, -27, 0));
        row4.setSpacing(5);
        
        addButton("1", row4);
        addButton("2", row4);
        addButton("3", row4);
        addButton("-", row4);
        addButton("=", row4);
        
        //---------------row 5-------------------
        HBox row5 = new HBox();
        row5.setPadding(new Insets(0, 0, 0, 0));
        row5.setSpacing(5);
        
        addButton("0", row5);
        addButton(".", row5);
        addButton("+", row5);
        
        standardButtons.getChildren().addAll(memoryRow, row1, row2, row3, row4, row5);
    }
    
    private void addButton(String text, HBox buttons)
    {
        int standardButtonWidth = 35;
        int standardButtonHeight = 27;
        
        Button newButton = new Button(text);
        
        //modifies button size and text size
        switch(text)
        {
            case "MC":
            case "MR":
            case "MS":
            case "M+":
            case "M-":
                newButton.setFont(Font.font(11));
                newButton.setPrefSize(standardButtonWidth, standardButtonHeight);
                break;
            case "=":
                newButton.setPrefSize(35, 59);
                break;
            case "0":
                newButton.setPrefSize(72, 27);
                break;
            case "%":
                if(viewType.equals("Scientific"))
                {
                    newButton.setDisable(true);
                }
            default:
                newButton.setPrefSize(standardButtonWidth, standardButtonHeight);
                break;
        }
        
        newButton.setOnAction(buttonClick);
        
        newButton.setFocusTraversable(false);
        buttons.getChildren().add(newButton);
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
    
    private String storedMNumber = "0";
    
    private static StringBuilder number = new StringBuilder("0");
    
    public static void updateNumber(String newNumber)
    {
        resetString(number, newNumber);
    }
    
    private void evaluateClick(String buttonText)
    {
        int displayLength = number.length();
        
        boolean decreaseSize = displayLength > 12;

        switch(buttonText)
        {
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
            case "7":
            case "8":
            case "9":
            case "0":
            case ".":
                if(displayLength < 17)
                {
                    if(number.toString().equals("0"))
                    {
                        number = resetString(number, buttonText);
                    }
                    else
                    {
                        number.append(buttonText);
                    }
                    OutputDisplay.updateDisplay(number.toString(), decreaseSize);
                }
                break;
            case "+":
            case "-":
            case "*":
            case "/":
                OutputDisplay.updateEquation(number + " " + buttonText + " ");
                number = resetString(number, "0");
                OutputDisplay.clearDisplay();
                break;
            case "1/x":
                if(!number.toString().equals("0"))
                {
                    String reciprocatedNumber = StandardOperations.reciprocate(number.toString());
                    number = resetString(number, reciprocatedNumber);
                    OutputDisplay.updateDisplay(number.toString(), decreaseSize);
                }
                break;
            case "\u221A": //square root
                String reciprocatedNumber = StandardOperations.squareRoot(number.toString());
                number = resetString(number, reciprocatedNumber);
                OutputDisplay.updateDisplay(number.toString(), decreaseSize);
                break;
            case "+-":
                if(!number.toString().equals("0"))
                {
                    number = flipNumberSign(number);
                    OutputDisplay.updateDisplay(number.toString(), decreaseSize);
                }
                break;
            case "%":
                if(OutputDisplay.canFindPercentage())
                {
                    OutputDisplay.updateEquation(number + " ");
                    String updatedEquation = StandardOperations.percentage(OutputDisplay.getEquation());
                    OutputDisplay.clearEquation();
                    OutputDisplay.updateEquation(updatedEquation);
                    OutputDisplay.clearDisplay();
                }
                break;
            case "MC":
                storedMNumber = "0";
                OutputDisplay.toggleStoredDisplay(false);
                break;
            case "MR":
                number = resetString(number, storedMNumber);
                OutputDisplay.updateDisplay(number.toString(), decreaseSize);
                break;
            case "MS":
                storedMNumber = number.toString();
                OutputDisplay.toggleStoredDisplay(true);
                number = resetString(number, "0");
                OutputDisplay.clearDisplay();
                break;
            case "M+":
                storedMNumber = StandardOperations.add(number.toString(), storedMNumber);
                OutputDisplay.toggleStoredDisplay(true);
                number = resetString(number, "0");
                OutputDisplay.clearDisplay();
                break;
            case "M-":
                storedMNumber = StandardOperations.subtract(storedMNumber, number.toString());
                OutputDisplay.toggleStoredDisplay(true);
                number = resetString(number, "0");
                OutputDisplay.clearDisplay();
                break;
            case "C":
                number = resetString(number, "0");
                OutputDisplay.clearDisplay();
                OutputDisplay.clearEquation();
                break;
            case "CE":
                number = resetString(number, "0");
                OutputDisplay.clearDisplay();
                break;
            case "<-":
                number.deleteCharAt(number.length() - 1);
                OutputDisplay.updateDisplay(number.toString(), decreaseSize);
                break;
            case "=":
                OutputDisplay.updateEquation(number.toString());
                number = resetString(number, StandardOperations.EvaluateExpression(OutputDisplay.getEquation())); //gets the answer
                OutputDisplay.updateDisplay(number.toString(), false); 
                OutputDisplay.clearEquation();
                break;
        }
    }
    
    private static StringBuilder resetString(StringBuilder numberBuilder, String number)
    {
        numberBuilder.delete(0, numberBuilder.length());
        numberBuilder.append(number);
        return numberBuilder;
    }
    
    private StringBuilder flipNumberSign(StringBuilder numberBuilder)
    {
        if(!(numberBuilder.charAt(0) == '-'))
        {
            numberBuilder.insert(0, "-");
        }
        else
        {
            numberBuilder.deleteCharAt(0);
        }
        
        return numberBuilder;
    }
}
