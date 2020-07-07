
import java.util.ArrayList;

public class OneDimensionalPolynomial implements RealFunction {
    private int degree;
    private ArrayList<Double> coefficients;
    private char variableSymbol;


    public OneDimensionalPolynomial(int degree, ArrayList<Double> coefficients, char variableSymbol){
        this.degree = degree;
        this.variableSymbol = variableSymbol;
        this.coefficients = new ArrayList<>();
        for(int i = 0; i <= degree; i++){
            try{
                this.coefficients.add(coefficients.get(i));
            }catch (IndexOutOfBoundsException e){
                this.coefficients.add(new Double(0));
            }
        }
    }

    @Override
    public double evaluateAt(Point point) throws Exception{
        if(point.getDimensions() != 1) throw new Exception("One dimensional polynomial evaluates only at one dimensional point");
        double result = 0;
        for(int i = 0; i <= degree; i++){
            result += coefficients.get(i) * Math.pow(point.getCoordinate(0), degree-i);
        }
        return result;
    }

    @Override
    public double evaluateFirstDerivativeAt(Point point) throws Exception{
        if(point.getDimensions() != 1) throw new Exception("One dimensional polynomial evaluates only at one dimensional point");
        double result = 0;
        for(int i = 0; i < degree;  i++){
            result += coefficients.get(i) * (degree-i) * Math.pow(point.getCoordinate(0), degree - i - 1);
        }
        return result;
    }

    @Override
    public Point evaluateFirstPartialDerivativeAt(Point point) throws Exception {
        ArrayList<Double> resultPointCoordinates = new ArrayList<>();
        resultPointCoordinates.add(evaluateFirstDerivativeAt(point));
        return new Point(resultPointCoordinates);
    }

    @Override
    public String toString() {
        String result = "";
        for(int i = 0; i <= degree; i++){
            result += coefficients.get(i) + "*" + variableSymbol + "^" + (degree-i);
            if(i != degree) result+= " + ";
        }

        return result;
    }
}
