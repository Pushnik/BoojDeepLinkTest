package com.activewebsite.booj;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

public class IntentHelper {

    private static final String TAG = "IntentHelper";
    public static final String PLAY_STORE_DETAILS = "https://play.google.com/store/apps/details?";
    public static final String MARKET_DETAILS = "market://details?";
    public static final String QUERY_ID = "id=";
    public static final String URL_START = PLAY_STORE_DETAILS + QUERY_ID;
    public static final String QUERY_URL = "&url=link%3A%2F%2F";
    public static final String QUERY_ACTION = "%2Faction%3Fkey%3Dvalue%23data&referrer=";
//    public static final String QUERY_REFERRER = "referrer=utm_source=";
    public static final String QUERY_REFERRER_SOURCE = "referrer=utm_source=";
    public static final String QUERY_MEDIUM = "&utm_medium=mobile";
    public static final String QUERY_CAMPAIGN = "&utm_campaign=mobile_download";
    public static final String QUERY_MEDIUM_CAMPAIGN = QUERY_MEDIUM + QUERY_CAMPAIGN;
    public static final String QUERY_AGENT_ID = "agentId=";

    public void startNewActivity(Context context, String packageName, String link, String referrer) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        String data;
        if (intent == null) {
            // Bring user to the market or let them choose an app?
            intent = new Intent(Intent.ACTION_VIEW);
// Play store URL https://play.google.com/store/apps/details?id=com.activewebsite.npdodge&referrer=utm_source=richardbilek&utm_medium=mobile&utm_campaign=mobile_download
            data = URL_START + packageName + ((referrer == null || referrer.isEmpty()) ? "" : ("&" + QUERY_REFERRER_SOURCE + referrer)) + QUERY_MEDIUM_CAMPAIGN;
// Play Store URL  https://play.google.com/store/apps/details?id=com.activewebsite.npdodge&url=link%3A%2F%2Fwww.npdodge.com%2Faction%3Fkey%3Dvalue%23data&referrer=benjygaudreau
//            data = URL_START + packageName + ((referrer == null || referrer.isEmpty()) ? "" : (QUERY_URL + link + QUERY_ACTION + referrer));
        } else if (referrer == null || referrer.isEmpty()) {
            data = link;
            intent.putExtra("company_property_id", "");
        } else {
// market URL market://details?referrer=utm_source=richardbilek&utm_medium=mobile&utm_campaign=mobile_download
            data = MARKET_DETAILS + QUERY_REFERRER_SOURCE + referrer + QUERY_MEDIUM_CAMPAIGN;
        }
        Log.e(TAG, "packageName = " + packageName + ", data = " + data);
        intent.setData(Uri.parse(data));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    public void launchProperty(Context context, String packageName, String link, String id) {
        Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
        if (intent == null) {
            // Bring user to the market or let them choose an app?
            intent = new Intent(Intent.ACTION_VIEW);
        }
        Log.e(TAG, "packageName = " + packageName + ", data = " + link);
        intent.setData(Uri.parse(link));
        if (packageName.contains("remax")) {
            intent.putExtra("company_property_id", id);
        } else {
            intent.putExtra("company_property_id", Integer.parseInt(id));
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

}
