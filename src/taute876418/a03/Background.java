package taute876418.a03;

import cgtools.Vec3;

public class Background implements Shapes {
    Vec3 color;
	Material material;
    
    public Background() {
    	this.color = new Vec3(0.1,0.1,0.1);
    }
    
    public Background(Material material) {
        this.material = material;
    }
    
	@Override
	public Hit intersect(Ray r) {
		// TODO Auto-generated method stub
		double rX = -Double.POSITIVE_INFINITY;
        double rY = -Double.POSITIVE_INFINITY;
        double rZ = -Double.POSITIVE_INFINITY;
        if(r.tmax == Double.POSITIVE_INFINITY) {
            if(r.direction.x > 0 ) {
                rX = Double.POSITIVE_INFINITY;
            }
            if(r.direction.y > 0 ) {
                rY = Double.POSITIVE_INFINITY;
            }
            if(r.direction.z > 0 ) {
                rZ = Double.POSITIVE_INFINITY;
            }
            return new Hit(Double.POSITIVE_INFINITY, new Vec3(rX, rY, rZ), new Vec3(0,0,0), this.material);
        }
        return null;
	}
}
