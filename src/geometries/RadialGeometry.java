/*Beila Wellner 205823792 and Shira Yodaiken 315119461*/
package geometries;

import primitives.Material;
import primitives.Point3D;
import primitives.Vector;

import java.awt.*;

public abstract class RadialGeometry extends Geometry {
    //fields
    protected double _radius;
    protected Color clr;
    protected Material material;
    //constructors
    public RadialGeometry(double radius){
        this._radius = radius;
    }

    public RadialGeometry() {
        super();
        this._radius=0;
    }

    public RadialGeometry(Color color,double radius)
    {
        super(color);
        this._radius = radius;
    }

    public RadialGeometry(RadialGeometry radius){
        super(radius.getColor());
        this._radius = radius._radius;
    }



    public RadialGeometry(Color clr) {
        super(clr);
    }

    public RadialGeometry(Color clr, Material material) {
        super(clr,material);

    }

    //getters & setters
    public double get_radius() {
        return _radius;
    }
    public void set_radius(double _radius) {
        this._radius = _radius;
    }

    public abstract Vector getNormal(Point3D sec);


}
