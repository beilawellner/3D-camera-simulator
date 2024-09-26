package elements;

import primitives.Point3D;

import java.awt.*;
import java.util.Objects;

public class AmbientLight {
    //fields
    Color _color;
    double _ka;//The power of light

    //constructors

    public AmbientLight() {
        this.set_color(new Color(255,255,255));
        this.set_ka(1.0);
    }

    public AmbientLight(Color color, double ka) {
        this.set_color(color);
        this.set_ka(ka);
    }

    public AmbientLight(int r, int g, int b) {
        this.set_color(new Color(r,g,b));
        this.set_ka(1.0);
    }

    public AmbientLight(AmbientLight other) {
        this.set_color(other.get_color());
        this.set_ka(other.get_ka());
    }

    //setters & getters
    public Color get_color() { return _color; }
    public void set_color(Color _color) { this._color = _color; }
    public double get_ka() { return _ka; }
    public void set_ka(double _ka) { this._ka = _ka; }

    //methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AmbientLight)) return false;
        AmbientLight that = (AmbientLight) o;
        return Double.compare(that.get_ka(), get_ka()) == 0 &&
                get_color().equals(that.get_color());
    }

    @Override
    public int hashCode() {
        return Objects.hash(get_color(), get_ka());
    }

    @Override
    public String toString() {
        return "AmbientLight{" +
                "_color=" + _color +
                ", _ka=" + _ka +
                '}';
    }

    public Color getIntensity(Point3D point){
        int r = this.get_color().getRed();
        int g = this.get_color().getGreen();
        int b = this.get_color().getBlue();

        double ka = this.get_ka();

        return new Color((int)(r*ka),(int)(g*ka),(int)(b*ka));
    }
}
