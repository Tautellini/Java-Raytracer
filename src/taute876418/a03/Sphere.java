package taute876418.a03;

import cgtools.Vec3;

public class Sphere implements Shapes {
	public Vec3 center;
	double radius;
	Material mat;
	
	public Sphere(Vec3 center, double radius, Material mat) {
		this.center = center;
		this.radius = radius;
		this.mat = mat;
	}
	
//	public Vec3 getKartesian(Hit h) {
//		Vec3 zAxis = new Vec3(0,0,1);
//		double polarCos = ( Vec3.dotProduct(zAxis, h.normal) ) / ( 1 * Vec3.length(h.normal) );
//		double polarSin = Vec3.length( Vec3.crossProduct(zAxis, h.normal) ) / ( 1 * Vec3.length(h.normal) );
//		
//		Vec3 xAxis = new Vec3(1,0,0);
//		double z = 0;
//		Vec3 tempVec = new Vec3(h.normal.x, h.normal.y, z);
//		double azimutCos = ( Vec3.dotProduct(xAxis, h.normal) ) / ( 1 * Vec3.length(h.normal) );
//		
//		Vec3 outt = new Vec3(radius * polarSin * Math.cos(azimut), 
//							 radius * polarSin * Math.sin(azimut),
//							 radius * polarCos );
//		Vec3 out = new Vec3(radius * Math.sin(polar) * Math.cos(azimut), 
//							radius * Math.sin(polar) * Math.sin(azimut),
//							radius * Math.cos(polar) );
//	}

	@Override
	public Hit intersect(Ray r) {
		Vec3   v = Vec3.subtract(r.origin, center); //Vektor center->origin
		double a = Vec3.dotProduct(r.direction, r.direction);
		double b = 2 * Vec3.dotProduct(v, r.direction);
		double c = Vec3.dotProduct(v, v) - Math.pow(radius, 2);
		double disc = Math.pow(b, 2) - 4 * c;
		if(disc < 0) {
			return null;
		}
		
		double t0 = (-b - Math.sqrt(disc)) / 2 * a;
		double t1 = (-b + Math.sqrt(disc)) / 2 * a;
		boolean t0InBounds  = false;      //t Grenzwertcheck
		if(t0 <= r.tmax && t0 >= r.tmin) {
			t0InBounds = true;
		}
		boolean t1InBounds = false;      //t Grenzwertcheck
		if(t1 <= r.tmax && t1 >= r.tmin) {
			t1InBounds = true;
		}
		if(t0InBounds) {
			Vec3 location = r.pointAt(t0);
			Vec3 normal = Vec3.divide(Vec3.subtract(location, this.center), this.radius);
			return new Hit(t0, location, normal, this.mat);
		}
		if(t1InBounds) {
			Vec3 location = r.pointAt(t1);
			Vec3 normal = Vec3.divide(Vec3.subtract(location, this.center), this.radius);
			return new Hit(t1, location, normal, this.mat);
		}
		return null;
	}
}
