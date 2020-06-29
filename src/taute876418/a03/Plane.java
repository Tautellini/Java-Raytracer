package taute876418.a03;

import cgtools.Vec3;

public class Plane implements Shapes {
	Vec3 anker;
	Vec3 normal;
	Vec3 color;
	Material material;
	
	public Plane (Vec3 anker, Vec3 normal, Material material) {
		this.anker = anker;
		this.normal = normal;
		this.material = material;
	}

	@Override
	public Hit intersect(Ray r) {
		// TODO Auto-generated method stub
		double d = Vec3.dotProduct(normal, r.direction);
		if (d == 0) {
			return null;
		}
		double t = (Vec3.dotProduct(anker, normal) - Vec3.dotProduct(normal, r.origin))/d;
		if(t <= r.tmax && t >= r.tmin) {
//			if(t > r.tmax) return null; 
			Vec3 intersection = r.pointAt(t);
			Vec3 normNeg = new Vec3(-normal.x, -normal.y, -normal.z);
			Vec3 n = d > 0 ? normNeg : normal;
//			System.out.println(intersection);
			return new Hit(t, intersection, n, material);
		}
		return null;
	}

}
