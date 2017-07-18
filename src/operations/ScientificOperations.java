package operations;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class ScientificOperations
{
    public final static String EvaluateExpression(String expression)
    {                
        List<String> equation= new ArrayList<>(Arrays.asList(expression.split(" ")));
        
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
                    
                    switch (value)
                    {
                        case "^":
                            result = exponential(firstNumber, secondNumber);
                            break;
                        case "%":
                            result = modulus(firstNumber, secondNumber);
                            break;
                        case "\u207F\u221A":
                            result = nthRoot(firstNumber, secondNumber);
                            break;
                        default:
                            break;
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
    public final static String log(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        
        if(number > 0)
        {
            return convertResult(Math.log10(number));
        }
        else
        {
            return numberAsString;
        }
    }
    
    public final static String ln(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        
        if(number > 0)
        {
            return convertResult(Math.log(number));
        }
        else
        {
            return numberAsString;
        }
    }
    
    public final static String eToThePowerOf(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        return convertResult(Math.pow(Math.E, number));
    }
    
  //------------------hyperbolic functions------------------
    public final static String sinh(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        return convertResult(Math.sinh(number));
    }
    
    public final static String inverseSinh(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        return convertResult(Math.log(number + Math.sqrt(number * number + 1)));
    }
    
    public final static String cosh(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        return convertResult(Math.cosh(number));
    }
    
    public final static String inverseCosh(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        if(number >=1 )
        {
            return convertResult(Math.log(number + Math.sqrt(number * number - 1)));
        }
        else
        {
            return numberAsString;
        }
    }
    
    public final static String tanh(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        return convertResult(Math.tanh(number));
    }
    
    public final static String inverseTanh(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        if(number > -1 && number < 1 )
        {
            return convertResult((Math.log((1 + number) / (1 - number)))/2);
        }
        else
        {
            return numberAsString;
        }
    }
    
  //------------------trig functions------------------
    public final static String sin(String numberAsString, String mode)
    {
        double number = Double.parseDouble(numberAsString);
        number = convertToRadians(number, mode);
        
        return convertResult(Math.sin(number));
    }
    
    public final static String inverseSin(String numberAsString, String mode)
    {
        double number = Double.parseDouble(numberAsString);
        number = convertToRadians(number, mode);
        
        if(number >= -1 && number <= 1)
        {
            return convertResult(Math.asin(number));
        }
        else
        {
            return numberAsString;
        }
    }
    
    public final static String cos(String numberAsString, String mode)
    {
        double number = Double.parseDouble(numberAsString);
        number = convertToRadians(number, mode);
        
        return convertResult(Math.cos(number));
    }
    
    public final static String inverseCos(String numberAsString, String mode)
    {
        double number = Double.parseDouble(numberAsString);
        number = convertToRadians(number, mode);
        
        if(number >= -1 && number <= 1)
        {
            return convertResult(Math.acos(number));
        }
        else
        {
            return numberAsString;
        }
    }
    
    public final static String tan(String numberAsString, String mode)
    {
        double number = Double.parseDouble(numberAsString);
        number = convertToRadians(number, mode);
        
        return convertResult(Math.tan(number));
    }
    
    public final static String inverseTan(String numberAsString, String mode)
    {
        double number = Double.parseDouble(numberAsString);
        number = convertToRadians(number, mode);
        
        return convertResult(Math.atan(number));
    }
    
    private static double convertToRadians(double number, String mode)
    {
        switch(mode)
        {
            case "Degrees":
                number = Math.toRadians(number);
                break;
            case "Grads":
                BigDecimal exactNumber = BigDecimal.valueOf(number);
                exactNumber = exactNumber.multiply(new BigDecimal(Math.PI));
                exactNumber = exactNumber.divide(new BigDecimal(200));
                number = exactNumber.doubleValue();
                break;
        }
        
        return number;
    }
    
  //------------------exponential functions------------------
    public final static String square(String numberAsString)
    {
        BigDecimal number = BigDecimal.valueOf(Double.parseDouble(numberAsString));
        return number.pow(2).stripTrailingZeros().toPlainString();
    }
    
    public final static String cube(String numberAsString)
    {
        BigDecimal number = BigDecimal.valueOf(Double.parseDouble(numberAsString));
        return number.pow(3).stripTrailingZeros().toPlainString();
    }
    
    public final static String exponential(String baseNumberAsString, String exponentNumberAsString)
    {
        double baseNumber = Double.parseDouble(baseNumberAsString);
        double exponentNumber = Double.parseDouble(exponentNumberAsString);
        
        return convertResult(Math.pow(baseNumber, exponentNumber));
    }
    
    public final static String tenToThePower(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        return convertResult(Math.pow(10, number));
    }
    
    public final static String factorial(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        
        if(number >= 0 && (number == Math.floor(number)))
        {
            int result = 1;
            for(int i = (int)number; i > 0; i--)
            {
                result *= i;
            }
            return convertResult(result);
        }
        else
        {
            return numberAsString;
        }
    }
    
  //------------------root functions------------------
    public final static String cubeRoot(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        return convertResult(Math.cbrt(number));
    }
    
    public final static String nthRoot(String baseNumberAString, String rootNumberAsString)
    {
        double baseNumber = Double.parseDouble(baseNumberAString);
        BigDecimal rootNumber = BigDecimal.valueOf(Double.parseDouble(rootNumberAsString));
        rootNumber = BigDecimal.ONE.divide(rootNumber, 16, RoundingMode.HALF_UP).stripTrailingZeros();
        
        return convertResult(Math.pow(baseNumber, rootNumber.doubleValue()));
    }
    
  //------------------other functions------------------
    public final static String getInt(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        return convertResult(Math.floor(number));
    }
    
    public final static String getFraction(String numberAsString)
    {
        BigDecimal exactNumber = BigDecimal.valueOf(Double.parseDouble(numberAsString));
        BigDecimal exactWholeNumber = exactNumber.setScale(0, RoundingMode.FLOOR);
        
        return exactNumber.subtract(exactWholeNumber).toPlainString();
    }
    
    public final static String getPi()
    {
        return convertResult(Math.PI);
    }
    
    public final static String getDoublePi()
    {
        BigDecimal pi = BigDecimal.valueOf(Math.PI);
        return pi.multiply(new BigDecimal(2)).stripTrailingZeros().toPlainString();
    }
    
    public final static String modulus(String firstNumberAsString, String secondNumberAsString)
    {
        BigDecimal exactFirstNumber = BigDecimal.valueOf(Double.parseDouble(firstNumberAsString));
        BigDecimal exactSecondNumber = BigDecimal.valueOf(Double.parseDouble(secondNumberAsString));
        
       return exactFirstNumber.remainder(exactSecondNumber).stripTrailingZeros().toPlainString();
    }
    
    public final static String toDMS(String numberAsString)
    {
        BigDecimal decimal = BigDecimal.valueOf(Double.parseDouble(numberAsString));
        boolean isNegative = false;
        if(decimal.compareTo(BigDecimal.ZERO) < 0)
        {
            isNegative = true;
            decimal = decimal.multiply(new BigDecimal(-1));
        }
        
        BigDecimal degrees = decimal.setScale(0, RoundingMode.FLOOR).stripTrailingZeros();
        String degreesAsString = degrees.toPlainString();
        if(isNegative == true)
        {
            degreesAsString = "-" + degreesAsString;
        }
        
        decimal = (decimal.subtract(degrees)).multiply(new BigDecimal(60)).stripTrailingZeros();
        BigDecimal minutes = decimal.setScale(0, RoundingMode.FLOOR);
        String minutesAsString = minutes.toPlainString();
        if(minutes.compareTo(new BigDecimal(10)) < 0)
        {
            minutesAsString = "0" + minutesAsString;
        }
        
        decimal = decimal.subtract(minutes);
        BigDecimal seconds = decimal.multiply(new BigDecimal(60)).stripTrailingZeros();
        String secondsAsString;
        if(minutes.compareTo(new BigDecimal(10)) < 0)
        {
            secondsAsString = "0" + seconds;
        }
        else
        {
            secondsAsString = seconds.toPlainString();
        }
        
        if(secondsAsString.contains("."))
        {
            secondsAsString = secondsAsString.replaceAll("[.]", "");
        }
        
        StringBuilder result = new StringBuilder(degreesAsString);
        if(seconds.compareTo(BigDecimal.ZERO) > 0)
        {
            result.append(".").append(minutesAsString).append(secondsAsString);
        }
        else if(minutes.compareTo(BigDecimal.ZERO) > 0)
        {
            result.append(".").append(minutesAsString);
        }
        
        return result.toString();
    }
    
    public final static String toDeg(String numberAsString)
    {
        BigDecimal dmsNumber = BigDecimal.valueOf(Double.parseDouble(numberAsString));
        BigDecimal degrees = dmsNumber.setScale(0, RoundingMode.FLOOR);
        
        BigDecimal minSecDecimal = dmsNumber.subtract(degrees);
        
        BigDecimal decimal = BigDecimal.ZERO;
        if(minSecDecimal.compareTo(BigDecimal.ZERO) > 0)
        {
            String minSecDecimalAsString = minSecDecimal.toPlainString();
            minSecDecimalAsString = minSecDecimalAsString.replace("0.", "");
            
            BigDecimal minutes; 
            if(minSecDecimalAsString.length() > 2)
            {
                String minutesAsString = minSecDecimalAsString.substring(0, 2);
                minutes = BigDecimal.valueOf(Double.parseDouble(minutesAsString)).stripTrailingZeros();
                try
                {
                   minutes = minutes.divide(new BigDecimal(60)); 
                } 
                catch (ArithmeticException e)
                {
                    minutes = minutes.divide(new BigDecimal(60), 10, RoundingMode.HALF_UP);
                }

                String secondsAsString = minSecDecimalAsString.substring(2, minSecDecimalAsString.length());
                secondsAsString = "." + secondsAsString;
                BigDecimal seconds = BigDecimal.valueOf(Double.parseDouble(secondsAsString)).stripTrailingZeros();
                seconds = seconds.multiply(new BigDecimal(100)).stripTrailingZeros();
                try
                {
                   seconds = seconds.divide(new BigDecimal(3600));
                } 
                catch (ArithmeticException e)
                {
                    seconds = seconds.divide(new BigDecimal(3600), 10, RoundingMode.HALF_UP);
                }

                decimal = minutes.add(seconds).stripTrailingZeros();
            }
            else
            {
                minutes = BigDecimal.valueOf(Double.parseDouble(minSecDecimalAsString)).stripTrailingZeros();
                if(minSecDecimalAsString.length() < 2)
                {
                    minutes = minutes.multiply(BigDecimal.TEN);
                }
                
                try
                {
                   minutes = minutes.divide(new BigDecimal(60)); 
                } 
                catch (ArithmeticException e)
                {
                    minutes = minutes.divide(new BigDecimal(60), 10, RoundingMode.HALF_UP);
                }
                decimal = minutes;
            }
        }
        
        BigDecimal result = degrees.add(decimal);
        
        return result.toPlainString();
    }
    
    public final static String convertResult(double result)
    {
        return BigDecimal.valueOf(result).stripTrailingZeros().toPlainString();
    }
}
