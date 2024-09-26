package tests.renderer;

import org.junit.jupiter.api.Test;
import renderer.ImageWriter;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ImageWriterTest {

    public void printGrid(int interval, ImageWriter im)
    {
        for(int i=0;i<im.getNx();i+= interval)
            for(int j=0;j<im.getNy();j++)
                im.writePixel(i, j, new Color(255,255,255));

        for (int j = 0; j < im.getNx(); j += interval)
        {
            for (int i = 0; i < im.getNy(); i++)
            {
                im.writePixel(i, j, Color.WHITE);
            }
        }
    }

    @Test
    void writePixel_1() {//int xIndex, int yIndex, int r, int g, int b
        ImageWriter image1= new ImageWriter("image1",501,501,501,501);
        for(int i=0;i<500;i++)
            for(int j=0;j<500;j++)
                image1.writePixel(j, i, 200,200,200);
        printGrid(50,image1);
        image1.writeToimage();
    }

    @Test
    void writePixel_2() { //int xIndex, int yIndex, int[] rgbArray
        ImageWriter image2= new ImageWriter("image2",501,501,501,501);
        int colors[] = new int[]{100,50,200};
        for(int i=0;i<500;i++)
            for(int j=0;j<500;j++)
                image2.writePixel(j, i, colors);
        printGrid(50,image2);
        image2.writeToimage();
    }

    @Test
    void writePixe_3() {//int xIndex, int yIndex, Color color
        ImageWriter image3= new ImageWriter("image3",501,501,501,501);
        for(int i=0;i<500;i++)
            for(int j=0;j<500;j++)
                image3.writePixel(j, i, new Color(0,0,0));
        printGrid(50,image3);
        image3.writeToimage();
    }

    @Test
    void writePixel_4() {
        ImageWriter image4= new ImageWriter("image4",501,501,501,501);
        for(int i=0;i<500;i++)
            for(int j=0;j<500;j++)
                image4.writePixel(j, i, new Color(0,0,0));
        printGrid(50,image4);
        for(int i=200;i<300;i++)
            for(int j=200;j<i;j++)
                image4.writePixel(j, i, new Color(0,255,255));
        for(int j=200;j<300;j++)
            for(int i=200;i<j;i++)
                image4.writePixel(j, i, new Color(255,0,255));
        image4.writeToimage();
    }
}