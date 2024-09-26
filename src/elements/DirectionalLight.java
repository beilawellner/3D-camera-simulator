package elements;
import primitives.*;

import java.awt.Color;

public class DirectionalLight extends Light
{
    protected Vector direction;

    // ***************** Constructors ********************** //
    public DirectionalLight() {
        super();
        this.direction = new Vector();
        direction.normalize();
    }

    public DirectionalLight(Color color, Vector direction) {
        super(color);
        this.direction = direction;
        direction.normalize();
    }
    public DirectionalLight(DirectionalLight dl) {
        super(dl._color);
        this.direction = dl.direction;
        direction.normalize();

    }

    //***************** Getters/Setters ********************** //
    public Vector getDirection() {
        return direction;
    }
    public void setDirection(Vector direction) {
        this.direction = direction;
    }
    //***************** Administration  ******************** //
    @Override
    public String toString() {
        return "DirectionalLight [direction=" + direction + "]";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        DirectionalLight other = (DirectionalLight) obj;
        if (direction == null) {
            if (other.direction != null)
                return false;
        } else if (!direction.equals(other.direction))
            return false;
        return true;
    }

    @Override
    public Vector getL(Point3D point)
    {
        return direction;
    }

    @Override
    public  Color  getIntensity(Point3D point)
    {
        return _color;
    }

    public  double distance(Point3D p)
    {
        return 0;
    }


}
