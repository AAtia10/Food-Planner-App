package com.example.foodplanningapp.models;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkRequest;
import android.os.Build;
import androidx.annotation.NonNull;

public class Connection {

    private static ConnectivityManager.NetworkCallback networkCallback;

    // Method to check if the device is connected to the internet
    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                Network network = cm.getActiveNetwork();
                if (network == null) return false;
                NetworkCapabilities capabilities = cm.getNetworkCapabilities(network);
                return capabilities != null &&
                        (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET));
            } else {
                return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
            }
        }
        return false;
    }

    // Register a network callback to monitor connectivity changes
    public static void registerNetworkCallback(Context context, final ConnectionListener listener) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                networkCallback = new ConnectivityManager.NetworkCallback() {
                    @Override
                    public void onAvailable(@NonNull Network network) {
                        listener.onNetworkStatusChanged(true);
                    }

                    @Override
                    public void onLost(@NonNull Network network) {
                        listener.onNetworkStatusChanged(false);
                    }
                };

                NetworkRequest request = new NetworkRequest.Builder()
                        .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                        .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
                        .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
                        .build();
                cm.registerNetworkCallback(request, networkCallback);
            }
        }
    }

    // Unregister the network callback to avoid memory leaks
    public static void unregisterNetworkCallback(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null && Build.VERSION.SDK_INT >= Build.VERSION_CODES.N && networkCallback != null) {
            cm.unregisterNetworkCallback(networkCallback);
        }
    }

    // Interface for callback method
    public interface ConnectionListener {
        void onNetworkStatusChanged(boolean isConnected);
    }
}
