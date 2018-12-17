package com.dohr.complaint.cell.javaClasses.AdapterClasses;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.dohr.complaint.cell.R;
import com.dohr.complaint.cell.UtilsClasses.FileUtils;
import com.dohr.complaint.cell.interfaceClasses.ImageAdapterListener;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends BaseAdapter {
    private Context context;
    List<Uri> mImageList;
    RelativeLayout delete_image;
    TextView part;
    ImageAdapterListener mImageAdapterListener;
    public ImageAdapter(Context context, List<Uri> mImageList, ImageAdapterListener mImageAdapterListener) {
        this.context = context;
        this.mImageList = mImageList;
        this.mImageAdapterListener = mImageAdapterListener;
    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View gridView;
        if (convertView == null) {

            gridView = new View(context);

            // get layout from mobile.xml
            gridView = inflater.inflate(R.layout.image_item, null);

            // set image based on selected text
            ImageView imageView = (ImageView) gridView.findViewById(R.id.grid_item_image);
            delete_image = (RelativeLayout) gridView.findViewById(R.id.delete_image);
            delete_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mImageAdapterListener.classOnClick(view, position);
                }
            });
            if (mImageList.size()<0){

            }else {
                imageView.setImageURI(mImageList.get(position));
            }



        } else {
            gridView = (View) convertView;
        }

        return gridView;
    }

    @Override
    public int getCount() {
        return mImageList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addItems() {

        notifyDataSetChanged();
    }
}
