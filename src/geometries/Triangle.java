/*Beila Wellner 205823792 and Shira Yodaiken 315119461*/
package geometries;

import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Triangle extends Geometry implements FlatGeometry
{    //fields
    Point3D _point1;
    Point3D _point2;
    Point3D _point3;

    //constructors
    public Triangle(Color clr, Point3D point1, Point3D point2, Point3D point3) {
        super(clr);
        set_point1(point1);
        set_point2(point2);
        set_point3(point3);
    }

    public Triangle(Color clr,Material m, Point3D point1, Point3D point2, Point3D point3) {
        super(clr,m);
        set_point1(point1);
        set_point2(point2);
        set_point3(point3);
    }

    public Triangle(Point3D p1, Point3D p2, Point3D p3) {
        set_point1(p1);
        set_point2(p2);
        set_point3(p3);
    }

    public Triangle() {
        set_point1(new Point3D());
        set_point2(new Point3D());
        set_point3(new Point3D());
    }

    public Triangle(Triangle other) {
        this.set_point1(other.get_point1());
        this.set_point2(other.get_point2());
        this.set_point3(other.get_point3());
    }

    //getters & setters
    public Point3D get_point1() {
        return _point1;
    }
    public Point3D get_point2() {
        return _point2;
    }
    public Point3D get_point3() {
        return _point3;
    }
    public void set_point1(Point3D _point1) {
        this._point1 = _point1;
    }
    public void set_point2(Point3D _point2) {
        this._point2 = _point2;
    }
    public void set_point3(Point3D _point3) {
        this._point3 = _point3;
    }

    //methods
    @Override
    public Vector getNormal(Point3D point) {
        Vector vector1 = new Vector(get_point1(),get_point2());
        Vector vector2 = new Vector(get_point1(),get_point3());
        Vector normal = new Vector(vector1.crossProduct(vector2));
        normal.normalize();
        normal.scale(-1);
        return normal;
    }

    @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersectionPoints = new ArrayList<Point3D>(1);

        // Intersecting the triangular plane
        Point3D P0 = ray.get_p0();
        Vector N = getNormal(null);

        Plane plane = new Plane(N, get_point3());
        if (plane.findIntersections(ray).isEmpty())//if there is no intersection points
            return intersectionPoints;

        Point3D intersectionPlane = plane.findIntersections(ray).get(0);

        // Checking if the interseculating point is bounded by the triangle
        Vector P_P0 = new Vector(P0, intersectionPlane);

        // Checking 1/3 triangular side
        Vector V1_1 = new Vector(P0, this.get_point1());
        Vector V2_1 = new Vector(P0, this.get_point2());
        Vector N1 = V1_1.crossProduct(V2_1);
        N1=N1.normalize();
        double S1 = -P_P0.dotProduct(N1);

        // Checking 2/3 triangular side
        Vector V1_2 = new Vector(P0, this.get_point2());
        Vector V2_2 = new Vector(P0, this.get_point3());
        Vector N2 = V1_2.crossProduct(V2_2);
        N2=N2.normalize();
        double S2 = -P_P0.dotProduct(N2);

        // Checking 1/3 triangular side
        Vector V1_3 = new Vector(P0, this.get_point3());
        Vector V2_3 = new Vector(P0, this.get_point1());
        Vector N3 = V1_3.crossProduct(V2_3);
        N3=N3.normalize();
        double S3 = -P_P0.dotProduct(N3);

        if (((S1 > 0) && (S2 > 0) && (S3 > 0)) ||
                ((S1 < 0) && (S2 < 0) && (S3 < 0))) {
            intersectionPoints.add(intersectionPlane);
        }

        return intersectionPoints;
    }
}