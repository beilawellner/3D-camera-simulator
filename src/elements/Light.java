package elements;
import java.awt.Color;
import primitives.*;

public abstract class Light
{
    Color _color;

    // ***************** Constructors ********************** //
    public Light(Color color)
    {
        this._color = color;
    }

    public Light(Light l)
    {
        this._color = l._color;
    }
    public Light()
    {
        _color=new Color(255,255,255);
    }
    // ***************** Getters/Setters ********************** //

    public Color getl0()
    {
        return _color;
    }

    public void setl0(Color l0)
    {
        this._color = l0;
    }
    // ***************** Administration  ******************** //

    @Override
    public String toString()
    {
        return "Light [l0=" + _color + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Light other = (Light) obj;
        if (_color == null) {
            if (other._color != null)
                return false;
        } else if (!_color.equals(other._color))
            return false;
        return true;
    }

    public abstract Vector getL(Point3D point);

    public abstract double distance(Point3D p);
    public abstract Color  getIntensity(Point3D point);
}

