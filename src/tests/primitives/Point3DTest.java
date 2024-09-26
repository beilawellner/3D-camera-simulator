/*Beila Wellner 205823792 and Shira Yodaiken 315119461*/
package tests.primitives;

import static org.junit.Assert.*;
import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Vector;

public class Point3DTest {

    @Test
    public void subtract() {
        Point3D p1=new Point3D(new Coordinate(1),new Coordinate(0),new Coordinate(0));
        Point3D p2=new Point3D(new Coordinate(1),new Coordinate(2),new Coordinate(3));
        Vector help=new Vector(p1.subtract(p2));
        double d=help.get_head().get_x().get();
        assertEquals(0, d, 0);
        double e=help.get_head().get_y().get();
        assertEquals(2, e, 0);
        double f=help.get_head().get_z().get();
        assertEquals(3, f, 0);
    }

    @Test
    public void addVactor() {
        Point3D p1=new Point3D(new Coordinate(1),new Coordinate(0),new Coordinate(0));
        Point3D p2=new Point3D(new Coordinate(1),new Coordinate(2),new Coordinate(3));
        Vector v=new Vector(p2);
        Point3D help=new Point3D(p1.addVector(v));
        double d=help.get_x().get();
        assertEquals(2, d, 0);
        double e=help.get_y().get();
        assertEquals(2, e, 0);
        double f=help.get_z().get();
        assertEquals(3, f, 0);
    }

    @Test
    public void distance() {
        Point3D p1=new Point3D(new Coordinate(1),new Coordinate(0),new Coordinate(0));
        Point3D p2=new Point3D(new Coordinate(1),new Coordinate(4),new Coordinate(3));
        double d=p1.distance(p2);
        assertEquals(5, d, 0);
    }
}