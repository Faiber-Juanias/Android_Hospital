package com.example.multimedia.hospital;

import android.location.Location;
import android.location.LocationListener;
import android.location.LocationProvider;
import android.os.Bundle;
import android.widget.Toast;

import com.example.multimedia.hospital.fragments.Fragment_solicitud;

public class LocalizacionGps implements LocationListener{

    private Fragment_solicitud fragment_solicitud;

    public void setFragment_solicitud(Fragment_solicitud fragment_solicitud) {
        this.fragment_solicitud = fragment_solicitud;
    }

    @Override
    public void onLocationChanged(Location location) {
        fragment_solicitud.txtGps.setText(String.valueOf(location.getLatitude() + " - " + location.getLongitude()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        switch (status){
            case LocationProvider.AVAILABLE:
                Toast.makeText(fragment_solicitud.getContext(), "GPS disponible.", Toast.LENGTH_SHORT).show();
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                Toast.makeText(fragment_solicitud.getContext(), "GPS no disponible temporalmente.", Toast.LENGTH_SHORT).show();
                break;
            case LocationProvider.OUT_OF_SERVICE:
                Toast.makeText(fragment_solicitud.getContext(), "GPS fuera de servicio.", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onProviderEnabled(String s) {
        Toast.makeText(fragment_solicitud.getContext(), "GPS activado.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String s) {
        Toast.makeText(fragment_solicitud.getContext(), "GPS desactivado.", Toast.LENGTH_SHORT).show();
    }
}
