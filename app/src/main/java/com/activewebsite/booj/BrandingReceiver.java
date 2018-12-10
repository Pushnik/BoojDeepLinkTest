package com.activewebsite.booj;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;

/**
 * Created by osx on 5/10/16.
 */
public class BrandingReceiver extends BroadcastReceiver {

    private final String TAG = "BrandingReceiver";
    public static final String REFERRER = "referrer";
    public static final String PREFS = "BoojPrefs";

    private SharedPreferences sharedPreferences;

    //TODO Revisit branding

    @Override
    public void onReceive(Context context, Intent intent) {

        sharedPreferences = context.getSharedPreferences(PREFS, 0);

        String data = intent.toUri(Intent.URI_INTENT_SCHEME);
        StringBuilder sb = new StringBuilder();
        sb.append("Action: " + intent.getAction() + "\n");
        sb.append("URI: " + data + "\n");
        sb.append("Referrer: " + intent.getExtras().getString("referrer") + "\n");
        sb.append("intent.getExtras: " + intent.getExtras().toString() + "\n");
        String log = sb.toString();
        Log.e(TAG, log);

//        String rawReferrerString = intent.getStringExtra("referrer");
        String rawReferrerString = intent.getExtras().getString("referrer")
                .replace("utm_source", "")
                .replace("=", "")
                .replace("%3D", "");
        if(rawReferrerString != null) {
            Log.e(TAG, "referrer = " + rawReferrerString);
            context.getSharedPreferences(PREFS, Context.MODE_PRIVATE)
                    .edit().putString(REFERRER, rawReferrerString)
                    .commit();
        }

//        if(context.getSharedPreferences(Constants.SP_MASTER_KEY,Context.MODE_PRIVATE)
//                .getBoolean("processed_referrer",false)) {
//            return;
//            //no need to evaluate, we've already had our chance to process rebrand code from play store
//        }
//
//        new CampaignTrackingReceiver().onReceive(context, intent);

//        if(intent.hasExtra("referrer")) {
//
//            String refString = intent.getStringExtra("referrer").replace("referrer=","");
//            refString = "http://www.booj.com/branding?" + refString;
//            Log.e(TAG, "Branding refString:" + refString);
//
//            try {
//                refString = URLDecoder.decode(refString, "UTF-8");
//            } catch (Exception e) {
//                Log.e(TAG, "Branding decoding error:" + e.getMessage());
//            }
//
//            Uri brandingUri = Uri.parse(refString);
//            Set<String> params = brandingUri.getQueryParameterNames();
//
//            if(params.contains("utm_source")) {
//
//                String potentialRebrand = brandingUri.getQueryParameter("utm_source");
//                if(!TextUtils.isEmpty(potentialRebrand) && !potentialRebrand.toLowerCase().contains("google")) {
//                    Log.e(TAG, "!TextUtils.isEmpty(potentialRebrand) && !potentialRebrand.toLowerCase().contains(\"google\")");
//
////                    context.getSharedPreferences(Constants.SP_MASTER_KEY, Context.MODE_PRIVATE)
////                            .edit().putString("rebrand_code", potentialRebrand)
////                            .commit();
//                } else if(!TextUtils.isEmpty(potentialRebrand)) {
//                    Log.e(TAG, "!TextUtils.isEmpty(potentialRebrand) && potentialRebrand.toLowerCase().contains(\"google\")");
//
////                    context.getSharedPreferences(Constants.SP_MASTER_KEY, Context.MODE_PRIVATE)
////                            .edit().putString("rebrand_code", potentialRebrand)
////                            .commit();
//                } else {
//                    Log.e(TAG, "Referrer already processed or discarded for being likely incorrect");
//                }
//            }
//
//        } else {
//            Log.e(TAG, "Received no ref string");
//        }

    }
}
