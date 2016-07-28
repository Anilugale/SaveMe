package com.example.anil.saveme;

import android.content.Context;
import android.telephony.TelephonyManager;
import android.telephony.gsm.GsmCellLocation;
import android.text.TextUtils;
import android.util.Log;

/**
 Created by anil on 6/17/16.
 */
public class Utility {

    static  TelephonyManager telemamanger;


    public static String getNumberOfPhone(Context mainActivity) {
        if(telemamanger== null){
            initTeleManager(mainActivity);
        }
        String getSimSerialNumber= null;
        if(mainActivity!=null) {
            getSimSerialNumber = telemamanger.getSimSerialNumber();
        }
        return getSimSerialNumber;
    }
    public static GsmCellLocation getLACCid(Context mainActivity) {
        GsmCellLocation location = null;
        if(telemamanger== null){
            initTeleManager(mainActivity);
        }
        String info = null ;
        telemamanger = (TelephonyManager) mainActivity .getSystemService(Context.TELEPHONY_SERVICE);
        if (telemamanger.getPhoneType() == TelephonyManager.PHONE_TYPE_GSM) {
            location = (GsmCellLocation) telemamanger.getCellLocation();

        }


        String networkOperator = telemamanger.getNetworkOperator();

        if (TextUtils.isEmpty(networkOperator) == false) {
            int mcc = Integer.parseInt(networkOperator.substring(0, 3));
            int mnc = Integer.parseInt(networkOperator.substring(3));

            Log.e("test",mcc+" "+mnc);
        }
        return location ;
    }

    private static void initTeleManager(Context mainActivity) {
        telemamanger = (TelephonyManager) mainActivity.getSystemService(Context.TELEPHONY_SERVICE);
    }


}
