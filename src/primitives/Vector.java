/*Beila Wellner 205823792 and Shira Yodaiken 315119461*/
package primitives;

import java.util.Objects;

public class Vector {
    //filed
    private Point3D _head;

    //constructors
    public Vector(){
        this.set_head(new Point3D());
    }

    public Vector(Point3D head) {
       /* if (head.equals(Point3D.ZERO))
        {
            throw new IllegalArgumentException("head cannot be explictli (0.0.0)");
        }*/
        this.set_head(head);
    }

    public Vector(double x, double y, double z) {
        this.set_head(new Point3D(new Coordinate(x), new Coordinate(y), new Coordinate(z)));
    }

    public Vector(Point3D p1,Point3D p2) {
       /* if(p1.equals(p2))
        {
            throw new IllegalArgumentException("p1 == p2");
        }*/
        set_head(new Point3D(
                new Coordinate(p2.get_x().subtract(p1.get_x())),
                new Coordinate(p2.get_y().subtract(p1.get_y())),
                new Coordinate(p2.get_z().subtract(p1.get_z()))));
    }

    public Vector(Vector other) {

        this.set_head(other.get_head());
    }

    //setters & getters
    public void set_head(Point3D head) {
        this._head = new Point3D(head);
    }
    public Point3D get_head() {
        return new Point3D(_head);
    }

    //methods
    public Vector add_vector(Vector v) {

       Point3D point = new Point3D(this.get_head().get_x().add(v.get_head().get_x()),
                        this.get_head().get_y().add(v.get_head().get_y()),
                        this.get_head().get_z().add(v.get_head().get_z()));
       return new Vector(point);
    }

    public Vector substract_vector(Vector v){
        double x = v.get_head().get_x().get()*-1;
        double y = v.get_head().get_y().get()*-1;
        double z = v.get_head().get_z().get()*-1;
        Point3D point=new Point3D(this.get_head().get_x().add(new Coordinate(x)),
                this.get_head().get_y().add(new Coordinate(y)),
                this.get_head().get_z().add(new Coordinate(z)));

        return new Vector(point);
    }

    public Vector scale(double n){//mult vector and skalar
        double x=(this.get_head().get_x().get())*n;
        double y=this.get_head().get_y().get()*n;
        double z=this.get_head().get_z().get()*n;
        return new Vector(new Point3D(new Coordinate(x),new Coordinate(y),new Coordinate(z)));
    }

    public double dotProduct(Vector v){//machpela skalarit
        double x1 = this.get_head().get_x().get();
        double y1 = this.get_head().get_y().get();
        double z1 = this.get_head().get_z().get();

        double x2 = v.get_head().get_x().get();
        double y2 = v.get_head().get_y().get();
        double z2 = v.get_head().get_z().get();

        return Util.uadd(
                Util.uadd(
                        Util.uscale(x1, x2),
                        Util.uscale(y1, y2)),
                Util.uscale(z1, z2));

       /*return ((this.get_head().get_x().get()*v.get_head().get_x().get())+
               (this.get_head().get_y().get()*v.get_head().get_y().get()) +
               (this.get_head().get_z().get()*v.get_head().get_z().get()));*/
    }

    public Vector crossProduct(Vector v){//machpela vectorit
        double x1 = this.get_head().get_x().get();
        double y1 = this.get_head().get_y().get();
        double z1 = this.get_head().get_z().get();

        double x2 = v.get_head().get_x().get();
        double y2 = v.get_head().get_y().get();
        double z2 = v.get_head().get_z().get();

        return new Vector(new Point3D(
                new Coordinate(Util.usubtract(Util.uscale(y1, z2), Util.uscale(z1, y2))),
                new Coordinate(Util.usubtract(Util.uscale(z1, x2), Util.uscale(x1, z2))),
                new Coordinate(Util.usubtract(Util.uscale(x1, y2), Util.uscale(y1, x2)))));
    }

    public double length(){
        return Math.sqrt((this.get_head().get_x().get()*this.get_head().get_x().get())+
                (this.get_head().get_y().get()*this.get_head().get_y().get())+
                (this.get_head().get_z().get()*this.get_head().get_z().get()));
    }

    public Vector normalize(){
        Vector v = this;
        v.set_head(new Point3D((new Coordinate( v.get_head().get_x()._coordinate/ v.length())),
        (new Coordinate( v.get_head().get_y()._coordinate/ v.length())),
        (new Coordinate( v.get_head().get_z()._coordinate/ v.length()))));
        return v;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        if (!(o instanceof Vector)) return false;
        Vector vector = (Vector) o;
        return _head.equals(vector._head);
    }

    @Override
    public int hashCode() {
        return Objects.hash(_head);
    }

    @Override
    public String toString() {
        return "Vector{" +
                "_head=" + _head +
                '}';
    }
}
