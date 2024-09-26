/*Beila Wellner 205823792 and Shira Yodaiken 315119461*/
package geometries;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

public class Tube extends RadialGeometry {
    //fields
    Point3D _axisPoint;
    Vector _axisDirection;

    //constructors
    public Tube(Point3D axisPoint, Vector axisDirection) {
        super(0.0);
        this._axisPoint = axisPoint;
        this._axisDirection = axisDirection;
    }

    //getters & setters
    public Point3D get_axisPoint() {
        return _axisPoint;
    }
    public Vector get_axisDirection() {
        return _axisDirection;
    }

    //methods
    @Override
    public Vector getNormal(Point3D point) {
        return null ;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        return null;
    }
}

