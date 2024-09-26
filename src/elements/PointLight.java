package elements;
import java.awt.Color;

import primitives.*;

public class PointLight extends Light
{
    protected Point3D _position;
    protected double _kc;
    protected double _kl;
    protected double _kq;


    // ***************** Constructors ********************** //

    public PointLight(Color color, Point3D position, double kc, double kl,
                      double kq) {
        super(color);
        this._position = position;
        this._kc = kc;
        this._kl = kl;
        this._kq = kq;
    }
    public PointLight() {
        super();
        this._position = new Point3D();
        this._kc = 0.1;
        this._kl = 0.1;
        this._kq = 0.1;
    }
    public PointLight(PointLight p) {
        super(p._color);
        this._position = p._position;
        this._kc = p._kc;
        this._kl = p._kl;
        this._kq = p._kq;
    }


    //***************** Getters/Setters ********************** //
    public Point3D get_position() {
        return _position;
    }
    public void set_position(Point3D _position) {
        this._position = _position;
    }
    public double get_kc() {
        return _kc;
    }
    public void set_kc(double _kc) {
        this._kc = _kc;
    }
    public double get_kl() {
        return _kl;
    }
    public void set_kl(double _kl) {
        this._kl = _kl;
    }
    public double get_kq() {
        return _kq;
    }
    public void set_kq(double _kq) {
        this._kq = _kq;
    }
    //***************** Administration  ******************** //
    @Override
    public String toString() {
        return "PointLight [_position=" + _position + ", _kc=" + _kc + ", _kl="
                + _kl + ", _kq=" + _kq + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        PointLight other = (PointLight) obj;
        if (Double.doubleToLongBits(_kc) != Double.doubleToLongBits(other._kc))
            return false;
        if (Double.doubleToLongBits(_kl) != Double.doubleToLongBits(other._kl))
            return false;
        if (Double.doubleToLongBits(_kq) != Double.doubleToLongBits(other._kq))
            return false;
        if (_position == null) {
            if (other._position != null)
                return false;
        } else if (!_position.equals(other._position))
            return false;
        return true;
    }

    @Override
    public Vector getL(Point3D point)
    {
        Point3D p=new Point3D(point);
        Vector v=new Vector(new Point3D(_position));
        v=p.subtract(v.get_head());
        //v=new Vector(p);
        v.normalize();
        return v;
    }


    private Color timesColor (Color c1,double c2)
    {
        double red1=c1.getRed()*c2;
        double green1=c1.getGreen()*c2;
        double blue1=c1.getBlue()*c2;
        if((int)red1>255)
            red1=255;
        if((int)green1>255)
            green1=255;
        if((int)blue1>255)
            blue1=255;
        if((int)red1<0)
            red1=0;
        if((int)green1<0)
            green1=0;
        if((int)blue1<0)
            blue1=0;
        return new Color((int)red1, (int)green1, (int)blue1);
    }


    @Override
    /*public  Color  getIntensity(Point3D point)
    {
        double v=_position.distance(point);
        double temp=(1.0/(_kc+_kl*v+_kq*v*v));
        if(temp>1.0) temp=1.0;
        return timesColor(_color, (temp));

    }*/

    public Color getIntensity(Point3D point) {

        int r = _color.getRed();
        int g = _color.getGreen();
        int b = _color.getBlue();

        double d = _position.distance(point);

        double k = 1 / (_kc + _kl * d + _kq * Math.pow(d, 2));

        if (k > 1) k = 1;

        return new Color((int) (r * k),
                (int) (g * k),
                (int) (b * k));
    }


    public  double distance(Point3D p)
    {
        return _position.distance(p);
    }
}
