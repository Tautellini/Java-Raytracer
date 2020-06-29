package taute876418.a03;

import cgtools.Mat4;
import cgtools.Vec3;

public class Transformation {
	private Mat4 mat;
	private Mat4 matInv;
	private Mat4 matInvT;
	

//	public Hit transform(Hit h) {
//		return new Hit(h.t, mulMat.transformPoint(h.point), mulMat.transformDirection(h.normal), h.material);
//	}
//	public Hit revert(Hit h) {
//		return new Hit(h.t, mulMatInv.transformPoint(h.point), mulMatInv.transformDirection(h.normal), h.material);
//	}
//	public Ray transform(Ray r) {
//		return new Ray(mulMat.transformPoint(r.origin), mulMat.transformDirection(r.direction), r.tmin, r.tmax);
//	}
//	
	public Transformation(Vec3 pos, Vec3 rotAxis, double angle) {
        this.mat = Mat4.translate(pos).multiply(Mat4.rotate(rotAxis, angle));
        matInv = this.mat.invertFull();
        matInvT = matInv.transpose();
    }

	public Ray transformRay(Ray r) {
		return new Ray(matInv.transformPoint(r.origin), matInv.transformDirection(r.direction), r.tmin, r.tmax);
	}

	public Hit transformHit(Hit h) {
		if (h == null)
			return null;
		return new Hit(h.t, mat.transformPoint(h.point), Vec3.normalize(matInvT.transformPoint(h.normal)), h.material);
	}
//	public Ray revert(Ray r) {
//		return new Ray(locMatInv.transformPoint(r.origin), rotMatInv.transformDirection(r.direction), r.tmin, r.tmax);
//	}
}
