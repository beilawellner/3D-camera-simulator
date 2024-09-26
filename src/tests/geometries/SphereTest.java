package tests.geometries;

import elements.Camera;
import geometries.Sphere;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class SphereTest {

    @org.junit.Test
    public void getNormal() {
        Sphere s= new Sphere(new Point3D(2,3,4));
        Vector normal = new Vector(s.getNormal(new Point3D(6,7,8)));
        Vector expected = new Vector(4,4,4).normalize();
        assertEquals(normal,expected);
    }

    @org.junit.Test
    public void testSphereIntersections1() {//no Intersections points

        // creating the expected values
        List<Point3D> answerList1 = new ArrayList<Point3D>();

        Point3D answerPoint1 = new Point3D(0, 0, -200);
        Point3D answerPoint2 = new Point3D(0, 0, -600);

        // building the spheres
        Point3D p1 = new Point3D(0, 0, -400);
        Point3D p2 = new Point3D(p1);
        Point3D centerPoint = new Point3D(0,0,0);

        Vector direction1 = new Vector(50, -50, -50);
        Sphere sphere1 = new Sphere(200, p1);

        // building the ray that will intersect the spheres
        Ray ray1 = new Ray(centerPoint, direction1);

        // testing the findIntersection functions
        List<Point3D> list1 = new ArrayList<Point3D>();
        list1 = sphere1.findIntersections(ray1);
        assertEquals(answerList1, list1);
    }

    @org.junit.Test
    public void testSphereIntersections2() {

        // creating the expected values
        List<Point3D> answerList2 = new ArrayList<Point3D>();

        Point3D answerPoint1 = new Point3D(0, 0, -200);
        Point3D answerPoint2 = new Point3D(0, 0, -600);

        answerList2.add(answerPoint1);
        answerList2.add(answerPoint2);

        // building the spheres
        Point3D p1 = new Point3D(0, 0, -400);
        Point3D p2 = new Point3D(p1);
        Point3D centerPoint = new Point3D(0,0,0);

        Vector direction2 = new Vector(0, 0, -5);
        Sphere sphere2 = new Sphere(200, p2);

        // building the ray that will intersect the spheres
        Ray ray2 = new Ray(centerPoint, direction2);

        // testing the findIntersection function
        List<Point3D> list2 = new ArrayList<Point3D>();
        list2 = sphere2.findIntersections(ray2);
        assertEquals(answerList2, list2);
    }

}