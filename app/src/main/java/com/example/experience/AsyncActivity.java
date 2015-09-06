package com.example.experience;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.androidtest.R;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class AsyncActivity extends Activity {
    View base,futuretask;
    ExecutorService executorService;
    long time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_async);

        time=System.currentTimeMillis();

        executorService= Executors.newFixedThreadPool(4);

        Task t1=new Task();
        Task t2=new Task();
        Task t3=new Task();

        MyTask mt=new MyTask("ly");
        FutureTask<String> t4=new FutureTask<String>(mt);

        Future f1=executorService.submit(t1);
        Future f2=executorService.submit(t2);
        Future f3=executorService.submit(t3);
        Future<String> f4= (Future<String>) executorService.submit(t4);

        String ret="";
        try {
            f1.get();
            f2.get();
            f3.get();
            f4.get();
            ret=t4.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        Log.i("ly","cost="+(System.currentTimeMillis()-time)+","+ret);


    }
    class Task implements Runnable{
        @Override
        public void run() {
            try {
               // synchronized(AsyncActivity.class) {
                    Thread.currentThread().sleep(1000);
                    Log.i("ly",Thread.currentThread().getName()+" sleep 1 second");
               // }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public class MyTask implements Callable<String> {
        String mStr;
        public MyTask(String str){
            mStr=str;
        }
        @Override
        public String call() throws Exception {

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return mStr;
        }
    }

}
