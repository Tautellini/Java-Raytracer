package taute876418.a03;

import cgtools.Vec3;

public class PointLight implements Lights {
	Vec3 color = null;
	Vec3 lightPoint = null;
	
	public PointLight(Vec3 color, Vec3 lightPoint) {
		this.color = color;
		this.lightPoint = lightPoint;
	}
	
	@Override
	public Vec3 getColor(Hit h, Group g) {
		// TODO Auto-generated method stub
		Vec3 toLight = Vec3.normalize(Vec3.subtract(h.point, lightPoint));
		Ray r = new Ray(h.point, toLight, 0.00000001, Double.POSITIVE_INFINITY);
		Hit hitWithObject = g.intersect(r);
		if(hitWithObject == null || hitWithObject.t == Double.POSITIVE_INFINITY) {
			return color;
		}
		final double result = Vec3.dotProduct((r.direction),Vec3.subtract(r.origin, lightPoint)) 
				/ Vec3.dotProduct(toLight, toLight);
		final Vec3 rs = r.pointAt(h.t);
		if (hitWithObject.t < result) {
//			System.out.println("test");
			return null;
		} else {
			return color;
		}
	}
	
	public Vec3 getDir() {
		return lightPoint;
	}
}
