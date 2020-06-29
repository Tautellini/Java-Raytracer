package taute876418.a03;

import cgtools.Vec3;

public class BackMat implements Material {
	final Vec3 backColor;
	public BackMat() {
		backColor = new Vec3(0.5,0.5,1);
	}
	public BackMat(Vec3 backColor) {
		this.backColor = backColor;
	}
	@Override
	public Vec3 albedo(Ray r, Hit h) {
		return null;
	}

	@Override
	public Vec3 emission(Ray r, Hit h) {
		return backColor;
	}

	@Override
	public Ray scattered(Ray r, Hit h) {
		return null;
	}

}
