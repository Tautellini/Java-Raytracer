package taute876418.a03;

import java.io.IOException;

import cgtools.ImageTexture;
import cgtools.Vec3;

public class TexImage implements Sampler {
	ImageTexture imgt;
	
	public TexImage(String filename) {
		try {
			this.imgt = new ImageTexture(filename);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static double getRelCoord(double in) {
		double out = in % 1.0;
		if (out < 0.0) {
			out = out + 1.0;
		}
		return out;
	}
	
	public Vec3 color(double u, double v) {
		double uRel = getRelCoord(u);
		double vRel = getRelCoord(v);
		double x = (imgt.width - 1) * uRel;
		double y = (imgt.height - 1) - ((imgt.height - 1) * vRel);
		return imgt.color((int) Math.round(x),
				(int) Math.round(y));
	}
}
