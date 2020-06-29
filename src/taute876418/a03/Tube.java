package taute876418.a03;

import cgtools.Vec3;

public class Tube implements Shapes {
	Vec3 start;
	Vec3 end;
	double radius;
	Vec3 color;
	Material material;
	
	public Tube (Vec3 start, Vec3 end, double radius, Material material) {
		this.start = start;
		this.end = end;
		this.radius = radius;
		this.material = material;
	}
	
	@Override
	public Hit intersect (Ray r) {
		// Wir generieren einen unendlich langen hohlen Zylinder den wir
		// durch Begrenzung des Skalars aus Start & Intersection 
		// an Start und Endpunkt abschneiden.
		//
		// https://github.com/Daniel6/Ray-Tracing/blob/master/Ray%20Tracing/src/Cylinder.java
		// https://tramberend.beuth-hochschule.de/lehre/18-ws/bmi-cgg/lectures/03-raytracing/03-raytracing-handout.html
		// https://de.wikipedia.org/wiki/Zylinder_(Geometrie)
		// https://de.wikipedia.org/wiki/Kreis
		//
		// für geschlossenen Zylinder werden 2 Discs an den jeweiligen Endpunkten hinzugefügt
		// Disc(p1, Vec3.normalize(Vec3.subtract(p1, p2)), radius, material)
		// Disc(p2, Vec3.normalize(Vec3.subtract(p2, p1)), radius, material)
		//
		// dies wird im Shape Cylinder separat gelöst
		
		Vec3 z = Vec3.normalize(Vec3.subtract(end, start));	// Höhen-Achse
//		System.out.println(z);
		Vec3 x = Vec3.normalize(Vec3.crossProduct(z, r.direction)); // x steht Senkrecht auf Höhenachse & Ray
		Vec3 y = Vec3.normalize(Vec3.crossProduct(x, z)); // y steht Senkrecht auf x & Richtung
		Vec3 w = Vec3.subtract(r.origin, start); // Vektor aus Start zum Ursprung des Rays
//		System.out.println(Vec3.dotProduct(w, x));
		Vec3 originCircle = new Vec3(Vec3.dotProduct(w, x),Vec3.dotProduct(w, y), 0);
//		System.out.println(oCircle);
		Vec3 directionCircle = new Vec3(Vec3.dotProduct(r.direction, x),Vec3.dotProduct(r.direction, y), 0);
		// Schnittberechnung Kreis
		double a = (directionCircle.y * directionCircle.y);	
		double b = (2 * directionCircle.y * originCircle.y);	
		double c = originCircle.y * originCircle.y + originCircle.x * originCircle.x - radius * radius;
		if (a == 0) {
			return null;
		}
		double diskriminante = Math.pow(b, 2) - 4 * a * c;
		if (diskriminante < 0) {
			return null;
		}
		double t1 = (-1.0*b -Math.sqrt(diskriminante)) / (2.0 * a);
		double t2 = (-1.0*b +Math.sqrt(diskriminante)) / (2.0 * a);
		if(t1 <= r.tmax && t1 >= r.tmin) {
			Vec3 intersection = r.pointAt(t1);
			// Skalar welcher die Länge vom Start zur Intersection repräsentiert
			double skalar = Vec3.dotProduct(Vec3.subtract(intersection, start), z);
//			System.out.println(skalar);
			Vec3 startMinusEnd = Vec3.subtract(end, start);
			double length = Vec3.length(startMinusEnd);
			// Skalar muss größer als 0 sein, alle die kleiner 0 sind, treffen nur den unendlichen
			// langen Zylinder und nicht den durch uns definierten Zylinder
			// Das Maximum ist bestimmt durch die Länge der der Höhenachse z
			if (skalar > 0 && skalar < length ) {
//				System.out.println("ok");
				Vec3 q = Vec3.add(end, Vec3.multiply(z, skalar));
				Vec3 normal = Vec3.subtract(intersection, q);
				return new Hit(t1, intersection, normal, material);
			}
		}
		if(t2 <= r.tmax && t2 >= r.tmin) {
			Vec3 intersection = r.pointAt(t2);
			double skalar = Vec3.dotProduct(Vec3.subtract(intersection, start), z);
			Vec3 startMinusEnd = Vec3.subtract(end, start);
			double length = Vec3.length(startMinusEnd);
			if (skalar > 0 && skalar < length ) {
				Vec3 q = Vec3.add(start, Vec3.multiply(z, skalar));
				Vec3 normal = Vec3.subtract(q, intersection);
				return new Hit(t2, intersection, normal, material);
			}
		}
		return null;
	}
}