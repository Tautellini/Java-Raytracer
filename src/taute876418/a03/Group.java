package taute876418.a03;

import java.util.ArrayList;

import cgtools.Mat4;
import cgtools.Vec3;

public class Group implements Shapes{
	ArrayList<Shapes> shape;
	Transformation trans;
	Mat4 mat;
	public ArrayList<DirectedLight> directedLights;
	public ArrayList<PointLight> pointLights;
	
	public Group() {
		this.shape = new ArrayList<Shapes>();
		this.trans = new Transformation(new Vec3(0,0,0), new Vec3(0,1,0),0);
		this.directedLights = new ArrayList<DirectedLight>();
		this.pointLights = new ArrayList<PointLight>();
	}
	
	public Group (Transformation trans) {
        this.shape = new ArrayList<Shapes>();
        this.trans = trans;
        this.directedLights = new ArrayList<DirectedLight>();
        this.pointLights = new ArrayList<PointLight>();
    }
	
	public void addShape(Shapes s) {
		this.shape.add(s);
	}
	
	public void addDirLight(DirectedLight dirLight) {
		directedLights.add(dirLight);
	}
	
	public void addPointLight(PointLight pointLight) {
		pointLights.add(pointLight);
	}
	
	public Vec3 lightSect(Ray r, Hit h) {
		Vec3 lightValue = Vec3.black;
		for(DirectedLight directedLight : directedLights) {
			Vec3 temp = directedLight.getColor(h, this);
			if(temp != null) {
				lightValue = Vec3.add(lightValue, temp);
			}
		}
		for(PointLight pointLight : pointLights) {
			Vec3 temp = pointLight.getColor(h, this);
			if(temp != null) {
				lightValue = Vec3.add(lightValue, temp);
			}
		}
		return lightValue;
	}

	public Hit intersect(Ray r) {
		r = trans.transformRay(r);
		Hit hit = null;
		double tMaxCountdown = r.tmax;
		for (Shapes s : shape) {
			Hit shapeHit = s.intersect(r);
			if ((shapeHit != null) && shapeHit.t >= r.tmin && shapeHit.t <= tMaxCountdown) {
				hit = shapeHit;
				tMaxCountdown = shapeHit.t;
			}
		}
		if (hit == null) {
			return null;
		} else {
			hit = trans.transformHit(hit);
			return hit;
		}
//		System.out.println(hit);
	}
}
