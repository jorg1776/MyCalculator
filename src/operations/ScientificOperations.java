package operations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ScientificOperations
{
    public static String EvaluateExpression(String expression)
    {                
        List<String> equation= new ArrayList<String>(Arrays.asList(expression.split(" ")));
        
        //checks for multiplication and division
        for(int i = 0; i < equation.size(); i++)
        {
            String value = equation.get(i);
            
            if(value.contains(".e"))
            {
                System.out.println("woop");
            }
            switch(value)
            {
                case "^":
                case "%":
                case "\u207F\u221A": // nth root
                    String firstNumber = equation.get(i-1);
                    String secondNumber = equation.get(i+1);
                    
                    String result = "";
                    
                    if(value.equals("^"))
                    {
                        result = exponential(firstNumber, secondNumber);
                    }
                    else if(value.equals("%"))
                    {
                        result = modulus(firstNumber, secondNumber);
                    }
                    else if(value.equals("\u207F\u221A"))
                    {
                        result = nthRoot(firstNumber, secondNumber);
                    }
                    equation.set(i -1, result);
                    equation.remove(i);
                    equation.remove(i);
                    
                    i--;
                    break;
            }
        }
        
        StringBuilder newExpression = new StringBuilder();
        
        for(String value : equation)
        {
            newExpression.append(value).append(" ");
        }
        
        String finalResult = StandardOperations.EvaluateExpression(newExpression.toString());
        
        return finalResult;
    }
    
  //------------------logarithmic functions------------------
    public static String log(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        
        if(number > 0)
        {
            float result = (float)Math.log10(number);

            return checkedResult(result);
        }
        else
        {
            return numberAsString;
        }
    }
    
    public static String ln(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        
        if(number > 0)
        {
            float result = (float)Math.log(number);
            return checkedResult(result);
        }
        else
        {
            return numberAsString;
        }
    }
    
    public static String eToThePowerOf(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        float result = (float)Math.pow(Math.E, number);
        
        return checkedResult(result);
    }
    
  //------------------hyperbolic functions------------------
    public static String sinh(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        float result = (float)Math.sinh(number);
        
        return checkedResult(result);
    }
    
    public static String inverseSinh(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        float result = (float)Math.log(number + Math.sqrt(number * number + 1));
        
        return checkedResult(result);
    }
    
    public static String cosh(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        float result = (float)Math.cosh(number);
        
        return checkedResult(result);
    }
    
    public static String inverseCosh(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        if(number >=1 )
        {
            float result = (float)Math.log(number + Math.sqrt(number * number - 1));

            return checkedResult(result);
        }
        else
        {
            return numberAsString;
        }
    }
    
    public static String tanh(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        float result = (float)Math.tanh(number);
        
        return checkedResult(result);
    }
    
    public static String inverseTanh(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        if(number > -1 && number < 1 )
        {
            float result = (float)(Math.log((1 + number) / (1 - number)))/2;

            return checkedResult(result);
        }
        else
        {
            return numberAsString;
        }
    }
    
  //------------------trig functions------------------
    public static String sin(String numberAsString, String mode)
    {
        double number = Double.parseDouble(numberAsString);
        number = convertToRadians(number, mode);
        
        float result = (float)Math.sin(number);
        
        return checkedResult(result);
    }
    
    public static String inverseSin(String numberAsString, String mode)
    {
        double number = Double.parseDouble(numberAsString);
        number = convertToRadians(number, mode);
        
        if(number >= -1 && number <= 1)
        {
            float result = (float)Math.asin(number);

            return checkedResult(result);
        }
        else
        {
            return numberAsString;
        }
    }
    
    public static String cos(String numberAsString, String mode)
    {
        double number = Double.parseDouble(numberAsString);
        number = convertToRadians(number, mode);
        
        float result = (float)Math.cos(number);
        
        return checkedResult(result);
    }
    
    public static String inverseCos(String numberAsString, String mode)
    {
        double number = Double.parseDouble(numberAsString);
        number = convertToRadians(number, mode);
        
        if(number >= -1 && number <= 1)
        {
            float result = (float)Math.acos(number);

            return checkedResult(result);
        }
        else
        {
            return numberAsString;
        }
    }
    
    public static String tan(String numberAsString, String mode)
    {
        double number = Double.parseDouble(numberAsString);
        number = convertToRadians(number, mode);
        
        float result = (float)Math.tan(number);
        
        return checkedResult(result);
    }
    
    public static String inverseTan(String numberAsString, String mode)
    {
        double number = Double.parseDouble(numberAsString);
        number = convertToRadians(number, mode);
        
        float result = (float)Math.atan(number);

        return checkedResult(result);
    }
    
    private static double convertToRadians(double number, String mode)
    {
        switch(mode)
        {
            case "Degrees":
                number = Math.toRadians(number);
                break;
            case "Grads":
                number = number * Math.PI / 200;
                break;
        }
        
        return number;
    }
  //------------------exponential functions------------------
    public static String square(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        float result = (float)(number * number);
        
        return checkedResult(result);
    }
    
    public static String cube(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        float result = (float)(number * number * number);
        
        return checkedResult(result);
    }
    
    public static String exponential(String baseNumberAsString, String exponentNumberAsString)
    {
        double baseNumber = Double.parseDouble(baseNumberAsString);
        double exponentNumber = Double.parseDouble(exponentNumberAsString);
        float result = (float)Math.pow(baseNumber, exponentNumber);
        
        return checkedResult(result);
    }
    
    public static String tenToThePower(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        float result = (float)Math.pow(10, number);
        
        return checkedResult(result);
    }
    
    public static String factorial(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        
        if(number >= 0 && (number == Math.floor(number)))
        {
            float result = 1;
            for(int i = (int)number; i > 0; i--)
            {
                result *= i;
            }
            return checkedResult(result);
        }
        else
        {
            return numberAsString;
        }
        
    }
    
  //------------------root functions------------------
    public static String cubeRoot(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        float result = (float)Math.cbrt(number);
        
        return checkedResult(result);
    }
    
    public static String nthRoot(String baseNumberAString, String rootNumberAsString)
    {
        double baseNumber = Double.parseDouble(baseNumberAString);
        double rootNumber = Double.parseDouble(rootNumberAsString);
        
        float result = (float)Math.pow(baseNumber, 1 / rootNumber);
        
        return checkedResult(result);
    }
    
  //------------------other functions------------------
    public static String convertToInt(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        int result = (int)Math.floor(number);
        
        return result + "";
    }
    
    public static String getFraction(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        BigDecimal exactNumber = BigDecimal.valueOf(number);
        
        double wholeNumber = Math.floor(number);
        BigDecimal exactWholeNumber = BigDecimal.valueOf(wholeNumber);
        
        BigDecimal result = exactNumber.subtract(exactWholeNumber);
        
        return result + "";
    }
    
    public static String getPi()
    {
        float pi = (float)Math.PI;
        return pi + "";
    }
    
    public static String getDoublePi()
    {
        float pi = (float)Math.PI;
        return (pi * 2) + "";
    }
    
    public static String modulus(String firstNumberAsString, String secondNumberAsString)
    {
        double firstNumber = Double.parseDouble(firstNumberAsString);
        BigDecimal exactFirstNumber = BigDecimal.valueOf(firstNumber);
        double secondNumber = Double.parseDouble(secondNumberAsString);
        BigDecimal exactSecondNumber = BigDecimal.valueOf(secondNumber);
        
        double result = firstNumber % secondNumber;
        BigDecimal exactResult = exactFirstNumber.remainder(exactSecondNumber);
        
        if(result == Math.floor(result))
        {
            return exactResult.setScale(0, RoundingMode.DOWN) + "";
        }
        else
        {
            return exactResult + "";
        }
    }
    
    private static String checkedResult(float result)
    {        
        if(result == Math.floor(result))
        {
            int resultAsInteger = (int)result;
            return resultAsInteger + "";
        }
        else
        {
            return result + "";
        }
    }
}
