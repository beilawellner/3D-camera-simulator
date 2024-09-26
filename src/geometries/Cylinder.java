/*Beila Wellner 205823792 and Shira Yodaiken 315119461*/
package geometries;

import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

public class Cylinder extends Tube {
    //fields
    double _hight;

    //constructors
    public Cylinder(Point3D axisPoint, Vector axisDirection, double hight) {
        super(axisPoint, axisDirection);
        this._hight = hight;
    }

    //getters & setters
    public double get_hight() {
        return _hight;
    }
    public void set_hight(double _hight) { this._hight = _hight; }
}
