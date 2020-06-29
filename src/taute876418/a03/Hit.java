package taute876418.a03;

import cgtools.Vec3;

public class Hit {
	double t;
	Vec3 point;
	Vec3 normal;
	Material material;
	
	public Hit(double t, Vec3 point, Vec3 normal, Material material) {
		this.t = t;
		this.point = point;
		this.normal = Vec3.normalize(normal);
		this.material = material;
	}
	
	public String toString() {
        String returner = "T: " + this.t + ", LOCATION: " + this.point + ", NORMAL: " + 
                              this.normal + ", Material: " + this.material;
        return returner;
    }
}
