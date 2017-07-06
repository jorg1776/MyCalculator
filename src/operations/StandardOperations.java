package operations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author gruenewaldjo
 */
public class StandardOperations
{
    public static String EvaluateExpression(String expression)
    {                
        List<String> equation= new ArrayList<String>(Arrays.asList(expression.split(" ")));
        
        //checks for multiplication and division
        for(int i = 0; i < equation.size(); i++)
        {
            String value = equation.get(i);
            
            switch(value)
            {
                case "*":
                case "/":
                    String firstNumber = equation.get(i-1);
                    String secondNumber = equation.get(i+1);
                    
                    String result;
                    
                    if(value.equals("*"))
                    {
                        result = multiply(firstNumber, secondNumber);
                    }
                    else
                    {
                        result = divide(firstNumber, secondNumber);
                    }
                    
                    equation.set(i -1, result);
                    equation.remove(i);
                    equation.remove(i);
                    
                    i--;
                    break;
            }
        }
        
        //checks for addition and subtraction
        for(int i = 0; i < equation.size(); i++)
        {
            String value = equation.get(i);
            
            switch(value)
            {
                case "+":
                case "-":
                    String firstNumber = equation.get(i-1);
                    String secondNumber = equation.get(i+1);
                    
                    String result;
                    
                    if(value.equals("+"))
                    {
                        result = add(firstNumber, secondNumber);
                    }
                    else
                    {
                        result = subtract(firstNumber, secondNumber);
                    }
                    
                    equation.set(i -1, result);
                    equation.remove(i);
                    equation.remove(i);
                    
                    i--;
                    break;
            }
        }
        
        String finalResult = equation.get(0);
        return finalResult;
    }
    
    public static String multiply(String firstNumber, String secondNumber)
    {
        float number1 = Float.parseFloat(firstNumber);
        float number2 = Float.parseFloat(secondNumber);
        
        float result = number1 * number2;
        
        return checkedResult(result);
    }
    
    public static String divide(String firstNumber, String secondNumber)
    {
        float number1 = Float.parseFloat(firstNumber);
        float number2 = Float.parseFloat(secondNumber);
        
        float result = number1 / number2;
        
        return checkedResult(result);
    }
    
    public static String add(String firstNumber, String secondNumber)
    {
        float number1 = Float.parseFloat(firstNumber);
        float number2 = Float.parseFloat(secondNumber);
        
        float result = number1 + number2;
        
        return checkedResult(result);
    }
    
    public static String subtract(String firstNumber, String secondNumber)
    {
        float number1 = Float.parseFloat(firstNumber);
        float number2 = Float.parseFloat(secondNumber);
        
        float result = number1 - number2;
        
        return checkedResult(result);
    }
    
    public static String reciprocate(String numberAsString)
    {
        float number = Float.parseFloat(numberAsString);
        
        float result = 1 / number;
        
        return checkedResult(result);
    }
    
    public static String percentage(String expression)
    {
        List<String> equation= new ArrayList<String>(Arrays.asList(expression.split(" ")));
        
        String percentageString = equation.get(equation.size() - 1);
        String numberString = equation.get(equation.size() - 3);
        
        float percentage = Float.parseFloat(percentageString);
        percentage /= 100;
        float number = Float.parseFloat(numberString);
        float calculatedPercentage = percentage * number;
        percentageString = calculatedPercentage + "";
        equation.set(equation.size() - 1, percentageString);
        
        StringBuilder calculatedEquation = new StringBuilder();
        for(String value : equation)
        {
            calculatedEquation.append(value).append(" ");
        }
        
        return calculatedEquation.toString();
    }
    
    public static String squareRoot(String numberAsString)
    {
        float number = Float.parseFloat(numberAsString);
                
        if(number < 0)
        {
            return numberAsString;
        }
        else
        {
            float result = (float) Math.sqrt(number);

            return checkedResult(result);
        }
    }
    
    private static String checkedResult(float result)
    {        
        if(result == Math.floor(result))
        {
            int resultAsInteger = (int)result;
            return "" + resultAsInteger;
        }
        else
        {
            return "" + result;
        }
    }
}
