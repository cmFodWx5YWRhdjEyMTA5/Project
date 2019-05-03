package cu.cdac.myfinalapproyect;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.google.android.gms.location.FusedLocationProviderClient;

public class LocationService extends Service {
    private FusedLocationProviderClient fusedLocationProviderClient;
    private LocationServiceBinder binder = new LocationServiceBinder();


    public LocationService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return binder;

    }

    class LocationServiceBinder extends Binder {
        public LocationService getService() {
            return LocationService.this;
        }
    }
}
