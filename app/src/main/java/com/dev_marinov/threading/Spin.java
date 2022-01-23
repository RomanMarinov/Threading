package com.dev_marinov.threading;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;

import java.util.Random;

class Spin {

    myInterface myInterface;


    Context context;
  //  ThreadLocalRandom random = new ThreadLocalRandom();
    boolean boolRun_1, boolRun_2, boolRun_3;
    Thread thread_1, thread_2, thread_3;

    int i;
    int z;
    Random r = new Random();

    public Spin(Context context) {
        this.context = context;

//        run_1(); // инициализация метода
//        run_2(); // инициализация метода
//        run_3(); // инициализация метода

    }

    // код для вращения
    public void run_1()
    {
        i = -1;

     new Thread(new Runnable() {
            @Override
            public void run() {
                while (boolRun_1)
                {
                    Log.e("333 run_1","-thread_1-");
                    i=i+50;

                    ((MainActivity)context).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                    ((MainActivity)context).img_spin.setRotation(i);
                    Log.e("тут","-тут-" + i);
                          //  ((MainActivity)context).img_spin.setImageDrawable(android.R.color.black);
                        }
                    });


                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                }
            }
     }).start();

    }

    // код по изменению цвета
    public void run_2() { // главный поток

        new Thread(new Runnable() { // не главный поток
            @Override
            public void run() {
                while (boolRun_2)
                {

                    Log.e("333 run_2","-thread_2-");

                    int c = Color.argb(255, r.nextInt(255), r.nextInt(255), r.nextInt(255));
                    ((MainActivity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ((MainActivity)context).img_spin.setBackgroundColor(c);
                    }
                });
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                }
            }
        }).start();


    }

    // код счетчика
    public void run_3() { // главный поток

        z = -1;

        new Thread(new Runnable() { // не главный поток
            @Override
            public void run() {
                while (boolRun_3)
                {
                    Log.e("333 run_3","-thread_3-");
                    z++;
                    if(z > 10)
                    {
                        z=0;
                    }
                ((MainActivity)context).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //((MainActivity)context).tv_time.setText(String.valueOf(z));

                        myInterface.methodInterface("" + z);
                    }
                });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


    interface myInterface
    {
        void methodInterface(String text);
    }

    public void setMyInterface(myInterface myInterface)
    {
        this.myInterface = myInterface;
    }



}
