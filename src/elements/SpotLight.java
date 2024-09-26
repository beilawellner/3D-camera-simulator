package elements;
import java.awt.Color;

import primitives.*;

public class SpotLight extends PointLight
{
    protected Vector _direction;
    // ***************** Constructors ********************** //
    public SpotLight()
    {
        super();
        this._direction = new Vector();
        _direction=_direction.normalize();
    }

    public SpotLight(Color color, Point3D position, double kc, double kl,double kq, Vector direction)
    {
        super(color, position, kc, kl, kq);
        this._direction = direction;
        _direction=_direction.normalize();
    }
    public SpotLight(SpotLight s) {
        super(s._color, s._position, s._kc, s._kl, s._kq);
        this._direction = s._direction;
        _direction=_direction.normalize();
    }

    //***************** Getters/Setters ********************** //
    public Vector get_direction() {
        return _direction;
    }

    public void set_direction(Vector _direction) {
        this._direction = _direction;
        this._direction=_direction.normalize();
    }
    //***************** Administration  ******************** //
    @Override
    public String toString() {
        return "SpotLight [_direction=" + _direction + "]";
    }
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((_direction == null) ? 0 : _direction.hashCode());
        return result;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        SpotLight other = (SpotLight) obj;
        if (_direction == null) {
            if (other._direction != null)
                return false;
        } else if (!_direction.equals(other._direction))
            return false;
        return true;
    }

    @Override
    public Vector getL(Point3D point)
    {
        Point3D p=new Point3D(point);
        Vector v=new Vector(p,_position);
        v=v.normalize();
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
    public  Color getIntensity(Point3D point)
    {

        Color pointColor = super.getIntensity(point);

        Vector l = getL(point);
        l=l.normalize();

        double k = Math.abs(_direction.dotProduct(l));

        if (k > 1) k = 1; // doesn't allow light magnification

        return new Color((int)(pointColor.getRed()   * k),
                (int)(pointColor.getGreen() * k),
                (int)(pointColor.getBlue()  * k));
    }
    public  double distance(Point3D p)
    {
        return _position.distance(p);
    }

}
