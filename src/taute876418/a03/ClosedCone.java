package taute876418.a03;

import cgtools.Vec3;

public class ClosedCone extends Group implements Shapes {
	public ClosedCone (Vec3 center, double height, double radius, Material material) {
		addShape(new Cone(center, height, radius, material));
		addShape(new Disc(center, new Vec3(0,-1,0), radius, material));
	}
}
