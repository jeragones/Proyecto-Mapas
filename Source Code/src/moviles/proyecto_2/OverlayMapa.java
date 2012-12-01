package moviles.proyecto_2;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

import com.google.android.maps.GeoPoint;
import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;
import com.google.android.maps.Projection;

public class OverlayMapa extends Overlay {
	
	private Double latitud;// = 37.40*1E6;
    private Double longitud;// = -5.99*1E6;
    private Canvas canvas;
    
    
	public static boolean flag=false;
	public static Double lat1=null;
	public static Double lon1=null;
	public static Double lat2=null;
	public static Double lon2=null;
	
	public void draw(Canvas canvas, MapView mapView, boolean shadow)
    {
		latitud= 37.40*1E6;
	    longitud = -5.99*1E6;
		this.canvas = canvas;
    }
	
	public boolean onTap(GeoPoint point, MapView mapView){
		latitud = point.getLatitudeE6()/1E6;
		longitud = point.getLongitudeE6()/1E6;
		
		Projection projection = mapView.getProjection();
        GeoPoint geoPoint =
            new GeoPoint(latitud.intValue(), longitud.intValue());
        
            Point centro = new Point();
            projection.toPixels(geoPoint, centro);
            Paint p = new Paint();
            p.setColor(Color.BLUE);
            Bitmap bm = BitmapFactory.decodeResource(
                    mapView.getResources(),
                    R.drawable.ic_launcher);

           canvas.drawBitmap(bm, centro.x - bm.getWidth(),
                                  centro.y - bm.getHeight(), p);        		
		if(flag){
			lat2=point.getLatitudeE6()/1E6;
			lon2=point.getLongitudeE6()/1E6;
			flag=false;
			
			List<Overlay> mapOverlays = MainActivity.mapa.getOverlays();
			List<GeoPoint> list = new ArrayList<GeoPoint>();
			int localLat1 = (int)(lat1*1E6);
			int localLon1 = (int)(lon1*1E6);
			int localLat2 = (int)(lat2*1E6);
			int localLon2 = (int)(lon2*1E6);
			list.add(new GeoPoint(localLat1, localLon1));
			list.add(new GeoPoint(localLat2, localLon2));
			for(int i=0; i<list.size(); i++){
				if (i>0){
					mapOverlays.add(new Linea(list.get(i-1),list.get(i)));
				}
			}
			MainActivity.mapa.postInvalidate();
			Context contexto=MainActivity.mapa.getContext();
		}
		else{
			lat1=point.getLatitudeE6()/1E6;
			lon1=point.getLongitudeE6()/1E6;
			if(lat2 != null & lon2 != null) {
				List<Overlay> mapOverlays = MainActivity.mapa.getOverlays();
				List<GeoPoint> list = new ArrayList<GeoPoint>();
				int localLat1 = (int)(lat1*1E6);
				int localLon1 = (int)(lon1*1E6);
				int localLat2 = (int)(lat2*1E6);
				int localLon2 = (int)(lon2*1E6);
				list.add(new GeoPoint(localLat1, localLon1));
				list.add(new GeoPoint(localLat2, localLon2));
				for(int i=0; i<list.size(); i++){
					if (i>0){
						mapOverlays.add(new Linea(list.get(i-1),list.get(i)));
					}
				}
				MainActivity.mapa.postInvalidate();
				Context contexto=MainActivity.mapa.getContext();
			}
			flag=true;
		}
		return false;
	}
	

}
