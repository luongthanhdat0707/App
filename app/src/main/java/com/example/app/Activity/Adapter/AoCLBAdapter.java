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

public class AoCLBAdapter extends BaseAdapter {
    Context context;
    ArrayList<Sanpham> arrayaoCLB;

    public AoCLBAdapter(Context context, ArrayList<Sanpham> arrayaoCLB) {
        this.context = context;
        this.arrayaoCLB = arrayaoCLB;
    }

    @Override
    public int getCount() {
        return arrayaoCLB.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayaoCLB.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }
    public class ViewHolder{
        public TextView txttenao,txtgiaaoclb,txtmotaaoclb;
        public ImageView imgaoclb;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if(view ==null ){
            viewHolder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dong_aoclb,null);
            viewHolder.txttenao =  view.findViewById(R.id.textviewaoclb);
            viewHolder.txtgiaaoclb =  view.findViewById(R.id.textviewgiaaoclb);
            viewHolder.txtmotaaoclb = view.findViewById(R.id.textviewmotaaoclb);
            viewHolder.imgaoclb = view.findViewById(R.id.imageviewclb);
            view.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) view.getTag();
        }
        Sanpham sanpham = (Sanpham) getItem(i);
        viewHolder.txttenao.setText(sanpham.getTensanpham());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        viewHolder.txtgiaaoclb.setText("Giá : " + decimalFormat.format(sanpham.getGiasanpham())+ " Đ");
        viewHolder.txtmotaaoclb.setMaxLines(3);
        viewHolder.txtmotaaoclb.setEllipsize(TextUtils.TruncateAt.END);
        viewHolder.txtmotaaoclb.setText(sanpham.getMotasanpham());
        Picasso.get().load(sanpham.getHinhanhsanpham())
                .placeholder(R.drawable.no_image)
                .error(R.drawable.cancel)
                .into(viewHolder.imgaoclb);
        return view;
    }
}
