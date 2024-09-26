package renderer;

import elements.Light;
import geometries.FlatGeometry;
import geometries.Geometry;
import primitives.Point3D;
import primitives.Ray;
import primitives.Vector;
import scene.Scene;

import java.awt.*;
import java.util.List;
import java.util.*;


public class Render {
    //fields
    Scene _scene;
    ImageWriter _imageWriter;
    private final int RECURSION_LEVEL = 3;

    private static class Entry implements Map<Geometry, Point3D> {
        public Geometry geometry;
        public Point3D point;

        public Entry(Geometry g, Point3D p) {
            geometry = g;
            point = p;
        }


        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean containsKey(Object key) {
            return false;
        }

        @Override
        public boolean containsValue(Object value) {
            return false;
        }

        @Override
        public Point3D get(Object key) {
            return null;
        }

        @Override
        public Point3D put(Geometry key, Point3D value) {
            return null;
        }

        @Override
        public Point3D remove(Object key) {
            return null;
        }

        @Override
        public void putAll(Map<? extends Geometry, ? extends Point3D> m) {

        }

        @Override
        public void clear() {

        }

        @Override
        public Set<Geometry> keySet() {
            return null;
        }

        @Override
        public Collection<Point3D> values() {
            return null;
        }

        @Override
        public Set<Entry<Geometry, Point3D>> entrySet() {
            return null;
        }
    }
    //constructors
    public Render(Scene scene, ImageWriter imageWriter) {
        this._scene = scene;
        this._imageWriter = imageWriter;
    }

    //getters & setters
    public Scene get_scene() {
        return _scene;
    }

    public void set_scene(Scene _scene) {
        this._scene = _scene;
    }

    public ImageWriter get_imageWriter() {
        return _imageWriter;
    }

    public void set_imageWriter(ImageWriter _imageWriter) {
        this._imageWriter = _imageWriter;
    }

    private Color addColor (Color c1,Color c2)
    {
        int red=c1.getRed()+c2.getRed();
        int green=c1.getGreen()+c2.getGreen();
        int blue=c1.getBlue()+c2.getBlue();
        if(red>255)
            red=255;
        if(green>255)
            green=255;
        if(blue>255)
            blue=255;
        if((int)red<0)
            red=0;
        if((int)green<0)
            green=0;
        if((int)blue<0)
            blue=0;
        return new Color(red, green, blue);
    }
    //private methods
    private List<Point3D> getSceneRayIntersection(Ray ray) {
        Iterator<Geometry> geometries = get_scene().getGeometriesIterator();
        List<Point3D> intersectionPoints = new ArrayList<Point3D>();
        while (geometries.hasNext()) {
            Geometry geometry = geometries.next();
            List<Point3D> geometryIntersectionPoints = geometry.findIntersections(ray);
            if (!geometryIntersectionPoints.isEmpty()) {
                for (int i = 0; i < geometryIntersectionPoints.size(); i++)
                    intersectionPoints.add(geometryIntersectionPoints.get(i));
            }
        }
        return intersectionPoints;
    }

    private Point3D getClosestPoint(List<Point3D> intersectionPoints) {
        double distance = Double.MAX_VALUE;
        Point3D P0 = get_scene().get_camera().get_p0();
        Point3D minDistancePoint = null;
        for (Point3D point : intersectionPoints) {
            if (P0.distance(point) < distance) {
                minDistancePoint = new Point3D(point);
                distance = P0.distance(point);
            }
        }
        return minDistancePoint;
    }

    private Color calcColor(Geometry geometry, Point3D point, Ray ray)
    {
        return calcColor(geometry, point, ray, 0);
    }


    private Color calcColor(Geometry geometry,Point3D point, Ray inRay, int level)
    {

        if (level == RECURSION_LEVEL)
        {
            return new Color(0, 0, 0);
        }

        Vector v= new Vector();
        v=new Vector(point, _scene.get_camera().get_p0());


        Color ambientLight = _scene.get_ambientLight().getIntensity(point);
        Color emissionLight = geometry.getEmission();
        Color specularLight=new Color(0,0,0);
        Color diffuseLight=new Color(0,0,0);

        Iterator<Light> lights = _scene.getLightIterator();
        while (lights.hasNext())
        {

            Light light=lights.next();
            if (!occluded(light, point, geometry))
            {
                diffuseLight=addColor(diffuseLight,calcDiffusiveComp(geometry.getMaterial().get_Kd(),
                        geometry.getNormal(point),
                        light.getL(point),
                        light.getIntensity(point)));
                specularLight=addColor(specularLight, calcSpecularComp(geometry.getMaterial().get_Ks(),
                        v,
                        geometry.getNormal(point),
                        light.getL(point),
                        geometry.getMaterial().get_n(),
                        light.getIntensity(point)));
            }
        }

        Color I0=addColor(ambientLight, emissionLight);
        Color I1=addColor(I0, diffuseLight);
        Color I2=addColor(I1, specularLight);

        Color refracted = new Color(0, 0, 0);
        Color reflected = new Color(0, 0, 0);

        double kr = geometry.getMaterial().get_Kr();
        if (kr>0)
        {

            reflected=_scene.get_background();
            Ray reflectedRay = constructReflectedRay(geometry.getNormal(point), point, inRay);
            Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(reflectedRay);
            if (intersectionPoints!=null && intersectionPoints.size()!= 0)
            {
                intersectionPoints.remove(geometry);
                Map<Geometry, Point3D> closestPoint = getClosestPoint1(intersectionPoints);
                for (Map.Entry<Geometry, Point3D> reflectedEntry : closestPoint.entrySet())
                {
                    Geometry g = (Geometry) (reflectedEntry.getKey());
                    if (!(g.equals(geometry)))
                    {
                        reflected = calcColor(reflectedEntry.getKey(), reflectedEntry.getValue(), reflectedRay, level + 1);
                        reflected = new Color ((int)(reflected.getRed() * kr), (int)(reflected.getGreen() * kr),(int)(reflected.getBlue() * kr));

                    }
                }
            }
            else
            {
                reflected=calcColor(null,null,null,RECURSION_LEVEL);
                I2 = addColor(I2,reflected);
            }
        }
        double kt = geometry.getMaterial().get_Kt();
        if (kt>0)
        {
            Ray refractedRay = constructRefractedRay(geometry, point, inRay);
            Entry refractedEntry = findClosestIntersection(refractedRay);

            if (refractedEntry != null)
            {

                refracted = calcColor(refractedEntry.geometry, refractedEntry.point, refractedRay, level + 1);
                kt = geometry.getMaterial().get_Kt();
                refracted = new Color ((int)(refracted.getRed() * kt), (int)(refracted.getGreen() * kt),(int)(refracted.getBlue() * kt));
            }
        }
        I2 = addColor(I2,reflected);
        I2 = addColor(I2,refracted);

        return I2;

    }


    private boolean occluded(Light light, Point3D point,Geometry geometry)
    {
        Vector lightDirection = light.getL(point);
        lightDirection=lightDirection.normalize();
        //lightDirection.scale(-1);
        Point3D geometryPoint = new Point3D(point);
        Vector epsVector = new Vector(geometry.getNormal(point));
        epsVector=epsVector.normalize();
        epsVector=epsVector.scale(2);
        geometryPoint=geometryPoint.addVector(epsVector);
        Ray lightRay = new Ray(geometryPoint, lightDirection);

        Map<Geometry, List<Point3D>> intersectionPoint = getSceneRayIntersections(lightRay);
        if (geometry instanceof FlatGeometry)
            intersectionPoint.remove(geometry);

        for (Map.Entry<Geometry, List<Point3D>> entry : intersectionPoint.entrySet())
        {
            if (entry.getKey().getMaterial().get_Kt()==0)//==0
                return true;

        }
//		}
        return false;
    }

    private Color calcDiffusiveComp(double d, Vector normal, Vector l, Color intensity)
    {
        normal=normal.normalize();
		l=l.normalize();
		double dot = normal.dotProduct(l);
		dot *= d;
		dot = Math.abs(dot);
		if (dot > 1)
			dot = 1;
		int r = (int) (dot * intensity.getRed());
		if (r > 255)
			r = 255;
		int g = (int) (dot * intensity.getGreen());
		if (g > 255)
			g = 255;
		int b = (int) (dot * intensity.getBlue());
		if (b > 255)
			b = 255;
		Color I0 = new Color(r, g, b);
		return I0;

    }

   /* private Color timesColor (Color c1,double c2)
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
    }*/

    private Color calcSpecularComp(double ks, Vector vector, Vector normal, Vector l, double n,Color intensity)
    {
        vector=vector.normalize();
        normal=normal.normalize();
        l=l.normalize();
        double dot = l.dotProduct(normal);
        dot *= 2;
        Vector temp = new Vector(normal);
        temp=temp.normalize();
        temp=temp.scale(dot);
        Vector R = new Vector(l.substract_vector(temp));// r=l-2(D*N)N
        R=R.normalize();
        double dot2 = Math.abs(vector.dotProduct(R));
        if (dot2 > 1)
            dot2 = 1;
        dot2 = Math.pow(dot2, n);

        dot2 *= ks;
        dot2 = Math.abs(dot2);
        int r = (int) (dot2 * intensity.getRed());
        if (r > 255)
            r = 255;
        int g = (int) (dot2 * intensity.getGreen());
        if (g > 255)
            g = 255;
        int b = (int) (dot2 * intensity.getBlue());
        if (b > 255)
            b = 255;
        Color I0 = new Color(r, g, b);
        return I0;
    }

    private Ray constructReflectedRay(Vector normal, Point3D point, Ray ray)
    {
        Vector r = ray.get_directionVector().substract_vector(new Vector(normal.scale(2 * ray.get_directionVector().dotProduct(normal))));
       point=point.addVector(new Vector(r.scale(2)));
        return new Ray(point, r);
    }

    private Ray constructRefractedRay(Geometry geometry, Point3D point, Ray inRay)
    {
        Vector normal = geometry.getNormal(point);
        normal=normal.scale(-2);
        point=point.addVector(normal);

        if (geometry instanceof FlatGeometry)
        {
            return new Ray (point, inRay.get_directionVector());
        }
        else
        {
            return new Ray (point, inRay.get_directionVector());
        }
    }

    private Entry findClosestIntersection(Ray ray)
    {

        Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(ray);

        if (intersectionPoints.size() == 0)
            return null;

        //Map<Geometry, Point3D> closestPoint = getClosestPoint(intersectionPoints);
        //Map.Entry<Geometry, Point3D> entry= closestPoint.entrySet().iterator().next();
        //return entry;
        return (Entry) getClosestPoint(intersectionPoints);

    }






    private  Map<Geometry, List<Point3D>> getSceneRayIntersectionsForOcludded(Ray ray,Light l,Point3D geometryPoint)
    {
        Iterator<Geometry> geometries = _scene.getGeometriesIterator();
        Map<Geometry, List<Point3D>> intersectionPoints = new HashMap<Geometry,List<Point3D>>();

        while (geometries.hasNext())
        {
            Geometry geometry = geometries.next();
            List<Point3D> geometryIntersectionPoints = geometry.findIntersections(ray);
            List<Point3D> geoIntersectionPoints =new ArrayList<Point3D>();

            for (Point3D point: geometryIntersectionPoints)
            {
                if (point.distance(geometryPoint) > l.distance(point))
                //if (point.distance(geometryPoint) < l.distance(geometryPoint))
                {
                    geoIntersectionPoints.add(point);
                }
            }

            if (!geoIntersectionPoints.isEmpty())
            {
                intersectionPoints.put(geometry,geometryIntersectionPoints);
            }
        }
        return intersectionPoints;
    }



    //methods
    public void printGrid(int interval) {
        for (int i = 0; i < get_imageWriter().getNx(); i += interval)
            for (int j = 0; j < get_imageWriter().getNy(); j++)
                get_imageWriter().writePixel(i, j, new Color(255, 255, 255));

        for (int j = 0; j < get_imageWriter().getNy(); j += interval)
            for (int i = 0; i < get_imageWriter().getNx(); i++)
                get_imageWriter().writePixel(i, j, Color.WHITE);

    }

    /*public void renderImage() {
        for (int i = 0; i < this.get_imageWriter().getNx(); i++)
            for (int j = 0; j < this.get_imageWriter().getNy(); j++) {
                Ray ray = this.get_scene().get_camera().constructRayThrowAPixel(get_imageWriter().getNx(),
                        get_imageWriter().getNy(),
                        j,
                        i,
                        get_scene().get_screenDistance(),
                        get_imageWriter().getWidth(),
                        get_imageWriter().getHeight());
                List<Point3D> intersectionPoints = getSceneRayIntersection(ray);
                if (intersectionPoints.isEmpty()) {
                    _imageWriter.writePixel(j, i, get_scene().get_background());
                } else {
                    Point3D closestPoint = getClosestPoint(intersectionPoints);
                    _imageWriter.writePixel(i, j, calcColor(closestPoint));
                }

            }
    }*/
    public void renderImage()
    {
        for(int i=0;i<_imageWriter.getNx();i++)
        {
            for(int j=0;j<_imageWriter.getNy();j++)
            {
                int pixelColorRed = 0;
                int pixelColorGreen = 0;
                int pixelColorBlue = 0;
                Ray ray1 = _scene.get_camera().constructRayThrowAPixel(_imageWriter.getNx(), _imageWriter.getNy(), j-0.5,i-0.5,_scene.get_screenDistance(), _imageWriter.getWidth(),_imageWriter.getHeight()) ;
                Ray ray2 = _scene.get_camera().constructRayThrowAPixel(_imageWriter.getNx(), _imageWriter.getNy(), j-0.5,i+0.5,_scene.get_screenDistance(), _imageWriter.getWidth(),_imageWriter.getHeight()) ;
                Ray ray3 = _scene.get_camera().constructRayThrowAPixel(_imageWriter.getNx(), _imageWriter.getNy(), j,i,_scene.get_screenDistance(), _imageWriter.getWidth(),_imageWriter.getHeight()) ;
                Ray ray4 = _scene.get_camera().constructRayThrowAPixel(_imageWriter.getNx(), _imageWriter.getNy(), j+0.5,i-0.5,_scene.get_screenDistance(), _imageWriter.getWidth(),_imageWriter.getHeight()) ;
                Ray ray5 = _scene.get_camera().constructRayThrowAPixel(_imageWriter.getNx(), _imageWriter.getNy(), j+0.5,i+0.5,_scene.get_screenDistance(), _imageWriter.getWidth(),_imageWriter.getHeight()) ;
                Ray rayList[] = {ray1,ray2,ray3,ray4,ray5};
                for (int k = 0; k < 5; k++) {
                    Map<Geometry, List<Point3D>> intersectionPoints = getSceneRayIntersections(rayList[k]);
                    if (intersectionPoints.isEmpty()) {
                        pixelColorRed += _scene.get_background().getRed();
                        pixelColorGreen += _scene.get_background().getGreen();
                        pixelColorBlue += _scene.get_background().getBlue();
                    } else {
                        Entry closestPoint = (Entry) getClosestPoint(intersectionPoints);

                        pixelColorRed += calcColor(closestPoint.geometry, closestPoint.point, rayList[k]).getRed();
                        pixelColorGreen += calcColor(closestPoint.geometry, closestPoint.point, rayList[k]).getGreen();
                        pixelColorBlue += calcColor(closestPoint.geometry, closestPoint.point, rayList[k]).getBlue();
                    }
                }
                _imageWriter.writePixel(j,i,new Color((int)pixelColorRed/5,(int)pixelColorGreen/5,(int)pixelColorBlue/5));
            }
        }
        _imageWriter.writeToimage();
    }



    private Map<Geometry, List<Point3D>> getSceneRayIntersections(Ray ray) {
        Iterator<Geometry> geometries = _scene.getGeometriesIterator();
        Map<Geometry, List<Point3D>> intersectionPoints = new HashMap<Geometry, List<Point3D>>();

        while (geometries.hasNext()) {
            Geometry geometry = geometries.next();
            List<Point3D> geometryIntersectionPoints = geometry.findIntersections(ray);

            if (!geometryIntersectionPoints.isEmpty()) {
                intersectionPoints.put(geometry, geometryIntersectionPoints);
            }
        }
        return intersectionPoints;
    }

    private  Map<Geometry, Point3D> getClosestPoint1( Map<Geometry, List<Point3D>> intersectionPoints)
    {
        double distance = Double.MAX_VALUE;
        Point3D P0 = _scene.get_camera().get_p0();
        Map<Geometry, Point3D> minDistancePoint = new HashMap<Geometry, Point3D>();


        for (Map.Entry<Geometry, List<Point3D>> entry: intersectionPoints.entrySet())
        {
            for (Point3D point: entry.getValue())
            {
                if (P0.distance(point) < distance)
                {
                    minDistancePoint.clear();
                    minDistancePoint.put(entry.getKey(), new Point3D(point));
                    distance = P0.distance(point);
                }
            }
        }

        return minDistancePoint;
    }

    private  Entry getClosestPoint( Map<Geometry, List<Point3D>> intersectionPoints)
    {
        double distance = Double.MAX_VALUE;
        Point3D P0 = _scene.get_camera().get_p0();
        Entry minDistancePoint=null;

        for (Map.Entry<Geometry, List<Point3D>> entry: intersectionPoints.entrySet())
        {
            for (Point3D point: entry.getValue())
            {
                if (P0.distance(point) < distance)
                {
                    minDistancePoint = new Entry(entry.getKey(), new Point3D(point));
                    distance = P0.distance(point);
                }
            }
        }
        return minDistancePoint;
    }





}
