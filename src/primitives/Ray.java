/*Beila Wellner 205823792 and Shira Yodaiken 315119461*/
package primitives;

import java.util.Objects;

public class Ray {
    //fields
    Point3D _p0;
    Vector _directionVector;

    //getters & setters
    public Point3D get_p0() { return _p0; }
    public void set_p0(Point3D p0) { this._p0 = p0; }
    public Vector get_directionVector() { return _directionVector; }
    public void set_directionVector(Vector directionVector) { this._directionVector = directionVector; }

    //constructors
    public Ray() {
        this.set_p0(new Point3D());
        this.set_directionVector(new Vector());
    }

    public Ray(Point3D poo, Vector direction) {
        this.set_p0(new Point3D(poo));
        this.set_directionVector(new Vector(direction).normalize());
    }

    public Ray(Ray a) {
        this.set_p0(a.get_p0());
        this.set_directionVector(a.get_directionVector());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ray)) return false;
        Ray ray = (Ray) o;
        return get_p0().equals(ray.get_p0()) &&
                get_directionVector().equals(ray.get_directionVector());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_p0(), get_directionVector());
    }

    @Override
    public String toString() {
        return "Ray{" +
                "_p0=" + _p0 +
                ", _directionVector=" + _directionVector +
                '}';
    }
}
