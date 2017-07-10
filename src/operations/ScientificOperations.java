package operations;

public class ScientificOperations
{
  //------------------logarithmic functions------------------
    public static String ln(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        float result = (float)Math.log(number);
        
        return checkedResult(result);
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
        float result = (float)Math.log(number + Math.sqrt(number * number - 1));
        
        return checkedResult(result);
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
        
        if(number > -1 && number < 1)
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
        
        if(number > -1 && number < 1)
        {
            float result = (float)Math.acos(number);

            return checkedResult(result);
        }
        else
        {
            return numberAsString;
        }
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
    
    public static String exponential()
    {
        return "";
    }
    
  //------------------root functions------------------
    
  //------------------other functions------------------
    public static String convertToInt(String numberAsString)
    {
        double number = Double.parseDouble(numberAsString);
        int result = (int)Math.floor(number);
        
        return result + "";
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
