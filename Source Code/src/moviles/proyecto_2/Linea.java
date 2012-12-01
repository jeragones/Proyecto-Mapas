package moviles.proyecto_2;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class Linea  extends Overlay{
	private GeoPoint geoPointInicio;
	private GeoPoint geoPointFin;
	private Projection projection;
	private String HexColor="red";
	
	public Linea(){
		
	}
	public Linea(GeoPoint geoPointInicio, GeoPoint geoPointFin, String color){
		if(color.length()>0){
			HexColor="#"+color;
		}
		this.geoPointInicio=geoPointInicio;
		this.geoPointFin=geoPointFin;
	}
	public Linea(GeoPoint geoPointInicio, GeoPoint geoPointFin){
		this.geoPointInicio=geoPointInicio;
		this.geoPointFin=geoPointFin;
	}
	public void draw(Canvas canvas, MapView mapView, boolean shadow){
		super.draw(canvas, mapView, shadow);
		Paint mPaint = new Paint();
		mPaint.setDither(true);
		mPaint.setColor(Color.parseColor(HexColor));
		mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(4);
		Point p1 = new Point();
		Point p2 = new Point();
		projection = mapView.getProjection();
		projection.toPixels(this.geoPointInicio, p1);
		projection.toPixels(this.geoPointFin, p2);
		Path path=new Path();
		path.moveTo(p2.x, p2.y);
		path.lineTo(p1.x, p1.y);
		canvas.drawPath(path, mPaint);
	}
	
	/**
	 * La f—rmula de Haversine 
	 * @param lat_a
	 * @param lng_a
	 * @param lat_b
	 * @param lon_b
	 * @return
	 */
	public static int getDistance(int lat_a,int lng_a, int lat_b, int lon_b){
		  int Radius = 6371000; //Radio de la tierra
		  double lat1 = lat_a / 1E6;
		  double lat2 = lat_b / 1E6;
		  double lon1 = lng_a / 1E6;
		  double lon2 = lon_b / 1E6;
		  double dLat = Math.toRadians(lat2-lat1);
		  double dLon = Math.toRadians(lon2-lon1);
		  double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.sin(dLon /2) * Math.sin(dLon/2);
		  double c = 2 * Math.asin(Math.sqrt(a));
		  return (int) (Radius * c);  

		 }
	public static String Distance(int distance){  
		if(distance >= 1000){
			int k = distance / 1000;
			int m = distance - (k*1000);
			m = m / 100;
			return String.valueOf(k) + (m>0?("."+String.valueOf(m)):"") + " Km" +(k>1?"s":"");
		} else {
		   return String.valueOf(distance) + " metro"+(distance==1?"":"s");
		}  
	 }

}
