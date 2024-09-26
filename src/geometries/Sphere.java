/*Beila Wellner 205823792 and Shira Yodaiken 315119461*/
package geometries;

import primitives.Material;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Sphere extends RadialGeometry{
    //field
    Point3D _center;
    public final static double EPSILON = 0.0001;


    //constructors
    public Sphere(Color clr, double radius, Point3D center) {
        super(clr);
        this._radius = radius;
        this._center = new Point3D(center);
    }
    public Sphere(Color clr, Material material, double radius, Point3D center) {
        super(clr, material);
        this._radius = radius;
        this._center = center;
    }

    public Sphere() {
        super(0.0);
        this._center = new Point3D();
    }

    public Sphere(Sphere sphere) {
        super(sphere.get_radius());
        set_center(sphere._center);
    }

    public Sphere(double radius, Point3D center) {
            super(radius);
            set_center(center);
    }

    public Sphere(Point3D center) {
                super(0.0);
                set_center(center);
    }

    //getters & setters
    public Point3D get_center() {
        return _center;
    }
    public void set_center(Point3D _center) {
        this._center = _center;
    }

    //methods
    @Override
    public Vector getNormal(Point3D point) {
        Vector normal = new Vector(this.get_center(), point).normalize();//vector from the center of sphere to the point
        return normal;
    }

   /* @Override
    public List<Point3D> findIntersections(Ray ray) {
        List<Point3D> intersectionPoints= new ArrayList<Point3D>(2);

        Vector V = ray.get_directionVector();
        Point3D P0 = new Point3D(ray.get_p0());
        Vector L = new Vector(P0, this.get_center());
        double tm = L.dotProduct(V);
        double d = Math.sqrt((L.length()*L.length()) - (tm*tm));


        if (d > this.get_radius()) {
            return intersectionPoints; // return null;
        }

        double th = Math.sqrt((this.get_radius()*this.get_radius()) - (d*d));

        double t1 = tm - th;
        double t2 = tm + th;

        if (t1 >= 0){
            Vector V1 = new Vector(V.scale(t1));
            Point3D P1 =new Point3D(P0.addVector(V1));
            intersectionPoints.add(P1);
        }

        if (t2 >= 0){
            Vector V2 = new Vector(V.scale(t2));
            Point3D P2 =new Point3D(P0.addVector(V2));
            intersectionPoints.add(P2);
        }

        return intersectionPoints;
    }*/

    public List<Point3D> findIntersections(Ray r)
    {

        List<Point3D > lst= new ArrayList<Point3D>();

        //V.normalize();
        Point3D p0= new Point3D(r.get_p0());
        Point3D cen= new Point3D(_center);
        cen.subtract(p0);
        Vector L= new Vector(cen);
        double l=L.length();

        double tm=L.dotProduct(r.get_directionVector());

        if (Math.abs(l - _radius) < EPSILON) {
            // Ray starts at the sphere
            lst.add(p0); // add the head of the ray
            if (tm < EPSILON)
                // It is either out of sphere or tangent to sphere
                return lst;
            // ray goes through the sphere - find the 2nd intersection point
            lst.add(new Point3D(p0.addVector(new Vector(r.get_directionVector().scale(tm + tm)))));
            return lst;
        } else if (l < _radius) {
            // ray starts inside the sphere - there must be exactly one
            // intersection point - lets calculate it
            //
            // d2 = l2 - tm2
            // th = r2 - d2 = r2 - l2 + tm2
            double th = Math.sqrt((_radius*_radius) - (l*l) + tm * tm);
            lst.add(new Point3D(p0.addVector(new Vector(r.get_directionVector().scale(tm + th)))));
            return lst;
        } else {
            // Ray starts outside the sphere
            if (tm <EPSILON) {
                // If it is close to zero - L & D are orthogonal and if it is
                // negative than the sphere is in the back of the ray - no
                // intersections in both cases
                return lst;
            }
            double d2 = l * l - tm * tm;
            if (Math.abs((_radius*_radius) - d2) < EPSILON *EPSILON) {
                lst.add(new Point3D(p0.addVector(new Vector(r.get_directionVector().scale(tm)))));
            } else if (d2 > (_radius*_radius)) {
                return lst;
            } else {
                double th = Math.sqrt((_radius*_radius) - d2);
                lst.add(new Point3D(p0.addVector(new Vector(r.get_directionVector().scale(tm - th)))));
                lst.add(new Point3D(p0.addVector(new Vector(r.get_directionVector().scale(tm + th)))));
            }
            return lst;
        }

    }
}
