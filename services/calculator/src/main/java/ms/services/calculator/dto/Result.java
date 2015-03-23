package ms.services.calculator.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class Result {

    private BigDecimal result;
    
    private String equation;

    /**
     * @return the result
     */
    public BigDecimal getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(BigDecimal result) {
        this.result = result;
    }

    /**
     * @return the equation
     */
    public String getEquation() {
        return equation;
    }

    /**
     * @param equation the equation to set
     */
    public void setEquation(String equation) {
        this.equation = equation;
    }
        
}
