
import java.util.ArrayList;

public class RefactoringTest {

    public static void main(String[] args) throws Exception{
        // test with oneDimensionalPolynomial
        ArrayList<Double> initialPointCoordinates = new ArrayList<>();
        ArrayList<Double> costFunctionCoefficients = new ArrayList<>();

        initialPointCoordinates.add(1.0);
        costFunctionCoefficients.add(2.0);
        costFunctionCoefficients.add(3.0);
        costFunctionCoefficients.add(1.0);

        Point initialPoint = new Point(initialPointCoordinates);
        RealFunction costFunction = new OneDimensionalPolynomial(2, costFunctionCoefficients, 'x');
        Point minimumPoint = MinimumPointFinder.findMinimumPoint(initialPoint, costFunction);
        System.out.println("Function to minimize is: " + costFunction.toString());
        System.out.println("Minimum point is: " + minimumPoint);
        System.out.println("Optimum value is: " + costFunction.evaluateAt(minimumPoint));


        //test with twoDimensionalPolynomial
        initialPointCoordinates.clear();
        initialPointCoordinates.add(1.0);
        initialPointCoordinates.add(1.0);
        initialPoint = new Point(initialPointCoordinates);

        costFunctionCoefficients.clear();
        costFunctionCoefficients.add(3.0);
        costFunctionCoefficients.add(1.0);
        costFunctionCoefficients.add(1.0);
        OneDimensionalPolynomial subPolynomialX = new OneDimensionalPolynomial(2, costFunctionCoefficients, 'x');

        costFunctionCoefficients.clear();
        costFunctionCoefficients.add(-1.0);
        costFunctionCoefficients.add(2.0);
        costFunctionCoefficients.add(-4.0);
        OneDimensionalPolynomial subPolynomialY = new OneDimensionalPolynomial(2, costFunctionCoefficients, 'y');

        costFunction = new TwoDimensionalPolynomial(subPolynomialX, subPolynomialY);

        minimumPoint = MinimumPointFinder.findMinimumPoint(initialPoint, costFunction);
        System.out.println("Function to minimize is: " + costFunction.toString());
        System.out.println("Minimum point is: " + minimumPoint);
        System.out.println("Optimum value is: " + costFunction.evaluateAt(minimumPoint));
    }

}

// list of cost functions (more function can be added by adding corresponding enumeration in here)
enum CostFunction { Function1, Function2 };

class MinimumPointFinder {
    private static final double MAX_NORM2 = 1e-4;
    private static final double CONVERGENCE_STEP = 0.01;

    // finds the minimum point of the given function cf
    //   see https://en.wikipedia.org/wiki/Gradient_descent
    //   see https://www.youtube.com/watch?v=sXUf5kx2Gi8
    public static Point findMinimumPoint(Point initialPoint, RealFunction costFunction) throws Exception{

        Point result = new Point(initialPoint);

        // make a gradient descent for maximum of 1000 iteration
        Point gradientDescent;
        for (int k=1; k<1000; k++) {
            gradientDescent = costFunction.evaluateFirstPartialDerivativeAt(result);

            // l2-norm of the derivative of the function (see https://en.wikipedia.org/wiki/Norm_(mathematics)#Euclidean_norm)
            double norm2 =  gradientDescent.getNorm2();


            // if l2-norm of derivative is less than a tolerance then loop is finished (minimum is already found)
            if (norm2 < MAX_NORM2) {
                break;
            }

            // update result with step size CONVERGENCE_STEP
            for (int i=0; i<result.getDimensions(); i++) {
                result.setCoordinate(i, result.getCoordinate(i) - CONVERGENCE_STEP * gradientDescent.getCoordinate(i));
            }
        }

        return result;
    }

}

