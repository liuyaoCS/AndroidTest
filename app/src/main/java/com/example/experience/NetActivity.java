package com.example.experience;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.androidtest.R;
import com.facebook.ProfileTracker;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Scanner;

public class NetActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_net);
        new Thread(){
            @Override
            public void run() {
                String username="liuyao@chinaso.com";
                String password="Ly985910";
                String agent="Mozilla/5.0 (compatible; MSIE 9.0; Windows NT 6.1; Trident/5.0";
                HttpClient client=new DefaultHttpClient();
                HttpGet get=new HttpGet("http://my.chinaso.com/login/index.htm?username="+username+"&password="+password);
                get.setHeader("Connection", "keep-alive");
                get.setHeader("User-Agent",agent);
                try {
                    HttpResponse response=client.execute(get);
                    if(response.getStatusLine().getStatusCode()== HttpStatus.SC_OK){
                        InputStream  is=response.getEntity().getContent();
                        Scanner scanner=new Scanner(is,"utf-8");

//                        File dir= Environment.getExternalStorageDirectory();
//                        File file=new File(dir,"test.txt");
//                        PrintWriter printWriter=new PrintWriter(new FileWriter(file),true);
                        String file=Environment.getExternalStorageDirectory().getPath()+File.separator+"data.txt";
                        PrintWriter pr=new PrintWriter(file,"utf-8");

                        while(scanner.hasNextLine()){
                            String str=scanner.nextLine();
                            pr.print(str);
                            Log.i("ly",str);
                        }
                        pr.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

}
