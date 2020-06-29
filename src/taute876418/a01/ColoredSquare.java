package taute876418.a01;

import cgtools.Vec3;
import java.io.IOException;
import taute876418.Image;

public class ColoredSquare {

    public static void main(String[] args) {
        final int width = 400;
        final int height = 400;

        Image image = new Image(width, height);

        class Square {
        	int width;
        	int height;
            Vec3 red;
            Vec3 black;

            Square(Vec3 red, Vec3 black, int width, int height) {
                this.red = red;
                this.black = black;
                this.width = width;
                this.height= height;
            }

            Vec3 pixelColor(double x, double y) {
            	if (x >= ( (width/2)-40) && x <= ((width/2)+40) 
            			&& y >= ((height/2)-40) && y <= ((height/2)+40) ) {
            		return red;
            	}
                return black;
            }
        }

        Square allGray = new Square(Vec3.red, Vec3.black, width, height);
        
        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                image.setPixel(x, y, allGray.pixelColor(x, y));
            }
        }
        write(image, "doc/a01-square.png");
    }

    static void write(Image image, String filename) {
        try {
            image.write(filename);
            System.out.println("Wrote image: " + filename);
        } catch (IOException error) {
            System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
        }
    }
}
