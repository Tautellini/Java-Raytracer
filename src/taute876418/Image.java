package taute876418;

import cgtools.Vec3;

import java.io.IOException;

import cgtools.ImageWriter;

public class Image {
	int height;
	int width;
	double data[];
	final long startTime = System.currentTimeMillis();
	
    public Image(int width, int height) {
    	int size = (width * height) * 3;
    	data = new double[size];
    	this.height = height;
    	this.width = width;
        System.out.println("Dimensionen festgelegt");
    }

    public void setPixel(int x, int y, Vec3 color) {
    	int pos = (y * width + x) * 3;
    	data[pos] = color.x;
    	data[pos+1] = color.y;
    	data[pos+2] = color.z;
//        System.out.println("x:"+x+" y:"+y+" // "+color.x+" "+color.y+" "+color.z);
    }

    public void write(String filename) throws IOException {
    	ImageWriter.write(filename, data, width, height);
    	final long endTime = System.currentTimeMillis();
    	final double time = ((endTime - startTime)*Math.pow(10, -3))/60;
    	System.out.println("Total execution time: " + time + "Minuten");
    }
}
