package org.opencv.rps;
//package com.example.gesturegame;
//
//import com.example.gesturegame.R;
//
//import android.app.Activity;
//import android.content.Intent;
//import android.os.Bundle;
//import android.support.v4.app.FragmentTabHost;
//import android.view.View;
//
///**
// * Created by Seshank on 9/30/2015.
// */
//public class WelcomeActivity extends Activity implements View.OnClickListener{
//    private FragmentTabHost mTabHost;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_welcome);
//
//        mTabHost = (FragmentTabHost)findViewById(android.R.id.tabhost);
//        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
//
//        mTabHost.addTab(mTabHost.newTabSpec("tab1").setIndicator("Tab1"),
//                tab1.class, null);
//        mTabHost.addTab(mTabHost.newTabSpec("tab2").setIndicator("Tab2"),
//                tab2.class, null);
//        mTabHost.addTab(mTabHost.newTabSpec("tab3").setIndicator("Tab3"),
//                tab3.class, null);
//        Intent intent = getIntent();
//        Bundle extras = intent.getExtras();
//        if(!extras.getBoolean("login")){
//
//            Intent intent1 = new Intent(this,MultiMode.class);
//            intent1.putExtra("signout", true);
//            intent1.putExtra("login",false);
//            startActivity(intent1);
//        }
//
//
//        String username = getIntent().getStringExtra("Username");
//
//        //TextView tv = (TextView)findViewById(R.id.TVusername);
//       // tv.setText(username);
//
//    }
//
//    public void onClick(View view) {
//        // ...
//        if (view.getId() == R.id.sign_out_button) {
//            onSignOutClicked();
//        }
//    }
//
//    private void onSignOutClicked() {
//        // Clear the default account so that GoogleApiClient will not automatically
//        // connect in the future.
//
//        Intent intent = new Intent(this,MultiMode.class);
//        intent.putExtra("signout", true);
//        intent.putExtra("login",false);
//        startActivity(intent);
//        //showSignedOutUI();
//    }
//}
