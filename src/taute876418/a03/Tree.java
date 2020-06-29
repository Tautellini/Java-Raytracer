package taute876418.a03;

import cgtools.Vec3;

public class Tree extends Group implements Shapes {
	public Tree (Vec3 p1, Vec3 p2, double radius, Material material) {
		DiffusMat stamm = new DiffusMat(new Vec3(0.54,0.27,0.075));
		addShape(new Tube(p1, p2, radius, stamm));
		addShape(new ClosedCone(p2, Vec3.length(Vec3.subtract(p2, p1))/1.3, Vec3.length(Vec3.subtract(p2, p1))/2, material));
	}
}
