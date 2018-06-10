package com.example.bestf.marioclient;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

public class C_AccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_access);

        final ImageView equipmentTitle = (ImageView) findViewById(R.id.equipmentTitle);
        final ImageButton enterEquipmentStateBtn = (ImageButton) findViewById(R.id.enterEquipmentStateBtn);
        final TextView enterEquipmentStateBtnTitle = (TextView) findViewById(R.id.enterEquipmentStateBtnTitle);
        final ImageView bgLogo = (ImageView) findViewById(R.id.bgLogo);
        final TextView copyRight = (TextView) findViewById(R.id.copyRight);

        enterEquipmentStateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent loginIntent = new Intent(C_AccessActivity.this, C_LoginActivity.class);
                C_AccessActivity.this.startActivity(loginIntent);
            }
        });
    }
}
