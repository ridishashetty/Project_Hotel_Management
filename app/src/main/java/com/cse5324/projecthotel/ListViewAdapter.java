package com.cse5324.projecthotel;

import java.util.ArrayList;
import java.util.HashMap;


import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cse5324.projecthotel.R;

public class ListViewAdapter extends BaseAdapter{

    public ArrayList<HashMap<String, String>> list;
    Activity activity;
    public static final String FIRST_COLUMN="StartDate";
    public static final String SECOND_COLUMN="StartTime";
    public static final String THIRD_COLUMN="HotelName";
    public static final String FOURTH_COLUMN="NumberofRooms";
    public static final String FIFTH_COLUMN="CheckinDate";
    public static final String SIXTH_COLUMN="CheckoutDate";
    public static final String SEVENTH_COLUMN="RoomType";
    public static final String EIGHTH_COLUMN="TotalPrice";

    public ListViewAdapter(Activity activity,ArrayList<HashMap<String, String>> list){
        super();
        this.activity=activity;
        this.list=list;
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    private class ViewHolder{
        TextView txtFirst;
        TextView txtSecond;
        TextView txtThird;
        TextView txtFourth;
        TextView txtFifth;
        TextView txtSixth;
        TextView txtSeventh;
        TextView txtEigth;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub

        ViewHolder holder;

        LayoutInflater inflater=activity.getLayoutInflater();

        if(convertView == null){

            convertView=inflater.inflate(R.layout.display_reservation_layouts, null);
            holder=new ViewHolder();

            holder.txtFirst=(TextView) convertView.findViewById(R.id.StartDate);
            holder.txtSecond=(TextView) convertView.findViewById(R.id.StartTime);
            holder.txtThird=(TextView) convertView.findViewById(R.id.HotelName);
            holder.txtFourth=(TextView) convertView.findViewById(R.id.NumberofRooms);
            holder.txtFifth=(TextView) convertView.findViewById(R.id.CheckinDate);
            holder.txtSixth=(TextView) convertView.findViewById(R.id.CheckoutDate);
            holder.txtSeventh=(TextView) convertView.findViewById(R.id.Roomtype);
            holder.txtEigth=(TextView) convertView.findViewById(R.id.Price);

            convertView.setTag(holder);
        }else{

            holder=(ViewHolder) convertView.getTag();
        }

        HashMap<String, String> map=list.get(position);
        holder.txtFirst.setText(map.get(FIRST_COLUMN));
        holder.txtSecond.setText(map.get(SECOND_COLUMN));
        holder.txtThird.setText(map.get(THIRD_COLUMN));
        holder.txtFourth.setText(map.get(FOURTH_COLUMN));
        holder.txtFifth.setText(map.get(FIFTH_COLUMN));
        holder.txtSixth.setText(map.get(SIXTH_COLUMN));
        holder.txtSeventh.setText(map.get(SEVENTH_COLUMN));
        holder.txtEigth.setText(map.get(EIGHTH_COLUMN));

        return convertView;
    }

}