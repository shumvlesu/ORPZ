package com.mikrozajm.mymap;

import android.graphics.Color;
import android.graphics.PointF;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.location.LocationListener;
import com.yandex.mapkit.Animation;
import com.yandex.mapkit.MapKitFactory;
import com.yandex.mapkit.geometry.Point;
import com.yandex.mapkit.layers.ObjectEvent;
import com.yandex.mapkit.location.Location.*;
import com.yandex.mapkit.map.CameraPosition;
import com.yandex.mapkit.mapview.MapView;
import com.yandex.mapkit.user_location.UserLocationLayer;
import com.yandex.mapkit.user_location.UserLocationObjectListener;
import com.yandex.mapkit.user_location.UserLocationView;
import com.yandex.runtime.image.ImageProvider;

//import static android.location.LocationManager.*;


public class MyMap extends AppCompatActivity implements UserLocationObjectListener {

    private MapView mapview;
    private UserLocationLayer userLocationLayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        

        MapKitFactory.setApiKey("12903239-d527-4f83-9b5b-46c7c8b90113");
        MapKitFactory.initialize(this);

        //Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        setContentView(R.layout.activity_my_map);
        mapview = (MapView) findViewById(R.id.mapview);
        //mapview.getMap().setRotateGesturesEnabled(false);
        //mapview.getMap().move(new CameraPosition(new Point(0, 0), 14, 0, 0));

        mapview.getMap().move(
               new CameraPosition(new Point(45.01848400798782,39.10705410036076), 11.0f, 0.0f, 0.0f),
               //new CameraPosition(new Point(55.751574, 37.573856), 11.0f, 0.0f, 0.0f),
               new Animation(Animation.Type.SMOOTH, 0),
            null);


        userLocationLayer = mapview.getMap().getUserLocationLayer();
        userLocationLayer.setEnabled(true);
        userLocationLayer.setHeadingEnabled(true);

        userLocationLayer.setObjectListener(this);

    }



    @Override
    protected void onStop() {
        super.onStop();
        mapview.onStop();
        MapKitFactory.getInstance().onStop();
    }

    @Override
    protected void onStart() {
        super.onStart();
        mapview.onStart();
        MapKitFactory.getInstance().onStart();
    }

    //@Override
    public void onObjectAdded(UserLocationView userLocationView) {
        userLocationLayer.setAnchor(
                new PointF((float)(mapview.getWidth() * 0.5), (float)(mapview.getHeight() * 0.5)),
                new PointF((float)(mapview.getWidth() * 0.5), (float)(mapview.getHeight() * 0.83)));

        userLocationView.getPin().setIcon(ImageProvider.fromResource(
                this, R.drawable.arrow));
        userLocationView.getArrow().setIcon(ImageProvider.fromResource(
                this, R.drawable.arrow));
        userLocationView.getAccuracyCircle().setFillColor(Color.BLUE);
    }

    @Override
    public void onObjectRemoved(UserLocationView view) {
    }

    @Override
    public void onObjectUpdated(UserLocationView view, ObjectEvent event) {
    }
}
