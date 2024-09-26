/*Beila Wellner 205823792 and Shira Yodaiken 315119461*/
package tests.primitives;

import static org.junit.Assert.*;
import org.junit.Test;
import primitives.Coordinate;
import primitives.Point3D;
import primitives.Vector;

public class VectorTest {

    @Test
    public void testadd_vector() {
        Vector v1=new Vector(new Point3D(new Coordinate(1),new Coordinate(0),new Coordinate(0)));
        Vector v2=new Vector(new Point3D(new Coordinate(1),new Coordinate(2),new Coordinate(3)));
        Vector help=new Vector(v1.add_vector(v2));
        assertEquals(2, help.get_head().get_x().get(), 0);
        assertEquals(2, help.get_head().get_y().get(), 0);
        assertEquals(3, help.get_head().get_z().get(), 0);
    }

    @Test
    public void testsubstract_vector() {
        Vector v1=new Vector(new Point3D(new Coordinate(1),new Coordinate(0),new Coordinate(0)));
        Vector v2=new Vector(new Point3D(new Coordinate(1),new Coordinate(2),new Coordinate(3)));
        Vector help=new Vector(v1.substract_vector(v2));
        assertEquals(0, help.get_head().get_x().get(), 0);
        assertEquals(-2, help.get_head().get_y().get(), 0);
        assertEquals(-3, help.get_head().get_z().get(), 0);

    }

    @Test
    public void testmultVectorSkalar() {
        Vector v1=new Vector(new Point3D(new Coordinate(1),new Coordinate(0),new Coordinate(3)));
        Vector help=new Vector(v1.scale(10));
        assertEquals(10, help.get_head().get_x().get(), 0);
        assertEquals(0, help.get_head().get_y().get(), 0);
        assertEquals(30, help.get_head().get_z().get(), 0);

    }

    @Test
    public void testdotProduct() {
        Vector v1=new Vector(new Point3D(new Coordinate(1),new Coordinate(0),new Coordinate(2)));
        Vector v2=new Vector(new Point3D(new Coordinate(1),new Coordinate(2),new Coordinate(3)));
        double d= v1.dotProduct(v2);
        assertEquals(7, d, 0);
    }

    @Test
    public void testcrossProduct() {
        Vector v1=new Vector(new Point3D(new Coordinate(1),new Coordinate(0),new Coordinate(0)));
        Vector v2=new Vector(new Point3D(new Coordinate(0),new Coordinate(1),new Coordinate(0)));
        Vector v3= new Vector(v2.crossProduct(v1));
        Vector v4=new Vector(new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(-1)));
        assertEquals(v4,v3);
    }

    @Test
    public void testlength() {
        Vector v1=new Vector(new Point3D(new Coordinate(0),new Coordinate(0),new Coordinate(2)));
        double d= v1.length();
        assertEquals(2, d, 0);
    }

    @Test
    public void testnormalize() {
        Vector v1=new Vector(new Point3D(new Coordinate(0),new Coordinate(2),new Coordinate(0)));
        Vector help=new Vector(v1.normalize());
        Vector v2=new Vector(new Point3D(new Coordinate(0),new Coordinate(1),new Coordinate(0)));
        assertEquals(v2, help);
        assertEquals(1, help.length(), 0);
    }
    @Test
    public  void Vectortest2points()
    {
        Point3D p = new Point3D(2,3,4);
        try {
            Vector v = new Vector(p, p);
            fail("expected illegal exception");
        }
        catch  (IllegalArgumentException e)
        {
            System.out.println(e.getMessage());
            assertTrue(true);
        }
    }
}