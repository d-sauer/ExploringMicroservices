package ms.services.calculator.rest;

import java.text.ParseException;

import ms.commons.logging.Logger;
import ms.services.calculator.dto.Result;

import org.jdice.calc.Calculator;
import org.jdice.calc.CalculatorException;
import org.jdice.calc.Num;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalculatorController implements Logger {

    @RequestMapping(value = "/calculate",params = {"expression"}, method = { RequestMethod.GET }, produces = { "application/json" })
    public Result calculate(@RequestParam String expression) {
        info("Calculating equation: {}", expression);
        Result result = new Result();
        result.setEquation(expression);
        try {
            Calculator calc = Calculator.builder(expression);
            Num num = calc.calculate();
            result.setResult(num.toBigDecimal());
        } catch (CalculatorException | ParseException ce) {
            error("Error during calculation", ce);
        }
        
        return result;
    }

    @RequestMapping(value = "/calculate")
    public String calculate() {
        return "To calculate equation use url: /calculate?expression=&lt;math expression&gt;";
    }
    
}
