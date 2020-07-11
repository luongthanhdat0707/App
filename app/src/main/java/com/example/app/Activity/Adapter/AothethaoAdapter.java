package com.example.app.Activity.Adapter;

import android.content.Context;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.app.Activity.Model.Sanpham;
import com.example.app.R;
import com.squareup.picasso.Picasso;


import java.text.DecimalFormat;
import java.util.ArrayList;

public class AothethaoAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arrayaothethao;

    public AothethaoAdapter(Context context, ArrayList<Sanpham> arrayaothethao) {
        this.context = context;
        this.arrayaothethao = arrayaothethao;
    }


    @Override
    public int getCount() {
        return arrayaothethao.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayaothethao.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i ;
    }
    public class ViewHolder{
        public TextView txttenaott,txtgiaaott,txtmotaaott;
        public ImageView imgaott;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view == null){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_aothethao,null);
            viewHolder.txttenaott =  view.findViewById(R.id.textviewaott);
            viewHolder.txtgiaaott =  view.findViewById(R.id.textviewgiaaott);
            viewHolder.txtmotaaott = view.findViewById(R.id.textviewmotaaott);
            viewHolder.imgaott= view.findViewById(R.id.imageviewaott);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Sanpham sanpham = (Sanpham) getItem(i);
        viewHolder.txttenaott.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiaaott.setText("Giá : " + decimalFormat.format(sanpham.getGiasanpham())+ " Đ");
        viewHolder.txtmotaaott.setMaxLines(3);
        viewHolder.txtmotaaott.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotaaott.setText(sanpham.getMotasanpham());
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.cancel)
                .into(viewHolder.imgaott);
        return view;
    }
}
