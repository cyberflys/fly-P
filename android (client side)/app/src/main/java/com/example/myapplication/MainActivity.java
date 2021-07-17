package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity{
    ImageButton but;
    ImageButton off;
    ImageButton on2;
    ImageButton off2;

    TextView status1;




    FloatingActionButton fab;




    SharedPreferences prefs = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        but = findViewById(R.id.turnon);
        off = findViewById(R.id.turnoff);
        on2 = findViewById(R.id.turnon2);
        off2 = findViewById(R.id.turnoff2);

        String test = "et";



        status1 = findViewById(R.id.status1);










        off2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                off2.setVisibility(View.INVISIBLE);
                on2.setVisibility(View.VISIBLE);
                new AsyncTask<Integer, Void, Void>(){
                    @Override
                    protected Void doInBackground(Integer... params) {
                        try {
                            status1.setText("Plug 2: Off");
                            ssh("cd /home/pi/relay;./r3off");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }.execute(1);

            }
        });
        on2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("WORKING");
                off2.setVisibility(View.VISIBLE);
                on2.setVisibility(View.INVISIBLE);
                new AsyncTask<Integer, Void, Void>(){
                    @Override
                    protected Void doInBackground(Integer... params) {


                        try {
                            status1.setText("Plug 2: On");
                            ssh("cd /home/pi/relay;./r3on");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }.execute(1);
            }



        });


        off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                off.setVisibility(View.INVISIBLE);
                but.setVisibility(View.VISIBLE);
                new AsyncTask<Integer, Void, Void>(){
                    @Override
                    protected Void doInBackground(Integer... params) {


                        try {
                            status1.setText("Plug 1: Off");
                            ssh("cd /home/pi/relay;./r2off");


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }.execute(1);

            }
        });
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("WORKING");
                off.setVisibility(View.VISIBLE);
                but.setVisibility(View.INVISIBLE);
                new AsyncTask<Integer, Void, Void>(){
                    @Override
                    protected Void doInBackground(Integer... params) {
                        try {
                            status1.setText("Plug 1: On");
                            ssh("cd /home/pi/relay;./r2on");

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return null;
                    }
                }.execute(1);
            }



        });
    }

    public void ssh(String cmd1) throws JSchException, IOException {
        int port = 22;

        String host = "192.168.1.36";
        String password = "geohot123";
        java.util.Properties config = new java.util.Properties();
        config.put("StrictHostKeyChecking", "no");
        JSch jsch = new JSch();
        Session session = jsch.getSession("pi", host, 22);
        session.setPassword(password);
        session.setConfig(config);
        session.connect();
        System.out.println("Connected");
        Channel channel = session.openChannel("exec");
        ((ChannelExec) channel).setCommand(cmd1);
        channel.setInputStream(null);
        ((ChannelExec) channel).setErrStream(System.err);

        InputStream in = channel.getInputStream();
        channel.connect();

}

}

