package tests.geometries;

import elements.Camera;
import geometries.Plane;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class PlaneTest {

    @Test
    void getNormal() {
        Plane p= new Plane(new Point3D(1,0,0),new Point3D(0,1,0), new Point3D(0,0,2));
        assertEquals(p.getNormal(new Point3D(1,0,0)), new Vector(2,2,1).normalize());
    }

    @Test
    void findIntersections(){
        // creating the expected values
        List<Point3D> answerList = new ArrayList<Point3D>();
        Point3D answerPoint = new Point3D(0, 0, -200);
        answerList.add(answerPoint);

        // building the plane
        Point3D directionPoint = new Point3D(0, 0, -1);
        Point3D planePoint = new Point3D(0, 100, -200);
        Vector direction = new Vector(new Point3D(0, 0, -1));
        Plane plane = new Plane(direction,planePoint);

        // building the ray that will intersect the plane
        Point3D centerPoint = new Point3D(0,0,0);
        Vector vector = new Vector(0, 0, -5);
        Ray ray = new Ray(centerPoint, vector);

        // testing the findIntersection function
        List<Point3D> list = new ArrayList<Point3D>();
        list = plane.findIntersections(ray);
        assertEquals(answerList, list);
    }
}