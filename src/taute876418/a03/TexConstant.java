package taute876418.a03;

import cgtools.Vec3;

public class TexConstant implements Sampler {
	Vec3 color;
	
	public TexConstant(Vec3 color) {
		this.color = color;
	}
	
	public Vec3 color(double x, double y) {
		return color;
	}
	public Vec3 color() {
		return color;
	}
}
