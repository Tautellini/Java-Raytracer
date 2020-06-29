package taute876418.a03;

import cgtools.Vec3;

public interface Material {
	abstract Vec3 albedo(Ray r, Hit h);
	abstract Vec3 emission(Ray r, Hit h);
	abstract Ray scattered(Ray r, Hit h);
}
