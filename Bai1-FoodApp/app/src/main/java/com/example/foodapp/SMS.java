package com.example.foodapp;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class SMS extends AppCompatActivity {
    private static final Intent SENT_ACTION = null;
    private static final Intent DELIVERY_ACTION = null;
    TextView txtSelectedFood;
    EditText edtDiaChi;
    Button btnSendSMS;
    String phoneNumber = "0865477513";
    //0778605436
    private static final int SEND_SMS_PERMISSION_REQUEST_CODE = 1;
    //ArrayList<String> orderFood= new ArrayList<String>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_sms);


        init();

        String orderFoods="";
        for(String food : CustomListItemAdapter.selectedFoods)
        {
            orderFoods += food;
            orderFoods +="\n";
        }
        txtSelectedFood.setText(orderFoods);
        String finalOrderFoods = orderFoods;
        btnSendSMS.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                  sendSms(v, "CÁC MÓN ĂN ĐÃ CHỌN\n" + finalOrderFoods + "ĐỊA CHỈ GIAO HÀNG:\n" + edtDiaChi.getText(), phoneNumber);
            }
        });
    }


    public void init()
    {
        txtSelectedFood = findViewById(R.id.txtSelectedFood);
        edtDiaChi = findViewById(R.id.edtDiaChi);
        btnSendSMS = findViewById(R.id.btnSendSMS);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void sendSms(View v, String content, String phoneNumber)
    {

        if(isSimSupport(getApplicationContext()))
            if (checkPermission(Manifest.permission.SEND_SMS)) {
                try
                {
                    String SENT      = "SMS_SENT";
                    PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0);

                    registerReceiver(new BroadcastReceiver()
                    {
                        @Override
                        public void onReceive(Context arg0, Intent arg1)
                        {
                            int resultCode = getResultCode();
                            if(resultCode == Activity.RESULT_OK)
                                Toast.makeText(getBaseContext(), "Gửi thành công.",Toast.LENGTH_LONG).show();
                            else if(resultCode == SmsManager.RESULT_ERROR_RADIO_OFF)
                                Toast.makeText(getBaseContext(), "Bạn có thể đang mở chế độ máy bay.",Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(getBaseContext(), "Gửi thất bại.",Toast.LENGTH_LONG).show();

                        }
                    }, new IntentFilter(SENT));

                    SmsManager smsMgr = SmsManager.getDefault();
                    smsMgr.sendTextMessage(phoneNumber, null, content, sentPI, null);

                }
                catch (Exception e)
                {
                      Toast.makeText(this, e.getMessage()+"!\n"+"Gửi thất bại.", Toast.LENGTH_LONG).show();
                      e.printStackTrace();
                }
            }
            else{
                ActivityCompat.requestPermissions(this,  new String[]{Manifest.permission.SEND_SMS}, SEND_SMS_PERMISSION_REQUEST_CODE);

                if (!shouldShowRequestPermissionRationale(Manifest.permission.SEND_SMS))
                    Toast.makeText(getApplicationContext(), "Chưa cấp quyền Tin nhắn SMS", Toast.LENGTH_LONG).show();
                 }
            else
            Toast.makeText(getApplicationContext(), "Thiết bị không có SIM", Toast.LENGTH_LONG).show();
    }

    private boolean checkPermission(String permission) {
        int check = ContextCompat.checkSelfPermission(this, permission);
        return  check == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isSimSupport(Context context)
    {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);  //gets the current TelephonyManager
        return !(tm.getSimState() == TelephonyManager.SIM_STATE_ABSENT);

    }
}
