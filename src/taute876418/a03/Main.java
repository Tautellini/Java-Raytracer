package taute876418.a03;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import cgtools.Random;
import cgtools.Vec3;
import taute876418.Image;

public class Main {
	
	public static void main(String[] args) throws InterruptedException {
		Camera camera = new Camera(90, 800, 400, new Vec3(0,5, -10), new Vec3(1, 1, 0), -15);
		Camera camera2 = new Camera(90, 800, 400, new Vec3(5,10, 10), new Vec3(0, 1, 0), -30);
//		Camera cameraStandard = new Camera(90, 800, 500, new Vec3(10,4,-7), new Vec3(0,1,0), 0);
		Image image1 = new Image(camera.width, camera.height );
		Image image2 = new Image(camera.width, camera.height );
		Group group1 = new Group(new Transformation(new Vec3(10,0,0), new Vec3(0,1,0), 0));
//		Mat4 mat = Mat4.translate(new Vec3(2,0,0)).multiply(Mat4.rotate(new Vec3(0,0,1), 90));
//		Group group2 = new Group(new Transformation(new Vec3(0,0,0), new Vec3(0,1,0), 0));
//		Shapes backGround = new Background(new BackMat(new Vec3(0.025,0.025,0.025)));
		Shapes backGround = new Background(new BackMat(new Vec3(0.1,0.1,0.8)));
		group1.addShape(backGround);
//		group2.addShape(backGround);
		Shapes plane = new Plane(new Vec3(0.0, 0.0, 0.0), new Vec3(0,1,0), new DiffusMat(new Vec3(0.02,0.02,0.02)));
//		Group group3 = new Group(new Transformation(new Vec3(10,0,0), new Vec3(0,1,0), 10));
		Group ausgabe1 = new Group(new Transformation(new Vec3(0,0,0), new Vec3(0,1,0), 0));
		ausgabe1.addShape(backGround);
		ausgabe1.addShape(plane);
//		Shapes box = new Box(new Vec3(0,0,0), new Vec3(1,1,1) , new DiffusMat(new Vec3(1,1,1)));
		Shapes sphere = new Sphere(new Vec3(0,-1,-30), 5, new DiffusMat(new Vec3(.1,.1,2)));
		ausgabe1.addShape(sphere);
		DirectedLight dLight = new DirectedLight(new Vec3(.1,0.7,.1), new Vec3(1,0,0));
		PointLight pLight = new PointLight(new Vec3(.4,4.0,.4), new Vec3(10,10,10));
//		ausgabe1.addDirLight(dlight);
		ausgabe1.addPointLight(pLight);
		Group ausgabe2 = new Group(new Transformation(new Vec3(0,0,0), new Vec3(0,1,0), 0));
//		Shapes cyl = new Cylinder(new Vec3(0,0,0), new Vec3(0,4,0), 2, new DiffusMat(new Vec3(2,.1,.1)));
		Shapes sphere2 = new Sphere(new Vec3(0,3,-30), 5, new DiffusMat(new Vec3(.1,1,.1)));
//		Shapes box = new Box(new Vec3(1,1,1), new Vec3(3,3,3), new DiffusMat(new Vec3(2,.1,.1)));
		ausgabe2.addShape(backGround);
		ausgabe2.addShape(plane);
		ausgabe2.addShape(sphere2);
//		ausgabe2.addShape(box);
//		DirectedLight dlight2 = new DirectedLight(new Vec3(.2,1.0,.2), new Vec3(-1,0,0));
//		PointLight pLight2 = new PointLight(new Vec3(.2,.2,.9), new Vec3(10,10,10));
		ausgabe2.addDirLight(dLight);
//		Group boxGroup = new Group(new Transformation(new Vec3(0,0,0), new Vec3(0, 1, 0),0));
//		Shapes boxArray[] = new Shapes[10];
//		double rx = 0;
//		double rx2 = 2;
//		double ry = 0.5;
//		double light = Random.random()*25;
//		double light2 = Random.random()*25;
//		double light3 = Random.random()*25;
//		for (int i=0; i<10; i++) {
//			if (i%4 == 0) {
//				boxArray[i] = new Box(new Vec3(rx,0,0), new Vec3(rx2,ry,4), new BackMat(new Vec3(20,20,20)));
//				boxGroup.addShape(boxArray[i]);
//				rx += 2.5;
//				rx2 += 2.5;
//				ry += 0.2;
//				continue;
//			}
//			boxArray[i] = new Box(new Vec3(rx,0,0), new Vec3(rx2,ry,4), new DiffusMat(new Vec3(0.3,0.3,0.3)));
//			boxGroup.addShape(boxArray[i]);
//			rx += 2.5;
//			rx2 += 2.5;
//			ry += 0.2;
//		}
//		boxGroup.addShape(backGround);
//		Group boxGroupArray[] = new Group[10];
////		double rz = 0;
//		for (int k=0; k<10; k++) {
//			boxGroupArray[k] = new Group(new Transformation(new Vec3(0,0,4.5), new Vec3(0, 1, 0),0));
//			boxGroupArray[k].addShape(boxGroup);
//			group1.addShape(boxGroupArray[k]);
////			rz += 4.5;
//		}
		
//
//		Shapes tube = new Tube(new Vec3(0,0,0), new Vec3(0,1,0), 3.0, new DiffusMat(Vec3.red));
//		Shapes tube2 = new Tube(new Vec3(0,0,0), new Vec3(0,1,0), 3.0, new DiffusMat(Vec3.red));
//		Shapes tree = new Tree(new Vec3(0,0,0), new Vec3(0,1,0), 3.0, new DiffusMat(Vec3.red));
//		group1.addShape(tube);
//		group2.addShape(tube2);
//		group2.addShape(group1);
//		ausgabe.addShape(group2);
//		ausgabe.addShape(group3);
//		group1.addShape(tree);
//		group2.addShape(group1);
		rayTrace(camera, ausgabe1, image1, 2);
		write(image1,     "doc/a11-1.png");
		rayTrace(camera2, ausgabe2, image2, 2);
		write(image2,     "doc/a11-2.png");
		
//		rayTrace(camera, group2, image1, 2);
//		write(image1,     "doc/abgabe.png");
//		rayTrace(camera2, group2, image2, 4);
//		write(image2,     "doc/a08-2.png");
		
		
		// Da mir die Instanzierung von 12 Bildern mit anschließender Abspeicherung in ein Array zur direkten
		// programmatischen Darstellung der Tabelle zu kompliziert war und irgendwie auch einfach zu lange dauert für einen "run",
		// habe ich die Messung für die Szene manuell 12x vorgenommen.
		// Die Zeit in Minuten wird in der Image.java berechnet aus time = ((endTime - startTime)*Math.pow(10, -3))/60
		// die startTime und endTime wird aus der current-Systemtime in ms generiert.
		// Der folgende Graph stell dies dar:
		
//		XYSeries series = new XYSeries("XYGraph");
//		series.add(1, 6.176266666666667); // 6.176266666666667
//		series.add(2, 3.2301833333333336); // 3.2301833333333336
//		series.add(3, 2.1814166666666663); // 2.1814166666666663
//		series.add(4, 1.6830666666666667); // 1.6830666666666667
//		series.add(5, 1.4681833333333334); // 1.4681833333333334
//		series.add(6, 1.3023); // 1.3023 
//		series.add(7, 1.2630166666666667); // 1.2630166666666667
//		series.add(8, 1.2063666666666668); // 1.2063666666666668
//		series.add(9, 1.1532833333333334); // 1.1532833333333334
//		series.add(10, 1.1201333333333332); // 1.1201333333333332
//		series.add(11, 1.15215); // 1.15215   ???? Messung 4x vorgenommen, unerklärliches Ergebnis?!
//		series.add(12, 1.0816666666666668); // 1.0816666666666668
//		XYSeriesCollection dataset = new XYSeriesCollection();
//		dataset.addSeries(series);
//		// Graph
//		JFreeChart chart = ChartFactory.createXYLineChart(
//		"1280x720 - 16xSS - ~400 Objekte - Ryzen5 1600x ", // Title
//		"Threads", // x-axis Label
//		"Zeit in Minuten", // y-axis Label
//		 dataset, // Dataset
//		 PlotOrientation.VERTICAL, // Plot Orientation
//		 true, // Legende
//		 true, // Tooltips
//		 false // Configure chart to generate URLs?
//		 );
//		 XYPlot plot = chart.getXYPlot();
//		 ValueAxis xAxis = plot.getDomainAxis();
//		 xAxis.setAutoTickUnitSelection(false); // Auto-Tick Range ausschalten damit keine 0.5 Werte angezeigt werden
//		 try {
//		 ChartUtilities.saveChartAsPNG(new File("chart.png"), chart, 1000, 500);
//		 System.out.println("done");
//		 } catch (IOException e) {
//		 System.err.println("Problem occurred creating chart.");
//		 }
	}
	
	static void rayTrace(Camera camera, Group group, Image image, int n) throws InterruptedException {
		final ThreadPoolExecutor executor = new ThreadPoolExecutor(12, 12, 1, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
		for (int x = 0; x < camera.width; x++) {
			for (int y = 0; y < camera.height; y++) {
				final int xx = x;
				final int yy = y;
				executor.execute(new Runnable() {
					public void run() {
//						System.out.println(executor.getActiveCount());
//						System.out.println("rayTrace:  xx:"+xx);
						Vec3 superSampled = superSampling(xx, yy, n, camera, group);
						image.setPixel(xx, yy, superSampled);
					}
				});
			}
		}
		executor.shutdown();
		executor.awaitTermination(5, TimeUnit.MINUTES);
	}
	
	static Vec3 superSampling (double x, double y, int n, Camera camera, Group group) {
		Vec3 tmp = Vec3.zero;
		
		for (int xi = 0; xi != n; xi++) {
			for(int yi = 0; yi != n; yi++ ) {
				double rx = cgtools.Random.random();
				double ry = cgtools.Random.random();
				double xs = x + (xi + rx) / n;
				double ys = y + (yi + ry) / n;
				Ray ray = camera.Strahlrichtung(xs, ys);
				tmp = Vec3.add(tmp, ReflectionProperties.calculateRadiance(group, ray, 15));
			}
		}
		Vec3 rColor = Vec3.divide(tmp,  n*n);
		return new Vec3	(	Math.pow(rColor.x, 1/2.2	),
							Math.pow(rColor.y, 1/2.2	),
							Math.pow(rColor.z, 1/2.2 )	);
	}

	static void write(Image image, String filename) {
		try {
			image.write(filename);
			System.out.println("Wrote image: " + filename);
		} catch (IOException error) {
			System.out.println(String.format("Something went wrong writing: %s: %s", filename, error));
		}
	}
}