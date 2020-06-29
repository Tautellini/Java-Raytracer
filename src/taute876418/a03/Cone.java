package taute876418.a03;

import cgtools.Vec3;

public class Cone implements Shapes {
	Vec3 center;
	double height;
	double radius;
	Material material;
	
	public Cone (Vec3 center, double height, double radius, Material material) {
		this.center = center;
		this.height = height;
		this.radius = radius;
		this.material = material;
	}
	
	@Override
	public Hit intersect (Ray r) {
		// Schnittpunktberechnung
		// (gerader) Kegel
		//
		// https://github.com/iceman201/RayTracing
		// Mathematische Erklärung in der Git-Readme unter Punkt 7
		// & https://de.wikipedia.org/wiki/Kegel_(Geometrie)
		// 
		// Vermutlich noch Fehler bei der Normalenberechnung
		// für geschlossenen Kegel wird eine Disc hinzugefügt
		// siehe ClosedCone

		double A = r.origin.x - center.x;
		double B = r.origin.z - center.z;
		double D = height - r.origin.y + center.y;
		double tan = (radius/height)*(radius/height);
		double a = (r.direction.x * r.direction.x) + (r.direction.z * r.direction.z) - (tan*(r.direction.y*r.direction.y));
		double b = (2*A*r.direction.x)+ (2*B*r.direction.z) + (2*tan*D*r.direction.y);
		double c = (A*A) + (B*B) - tan*(D*D);	
		double delta = Math.pow(b, 2) - 4 * (a * c);		
		double t1 = (-1.0*b -Math.sqrt(delta)) / (2.0 * a);
		double t2 = (-1.0*b +Math.sqrt(delta)) / (2.0 * a);
		double t;
		
		if (t1 > t2) {
//			System.out.println("t2");
			t = t2;
		} else {
//			System.out.println("t1");
			t = t1;
		}
		
//		if (t1 < r.tmin) {
//			if (t2 < r.tmin) {
//				return null;
//			} else {
//				t = t2;
//			}
//		} else {
//			if (t2 < r.tmin || t1 <= t2) {
//				t = t1;
//			} else {
//				t = t2;
//			}
//		}
		
		double rt = r.origin.y + t*r.direction.y;
		
		if ((rt > center.y) && (rt < center.y + height)) {
			Vec3 i = r.pointAt(t);
//			double q = Math.sqrt(i.x-center.x)*(i.x-center.x)+(i.z-center.z)*(i.z-center.z);
//			Vec3 norm = new Vec3(i.x-center.x, q*(radius/height), i.z-center.z);
			Vec3 norm = Vec3.normalize(Vec3.multiply(Vec3.subtract(i, center), new Vec3(1, 0, 1)));
			return new Hit(t, i, norm, material);
		}
		return null;
	}
}