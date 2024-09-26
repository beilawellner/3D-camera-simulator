/*Beila Wellner 205823792 and Shira Yodaiken 315119461*/
package primitives;

import java.util.Objects;

public class Point2D {
    //fields
    protected Coordinate _x;
    protected Coordinate _y;

    //constructors
    public Point2D() {
        set_x(Coordinate.ZERO);
        set_y(Coordinate.ZERO);
    }

    public Point2D(Coordinate x, Coordinate y) {
        this.set_x(x);
        this.set_y(y);
    }

    public Point2D(double x, double y) {
        this(new Coordinate(x),
                new Coordinate(y));
    }

    public Point2D(Point2D other){
        set_x(other.get_x());
        set_y(other.get_y());
    }

    //getters & setters
    public void set_x(Coordinate x) {
        this._x = new Coordinate(x);
    }
    public void set_y(Coordinate y) {
        this._y = new Coordinate (y);
    }
    public Coordinate get_x() {
        return new Coordinate(_x);
    }
    public Coordinate get_y() {
        return new Coordinate(_y);
    }

    //methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Point2D)) return false;
        Point2D point2D = (Point2D) o;
        return _x.equals(point2D._x) &&
                _y.equals(point2D._y);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_x, _y);
    }

    @Override
    public String toString() {
        return "Point2D{" +
                "_x=" + _x +
                ", _y=" + _y ;
    }
}
