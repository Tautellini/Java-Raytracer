package taute876418.a03;

import cgtools.Random;
import cgtools.Vec3;

public class DiffusMat implements Material {
	private Vec3 color;
	
	public DiffusMat (Vec3 color) {
		this.color = color;
	}
	@Override
	public Vec3 albedo(Ray r, Hit h) {
		return color;
	}
	@Override
	public Vec3 emission(Ray r, Hit h) {
		return Vec3.zero;
	}
	@Override
	public Ray scattered(Ray r, Hit h) {
		Vec3 random = null;
//		Vec3 norm = null;
		double c = 0;
		do {
			random = new Vec3(Random.random()*2-1, Random.random()*2-1, Random.random()*2-1);
			c = Math.pow(random.x, 2)+Math.pow(random.y, 2);
		} while (c > 1);
//		System.out.println(random);
		Vec3 direction = Vec3.normalize(Vec3.add(Vec3.normalize(h.normal), random));
		Vec3 origin = h.point;
		Ray ray = new Ray(origin, direction, 0.01, Double.POSITIVE_INFINITY);
		return ray;
	}

}
