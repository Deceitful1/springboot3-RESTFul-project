package br.com.deceitful1.controllers;

import br.com.deceitful1.models.MathOperations;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        return MathOperations.sum(numberOne, numberTwo);
    }

    @RequestMapping("/subtraction/{numberOne}/{numberTwo}")
    public Double subtraction(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo)
    {
        return MathOperations.subtraction(numberOne, numberTwo);
    }

    @RequestMapping("/divide/{numberOne}/{numberTwo}")
    public Double divide(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo)
    {
        return MathOperations.divide(numberOne, numberTwo);
    }

    @RequestMapping("/multiply/{numberOne}/{numberTwo}")
    public Double multiplication(@PathVariable(value = "numberOne") String numberOne, @PathVariable(value = "numberTwo") String numberTwo)
    {
        return MathOperations.multiplication(numberOne, numberTwo);
    }

    @RequestMapping("/average/{numbers}")
    public Double average(@PathVariable String numbers)
    {
        return MathOperations.average(numbers);
    }

    @RequestMapping("/squareroot/{number}")
    public Double squareRoot(@PathVariable(value = "number") String number)
    {
        return MathOperations.squareRoot(number);
    }


}
