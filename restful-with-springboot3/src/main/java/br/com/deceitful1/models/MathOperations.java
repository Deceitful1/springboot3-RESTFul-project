package br.com.deceitful1.models;

import br.com.deceitful1.controllers.exceptions.UnsupportedMathOperationException;

import java.util.Arrays;

public class MathOperations
{
    public static double sum(String strNumber1, String strNumber2)
    {
        if (strNumber1 == null || strNumber2 == null) {
            throw new UnsupportedMathOperationException("Numbers must not be null");
        }
        if (!isNumeric(strNumber1) || !isNumeric(strNumber2)) {
            throw new UnsupportedMathOperationException("The variable must only contains numbers!");
        }

        return convertToDouble(strNumber1) + convertToDouble(strNumber2);
    }

    public static double subtraction(String numberOne, String numberTwo)
    {
        if (numberOne == null || numberTwo == null)
            throw new UnsupportedMathOperationException("Numbers must not be null");
        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("The variable must only contains numbers!");
        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    public static double divide(String numberOne, String numberTwo)
    {
        if (numberOne == null || numberTwo == null)
            throw new UnsupportedMathOperationException("Numbers must not be null");
        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("The variable must only contains numbers!");
        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    public static double multiplication(String numberOne, String numberTwo)
    {
        if (numberOne == null || numberTwo == null)
            throw new UnsupportedMathOperationException("Numbers must not be null");
        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("The variable must only contains numbers!");
        return convertToDouble(numberOne) * convertToDouble(numberTwo);
    }

    public static double average(String numbers)
    {
        if (numbers == null) throw new UnsupportedMathOperationException("Numbers must not be null");
        if (!isNumeric(numbers))
            throw new UnsupportedMathOperationException("The variable must only contains numbers!");

        double[] values = Arrays.stream(numbers.split(","))
                .mapToDouble(Double::parseDouble)
                .toArray();

        return Arrays.stream(values).average().orElse(0);
    }
    public static double squareRoot(String number)
    {
        if (number == null) throw new UnsupportedMathOperationException("Numbers must not be null");
        if (!isNumeric(number)) throw new UnsupportedMathOperationException("The variable must only contains numbers!");
        return java.lang.Math.sqrt(convertToDouble(number));
    }




    private static Double convertToDouble(String strNumber)
    {
        if (strNumber == null || strNumber.isEmpty())
            throw new UnsupportedMathOperationException("The variable must not be null or empty!");
        String number = strNumber.replaceAll(",", ".");


        return Double.parseDouble(number);

    }

    private static boolean isNumeric(String strNumber)
    {
        if (strNumber == null || strNumber.isEmpty()) return false;
        String number = strNumber.replaceAll(",", ".");
        if (number.matches("[0-9]") || number.matches("[-+]?[0-9]*\\.?[0-9]+"))
            return true;


        return false;
    }
}
