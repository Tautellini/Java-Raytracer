package taute876418.a03;

import cgtools.Random;
import cgtools.Vec3;

public class RoughMat implements Material{
	final Vec3 color;
	final double roughness;
	
	public RoughMat() {
		color = new Vec3(0.5,0.5,1);
		roughness = 0.0;
	}
	
	public RoughMat(Vec3 color, double roughness) {
		this.color = color;
		this.roughness = roughness;
	}

	public Vec3 albedo(Ray r, Hit h) {
		return this.color;
	}

	public Vec3 emission(Ray r, Hit h) {
		return Vec3.zero;
	}

	public Ray scattered(Ray r, Hit h) {
		Vec3 rD = Vec3.subtract(r.direction, Vec3.multiply(2, Vec3.multiply(Vec3.dotProduct(h.normal, r.direction), h.normal)));
		Vec3 roughCalc = new Vec3( rD.x + ( Random.random() * 2 - 1 ) * roughness,
								   rD.y + ( Random.random() * 2 - 1 ) * roughness,
								   rD.z + ( Random.random() * 2 - 1 ) * roughness );
		Ray ray = new Ray(h.point, roughCalc, 0.00000001, Double.POSITIVE_INFINITY);
		return ray;
	}
}
