package com.dohr.complaint.cell.UtilsClasses;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Build;

public class CheckNetworkConnection {

    public static boolean checkNetworkConnection(Context context){
        ConnectivityManager check = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            Network[] networks = check.getAllNetworks();
            NetworkInfo networkInfo;
            for (Network network: networks){
                networkInfo = check.getNetworkInfo(network);
                if(networkInfo.getState().equals(NetworkInfo.State.CONNECTED)){
                    return true;
                }
            }
        }
        else{
            if(check != null){
                NetworkInfo[] info = check.getAllNetworkInfo();
                if (info != null) {
                    for (NetworkInfo networkInfo : info) {
                        if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
