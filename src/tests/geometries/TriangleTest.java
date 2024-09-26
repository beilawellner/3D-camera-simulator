package tests.geometries;

import elements.Camera;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class TriangleTest {

    @org.junit.Test
    public void getNormal() {
        Triangle t= new Triangle(new Point3D(1,0,0),new Point3D(0,1,0), new Point3D(0,0,2));
        Vector normal = t.getNormal(new Point3D());
        Vector expectedNormal = new Vector(2,2,1).normalize();
        assertEquals(normal, expectedNormal);
    }

    @org.junit.Test
    public void findIntersections1()
    {
        Vector v=new Vector(new Point3D(50,-50,-100));
        v.normalize();
        Point3D p=new Point3D(0,0,0);
        Ray ray=new Ray(p,v);

        Triangle triangle=new Triangle(new Point3D(100,-100,-200),new Point3D(-100,-100,-200),new Point3D(0,100,-200));
        java.util.List<Point3D> list=triangle.findIntersections(ray);
        assertEquals(list.size(),0);
    }

    @org.junit.Test
    public void findIntersections2() {

        // creating the expected values
        List<Point3D> answerList = new ArrayList<Point3D>();
        Point3D answerPoint = new Point3D(0, 0, -200);
        answerList.add(answerPoint);

        // building the triangle
        Point3D p1 = new Point3D(0, 100, -200);
        Point3D p2 = new Point3D(100, -100, -200);
        Point3D p3 = new Point3D(-100, -100, -200);

        Triangle t1 = new Triangle(p1,p2,p3);
        Triangle t2 = new Triangle(t1);

        // building the ray that will intersect the triangle
        Point3D centerPoint = new Point3D(0,0,0);
        Vector vector = new Vector(0, 0, -5);
        Ray ray = new Ray(centerPoint, vector);

        // testing the findIntersection function
        List<Point3D> list = new ArrayList<Point3D>();
        list = t2.findIntersections(ray);
        assertEquals(answerList, list);
    }
}