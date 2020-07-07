
import java.util.ArrayList;

public class Point{
    private ArrayList<Double> coordinates = new ArrayList<>();

    public Point(ArrayList<Double> coordinates){
        for (Double coordinate : coordinates){
            this.coordinates.add(new Double(coordinate.doubleValue()));
        }
    }

    public Point(Point point){
        for(Double coordinate : point.coordinates){
            coordinates.add(new Double(coordinate).doubleValue());
        }
    }

    public Point(int dimensions){
        for(int i = 0; i < dimensions; i++){
            coordinates.add(new Double(0));
        }
    }

   public int getDimensions(){return coordinates.size();}

    public void setCoordinate(int index, double value){
        coordinates.set(index, value);
    }

    public double getCoordinate(int index){
        return coordinates.get(index);
    }

    public double getNorm2(){
        double result = 0;
        for(Double coordinate : coordinates){
            result += coordinate*coordinate;
        }
        return Math.sqrt(result);
    }

    @Override
    public String toString() {
        String result = "(";
        for(int i = 0; i < coordinates.size(); i++){
            result += coordinates.get(i).toString();
            if (i != (coordinates.size() -1)) result += ", ";
        }
        result += ")";
        return  result;
    }
}
