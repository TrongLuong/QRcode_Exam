package com.example.qrcode_exam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.qrcode.QRCodeWriter;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    Button btnQR;
    TextView txtName, txtTuoi;
    ImageView imgQR;

    //ánh xạ
    public void anhxa(){
        btnQR = (Button)findViewById(R.id.btn_click);
        txtName = (TextView)findViewById(R.id.txt_name);
        txtTuoi = (TextView)findViewById(R.id.txt_tuoi);
        imgQR = (ImageView)findViewById(R.id.img_hinh);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhxa();


        final IntentIntegrator integrator = new IntentIntegrator(this);
        btnQR.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                integrator.initiateScan();


            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode,int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if(result != null){
            if(result.getContents() == null){
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
            }else

                try {
                    JSONObject jsonObject = new JSONObject(result.getContents());
                    Picasso.get().load(jsonObject.getString("url")).into(imgQR);
                    txtName.setText(jsonObject.getString("name" ));
                    txtTuoi.setText(jsonObject.getString(txtTuoi.getText().toString()+"tuoi"));


            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            super.onActivityResult(requestCode,resultCode,data);
        }

    }
}
