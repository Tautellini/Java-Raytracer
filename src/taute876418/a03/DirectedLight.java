package taute876418.a03;

import cgtools.Vec3;

public class DirectedLight implements Lights {
	Vec3 color = null;
	Vec3 lightDir = null;
	
	public DirectedLight(Vec3 color, Vec3 lightDir) {
		this.color = color;
		this.lightDir = lightDir;
	}
	
	public Vec3 getColor(Hit h, Group g) {
		Vec3 toLight = Vec3.multiply(-1, lightDir);
		Ray r = new Ray(h.point, toLight, 0.00000001, Double.POSITIVE_INFINITY);
		Hit hitWithObject = g.intersect(r);
		if(hitWithObject == null || hitWithObject.t == Double.POSITIVE_INFINITY) {
			return color;
		} else {
			return null;
		}
	}
	public Vec3 getDir() {
		return lightDir;
	}
}
