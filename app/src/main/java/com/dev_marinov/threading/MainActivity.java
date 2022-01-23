package com.dev_marinov.threading;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    ImageView img_spin;
    TextView tv_time;
    Button btSpin, btChangeColor, btCounter, btThreads;
    Boolean flagSpin = false;
    Boolean flagChangeColor = false;
    Boolean flagCounter = false;
    Boolean flagThreads = false;
    Spin spin;
    TextView tvSpinStatus, tvChangeColorStatus, tvCounterStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        img_spin = findViewById(R.id.img_spin);
        tv_time = findViewById(R.id.tv_time);

        btSpin = findViewById(R.id.btSpin);
        btChangeColor = findViewById(R.id.btChangeColor);
        btCounter = findViewById(R.id.btCounter);
        btThreads = findViewById(R.id.btThreads);

        tvSpinStatus = findViewById(R.id.tvSpinStatus);
        tvChangeColorStatus = findViewById(R.id.tvChangeColorStatus);
        tvCounterStatus = findViewById(R.id.tvCounterStatus);

        spin = new Spin(this);


        spin.setMyInterface(new Spin.myInterface() {
            @Override
            public void methodInterface(String text) {
                tv_time.setText(text);
            }
        });





        Log.e("333main_act","-flagThreads" + flagThreads);

        btSpin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                methodSpin();
            }
        });

        btChangeColor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                methodChangeColor();
            }
        });

        btCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                methodCounter();
            }
        });

        btThreads.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                methodThreads();
            }
        });

    }

    public void methodSpin()
    {
        if(flagSpin.equals(false))
        {
            Log.e("333main_act","-зашел flagSpin в IF-");
            btSpin.setText("close spin");
            img_spin.setVisibility(View.VISIBLE);
            flagSpin = true;
            spin.boolRun_1 = true;
            spin.run_1();
            tvSpinStatus.setText("- process...");
            Log.e("333main_act","-flagSpin в IF-" + flagSpin);

            checkClosedOrNot();
        }
        else
        {
            Log.e("333main_act","-зашел flagSpin в ELSE-");
            btSpin.setText("start spin");
            flagSpin = false;
            spin.boolRun_1 = false;
            tvSpinStatus.setText("- closed");

            img_spin.setVisibility(View.INVISIBLE);

            checkProcessOrNot();
            Log.e("333main_act","-flagSpin в ELSE-" + flagSpin);
        }
    }

    public void methodChangeColor()
    {
        if(flagChangeColor.equals(false))
        {
            Log.e("333main_act","-зашел flagChangeColor в IF-");
            btChangeColor.setText("close change color");
            flagChangeColor = true;
            spin.boolRun_2 = true;
            spin.run_2();
            tvChangeColorStatus.setText("- process...");
            Log.e("333main_act","-flagChangeColor в IF-" + flagChangeColor);

            checkClosedOrNot();
        }
        else
        {
            Log.e("333main_act","-зашел flagChangeColor в ELSE-");
            btChangeColor.setText("start change color");
            flagChangeColor = false;
            spin.boolRun_2 = false;
            tvChangeColorStatus.setText("- closed");
            img_spin.setImageDrawable(null);

            checkProcessOrNot();
            Log.e("333main_act","-flagChangeColor в ELSE-" + flagChangeColor);
        }
    }

    public void methodCounter()
    {
        if(flagCounter.equals(false)) // если false значит не запущен поток
        {
            Log.e("333main_act","-зашел flagCounter в IF-");
            btCounter.setText("close counter");
            flagCounter = true;
            spin.boolRun_3 = true;
            spin.run_3();
            tvCounterStatus.setText("- process...");
            Log.e("333main_act","-flagCounter в IF-" + flagCounter);

            checkClosedOrNot();
        }
        else
        {
            Log.e("333main_act","-зашел flagCounter в ELSE-");
            btCounter.setText("start counter");
            flagCounter = false;
            spin.boolRun_3 = false;
            tvCounterStatus.setText("- closed");
            tv_time.setText(""); // при остановке потока очищаем содержимое tv_time

            checkProcessOrNot();
            Log.e("333main_act","-flagCounter в ELSE-" + flagCounter);
        }
    }

    public void methodThreads()
    {
        if(flagThreads.equals(false)) // если все потоки закрыты
        {
            Log.e("333main_act","-зашел в IF-");
            btThreads.setText("close all threads"); // статус кнопки на будущее, что можно все потоки заркрыть

            flagThreads = true; // флаг что все потоки запущены
            flagSpin = true; // флаг true значит поток запущен
            flagChangeColor = true; // флаг true значит поток запущен
            flagCounter = true; // флаг true значит поток запущен

            spin.boolRun_1 = true; // передаем потоку true работать в вечном цикле
            spin.boolRun_2 = true; // передаем потоку true работать в вечном цикле
            spin.boolRun_3 = true; // передаем потоку true работать в вечном цикле
            spin.run_1(); // запустить поток
            spin.run_2(); // запустить поток
            spin.run_3(); // запустить поток
            img_spin.setVisibility(View.VISIBLE);

            Log.e("333main_act","-flagThreads в IF-" + flagThreads);

            btSpin.setText("close spin");  // статус кнопки на будущее, что можно поток заркрыть
            btChangeColor.setText("close change color");  // статус кнопки на будущее, что можно поток заркрыть
            btCounter.setText("close counter");  // статус кнопки на будущее, что можно поток заркрыть

            tvSpinStatus.setText("- process...");
            tvChangeColorStatus.setText("- process...");
            tvCounterStatus.setText("- process...");
        }
        else // иначе если flagThreads - true (все потоки работают)
        {
            Log.e("333main_act","-зашел в ELSE-");
            btThreads.setText("start all threads");
            flagThreads = false; // потоки закрыты
            flagSpin = false;
            flagChangeColor = false;
            flagCounter = false;
            img_spin.setVisibility(View.GONE);
            tv_time.setText("");

            btSpin.setText("start spin"); // статус кнопки на будущее, что можно поток запустить
            btChangeColor.setText("start change color"); // статус кнопки на будущее, что можно поток запустить
            btCounter.setText("start counter"); // статус кнопки на будущее, что можно поток запустить

            spin.boolRun_1 = false;  // передаем потоку false закрыться в вечном цикле
            spin.boolRun_2 = false;  // передаем потоку false закрыться в вечном цикле
            spin.boolRun_3 = false;  // передаем потоку false закрыться в вечном цикле

            tvSpinStatus.setText("- closed");
            tvChangeColorStatus.setText("- closed");
            tvCounterStatus.setText("- closed");
            Log.e("333main_act","-flagThreads в ELSE-" + flagThreads);
        }
    }

    public void checkProcessOrNot()
    {
        // если все потоки закрыты
        if(flagSpin.equals(false) && flagChangeColor.equals(false) && flagCounter.equals(false))
        {
            btThreads.setText("start all threads");
            flagThreads = false;



        }
    }

    public void checkClosedOrNot()
    {
        if(flagSpin.equals(true) && flagChangeColor.equals(true) && flagCounter.equals(true))
        {
            btThreads.setText("close all threads");
            flagThreads = true;
        }
    }

}