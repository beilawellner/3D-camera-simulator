package elements;

import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;

import java.util.Objects;

public class Camera {
    //fields
    Point3D _p0;
    Vector _vUp;
    Vector _vToward;
    Vector _vRight;

    //getters & setters
    public Point3D get_p0() { return _p0; }

    public void set_p0(Point3D _p0) { this._p0 = _p0; }

    public Vector get_vUp() { return _vUp; }

    public void set_vUp(Vector _vUp) { this._vUp = _vUp; }

    public Vector get_vToward() { return _vToward; }

    public void set_vToward(Vector _vToward) { this._vToward = _vToward; }

    public Vector get_vRight() { return _vRight; }

    public void set_vRight(Vector _vRight) { this._vRight = _vRight; }

    //constructors
    public Camera(){
        set_p0(new Point3D(0,0,0));
        set_vUp(new Vector(0,1,0));
        set_vToward(new Vector(0,0,-1));
        set_vRight( new Vector(new Point3D(1,0,0)));

       //set_vRight(get_vUp().crossProduct(get_vToward()));
    }


    public Camera(Camera camera){
        this.set_p0(camera.get_p0());
        this.set_vUp(camera.get_vUp());
        this.set_vToward(camera.get_vToward());
        this.set_vRight(camera.get_vRight());
    }

    public Camera(Point3D p0, Vector up, Vector to){
        this.set_p0(new Point3D(p0));
        this.set_vUp(new Vector(up).normalize());
        this.set_vToward(new Vector(to).normalize());
        this.set_vRight(up.crossProduct(to).normalize());
    }

    public Camera(Point3D p0, Vector up, Vector to, Vector right){
        this.set_p0(new Point3D(p0));
        this.set_vUp(new Vector(up));
        this.set_vToward(new Vector(to));
        this.set_vRight(new Vector(right));
    }

    public Camera( Vector vUp, Vector vToward, Vector vRight) {
        this._p0 = new Point3D(0,0,0);
        this._vUp = (vUp);
        this._vToward = (vToward);
        this._vRight = (vRight);
    }

    //methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Camera)) return false;
        Camera camera = (Camera) o;
        return get_p0().equals(camera.get_p0()) &&
                get_vUp().equals(camera.get_vUp()) &&
                get_vToward().equals(camera.get_vToward()) &&
                get_vRight().equals(camera.get_vRight());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_p0(), get_vUp(), get_vToward(), get_vRight());
    }

    @Override
    public String toString() {
        return "Camera{" +
                "_p0=" + _p0 +
                ", _vUp=" + _vUp +
                ", _vToward=" + _vToward +
                ", _vRight=" + _vRight +
                '}';
    }

    public Ray constructRayThrowAPixel(int Nx, int Ny, double x, double y, double screenDist, double screenWidth,
                                       double screenHeight){

        // Calculating the image center
        Vector vTo = new Vector(get_vToward().normalize());
        Vector vRight = new Vector(get_vRight().normalize());
        Vector vUp = new Vector(get_vUp().normalize());

        Point3D pc = get_p0().addVector(vTo.scale(screenDist));

        // Calculating the length of each pixel
        double Rx = screenWidth / Nx;
        double Ry = screenHeight / Ny;

        // Calculating P - the intersection point
        double A = ((x-(Nx/2.0))*Rx+(Rx/2.0));
        double B = ((y-(Ny/2.0))*Ry+(Ry/2.0));
        Vector helpRight = new Vector(vRight.scale(A*(-1)));
        Vector helpUp = new Vector(vUp.scale(B));
        Point3D p = new Point3D(pc.addVector(new Vector(helpRight.substract_vector(helpUp))));
        Ray r = new Ray(p, new Vector(get_p0(), p));

        return r;
    }


}
