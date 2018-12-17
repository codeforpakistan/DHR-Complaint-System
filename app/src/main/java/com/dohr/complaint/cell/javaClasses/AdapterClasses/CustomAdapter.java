package com.dohr.complaint.cell.javaClasses.AdapterClasses;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.dohr.complaint.cell.R;
import com.dohr.complaint.cell.modelClasses.AllCategory;
import com.dohr.complaint.cell.modelClasses.ComplaintModel;
import com.dohr.complaint.cell.modelClasses.ModelList;

import java.util.ArrayList;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<AllCategory> {

    //ArrayList<String> id;
    //ArrayList<String> name ;
    List<ComplaintModel.AllCategory> list;
    Context context;

    public CustomAdapter(Context context,  List<ComplaintModel.AllCategory> list) {
        super(context, R.layout.listrow);

        //this.id = id;
        //this.name = name;
        this.list = list;
        this.context = context;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView=layoutInflater.inflate(R.layout.listrow,null,true);
        TextView engine_type_name = convertView.findViewById(R.id.adaptertext);

        engine_type_name.setText(list.get(position).getCatName());

        return convertView;
    }
}