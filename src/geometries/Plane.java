/*Beila Wellner 205823792 and Shira Yodaiken 315119461*/
package geometries;

import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Plane extends Geometry implements FlatGeometry
{    //fields
    protected Vector _normal;
    protected Point3D _point;

    //constructors
    public Plane() {
        super();
        this.set_point(new Point3D());
        this.set_normal(new Vector());
    }

    public Plane(Vector normal, Point3D point) {
        this._normal = normal;
        this._point = point;
    }


    public Plane(Plane p)
    {
        super(p.clr,p.material);
        this._point = new Point3D(p._point);
        _normal=new Vector(p._normal);
    }

    public Plane(Point3D point1, Point3D point2, Point3D point3){//cross-product between two vectors created from the three points
        Vector vector1 = new Vector(point1, point2);
        Vector vector2 = new Vector(point1, point3);
        Vector normal = new Vector(vector1.crossProduct(vector2));
        normal=normal.normalize();
        normal=normal.scale(-1);
        set_normal((normal));
        set_point(point1);
    }

    public Plane(Color clr, Point3D point, Vector normal) {
        super(clr);
        this._point = point;
        this._normal = normal;
    }

    public Plane(Color clr, Material m, Point3D point, Vector normal) {
        super(clr,m);
        this._point = point;
        this._normal = normal;
    }
    //getters & setters
    public Vector get_normal() {
        return _normal;
    }
    public Point3D get_point() {
        return _point;
    }
    public void set_normal(Vector _normal) {
        this._normal = _normal;
    }
    public void set_point(Point3D _point) {
        this._point = _point;
    }

    //methods
    @Override
    public Vector getNormal(Point3D point) {
        return new Vector(get_normal());
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersectionPoints = new ArrayList<Point3D>();
        Point3D P0 = ray.get_p0();
        Point3D Q0 = this.get_point();
        Vector N = this.getNormal(null);
        Vector V = ray.get_directionVector();

        if(N.dotProduct(V)==0)//if they are vertical - there is no intersections point
        {
            return intersectionPoints;
        }

        Vector u = new Vector(Q0, P0);

        double t = (N.dotProduct(u) * -1) / N.dotProduct(V);

        /*if (t >= 0) {
            V.scale(t);
            Point3D p = P0.addVector(V);
            intersectionPoints.add(p);
        }*/

        Point3D p = P0.addVector(new Vector(V.scale(t)));
        intersectionPoints.add(p);

        return intersectionPoints;
    }

}
