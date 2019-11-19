package com.activewebsite.booj;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import static com.activewebsite.booj.BrandingReceiver.PREFS;
import static com.activewebsite.booj.BrandingReceiver.REFERRER;

public class MainActivity extends AppCompatActivity {
    public static final String PACKAGE = "com.activewebsite.";
    public static final String HTTPS = "https://";
    public static final String WWW = "www.";
    public static final String COM = ".com";
    public static final String PROPERTY = "/property/";

    public EditText etQueryPackage, etQueryId, etQueryReferrer;
    public TextView tvTitle;

    private IntentHelper mIntents = new IntentHelper();
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(PREFS, Context.MODE_PRIVATE);

        etQueryId = findViewById(R.id.query_id);
        etQueryPackage = findViewById(R.id.query_package);
        etQueryReferrer = findViewById(R.id.query_referrer);
        tvTitle = findViewById(R.id.title);
        if (sharedPreferences.contains(REFERRER)) {
            tvTitle.setText("Hello from " + sharedPreferences.getString(REFERRER, ""));
        }
    }

    public void hideButtonsByClient(String client){
        findViewById(R.id.layout_clark).setVisibility(client.equals(getString(R.string.text_clark))?View.VISIBLE : View.GONE);
//        findViewById(R.id.button_cindy).setVisibility(client.equals(getString(R.string.text_clark))?View.VISIBLE : View.GONE);
//        findViewById(R.id.button_sunny).setVisibility(client.equals(getString(R.string.text_clark))?View.VISIBLE : View.GONE);
//        findViewById(R.id.button_veronica).setVisibility(client.equals(getString(R.string.text_clark))?View.VISIBLE : View.GONE);

        findViewById(R.id.button_w_prop1).setVisibility(client.equals(getString(R.string.text_wkre))?View.VISIBLE : View.GONE);
        findViewById(R.id.button_w_prop2).setVisibility(client.equals(getString(R.string.text_wkre))?View.VISIBLE : View.GONE);

        findViewById(R.id.layout_wkre).setVisibility(client.equals(getString(R.string.text_wkre))?View.VISIBLE : View.GONE);
//        findViewById(R.id.button_rachel).setVisibility(client.equals(getString(R.string.text_wkre))?View.VISIBLE : View.GONE);
//        findViewById(R.id.button_chuck).setVisibility(client.equals(getString(R.string.text_wkre))?View.VISIBLE : View.GONE);

        findViewById(R.id.button_np_prop1).setVisibility(client.equals(getString(R.string.text_np))?View.VISIBLE : View.GONE);
        findViewById(R.id.button_np_prop2).setVisibility(client.equals(getString(R.string.text_np))?View.VISIBLE : View.GONE);

        findViewById(R.id.layout_npdodge).setVisibility(client.equals(getString(R.string.text_np))?View.VISIBLE : View.GONE);
//        findViewById(R.id.button_benjy).setVisibility(client.equals(getString(R.string.text_np))?View.VISIBLE : View.GONE);
//        findViewById(R.id.button_richard).setVisibility(client.equals(getString(R.string.text_np))?View.VISIBLE : View.GONE);
//        findViewById(R.id.button_mike).setVisibility(client.equals(getString(R.string.text_np))?View.VISIBLE : View.GONE);
//        findViewById(R.id.button_stephenbeach).setVisibility(client.equals(getString(R.string.text_np))?View.VISIBLE : View.GONE);
//        findViewById(R.id.button_zach).setVisibility(client.equals(getString(R.string.text_np))?View.VISIBLE : View.GONE);

        findViewById(R.id.button_mur_prop1).setVisibility(client.equals(getString(R.string.text_murney))?View.VISIBLE : View.GONE);
        findViewById(R.id.button_mur_prop2).setVisibility(client.equals(getString(R.string.text_murney))?View.VISIBLE : View.GONE);

    }

    public void onClick(View view) {
        if (view.getId() == R.id.button_boojtest
                || view.getId() == R.id.button_clark
                || view.getId() == R.id.button_murney
                || view.getId() == R.id.button_np
                || view.getId() == R.id.button_wkre
                || view.getId() == R.id.button_ebby
                ) {
//            if (name.contentEquals("lyon")) {
//                link = "www.golyon.com";
//            }
            String tag = (String)view.getTag();
            if (etQueryPackage.getText().toString().equals(tag)) {
                tag = "";
            }
            etQueryPackage.setText(tag);
            hideButtonsByClient(tag);

        } else if (view.getId() == R.id.button_go) {
            String name = etQueryPackage.getText().toString();
            String brand = etQueryReferrer.getText().toString();
            mIntents.startNewActivity(this, PACKAGE + name, WWW + name + COM, brand);
        } else if (view.getId() == R.id.button_share || view.getId() == R.id.button_share_brand) {
            String name = etQueryPackage.getText().toString();
            String id = etQueryId.getText().toString();
            String brand = etQueryReferrer.getText().toString() + ".";
            mIntents.launchProperty(this, PACKAGE + name, HTTPS + (view.getId() == R.id.button_share ? WWW : brand) + name + COM + PROPERTY + id, id);
        } else if (view.getId() == R.id.button_w_prop1
                || view.getId() == R.id.button_w_prop2
                || view.getId() == R.id.button_np_prop1
                || view.getId() == R.id.button_np_prop2
                || view.getId() == R.id.button_mur_prop1
                || view.getId() == R.id.button_mur_prop2
                ) {
            etQueryId.setText((String)view.getTag());
        } else {
            etQueryReferrer.setText((String)view.getTag());
        }
    }

}
