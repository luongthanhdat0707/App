package com.example.app.Activity.Activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;

import android.widget.ListView;
import android.widget.TextView;

import com.example.app.Activity.Adapter.GiohangAdapter;
import com.example.app.Activity.Ultil.CheckConnection;
import com.example.app.R;

import java.text.DecimalFormat;

public class Themgiohang extends AppCompatActivity {

    ListView listViewgiohang;
    TextView txtthongbao;
    TextView txttongtien;
    static TextView txtgiatri;
    Button btnthanhtoan,btntieptuc;
    Toolbar toolbargiohang;
    GiohangAdapter giohangAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_themgiohang);
        Anhxa();
        ActionToolBar();
        CheckData();
        EvenUltil();
        CatchOnItemListView();
        EvenButton();
    }

    private void EvenButton() {
        btntieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        btnthanhtoan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.manggiohang.size()>0){
                    Intent intent = new Intent(getApplicationContext(),Thongtinkhachhang.class);
                    startActivity(intent);
                }else {
                    CheckConnection.ShowToast_Short(getApplicationContext(),"Giỏ hàng của bạn chưa có sản phẩm");
                }
            }
        });
    }

    private void CatchOnItemListView() {
        listViewgiohang.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, final int position, long l) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Themgiohang.this);
                builder.setTitle("Xác nhận xóa sản phẩm");
                builder.setMessage("Bạn muốn xóa sản phẩm này");
                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(MainActivity.manggiohang.size() <= 0){
                            txtthongbao.setVisibility(View.VISIBLE);
                        }else{
                            MainActivity.manggiohang.remove(position);
                            giohangAdapter.notifyDataSetChanged();
                            EvenUltil();
                            if (MainActivity.manggiohang.size()<=0){
                                txtthongbao.setVisibility(View.VISIBLE);
                            }else{
                                txtthongbao.setVisibility(View.INVISIBLE);
                                giohangAdapter.notifyDataSetChanged();
                                EvenUltil();
                            }
                        }
                    }
                });
                builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        giohangAdapter.notifyDataSetChanged();
                        EvenUltil();
                    }
                });
                builder.show();
                return true;
            }
        });
    }

    public static void EvenUltil() {
        long tongtien = 0;
        for (int i=0; i<MainActivity.manggiohang.size();i++){
            tongtien += MainActivity.manggiohang.get(i).getGiasp();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtgiatri.setText(decimalFormat.format(tongtien)+ " Đ");
    }

    private void CheckData() {
        if(MainActivity.manggiohang.size() <=0){
            txtthongbao.setVisibility(View.VISIBLE);
            giohangAdapter.notifyDataSetChanged();
            listViewgiohang.setVisibility(View.INVISIBLE);
        }else{
            txtthongbao.setVisibility(View.INVISIBLE);
            giohangAdapter.notifyDataSetChanged();
            listViewgiohang.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbargiohang);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbargiohang.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void Anhxa() {
        listViewgiohang = findViewById(R.id.listviewgiohang);
        txtthongbao =findViewById(R.id.textviewthongbao);
        txttongtien =findViewById(R.id.textviewtongtien);
        txtgiatri = findViewById(R.id.textviewgiatri);
        btnthanhtoan = findViewById(R.id.buttonthanhtoan);
        btntieptuc = findViewById(R.id.buttontieptuc);
        toolbargiohang = findViewById(R.id.toolbargiohang);
        giohangAdapter = new GiohangAdapter(Themgiohang.this,MainActivity.manggiohang);
        listViewgiohang.setAdapter(giohangAdapter);
    }
}
