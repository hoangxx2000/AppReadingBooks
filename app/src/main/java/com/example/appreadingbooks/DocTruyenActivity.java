package com.example.appreadingbooks;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.appreadingbooks.api.ApiLayAnh;
import com.example.appreadingbooks.interfaces.LayAnhVe;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class DocTruyenActivity extends AppCompatActivity implements LayAnhVe {
ImageView imgAnh;
ArrayList<String> arrUrlAnh;
int soTrang,soTrangDangDoc;
TextView txvSoTrang;
String idChap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_truyen);
        init();
        anhXa();
        setUp();
        setClik();
        new ApiLayAnh(this,idChap).execute();
    }

    private void init() {

        Bundle b = getIntent().getBundleExtra("data");
        idChap= b.getString("idChap");
    }

    private void anhXa() {
        txvSoTrang =findViewById(R.id.txvSoTrang);
        imgAnh =findViewById(R.id.imgAnh);
    }

    private void setUp(){
        //docTheoTrang(0);
    }

    private void setClik() {

    }

    public void right(View view) {
        docTheoTrang(1);
    }

    public void left(View view) {
        docTheoTrang(-1);
    }

    private void docTheoTrang(int i){
        soTrangDangDoc= soTrangDangDoc+i;
        if(soTrangDangDoc==0){
            soTrangDangDoc=1;
        }
        if(soTrangDangDoc>soTrang){
            soTrangDangDoc = soTrang;
        }
        txvSoTrang.setText(soTrangDangDoc+" / "+soTrang);
        Glide.with(this).load(arrUrlAnh.get(soTrangDangDoc-1)).into(imgAnh);
    }

    @Override
    public void batDau() {

    }

    @Override
    public void ketThuc(String data) {
        try {
            arrUrlAnh = new ArrayList<>();
            JSONArray array = new JSONArray(data);
            for(int i=0;i<array.length();i++){
                arrUrlAnh.add(array.getString(i));
            }
            soTrangDangDoc = 1;
            soTrang=arrUrlAnh.size();
            docTheoTrang(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void biLoi() {

    }
}
