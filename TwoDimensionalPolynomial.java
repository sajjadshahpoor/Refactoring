
import java.util.ArrayList;

public class TwoDimensionalPolynomial implements RealFunction{
    private OneDimensionalPolynomial xPolynomial;
    private OneDimensionalPolynomial yPolynomial;

    public TwoDimensionalPolynomial(OneDimensionalPolynomial xPolynomial, OneDimensionalPolynomial yPolynomial){
        this.xPolynomial = xPolynomial;
        this.yPolynomial = yPolynomial;
    }

    @Override
    public double evaluateAt(Point point) throws Exception {
        if(point.getDimensions() != 2) throw new Exception("Two dimensional function evaluates only at two dimensional points");

        ArrayList<Double> xPointCoordinates = new ArrayList<>();
        ArrayList<Double> yPointCoordinates = new ArrayList<>();
        xPointCoordinates.add(point.getCoordinate(0));
        yPointCoordinates.add(point.getCoordinate(1));

        Point xPoint = new Point(xPointCoordinates);
        Point yPoint = new Point(yPointCoordinates);

        double result = 0;
        result += xPolynomial.evaluateAt(xPoint);
        result += yPolynomial.evaluateAt(yPoint);

        return result;
    }

    @Override
    public double evaluateFirstDerivativeAt(Point point) throws Exception {
        if(point.getDimensions() != 2) throw new Exception("Two dimensional function evaluates only at two dimensional points");

        ArrayList<Double> xPointCoordinates = new ArrayList<>();
        ArrayList<Double> yPointCoordinates = new ArrayList<>();
        xPointCoordinates.add(point.getCoordinate(0));
        yPointCoordinates.add(point.getCoordinate(1));

        Point xPoint = new Point(xPointCoordinates);
        Point yPoint = new Point(yPointCoordinates);

        double result = 0;
        result += xPolynomial.evaluateFirstDerivativeAt(xPoint);
        result += yPolynomial.evaluateFirstDerivativeAt(yPoint);

        return result;
    }

    @Override
    public Point evaluateFirstPartialDerivativeAt(Point point) throws Exception {
        ArrayList<Double> xPointCoordinates = new ArrayList<>();
        ArrayList<Double> yPointCoordinates = new ArrayList<>();
        xPointCoordinates.add(point.getCoordinate(0));
        yPointCoordinates.add(point.getCoordinate(1));

        Point xPoint = new Point(xPointCoordinates);
        Point yPoint = new Point(yPointCoordinates);

        ArrayList<Double> resultPointCoordinates = new ArrayList<>();
        resultPointCoordinates.add(xPolynomial.evaluateFirstDerivativeAt(xPoint));
        resultPointCoordinates.add(yPolynomial.evaluateFirstDerivativeAt(yPoint));

        return new Point(resultPointCoordinates);
    }

    @Override
    public String toString() {
        return xPolynomial.toString() + " + " + yPolynomial.toString();
    }
}
