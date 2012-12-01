package moviles.proyecto_2;

import java.util.List;

import com.google.android.maps.MapView;
import com.google.android.maps.Overlay;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {

	static MapView mapa=null;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mapa = (MapView) findViewById(R.id.mapa);
        //mapa.setBuiltInZoomControls(true);
        List<Overlay> capas = mapa.getOverlays();
        OverlayMapa om = new OverlayMapa();
        capas.add(om);
        mapa.postInvalidate();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
