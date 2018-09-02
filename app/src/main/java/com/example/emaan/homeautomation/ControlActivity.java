package com.example.emaan.homeautomation;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.os.AsyncTask;
import java.io.IOException;
import java.util.UUID;

public class ControlActivity extends AppCompatActivity {

    int count=0;
    EditText password,mobile_number,new_password;
    String lock= null , cell_number = null , security = "0000";
    ImageButton on_port1, off_port1, on_port2, off_port2, on_port3, off_port3, on_port4, off_port4, on_port5, off_port5, on_port6, off_port6, on_port8, off_port8, Discnt, Abt;
    String address = null;
    private ProgressDialog progress;
    BluetoothAdapter myBluetooth = null;
    BluetoothSocket btSocket = null;
    private boolean isBtConnected = false;
    static final UUID myUUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent newint = getIntent();
        address = newint.getStringExtra(device_list.EXTRA_ADDRESS); //receive the address of the bluetooth device
        setContentView(R.layout.activity_control);
        on_port1 = (ImageButton)findViewById(R.id.on_port1);
        off_port1 = (ImageButton)findViewById(R.id.off_port1);
        on_port2 = (ImageButton)findViewById(R.id.on_port2);
        off_port2 = (ImageButton)findViewById(R.id.off_port2);
        on_port3 = (ImageButton)findViewById(R.id.on_port3);
        off_port3 = (ImageButton)findViewById(R.id.off_port3);
        on_port4 = (ImageButton)findViewById(R.id.on_port4);
        off_port4 = (ImageButton)findViewById(R.id.off_port4);
        on_port5 = (ImageButton)findViewById(R.id.on_port5);
        off_port5 = (ImageButton)findViewById(R.id.off_port5);
        on_port6 = (ImageButton)findViewById(R.id.on_port6);
        on_port8 = (ImageButton)findViewById(R.id.on_port8);
        off_port8 = (ImageButton)findViewById(R.id.off_port8);
        off_port6 = (ImageButton)findViewById(R.id.off_port6);
        Discnt = (ImageButton)findViewById(R.id.discnt);
        Abt = (ImageButton)findViewById(R.id.abt);
        password = (EditText)findViewById(R.id.password);
        mobile_number = (EditText)findViewById(R.id.mobile_number);
        new ConnectBT().execute(); //Call the class to connect

        //commands to be sent to bluetooth
        on_port1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                turnOnSwitch1();      //method to turn on
            }
        });

        off_port1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                turnOffSwitch1();   //method to turn off
            }
        });

        on_port2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                turnOnSwitch2();      //method to turn on
            }
        });

        off_port2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                turnOffSwitch2();   //method to turn off
            }
        });
        on_port3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                turnOnSwitch3();      //method to turn on
            }
        });

        off_port3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                turnOffSwitch3();   //method to turn off
            }
        });
        on_port4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                turnOnSwitch4();      //method to turn on
            }
        });

        off_port4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                turnOffSwitch4();   //method to turn off
            }
        });
        on_port5.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                turnOnSwitch5();      //method to turn on
            }
        });

        off_port5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                turnOffSwitch5();   //method to turn off
            }
        });
        on_port6.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                turnOnSwitch6();      //method to turn on
            }
        });

        off_port6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                turnOffSwitch6();   //method to turn off
            }
        });
        on_port8.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                openDoor();      //method to open door
            }
        });

        off_port8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                closeDoor();   //method to open door
            }
        });
        Discnt.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Disconnect(); //close connection
            }
        });
    }
    private void Disconnect()
    {
        if (btSocket!=null) //If the btSocket is busy
        {
            try
            {
                btSocket.close(); //close connection
            }
            catch (IOException e)
            { msg("Error");}
        }
        finish(); //return to the first layout

    }

    private void turnOffSwitch1()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("A".toString().getBytes());
                Toast.makeText(ControlActivity.this , "OFF" ,Toast.LENGTH_SHORT).show();
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }

    private void turnOnSwitch1()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("B".toString().getBytes());
                Toast.makeText(ControlActivity.this , "ON" ,Toast.LENGTH_SHORT).show();
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }

    private void turnOffSwitch2()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("C".toString().getBytes());
                Toast.makeText(ControlActivity.this , "OFF" ,Toast.LENGTH_SHORT).show();
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }

    private void turnOnSwitch2()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("D".toString().getBytes());
                Toast.makeText(ControlActivity.this , "ON" ,Toast.LENGTH_SHORT).show();
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }

    private void turnOffSwitch3()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("E".toString().getBytes());
                Toast.makeText(ControlActivity.this , "OFF" ,Toast.LENGTH_SHORT).show();
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }

    private void turnOnSwitch3()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("F".toString().getBytes());
                Toast.makeText(ControlActivity.this , "ON" ,Toast.LENGTH_SHORT).show();
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }

    private void turnOffSwitch4()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("G".toString().getBytes());
                Toast.makeText(ControlActivity.this , "OFF" ,Toast.LENGTH_SHORT).show();
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }

    private void turnOnSwitch4()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("H".toString().getBytes());
                Toast.makeText(ControlActivity.this , "ON" ,Toast.LENGTH_SHORT).show();
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }

    private void turnOffSwitch5()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("I".toString().getBytes());
                Toast.makeText(ControlActivity.this , "OFF" ,Toast.LENGTH_SHORT).show();
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }

    private void turnOnSwitch5()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("J".toString().getBytes());
                Toast.makeText(ControlActivity.this , "ON" ,Toast.LENGTH_SHORT).show();
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }

    private void turnOffSwitch6()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("K".toString().getBytes());
                Toast.makeText(ControlActivity.this , "OFF" ,Toast.LENGTH_SHORT).show();
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }

    private void turnOnSwitch6()
    {
        if (btSocket!=null)
        {
            try
            {
                btSocket.getOutputStream().write("L".toString().getBytes());
                Toast.makeText(ControlActivity.this , "ON" ,Toast.LENGTH_SHORT).show();
            }
            catch (IOException e)
            {
                msg("Error");
            }
        }
    }

    private void closeDoor() {
        lock = password.getText().toString();
        cell_number = mobile_number.getText().toString();
        if (lock.equals(security)) {
            password.setText(" ");
            if (btSocket != null) {
                try {
                    btSocket.getOutputStream().write("O".toString().getBytes());
                    Toast.makeText(ControlActivity.this, "DOOR CLOSE", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    msg("Error");
                }
            }
        }
        else {
            Toast.makeText(ControlActivity.this, "TRY AGAIN", Toast.LENGTH_SHORT).show();
            count += 1;
            if (count == 3) {
                if (mobile_number.length() == 0) {
                    Toast.makeText(ControlActivity.this, "ENTER MOBILE NUMBER", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(ControlActivity.this, "ALERT", Toast.LENGTH_SHORT).show();
                if (mobile_number.length() > 0) {

                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.SEND_SMS)) {
                        } else {
                            // permission is already granted
                            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);
                        }
                    } else {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(cell_number, null,"Please Alert Unthorize User Access your home", null, null);
                        Toast.makeText(getApplicationContext(), "SMS sent.",Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }


    private void openDoor()
    {
        lock = password.getText().toString();
        cell_number = mobile_number.getText().toString();
        if (lock.equals(security)) {
            password.setText(" ");
            if (btSocket != null) {
                try {
                    btSocket.getOutputStream().write("1".toString().getBytes());
                    Toast.makeText(ControlActivity.this, "DOOR OPEN", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    msg("Error");
                }
            }
        }
        else {
            Toast.makeText(ControlActivity.this, "TRY AGAIN", Toast.LENGTH_SHORT).show();
            count += 1;
            if (count == 3) {
                if (mobile_number.length() == 0) {
                    Toast.makeText(ControlActivity.this, "ENTER MOBILE NUMBER", Toast.LENGTH_SHORT).show();
                }
                Toast.makeText(ControlActivity.this, "ALERT", Toast.LENGTH_SHORT).show();
                if (mobile_number.length() > 0) {

                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)!= PackageManager.PERMISSION_GRANTED) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.SEND_SMS)) {
                        } else {
                            // permission is already granted
                            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.SEND_SMS},0);
                        }
                    } else {
                        SmsManager smsManager = SmsManager.getDefault();
                        smsManager.sendTextMessage(cell_number, null,"Please Alert Unthorize User Access your home", null, null);
                        Toast.makeText(getApplicationContext(), "SMS sent.",Toast.LENGTH_LONG).show();
                    }
                }
            }
        }
    }


    // fast way to call Toast
    private void msg(String s)
    {
        Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
    }

    public  void about(View v)
    {
        if(v.getId() == R.id.abt)
        {
            Intent i = new Intent(this, about.class);
            startActivity(i);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_led_control, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private class ConnectBT extends AsyncTask<Void, Void, Void>  // UI thread
    {
        private boolean ConnectSuccess = true; //if it's here, it's almost connected

        @Override
        protected void onPreExecute()
        {
            progress = ProgressDialog.show(ControlActivity.this, "Connecting...", "Please wait!!!");  //show a progress dialog
        }

        @Override
        protected Void doInBackground(Void... devices) //while the progress dialog is shown, the connection is done in background
        {
            try
            {
                if (btSocket == null || !isBtConnected)
                {
                    myBluetooth = BluetoothAdapter.getDefaultAdapter();//get the mobile bluetooth device
                    BluetoothDevice dispositivo = myBluetooth.getRemoteDevice(address);//connects to the device's address and checks if it's available
                    btSocket = dispositivo.createInsecureRfcommSocketToServiceRecord(myUUID);//create a RFCOMM (SPP) connection
                    BluetoothAdapter.getDefaultAdapter().cancelDiscovery();
                    btSocket.connect();//start connection
                }
            }
            catch (IOException e)
            {
                ConnectSuccess = false;//if the try failed, you can check the exception here
            }
            return null;
        }
        @Override
        protected void onPostExecute(Void result) //after the doInBackground, it checks if everything went fine
        {
            super.onPostExecute(result);

            if (!ConnectSuccess)
            {
                msg("Connection Failed. Is it a SPP Bluetooth? Try again.");
                finish();
            }
            else
            {
                msg("Connected.");
                isBtConnected = true;
            }
            progress.dismiss();
        }
    }
    }
