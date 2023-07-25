package calculator;

/**
 *
 * @author ushan
 */
public class CalOperation {

    private Integer val1Int = null;
    private Double val1Dbl = null;
    private Integer val2Int = null;
    private Double val2Dbl = null;

    public Integer getVal1Int() {
        return val1Int;
    }

    public void setVal1Int(Integer val1Int) {
        this.val1Int = val1Int;
    }

    public Double getVal1Dbl() {
        return val1Dbl;
    }

    public void setVal1Dbl(Double val1Dbl) {
        this.val1Dbl = val1Dbl;
    }

    public Integer getVal2Int() {
        return val2Int;
    }

    public void setVal2Int(Integer val2Int) {
        this.val2Int = val2Int;
    }

    public Double getVal2Dbl() {
        return val2Dbl;
    }

    public void setVal2Dbl(Double val2Dbl) {
        this.val2Dbl = val2Dbl;
    }

    public void clearAll() {
        val1Dbl = null;
        val1Int = null;
        val2Dbl = null;
        val2Int = null;
    }

    public boolean checkAll() {
        return val1Dbl == null && val2Dbl == null && val1Int == null && val2Int == null;
    }

    public boolean check1Val() {
        return val1Dbl != null || val1Int != null;
    }

    public boolean check2Val() {
        return val2Dbl != null || val2Int != null;
    }

    public String chooseType(char op) {
        
        if (val1Dbl != null || val2Dbl != null) { // if one of the values is double
            Double result = 0.0;
            if (op == '√') {   // handle square root for double

                if (val2Int == null) {

                    result = sqrt(val1Dbl);
                } else {

                    return (int) sqrt(val2Int) + "";
                }
            } else if (val1Dbl != null && val2Dbl != null) {    // if both vals are double
                result = operate(val1Dbl, val2Dbl, op);

            } else if (val1Dbl != null) {              // if only val1 is double
                result = operate(val1Dbl, (double) val2Int, op);

            } else if (val2Dbl != null) {            // if only val2 is double
                result = operate((double) val1Int, val2Dbl, op);

            }
            clearAll();
            val1Dbl = result;

            return result + "";
        } else {  // if both values are int
            if (op == '√') {   // handle square root for int
                double result = sqrt((double) val1Int);
                clearAll();
                if (checkType(result)) { // if the result is an integer
                    val1Int = (int) result;
                    return (int) result + "";
                } else {
                    val1Dbl = result;
                    return result + "";
                }
            } else if (op == '/' && ((double) val1Int % (double) val2Int != 0.0)) { //handle int division results with decimals
                double result = operate((double) val1Int, (double) val2Int, op);
                return result + "";
            } else {     // handle other normal operations with int
                Integer result = operate(val1Int, val2Int, op);
                val1Int = result;
                val2Int = null;
                return result.toString();
            }
        }
    }

    private <t extends Number> t operate(t val1, t val2, char op) {
        switch (op) {
            case '+':
                return addition(val1, val2);
            case '-':
                return subtract(val1, val2);
            case '*':
                return multiply(val1, val2);
            case '/':
                return divide(val1, val2);
        }
        return null;
    }

    public <t extends Number> t addition(t val1, t val2) {
        if (val1.getClass() == Integer.class) {
            return (t) (Integer) ((Integer) val1 + (Integer) val2);
        }
        if (val1.getClass() == Double.class) {
            return (t) (Double) ((Double) val1 + (Double) val2);
        }
        return null;
    }

    private <t extends Number> t subtract(t val1, t val2) {
        if (val1.getClass() == Integer.class) {
            return (t) (Integer) ((Integer) val1 - (Integer) val2);
        }
        if (val1.getClass() == Double.class) {
            return (t) (Double) ((Double) val1 - (Double) val2);
        }
        return null;
    }

    private <t extends Number> t multiply(t val1, t val2) {
        if (val1.getClass() == Integer.class) {
            return (t) (Integer) ((Integer) val1 * (Integer) val2);
        }
        if (val1.getClass() == Double.class) {
            return (t) (Double) ((Double) val1 * (Double) val2);
        }
        return null;
    }

    private <t extends Number> t divide(t val1, t val2) {
        if (val1.getClass() == Integer.class) {
            return (t) (Integer) ((Integer) val1 / (Integer) val2);
        }
        if (val1.getClass() == Double.class) {
            return (t) (Double) ((Double) val1 / (Double) val2);
        }
        return null;
    }

    private <t extends Number> double sqrt(t val) {
        return Math.sqrt((Double) val);
    }

    private boolean checkType(double val) {  // to check if a number has decimal values
        return val - (int) val == 0.0;
    }

}
