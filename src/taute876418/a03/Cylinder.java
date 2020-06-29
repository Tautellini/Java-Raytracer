package taute876418.a03;

import cgtools.Vec3;

public class Cylinder extends Group implements Shapes {
	public Cylinder (Vec3 p1, Vec3 p2, double radius, Material material) {
		addShape(new Tube(p1, p2, radius, material));
		addShape(new Disc(p2, Vec3.normalize(Vec3.subtract(p1, p2)), radius, material));
		addShape(new Disc(p1, Vec3.normalize(Vec3.subtract(p2, p1)), radius, material));
	}
}
