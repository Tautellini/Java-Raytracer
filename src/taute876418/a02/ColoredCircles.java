package taute876418.a02;

import java.io.IOException;

import cgtools.Vec3;
import cgtools.Random;
import taute876418.Image;

public class ColoredCircles {
	public static void main (String[] args) {
		final int width = 400;
	    final int height = 400;
	    
		Image image = new Image(width, height);
		Image superSampled = new Image(width, height);
		
		class Circle {
			double x;
			double y;
			int radius;
			Vec3 color;
			
			Circle() {
				this.x = width * Random.random();
				this.y = height * Random.random();
				this.color = Vec3.vec3(Random.random(), Random.random(), Random.random());
				this.radius = (int)(100 * Random.random() + 10);
			}
		}
		
		class Circles {
			Circle[] CircleList;
			int counter = 0;
			Circle[] SortedCircleList;
			
			Circles (int max) {
				CircleList = new Circle[max];
				for (int i=0; i != max; i++) {
					CircleList[i] = new Circle();
				}
				SortedCircleList = new Circle[max];
				for (int cr = 10; cr != 110; cr++) {
					for (int k = 0; k != max; k++) {
						if (CircleList[k].radius == cr) {
							SortedCircleList[counter] = CircleList[k];
							counter++;
						}
					}
				}
			}
			
			Vec3 pixelColor (double x, double y) {
				for(int i = 0; i < SortedCircleList.length; i++) {
             		double dx = x - SortedCircleList[i].x;
             		double dy = y - SortedCircleList[i].y;
             		double sort = Math.sqrt(Math.pow(dx, 2) + Math.pow(dy, 2));
             		if(sort <= SortedCircleList[i].radius) {
             			return new Vec3	(	Math.pow(SortedCircleList[i].color.x, 1/2.2	),
             								Math.pow(SortedCircleList[i].color.y, 1/2.2	),
             								Math.pow(SortedCircleList[i].color.z, 1/2.2 )	);
             		}
             	}
				return Vec3.gray;
			}
			
			Vec3 superSampling(double x, double y, int n) {
				Vec3[] abt = new Vec3[n*n];
				int counter = 0;
				for (int xi = 0; xi != n; xi++) {
					for(int yi = 0; yi != n; yi++ ) {
						double rx = Random.random();
						double ry = Random.random();
						double xs = x + (xi + rx) / n;
						double ys = y + (yi + ry) / n;
						abt[counter] = pixelColor(xs, ys);
						counter++;
					}
				}
				double cX = 0;
				double cY = 0;
				double cZ = 0;
				int l = abt.length;
				for (int i = 0; i != l; i++) {
					cX += abt[i].x;
					cY += abt[i].y;
					cZ += abt[i].z;
				}
				cX = cX / l;
				cY = cY / l;
				cZ = cZ / l;
				return new Vec3(cX, cY, cZ);
            }
		}
		
		Circles yo = new Circles(30);

		for (int x = 0; x != width; x++) {
	        for (int y = 0; y != height; y++) {
	            image.setPixel(x, y, yo.pixelColor(x, y));
	            superSampled.setPixel(x, y, yo.superSampling(x, y, 10));
	        }
	    }
	    write(image, "doc/a02-discs.png");
	    write(superSampled, "doc/a02-supersampling.png");
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