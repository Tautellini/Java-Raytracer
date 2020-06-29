package taute876418.a03;

import cgtools.Vec3;

public class ReflectionProperties {
	
	public static Vec3 calculateRadiance(Group group, Ray ray, int depth) {
		Hit hit = group.intersect(ray);
		if (depth == 0) {
			return Vec3.black;
		}
		Vec3 emission = hit.material.emission(ray, hit);
		Ray scattered = hit.material.scattered(ray, hit);
		Vec3 lightColor = group.lightSect(ray, hit);
		emission = Vec3.add(emission, lightColor);
		if (scattered != null) {
			return Vec3.add(emission, Vec3.multiply(hit.material.albedo(ray, hit), 
					calculateRadiance(group, scattered, depth-1) ));
		} else {
			return emission;
		}
	}
}