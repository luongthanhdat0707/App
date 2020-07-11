package com.example.app.Activity.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.app.Activity.Activity.MainActivity;
import com.example.app.Activity.Activity.Themgiohang;
import com.example.app.Activity.Model.Giohang;
import com.example.app.R;
import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class GiohangAdapter extends BaseAdapter {
    Context context;
    ArrayList<Giohang>arraygiohang;

    public GiohangAdapter(Context context, ArrayList<Giohang> arraygiohang) {
        this.context = context;
        this.arraygiohang = arraygiohang;
    }

    @Override
    public int getCount() {
        return arraygiohang.size();
    }

    @Override
    public Object getItem(int i) {
        return arraygiohang.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        public TextView txttengiohang,txtgiagiohang;
        public ImageView imggiohang;
        Button btnmin,btngiatri,btnmax;
    }
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_giohang,null);
            viewHolder.txttengiohang = view.findViewById(R.id.textviewgiohang);
            viewHolder.txtgiagiohang = view.findViewById(R.id.textviewgiagiohang);
            viewHolder.imggiohang = view.findViewById(R.id.imagegiohang);
            viewHolder.btnmax = view.findViewById(R.id.buttonmax);
            viewHolder.btngiatri = view.findViewById(R.id.buttongiatri);
            viewHolder.btnmin= view.findViewById(R.id.buttonmin);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        final Giohang giohang = (Giohang) getItem(i);
        viewHolder.txttengiohang.setText(giohang.getTensp());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiagiohang.setText(decimalFormat.format(giohang.getGiasp())+" Đ");
        Picasso.get().load(giohang.getHinhanhsp())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.cancel)
                .into(viewHolder.imggiohang);
        viewHolder.btngiatri.setText(giohang.getSoluongsp()+"");
        final int sl = Integer.parseInt(viewHolder.btngiatri.getText().toString());
        if (sl >=10){
            viewHolder.btnmax.setVisibility(View.INVISIBLE);
            viewHolder.btnmin.setVisibility(View.VISIBLE);
        }else if(sl<=1){
            viewHolder.btnmin.setVisibility(View.INVISIBLE);
        }else if(sl>=1){
            viewHolder.btnmin.setVisibility(View.VISIBLE);
            viewHolder.btnmax.setVisibility(View.VISIBLE);
        }

        final ViewHolder finalViewHolder = viewHolder;
        final ViewHolder finalViewHolder1 = viewHolder;
        viewHolder.btnmax.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(finalViewHolder.btngiatri.getText().toString()) + 1;
                int slhientai = MainActivity.manggiohang.get(i).getSoluongsp();
                long giaht = MainActivity.manggiohang.get(i).getGiasp();
                MainActivity.manggiohang.get(i).setSoluongsp(slmoinhat);
                long giatrimoinhat=(giaht * slmoinhat) / slhientai;
                MainActivity.manggiohang.get(i).setGiasp(giatrimoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txtgiagiohang.setText(decimalFormat.format(giatrimoinhat) + " Đ");
                Themgiohang.EvenUltil();
                if (slmoinhat > 9){
                    finalViewHolder1.btnmax.setVisibility(View.INVISIBLE);
                    finalViewHolder1.btnmin.setVisibility(View.VISIBLE);
                    finalViewHolder1.btngiatri.setText(String.valueOf(slmoinhat));
                }else{
                    finalViewHolder1.btnmin.setVisibility(View.VISIBLE);
                    finalViewHolder1.btnmax.setVisibility(View.VISIBLE);
                    finalViewHolder1.btngiatri.setText(String.valueOf(slmoinhat));
                }
            }
        });
        viewHolder.btnmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int slmoinhat = Integer.parseInt(finalViewHolder.btngiatri.getText().toString()) - 1;
                int slhientai = MainActivity.manggiohang.get(i).getSoluongsp();
                long giaht = MainActivity.manggiohang.get(i).getGiasp();
                MainActivity.manggiohang.get(i).setSoluongsp(slmoinhat);
                long giatrimoinhat=(giaht * slmoinhat) / slhientai;
                MainActivity.manggiohang.get(i).setGiasp(giatrimoinhat);
                DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                finalViewHolder.txtgiagiohang.setText(decimalFormat.format(giatrimoinhat) + " Đ");
                if (slmoinhat <2){
                    finalViewHolder1.btnmin.setVisibility(View.INVISIBLE);
                    finalViewHolder1.btnmax.setVisibility(View.VISIBLE);
                    finalViewHolder1.btngiatri.setText(String.valueOf(slmoinhat));
                }else{
                    finalViewHolder1.btnmin.setVisibility(View.VISIBLE);
                    finalViewHolder1.btnmax.setVisibility(View.VISIBLE);
                    finalViewHolder1.btngiatri.setText(String.valueOf(slmoinhat));
                }
            }
        });
        return view;
    }
}
