package taute876418.a03;

import cgtools.Vec3;

public class Ray {
	Vec3 origin;
	Vec3 direction;
	double t;
	double tmin;
	double tmax;
	
	public Ray(Vec3 origin, Vec3 direction, double tmin, double tmax) {
		this.origin = origin;
		this.direction = Vec3.normalize(direction);
		this.tmin = tmin;
		this.tmax = tmax;
	}
	
	public Vec3 pointAt(double t) {
		return new Vec3(origin.x + t*(direction.x), origin.y + t*(direction.y), origin.z + t*(direction.z));
	}
}
