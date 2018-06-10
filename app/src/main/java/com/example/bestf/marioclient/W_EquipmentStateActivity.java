package com.example.bestf.marioclient;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.estimote.sdk.Beacon;
import com.estimote.sdk.BeaconManager;
import com.estimote.sdk.Region;
import com.estimote.sdk.SystemRequirementsChecker;

import org.json.JSONObject;

import java.util.List;
import java.util.UUID;

public class W_EquipmentStateActivity extends AppCompatActivity {
    private BeaconManager beaconManager;
    private Region region;

    /*
    int helmetCheck = 0x5678;
    int beltCheck = 0xfa01;
    int shoesCheck = 0x2345;
    */

    int helmetCheck = 0;
    int beltCheck = 0;
    int shoesCheck = 0;

    int helmetCount = 0;
    int beltCount = 0;
    int shoesCount = 0;

    String helmetStateCheck = "X";
    String beltStateCheck = "X";
    String shoesStateCheck = "X";
    String tempH = "X";
    String tempB = "X";
    String tempS = "X";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_equipment_state);
        Intent intent = getIntent();

        final String userID = intent.getStringExtra("userID");

        final TextView titleTextView = (TextView) findViewById(R.id.titleTextView);
        final ImageView equipment1 = (ImageView) findViewById(R.id.equipment1);
        final ImageView equipment2 = (ImageView) findViewById(R.id.equipment2);
        final ImageView equipment3 = (ImageView) findViewById(R.id.equipment3);

        /*
        helmetCheck = Integer.parseInt(intent.getStringExtra("helmetID"));
        beltCheck = Integer.parseInt(intent.getStringExtra("beltID"));
        shoesCheck = Integer.parseInt(intent.getStringExtra("shoesID"));
        */
        String helmet = intent.getStringExtra("helmetID");
        String belt = intent.getStringExtra("beltID");
        String shoes = intent.getStringExtra("shoesID");

        helmetCheck = Integer.parseInt(helmet);
        beltCheck = Integer.parseInt(belt);
        shoesCheck = Integer.parseInt(shoes);

        beaconManager = new BeaconManager(this);
        // add this below:
        beaconManager.setRangingListener(new BeaconManager.RangingListener() {
            @Override
            public void onBeaconsDiscovered(Region region, List<Beacon> list) {
                helmetCount++;
                beltCount++;
                shoesCount++;

                if(!list.isEmpty()){
                    for(Beacon beacon : list){
                        if(beacon.getMinor() == helmetCheck)
                            helmetCount = 0;
                        if(beacon.getMinor() == beltCheck)
                            beltCount = 0;
                        if(beacon.getMinor() == shoesCheck)
                            shoesCount = 0;
                    }
                    /*
                    Beacon nearestBeacon = list.get(0);
                    Log.d("Airport", "Nearest Places: " + nearestBeacon.getRssi());
                    helmet.setText(nearestBeacon.getMinor() + "");
                    */
                    tempH = helmetStateCheck;
                    tempB = beltStateCheck;
                    tempS = shoesStateCheck;

                    if(helmetCount <= 1){
                        equipment1.setBackgroundResource(R.drawable.possible);
                        helmetStateCheck = "O";
                    }else{
                        equipment1.setBackgroundResource(R.drawable.impossible);
                        helmetStateCheck = "X";
                    }
                    if(beltCount <= 1){
                        equipment2.setBackgroundResource(R.drawable.possible);
                        beltStateCheck = "O";
                    }else{
                        equipment2.setBackgroundResource(R.drawable.impossible);
                        beltStateCheck = "X";
                    }
                    if(shoesCount <= 1){
                        equipment3.setBackgroundResource(R.drawable.possible);
                        shoesStateCheck = "O";
                    }else{
                        equipment3.setBackgroundResource(R.drawable.impossible);
                        shoesStateCheck = "X";
                    }

                    if((!helmetStateCheck.equals(tempH))||(!beltStateCheck.equals(tempB))||(!shoesStateCheck.equals(tempS))){
                        Response.Listener<String> responseListener = new Response.Listener<String>(){
                            @Override
                            public void onResponse(String response){

                                try{
                                    JSONObject jsonResponse = new JSONObject(response);
                                    boolean success = jsonResponse.getBoolean("success");
                                    if(success){
                                        AlertDialog.Builder builder = new AlertDialog.Builder(W_EquipmentStateActivity.this);
                                        builder.setMessage("안전장비 착용상태 변화감지").create().show();
                                    }
                                }catch(Exception e){
                                    e.printStackTrace();
                                }

                            }
                        };
                        EquipStateRequest equipRequest = new EquipStateRequest(userID, helmetStateCheck, beltStateCheck, shoesStateCheck, responseListener);
                        //Volley 이용해서 인터넷으로 보냄.
                        RequestQueue queue = Volley.newRequestQueue(W_EquipmentStateActivity.this);
                        queue.add(equipRequest);
                    }

                }
            }
        });

        region = new Region("ranged region", UUID.fromString("74278BDA-B644-4520-8F0C-720EAF059935"), null, null);
        //ibeacon UUID
        // 본인이 연결할 Beacon의 ID와 Major / Minor Code를 알아야 한다.

    }

    @Override
    protected void onResume(){
        super.onResume();

        //블루투스 권한 및 활성화 코드
        SystemRequirementsChecker.checkWithDefaultDialogs(this);

        beaconManager.connect(new BeaconManager.ServiceReadyCallback() {
            @Override
            public void onServiceReady() {
                beaconManager.startRanging(region);
            }
        });
    }

    @Override
    protected void onPause(){
        beaconManager.stopRanging(region);
        super.onPause();
    }
}
