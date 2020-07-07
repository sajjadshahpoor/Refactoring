
public  interface RealFunction{
    public double evaluateAt(Point point) throws Exception;
    public double evaluateFirstDerivativeAt(Point point) throws Exception;
    public Point evaluateFirstPartialDerivativeAt(Point point) throws Exception;
}
