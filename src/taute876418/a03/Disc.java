package taute876418.a03;

import cgtools.Vec3;

public class Disc extends Plane implements Shapes {
	double radius;
	public Disc(Vec3 anker, Vec3 normal, double radius, Material material) {
		super(anker, normal, material);
		this.radius = radius;
		// TODO Auto-generated constructor stub
	}
	
	public Hit intersect(Ray r) {
		Hit intersection = super.intersect(r);
//		System.out.println(intersection);
		if (intersection == null) {
//			System.out.println(intersection);
			return null;
		}
//		Vec3 check1 = Vec3.subtract(p2, p1);
//		double cC = Math.sqrt(Math.pow(check1.x,2.0)+Math.pow(check1.y,2.0)+Math.pow(check1.z,2.0));
		Vec3 dD = Vec3.subtract(intersection.point, anker);
		double d = Vec3.length(dD);
//		System.out.println(d);
		return d > radius ? null : intersection;
	}
	

}
