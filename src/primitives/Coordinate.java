/*Beila Wellner 205823792 and Shira Yodaiken 315119461*/
package primitives;

import static primitives.Util.*;

public final class Coordinate {
    //private static final double EPSILON = 0.0000001;
    protected double _coordinate;

    public static Coordinate ZERO = new Coordinate(0.0);

    /********** Constructors ***********/
    public Coordinate(double coord) {
        // if it too close to zero make it zero
        _coordinate = alignZero(coord);
    }

    public Coordinate(Coordinate other) {
        _coordinate = other._coordinate;
    }

    /************** Getters/Setters *******/
    public double get() {
        return _coordinate;
    }

    /*************** Admin *****************/
    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (this == obj) return true;
        if (obj == null) return false;
        if (!(obj instanceof Coordinate)) return false;
        return usubtract(_coordinate, ((Coordinate)obj)._coordinate) == 0.0;
    }

    @Override
    public String toString() {
        return "" + _coordinate;
    }

    /************** Operations ***************/
    public Coordinate subtract(Coordinate other) {
        return new Coordinate(usubtract(_coordinate, other._coordinate));
    }

    public Coordinate add(Coordinate other) {
        return new Coordinate(uadd(_coordinate, other._coordinate));
    }

    public Coordinate scale(double num) {
        return new Coordinate(uscale(_coordinate, num));
    }

    public Coordinate multiply(Coordinate other) {
        return new Coordinate(uscale(_coordinate, other._coordinate));
    }

}






/*package primitives;

import java.util.Objects;

public final class Coordinate {
    protected double _coordinate;

    public Coordinate(double _coordinate) {
        this._coordinate = _coordinate;
    }

    public void set_coordinate(double _coordinate) {
        this._coordinate = _coordinate;
    }

    public void add(double x) {
        this._coordinate+= x;
    }

    public void substract(double x){
        this._coordinate-=x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Coordinate)) return false;
        Coordinate that = (Coordinate) o;
        return Double.compare(that._coordinate, _coordinate) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(_coordinate);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "_coordinate=" + _coordinate +
                '}';
    }


}*/
