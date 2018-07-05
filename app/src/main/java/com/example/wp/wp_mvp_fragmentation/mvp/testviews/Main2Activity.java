package com.example.wp.wp_mvp_fragmentation.mvp.testviews;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.wp.wp_mvp_fragmentation.R;

import java.util.ArrayList;

public class Main2Activity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        PieView view = findViewById(R.id.pie);
        ArrayList<PieData> datas = new ArrayList<>();
        PieData pieData = new PieData("sloop", 60);
        PieData pieData2 = new PieData("sloop", 30);
        PieData pieData3 = new PieData("sloop", 40);
        PieData pieData4 = new PieData("sloop", 20);
        PieData pieData5 = new PieData("sloop", 20);
        datas.add(pieData);
        datas.add(pieData2);
        datas.add(pieData3);
        datas.add(pieData4);
        datas.add(pieData5);
        view.setData(datas);
//        arrayList.add(pieData4);
//        arrayList.add(pieData5);
    }
}
