package br.com.deceitful1.controllers;

import br.com.deceitful1.controllers.exceptions.UnsupportedMathOperationException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/math")
public class MathController
{
    /*
      @RequestMapping("/sum")
        public Math sum(@RequestParam(value = "num1") double num1, @RequestParam(value = "num2") double num2)
        {
            return new Math(num1 + num2);
        }

     */
    @RequestMapping("/sum/{numberOne}/{numberTwo}")
    public Double sum(@PathVariable(value = "numberOne") String numberOne, @PathVariable("numberTwo") String numberTwo)
    {
        if (numberOne == null || numberTwo == null) {
            throw new UnsupportedMathOperationException("Numbers must not be null");
        }
        if (!isNumeric(numberOne) || !isNumeric(numberTwo)) {
            throw new UnsupportedMathOperationException("The variable must only contains numbers!");
        }

        return convertToDouble(numberOne) + convertToDouble(numberTwo);
    }

    @RequestMapping("/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo)
    {
        if (numberOne == null || numberTwo == null)
            throw new UnsupportedMathOperationException("Numbers must not be null");
        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("The variable must only contains numbers!");
        return convertToDouble(numberOne) - convertToDouble(numberTwo);
    }

    @RequestMapping("/divide/{numberOne}/{numberTwo}")
    public Double divide(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo)
    {
        if (numberOne == null || numberTwo == null)
            throw new UnsupportedMathOperationException("Numbers must not be null");
        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("The variable must only contains numbers!");
        return convertToDouble(numberOne) / convertToDouble(numberTwo);
    }

    @RequestMapping("/multiply/{numberOne}/{numberTwo}")
    public Double multiplication(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo)
    {
        if (numberOne == null || numberTwo == null)
            throw new UnsupportedMathOperationException("Numbers must not be null");
        if (!isNumeric(numberOne) || !isNumeric(numberTwo))
            throw new UnsupportedMathOperationException("The variable must only contains numbers!");
        return convertToDouble(numberOne) * convertToDouble(numberTwo);

    }

    @RequestMapping("/average/{numbers}")
    public Double average(@PathVariable String numbers)
    {
        if (numbers == null) throw new UnsupportedMathOperationException("Numbers must not be null");
        if (!isNumeric(numbers))
            throw new UnsupportedMathOperationException("The variable must only contains numbers!");

        double[] values = Arrays.stream(numbers.split(","))
                .mapToDouble(Double::parseDouble)
                .toArray();

        return Arrays.stream(values).average().orElse(0);
    }

    @RequestMapping("/squareroot/{number}")
    public Double squareRoot(@PathVariable(value = "number") String number)
    {
        if (number == null) throw new UnsupportedMathOperationException("Numbers must not be null");
        if (!isNumeric(number)) throw new UnsupportedMathOperationException("The variable must only contains numbers!");
        return Math.sqrt(convertToDouble(number));
    }


    private Double convertToDouble(String strNumber)
    {
        if (strNumber == null || strNumber.isEmpty())
            throw new UnsupportedMathOperationException("The variable must not be null or empty!");
        String number = strNumber.replaceAll(",", ".");


        return Double.parseDouble(number);

    }


    private boolean isNumeric(String strNumber)
    {
        if (strNumber == null || strNumber.isEmpty()) return false;
        String number = strNumber.replaceAll(",", ".");
        if (number.matches("[0-9]") || number.matches("[-+]?[0-9]*\\.?[0-9]+"))
            return true;


        return false;
    }


}
