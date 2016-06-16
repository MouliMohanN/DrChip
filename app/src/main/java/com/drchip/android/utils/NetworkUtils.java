package com.drchip.android.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;

import com.drchip.android.DrChipApplication;
import com.drchip.android.activities.BaseActivity;

import java.io.IOException;

/**
 */
public class NetworkUtils {
    public static boolean isWifiConnected() {
        ConnectivityManager connManager = (ConnectivityManager) DrChipApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mWifi = connManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        return mWifi.isConnected();
    }

    public static boolean isMobileDataConnected() {
        ConnectivityManager connManager = (ConnectivityManager) DrChipApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mMobileData = connManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        return mMobileData.isConnected();
    }

    /*
    * This method is checking whether mobile is connected to wifi or mobile data,
    * its not checking internet connetion,
    * This method returns true if you are Connected to a wifi which doesn't have internet connection,
    * so use isSocketConnected instead
    * */
    public static boolean isNetworkConnected(BaseActivity baseActivity) {
        ConnectivityManager connManager = (ConnectivityManager) baseActivity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = connManager.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        return isConnected;
    }

    public static boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("ping -c 1  www.google.com");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e) {
            e.printStackTrace();
        }catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }

    public static void getNetWorkType(String msg){
        TelephonyManager mTelephonyManager = (TelephonyManager)
                DrChipApplication.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        int networkType = mTelephonyManager.getNetworkType();
        String connectedTo = "";

        switch (networkType) {
            case TelephonyManager.NETWORK_TYPE_GPRS:
            case TelephonyManager.NETWORK_TYPE_EDGE:
            case TelephonyManager.NETWORK_TYPE_CDMA:
            case TelephonyManager.NETWORK_TYPE_1xRTT:
            case TelephonyManager.NETWORK_TYPE_IDEN:
                connectedTo = "2G";
                break;
            case TelephonyManager.NETWORK_TYPE_UMTS:
            case TelephonyManager.NETWORK_TYPE_EVDO_0:
            case TelephonyManager.NETWORK_TYPE_EVDO_A:
            case TelephonyManager.NETWORK_TYPE_HSDPA:
            case TelephonyManager.NETWORK_TYPE_HSUPA:
            case TelephonyManager.NETWORK_TYPE_HSPA:
            case TelephonyManager.NETWORK_TYPE_EVDO_B:
            case TelephonyManager.NETWORK_TYPE_EHRPD:
            case TelephonyManager.NETWORK_TYPE_HSPAP:
                connectedTo = "3G";
                break;
            case TelephonyManager.NETWORK_TYPE_LTE:
                connectedTo = "4G";
                break;
            default:
                connectedTo = "Unknown";
                break;
        }
    }
}
