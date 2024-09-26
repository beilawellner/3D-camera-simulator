/*Beila Wellner 205823792 and Shira Yodaiken 315119461*/
package primitives;

import java.util.Objects;

public class Point3D extends Point2D {

    //fields
    public static final Point3D ZERO = new Point3D() ;
    private Coordinate _z;

    //constructors
    public Point3D() {
        super();
        this.set_z(Coordinate.ZERO);
    }

    public Point3D(Coordinate x, Coordinate y, Coordinate z) {
        super(x, y);
        this.set_z(z);
    }

    public Point3D(double x, double y, double z) {
        this(new Coordinate(x),
                new Coordinate(y),
                new Coordinate(z));
    }

    public Point3D(Point3D other){
        super(other.get_x(),other.get_y());
        this.set_z(other.get_z());
    }

    //getters and setters
    public void set_z(Coordinate z) {
        this._z = new Coordinate(z);
    }
    public Coordinate get_z() { return new Coordinate(_z); }

    //methods
    public Vector subtract(Point3D point){

        return new Vector (this,point) ;
    }

    public Point3D addVector(Vector v){
        Point3D endVector=v.get_head();
        Point3D result=new Point3D(
                this.get_x().add(endVector.get_x()),
                this.get_y().add(endVector.get_y()),
                this.get_z().add(endVector.get_z()));
        return result;
    }

    public double distance(Point3D point){
        double x=Util.uscale((point.get_x().get()-this.get_x().get()),(point.get_x().get()-this.get_x().get()));
        double y=Util.uscale((point.get_y().get()-this.get_y().get()),(point.get_y().get()-this.get_y().get()));
        double z=Util.uscale((point.get_z().get()-this.get_z().get()),(point.get_z().get()-this.get_z().get()));
        return Math.sqrt(Util.uadd(z,Util.uadd(x,y)));
    }

    @Override
    public boolean equals(Object o) {
        if (o == null) return false;
        if (this == o) return true;
        if (!(o instanceof Point3D)) return false;
        if (!super.equals(o)) return false;
        Point3D point3D = (Point3D) o;
        return _z.equals(point3D._z);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), _z);
    }

    @java.lang.Override
    public java.lang.String toString() {
        return super.toString()+
                " _z=" + get_z() +
                '}';
    }
}
