package operations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class StandardOperations
{
    public final static String EvaluateExpression(String expression)
    {                
        List<String> equation= new ArrayList<>(Arrays.asList(expression.split(" ")));
        
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
    
    public final static String multiply(String firstNumber, String secondNumber)
    {
        BigDecimal number1 = BigDecimal.valueOf(Double.parseDouble(firstNumber));
        BigDecimal number2 = BigDecimal.valueOf(Double.parseDouble(secondNumber));
        
        return number1.multiply(number2).stripTrailingZeros().toPlainString();
    }
    
    public final static String divide(String firstNumber, String secondNumber)
    {
        BigDecimal number1 = BigDecimal.valueOf(Double.parseDouble(firstNumber));
        BigDecimal number2 = BigDecimal.valueOf(Double.parseDouble(secondNumber));
        
        return number1.divide(number2, 10, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
    }
    
    public final static String add(String firstNumber, String secondNumber)
    {
        BigDecimal number1 = BigDecimal.valueOf(Double.parseDouble(firstNumber));
        BigDecimal number2 = BigDecimal.valueOf(Double.parseDouble(secondNumber));
        
        return number1.add(number2).stripTrailingZeros().toPlainString();
    }
    
    public final static String subtract(String firstNumber, String secondNumber)
    {
        BigDecimal number1 = BigDecimal.valueOf(Double.parseDouble(firstNumber));
        BigDecimal number2 = BigDecimal.valueOf(Double.parseDouble(secondNumber));
        
        return number1.subtract(number2).stripTrailingZeros().toPlainString();
    }
    
    public final static String reciprocate(String numberAsString)
    {
        BigDecimal number = BigDecimal.valueOf(Double.parseDouble(numberAsString));
        
        return BigDecimal.ONE.divide(number, 10, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
    }
    
    public final static String percentage(String expression)
    {
        List<String> equation= new ArrayList<String>(Arrays.asList(expression.split(" ")));
        
        String percentageString = equation.get(equation.size() - 1);
        BigDecimal percentage = BigDecimal.valueOf(Double.parseDouble(percentageString));
        percentage = percentage.divide(new BigDecimal(100));
        
        String numberString = equation.get(equation.size() - 3);
        BigDecimal number = BigDecimal.valueOf(Double.parseDouble(numberString));
        
        BigDecimal calculatedPercentage = percentage.multiply(number);
        
        percentageString = calculatedPercentage.stripTrailingZeros().toPlainString();
        equation.set(equation.size() - 1, percentageString);
        
        StringBuilder calculatedEquation = new StringBuilder();
        for(String value : equation)
        {
            calculatedEquation.append(value).append(" ");
        }
        
        return calculatedEquation.toString();
    }
    
    public final static String squareRoot(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
                
        if(number < 0)
        {
            return numberAsString;
        }
        else
        {
            return BigDecimal.valueOf(Math.sqrt(number)).setScale(10, RoundingMode.HALF_UP).stripTrailingZeros().toPlainString();
        }
    }
}
