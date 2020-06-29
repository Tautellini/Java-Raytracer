package taute876418.a01;

import cgtools.Vec3;
import java.io.IOException;
import taute876418.Image;

public class ConstantColor {

    public static void main(String[] args) {
        final int width = 400;
        final int height = 400;

        Image image = new Image(width, height);

        class Constant {
            Vec3 color;

            Constant(Vec3 color) {
                this.color = color;
            }

            Vec3 pixelColor(double x, double y) {
                return color;
            }
        }

        Constant allGray = new Constant(Vec3.gray);
        for (int x = 0; x != width; x++) {
            for (int y = 0; y != height; y++) {
                image.setPixel(x, y, allGray.pixelColor(x, y));
            }
        }
        write(image, "doc/a01.png");
        
        ColoredSquare.main(null);
        CheckeredBackground.main(null);
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