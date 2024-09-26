package tests.renderer;

import elements.*;
import geometries.Geometry;
import geometries.Sphere;
import geometries.Triangle;
import org.junit.jupiter.api.Test;
import primitives.Point3D;
import primitives.*;
import renderer.ImageWriter;
import renderer.Render;
import scene.Scene;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RenderTest {
    @Test
    public void renderImage_1(){
        Scene scene = new Scene();

        scene.addGeometry(new Sphere(50, new Point3D(0.0, 0.0, -150)));

        Triangle triangle = new Triangle(new Point3D( 100, 0, -149),
                new Point3D(  0, 100, -149),
                new Point3D( 100, 100, -149));

        Triangle triangle2 = new Triangle(new Point3D( 100, 0, -149),
                new Point3D(  0, -100, -149),
                new Point3D( 100,-100, -149));

        Triangle triangle3 = new Triangle(new Point3D(-100, 0, -149),
                new Point3D(  0, 100, -149),
                new Point3D(-100, 100, -149));

        Triangle triangle4 = new Triangle(new Point3D(-100, 0, -149),
                new Point3D(  0,  -100, -149),
                new Point3D(-100, -100, -149));

        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);
        scene.addGeometry(triangle4);

        ImageWriter imageWriter = new ImageWriter("Render_1", 501, 501, 501, 501);
        scene.set_screenDistance(100);

        Render render = new Render(scene, imageWriter);

        render.renderImage();
        render.printGrid(50);
        imageWriter.writeToimage();
    }


    @Test
    public void renderImage_3(){
        Scene scene = new Scene();

        scene.addGeometry(new Sphere(new Color(29, 15,255),50, new Point3D(0.0, 0.0, -150)));

        Triangle triangle = new Triangle(new Color(0,255,0),new Point3D( 100, 0, -149),
                new Point3D(  0, 100, -149),
                new Point3D( 100, 100, -149));

        Triangle triangle2 = new Triangle(new Color(145,0,255),new Point3D( 100, 0, -149),
                new Point3D(  0, -100, -149),
                new Point3D( 100,-100, -149));

        Triangle triangle3 = new Triangle(new Color(255,0,65),new Point3D(-100, 0, -149),
                new Point3D(  0, 100, -149),
                new Point3D(-100, 100, -149));

        Triangle triangle4 = new Triangle(new Color(255,0,0),new Point3D(-100, 0, -149),
                new Point3D(  0,  -100, -149),
                new Point3D(-100, -100, -149));

        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);
        scene.addGeometry(triangle4);

        ImageWriter imageWriter = new ImageWriter("Render_3", 500, 500, 500, 500);
        scene.set_screenDistance(100);

        Render render = new Render(scene, imageWriter);

        render.renderImage();
        render.printGrid(50);
        imageWriter.writeToimage();
    }
    @Test
    public void renderImage_2()
    {

        Sphere _sphere_1=new Sphere(30,new Point3D(100,100,-100));
        Sphere _sphere_2=new Sphere(30,new Point3D(-100,100,-100));
        Sphere _sphere_3=new Sphere(30,new Point3D(100,-100,-100));
        Sphere _sphere_4=new Sphere(30,new Point3D(-100,-100,-100));
        Sphere _sphere_5=new Sphere(45,new Point3D(0,0,-150));
        Sphere _sphere_6=new Sphere(40,new Point3D(0,100,-90));
        Sphere _sphere_7=new Sphere(40,new Point3D(100,0,-90));
        Sphere _sphere_8=new Sphere(40,new Point3D(-100,0,-90));
        Sphere _sphere_9=new Sphere(40,new Point3D(0,-100,-90));
        List<Geometry> _geometries=new ArrayList<Geometry>();

        _geometries.add(_sphere_1);
        _geometries.add(_sphere_2);
        _geometries.add(_sphere_3);
        _geometries.add(_sphere_4);
        _geometries.add(_sphere_5);
        _geometries.add(_sphere_6);
        _geometries.add(_sphere_7);
        _geometries.add(_sphere_8);
        _geometries.add(_sphere_9);

        Scene scene = new Scene();
        scene.set_sceneName("Scene1");
        //scene.set_screenDistance();
        scene.set_geometries(_geometries);
        ImageWriter imageWriter= new ImageWriter("Render_2",501,501,501,501);

        Render render = new Render(scene, imageWriter);

        render.renderImage();
        imageWriter.writeToimage();



    }

    @Test
    public void renderImage_4()
    {

        Sphere _sphere_1=new Sphere(new Color(0,255,0),30,new Point3D(100,100,-100));
        Sphere _sphere_2=new Sphere(new Color(255,0,0),30,new Point3D(-100,100,-100));
        Sphere _sphere_3=new Sphere(new Color(0,0,255),30,new Point3D(100,-100,-100));
        Sphere _sphere_4=new Sphere(new Color(55,25,0),30,new Point3D(-100,-100,-100));
        Sphere _sphere_5=new Sphere(new Color(255,55,0),45,new Point3D(0,0,-150));
        Sphere _sphere_6=new Sphere(new Color(255,255,45),40,new Point3D(0,100,-90));
        Sphere _sphere_7=new Sphere(new Color(99,85,0),40,new Point3D(100,0,-90));
        Sphere _sphere_8=new Sphere(new Color(155,155,0),40,new Point3D(-100,0,-90));
        Sphere _sphere_9=new Sphere(new Color(0,155,30),40,new Point3D(0,-100,-90));
        List<Geometry> _geometries=new ArrayList<Geometry>();

        _geometries.add(_sphere_1);
        _geometries.add(_sphere_2);
        _geometries.add(_sphere_3);
        _geometries.add(_sphere_4);
        _geometries.add(_sphere_5);
        _geometries.add(_sphere_6);
        _geometries.add(_sphere_7);
        _geometries.add(_sphere_8);
        _geometries.add(_sphere_9);

        Scene scene = new Scene();
        scene.set_sceneName("Scene1");
        //scene.set_screenDistance();
        scene.set_geometries(_geometries);
        ImageWriter imageWriter= new ImageWriter("Render_4",501,501,501,501);

        Render render = new Render(scene, imageWriter);

        render.renderImage();
        imageWriter.writeToimage();



    }
































































    /*@Test
    void renderImage_1() {
        Scene scene = new Scene();

        Sphere sphere = new Sphere(50, new Point3D(0.0, 0.0, -150));

        Triangle triangle = new Triangle(new Point3D( 100, 0, -149),
                new Point3D(  0, 100, -149),
                new Point3D( 100, 100, -149));

        Triangle triangle2 = new Triangle(new Point3D( 100, 0, -149),
                new Point3D(  0, -100, -149),
                new Point3D( 100,-100, -149));

        Triangle triangle3 = new Triangle(new Point3D(-100, 0, -149),
                new Point3D(  0, 100, -149),
                new Point3D(-100, 100, -149));

        Triangle triangle4 = new Triangle(new Point3D(-100, 0, -149),
                new Point3D(  0,  -100, -149),
                new Point3D(-100, -100, -149));

        scene.addGeometry(sphere);
        scene.addGeometry(triangle);
        scene.addGeometry(triangle2);
        scene.addGeometry(triangle3);
        scene.addGeometry(triangle4);

        ImageWriter imageWriter = new ImageWriter("Render test", 500, 500, 500, 500);

        Render render = new Render(scene, imageWriter);

        render.renderImage();
        render.printGrid(50);
        imageWriter.writeToimage();*/










        /*Scene scene = new Scene();

        List<Geometry> geo = new ArrayList<Geometry>();
        geo.add(new Sphere(50, new Point3D(0, 0, -150)));
        geo.add(new Triangle(new Point3D(100, 0, -149), new Point3D(0, 100, -149), new Point3D(100, 100, -149)));
        geo.add(new Triangle(new Point3D(100, 0, -149), new Point3D(0, -100, -149), new Point3D(100, -100, -149)));
        geo.add(new Triangle(new Point3D(-100, 0, -149), new Point3D(0, 100, -149), new Point3D(-100, 100, -149)));
        geo.add(new Triangle(new Point3D(-100, 0, -149), new Point3D(0, -100, -149), new Point3D(-100, -100, -149)));
        scene.set_sceneName("Scene_1");
        scene.set_screenDistance(100);
        scene.set_geometries(geo);
        ImageWriter imageWriter = new ImageWriter("image_1", 501, 501, 501, 501);

        Render render = new Render(scene, imageWriter);
        render.renderImage();
        render.printGrid(50);

        render.get_imageWriter().writeToimage();
    }*/


    /*@Test
    void renderImage_2() {
        Sphere _sphere_1 = new Sphere(30, new Point3D(100, 100, -100));
        Sphere _sphere_2 = new Sphere(30, new Point3D(-100, 100, -100));
        Sphere _sphere_3 = new Sphere(30, new Point3D(100, -100, -100));
        Sphere _sphere_4 = new Sphere(30, new Point3D(-100, -100, -100));
        Sphere _sphere_5 = new Sphere(45, new Point3D(0, 0, -150));
        Sphere _sphere_6 = new Sphere(40, new Point3D(0, 100, -90));
        Sphere _sphere_7 = new Sphere(40, new Point3D(100, 0, -90));
        Sphere _sphere_8 = new Sphere(40, new Point3D(-100, 0, -90));
        Sphere _sphere_9 = new Sphere(40, new Point3D(0, -100, -90));
        List<Geometry> _geometries = new ArrayList<Geometry>();

        _geometries.add(_sphere_1);
        _geometries.add(_sphere_2);
        _geometries.add(_sphere_3);
        _geometries.add(_sphere_4);
        _geometries.add(_sphere_5);
        _geometries.add(_sphere_6);
        _geometries.add(_sphere_7);
        _geometries.add(_sphere_8);
        _geometries.add(_sphere_9);


        Scene _scene = new Scene();
        _scene.set_geometries(_geometries);

        ImageWriter _imageWriter = new ImageWriter("image_2", 501, 501, 501, 501);

        Render _render = new Render(_scene, _imageWriter);

        _render.renderImage();
        _render.get_imageWriter().writeToimage();
    }*/
}