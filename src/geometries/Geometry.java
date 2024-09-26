package geometries;
import primitives.*;
import java.awt.Color;
import java.util.List;

import com.sun.org.glassfish.gmbal.ManagedAttribute;

public abstract class Geometry
{
    protected Color clr;
    protected Material material;
    // ***************** Constructors ********************** //

    public Geometry(Color clr) {
        this.clr = clr;
        setMaterial(new Material());
    }

    public Geometry(Color clr, Material material) {
        this.clr = clr;
        this.material = material;
    }

    public Geometry() {
        clr=new Color(0,0,0);
        material=new Material();
    }

    public Geometry(Geometry g) {
        this.clr = g.clr;
        this.material =g. material;
    }
    // ***************** Getters/Setters ********************** //
    public Color getColor()
    {
        return clr;
    }

    public Color getEmission() {
        return clr;
    }

    public void setEmission(Color clr) {
        this.clr = clr;
    }
    public Material getMaterial() {
        return material;
    }
    public void setMaterial(Material material) {
        this.material = material;
    }
    // ***************** Administration  ******************** //
    public abstract Vector getNormal(Point3D point);
    public abstract List<Point3D> findIntersections(Ray r);

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Geometry other = (Geometry) obj;
        if (clr == null) {
            if (other.clr != null)
                return false;
        } else if (!clr.equals(other.clr))
            return false;
        if (material == null) {
            if (other.material != null)
                return false;
        } else if (!material.equals(other.material))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Geometry [clr=" + clr + ", material=" + material + "]";
    }


}
