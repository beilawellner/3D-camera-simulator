package scene;

import elements.AmbientLight;
import elements.Camera;
import elements.Light;
import geometries.Geometry;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Scene {
    //field
    String _sceneName;
    Color _background;
    AmbientLight _ambientLight;
    List<Geometry> _geometries;
    protected List<Light> lights;
    Camera _camera;
    double _screenDistance;

    //constructors
    public Scene(String str) {
        this();
        set_sceneName(str);
    }

    public Scene(String sceneName, AmbientLight iam, Color background, List<Geometry> geometries, List<Light> lights,
                 Camera camara, double screenDistance)
    {
        this._sceneName = sceneName;
        this._ambientLight = iam;
        this._background = background;
        this._geometries = geometries;
        this.lights = lights;
        this._camera = camara;
        this._screenDistance = screenDistance;
    }

    public Scene() {
        this._sceneName = "";
        this._ambientLight = new AmbientLight(new Color(255,255,255),0.1);
        this._background = new Color(0,0,0);
        this._geometries = new ArrayList<Geometry>();
        this._camera = new Camera();
        this._screenDistance = 100;
        this.lights=new ArrayList<Light>();
    }

    public Scene(Scene other) {
        this._sceneName = other.get_sceneName();
        this._background = other.get_background();
        this._ambientLight = other.get_ambientLight();
        this._geometries = other.get_geometries();
        this._camera = other.get_camera();
        this._screenDistance = other.get_screenDistance();
        this.lights = other.lights;

    }

    //getters & setters
    public String get_sceneName() { return _sceneName; }
    public void set_sceneName(String _sceneName) { this._sceneName = _sceneName; }
    public Color get_background() { return _background; }
    public void set_background(Color _background) { this._background = _background; }
    public List<Geometry> get_geometries() { return _geometries; }
    public void set_geometries(List<Geometry> _geometries) { this._geometries = _geometries; }
    public Camera get_camera() { return _camera; }
    public void set_camera(Camera _camera) { this._camera = _camera; }
    public double get_screenDistance() { return _screenDistance; }
    public void set_screenDistance(double _screenDistance) { this._screenDistance = _screenDistance; }
    public AmbientLight get_ambientLight() { return _ambientLight; }
    public void set_ambientLight(AmbientLight _ambientLight) { this._ambientLight = _ambientLight; }
    public List<Light> getLights() {
        return lights;
    }
    public void setLights(List<Light> lights) {
        this.lights = lights;
    }
    //methods

    public void addGeometry(Geometry g){
        _geometries.add(g);
    }
    public void addLight(Light l)
    {
        lights.add(l);
    }


    public Iterator<Geometry> getGeometriesIterator()
    {
        return _geometries.iterator();
    }
    public Iterator<Light>getLightIterator() { return lights.iterator(); }
}
