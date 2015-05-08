package ms.services.calculator.rest;

import java.text.ParseException;

import javax.annotation.PostConstruct;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import ms.commons.logging.Logger;
import ms.services.calculator.dto.Result;

import org.jdice.calc.Calculator;
import org.jdice.calc.CalculatorException;
import org.jdice.calc.Num;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "Calculate", description = "API that provide calculation using expression query. e.g. /calculate?expression=3-4/2")
@RestController
public class CalculatorController implements Logger {

    @PostConstruct
    private void postConstruct() {
        trace("Register calculator controller");
    }

    @ApiOperation(value = "Calculate expression", notes = "Calculate expression by url encoded expression")
    @RequestMapping(value = "/calculate", method = {RequestMethod.GET}, produces = {"application/json"})
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

}
