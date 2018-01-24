package com.techjini.progressbar;



import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;




public class MainActivity extends AppCompatActivity
{
    Button b1, b2;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);



        Button b1 =new Button(this);
        b1.setGravity(Gravity.CENTER);
        b1.setOnClickListener(new View.OnClickListener()

        {
            @Override
            public void onClick(View v)
            {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMessage("PLEASE WAIT UNTIL IT LOADS");
                progressDialog.setTitle("SPINNER TYPE PROGRESS DIALOG");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                progressDialog.show();
                progressDialog.setCancelable(false);



                new Thread(new Runnable() {
                    public void run() {
                        try {
                            Thread.sleep(10000);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        progressDialog.dismiss();
                    }
                }).start();
            }
        });






        b2.setOnClickListener(new View.OnClickListener()
        {
            @SuppressLint("HandlerLeak")
            Handler handle = new Handler() {
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    progressDialog.incrementProgressBy(4);
                }
            };





            @Override
            public void onClick(View v)
            {
                progressDialog = new ProgressDialog(MainActivity.this);
                progressDialog.setMax(100);
                progressDialog.setMessage("IT IS LOADING");
                progressDialog.setTitle("HORIZONTAL PROGRESS BAR");
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                progressDialog.show();
                progressDialog.setCancelable(false);

                new Thread(new Runnable() {
                    @Override
                    public void run()
                    {
                        try {
                            while (progressDialog.getProgress() <= progressDialog.getMax())
                            {
                                Thread.sleep(200);
                                handle.sendMessage(handle.obtainMessage());
                                if (progressDialog.getProgress() == progressDialog.getMax())
                                {
                                    progressDialog.dismiss();
                                }
                            }
                        }

                        catch (Exception e)
                        {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
}
