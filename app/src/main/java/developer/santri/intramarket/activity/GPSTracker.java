package developer.santri.intramarket.activity;

import android.app.AlertDialog;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.provider.Settings;
import android.util.Log;


public class GPSTracker extends Service implements LocationListener {
    private Context context = null;
    boolean isGPSEnabled = false, isNetworkEnabled = false,
            canGetLocation = false;
    Location location = null;
    double latitude, longitude;

    private static final long MIN_DISTANCE = (long) 1; // 10 meter
    private static final long MIN_TIME = 1000 * 1 * 1; // 1minute

    protected LocationManager locationManager;
    public static final String NEW_POSITION = "newPosition";


    public GPSTracker(Context c) {
        this.context = c;

        getLocation();
    }

    private Location getLocation() {
        try {
            locationManager = (LocationManager) context
                    .getSystemService(LOCATION_SERVICE);

            // getting gps status
            isGPSEnabled = locationManager
                    .isProviderEnabled(LocationManager.GPS_PROVIDER);
            // getting network status
            isNetworkEnabled = locationManager
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            //if (!isGPSEnabled && !isNetworkEnabled) {
            if (!isGPSEnabled && !isNetworkEnabled) {
                showSettingGps();
            } else {
                canGetLocation = true;
                // get lat/lng by network
                if (isNetworkEnabled) {


                    locationManager.requestLocationUpdates(
                            LocationManager.NETWORK_PROVIDER, MIN_TIME,
                            MIN_DISTANCE, this);
                    Log.d("network", "network enabled");
                    if (locationManager != null) {
                        location = locationManager
                                .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                        if (location != null) {
                            latitude = location.getLatitude();
                            longitude = location.getLongitude();
                        }
                    }
                }

                // get lat/lng by gps
                if (isGPSEnabled) {
                    if (location == null) {
                        locationManager.requestLocationUpdates(
                                LocationManager.GPS_PROVIDER, MIN_TIME,
                                MIN_DISTANCE, this);
                        Log.d("GPS", "GPS enabled");
                        if (locationManager != null) {
                            location = locationManager
                                    .getLastKnownLocation(LocationManager.GPS_PROVIDER);
                            if (location != null) {
                                latitude = location.getLatitude();
                                longitude = location.getLongitude();
                            }

                        }
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return location;

    }

    /**
     * Stop using GPS listener Calling this function will stop using GPS in your
     * app
     * */
    public void stopUsingGPS() {
        if (locationManager != null) {

            locationManager.removeUpdates(this);
        }
    }

    public double getLatitude(){
        if(location != null){
            latitude = location.getLatitude();
        }

        return latitude;
    }

    public Location getLocations(){
        if(location != null){
            return location;
        }

        return null;
    }

    public double getLongitude(){
        if(location != null){
            longitude = location.getLongitude();
        }

        return longitude;
    }

    /**
     * Function to check GPS/wifi enabled
     *
     * @return boolean
     * */
    public boolean canGetLocation(){
        return canGetLocation;
    }

    /**
     * Function to show settings alert dialog On pressing Settings button will
     * lauch Settings Options
     *
     * */
    public void showSettingGps(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);

        alertBuilder.setTitle("GPS Setting");
        alertBuilder.setMessage("GPS is not enabled. Do you want to go to settings menu?");

        alertBuilder.setPositiveButton("Setting", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);

            }
        });
        alertBuilder.setNegativeButton("Cancel"	, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();

            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {
        if(location != null){
            //RbHelper.pesan(context, "perubahan Alamat");


            if(this.location != location){
                sendPosisi(location.getLatitude(), location.getLongitude());
                this.location = location;
            }




        }


    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub

    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO Auto-generated method stub
        return null;
    }






    public void sendPosisi(double lat, double lng) {
        Intent i = new Intent(NEW_POSITION);
        i.putExtra("lat", lat);
        i.putExtra("lng", lng);
        context.sendBroadcast(i);
        Log.i("send broadcast new position", "");
    }


}
