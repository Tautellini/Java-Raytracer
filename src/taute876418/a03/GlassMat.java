package taute876418.a03;

import cgtools.Random;
import cgtools.Vec3;

public class GlassMat implements Material{
	final Vec3 color;
	final double opticIndex;
	
	public GlassMat() {
		color = new Vec3(0.5,0.5,1);
		opticIndex = 1.5;
	}
	
	public GlassMat(Vec3 color, double opticIndex) {
		this.color = color;
		this.opticIndex = opticIndex;
	}

	public Vec3 albedo(Ray r, Hit h) {
		return this.color;
	}

	public Vec3 emission(Ray r, Hit h) {
		return Vec3.zero;
	}

	public Ray scattered(Ray r, Hit h) {        
		double n1 = 1.0;
		double n2 = 1.0;
		Vec3 normal = h.normal;
		double s = Vec3.dotProduct(r.direction, h.normal);
		if(s > 0) {
			n1 = this.opticIndex;
			normal = Vec3.multiply(-1.0, normal);
		} else {
			n2 = this.opticIndex;
		}
		double rr = n1 / n2;
		double rr0 = Math.pow((n1-n2)/(n1+n2), 2);
		double schlick = rr0 + ((1-rr0) * Math.pow(1 + Vec3.dotProduct(r.direction, normal),5));
		double c = Vec3.dotProduct(Vec3.multiply(-1, normal), r.direction);
		double diskriminante = 1 - Math.pow(rr, 2) * (1-Math.pow(c, 2) );
		if(diskriminante < 0) {
//			System.out.println("DISC < 0");
			Vec3 rD = Vec3.subtract(r.direction, Vec3.multiply(2, Vec3.multiply
					(Vec3.dotProduct(h.normal, r.direction), h.normal) ) );
			return new Ray(h.point, rD, 0.00000001, Double.POSITIVE_INFINITY);
		} else {
//			System.out.println("DISC > 0");
			if(Random.random() > schlick) {
				Vec3 refracted = new Vec3(rr * r.direction.x + (rr*c - Math.sqrt(diskriminante) ) * normal.x,
										  rr * r.direction.y + (rr*c - Math.sqrt(diskriminante) ) * normal.y,
										  rr * r.direction.z + (rr*c - Math.sqrt(diskriminante) ) * normal.z );
				return new Ray(h.point, refracted, 0.00000001, Double.POSITIVE_INFINITY);	
			} else {
				return new Ray(h.point, Vec3.subtract(r.direction, Vec3.multiply(s*2, normal) ), 0.000000001, Double.POSITIVE_INFINITY);
			}
		}
		
//		double n1;
//        double n2;
//        Vec3 norm;
////        System.out.println("Vec3.dotProduct(r.direction, h.normal)  : "+Vec3.dotProduct(r.direction, h.normal));
//        if(Vec3.dotProduct(r.direction, h.normal) > 0) {
////        	System.out.println("OUTSIDE: Switching n1 and n2");
//            n1 = this.opticIndex;
//            n2 = 1.0;
//            norm = Vec3.multiply(-1.0, h.normal);
//        } else {
////        	System.out.println("INSIDE: Standard n1/n2");
//            n1 = 1.0;
//            n2 = this.opticIndex;
//            norm = Vec3.multiply(1.0, h.normal);
//        }
//        double rr = n1/n2;
//        double r0 = Math.pow((n1-n2)/(n1+n2), 2);
//        double c = Vec3.dotProduct(norm, r.direction);
//        double disc = 1-rr*rr*(1-c*c);
//        if(disc < 0) {
////            System.out.println("NEGATIV");
//        	Vec3 rD = Vec3.subtract(r.direction, Vec3.multiply(2, Vec3.multiply
//    				(Vec3.dotProduct(h.normal, r.direction), h.normal) ) );
//            return new Ray(h.point, rD, 0.00000000001, Double.POSITIVE_INFINITY);
//        }
////        Vec3 refracted = new Vec3((r0+(1-r0)*Math.pow((1+h.normal.x * r.direction.x),5)),
////        		(r0+(1-r0)*Math.pow((1+h.normal.y * r.direction.y),5)),
////        		(r0+(1-r0)*Math.pow((1+h.normal.z * r.direction.z),5))
////        		);
//        Vec3 refracted = new Vec3(rr * r.direction.x + ( rr * c - Math.sqrt(disc) ) * norm.x,
//                                  rr * r.direction.y + ( rr * c - Math.sqrt(disc) ) * norm.y,
//                                  rr * r.direction.z + ( rr * c - Math.sqrt(disc) ) * norm.z );
////        System.out.println("POSITIV");
//        return new Ray(h.point, refracted, 0.00000000001, Double.POSITIVE_INFINITY);
	}
}
