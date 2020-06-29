package taute876418.a03;

import cgtools.Vec3;

public class Box implements Shapes {
	Vec3 vmin;
	Vec3 vmax;
    Material material;
    private final Vec3 negX = new Vec3(-1, 0, 0);
	private final Vec3 posX = new Vec3(+1, 0, 0);
	private final Vec3 negY = new Vec3( 0,-1, 0);
	private final Vec3 posY = new Vec3( 0,+1, 0);
	private final Vec3 negZ = new Vec3( 0, 0,-1);
	private final Vec3 posZ = new Vec3( 0, 0,+1);
	
	public Box(Vec3 min, Vec3 max, Material material) {
		this.vmin = min;
		this.vmax = max;
		this.material = material;
		// TODO Auto-generated constructor stub
	}
	
	public Hit intersect(Ray r) {
		Vec3 o = r.origin;
		Vec3 d = r.direction;
		double txmin = r.tmin;
		double txmax = r.tmax;
		if (d.x > 0) {
			txmin = (vmin.x - o.x) / d.x;
			txmax = (vmax.x - o.x) / d.x;
		}
		if (d.x < 0) {
			txmin = (vmax.x - o.x) / d.x;
			txmax = (vmin.x - o.x) / d.x;
		}
		double tymin = r.tmin;
		double tymax = r.tmax;
		if (d.y > 0) {
			tymin = (vmin.y - o.y) / d.y;
			tymax = (vmax.y - o.y) / d.y;
		}
		if (d.y < 0) {
			tymin = (vmax.y - o.y) / d.y;
			tymax = (vmin.y - o.y) / d.y;
		}
		double tzmin = r.tmin;
		double tzmax = r.tmax;
		if (d.z > 0) {
			tzmin = (vmin.z - o.z) / d.z;
			tzmax = (vmax.z - o.z) / d.z;
		}
		if (d.z < 0) {
			tzmin = (vmax.z - o.z) / d.z;
			tzmax = (vmin.z - o.z) / d.z;
		}
		double tmin = Math.max(txmin, Math.max(tymin,  tzmin));
		double tmax = Math.min(txmax, Math.min(tymax,  tzmax));
		if (tmin > tmax)
			return null;
		if (tmax <= 0.0000001)
			return null;
		double t = tmin > 0.0000001 ? tmin : tmax;
		Vec3 i = r.pointAt(t);
		Vec3 n = null;
		Vec3 dmin = Vec3.subtract(i, vmin);
		Vec3 dmax = Vec3.subtract(i, vmax);
		double dx = Math.min(Math.abs(dmin.x), Math.abs(dmax.x));
		double dy = Math.min(Math.abs(dmin.y), Math.abs(dmax.y));
		double dz = Math.min(Math.abs(dmin.z), Math.abs(dmax.z));
		if (dx > 0.00000001) {
			n = d.x > 0 ? negX : posX;
		}
		if (dy > 0.00000001) {
			n = d.y > 0 ? negY : posY;
		}
		if (dz > 0.00000001) {
			n = d.z > 0 ? negZ : posZ;
		}
		if (n == null)
			return null;
		return new Hit(t, i, n, material);
	}
	

}
