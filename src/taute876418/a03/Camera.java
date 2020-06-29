package taute876418.a03;

import cgtools.Mat4;
import cgtools.Vec3;

public class Camera {
	int height;
	int width;
	double fov;
	Vec3 location;
	Mat4 mulMat;
	double x,y,z;
	
	public Camera(double fov, int width, int height, Vec3 location, Vec3 rotationAxis, double angle) {
		this.fov = fov;
		this.width = width;
		this.height = height;
		this.location = location;
		this.mulMat = Mat4.translate(location).multiply(Mat4.rotate(rotationAxis, angle));
		
	}
	public Ray Strahlrichtung(double x, double y) {
		z = ( (width/2) / (Math.tan(Math.toRadians(fov/2)) ) );
		this.x = x-(width/2);
		this.y = (height/2)-y;
		Vec3 direction = mulMat.transformDirection(new Vec3(this.x, this.y, -z));
		return new Ray(this.location, direction, 0, Double.POSITIVE_INFINITY);
	}
}