package com.dohr.complaint.cell.javaClasses.AdapterClasses;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.dohr.complaint.cell.R;
import com.dohr.complaint.cell.modelClasses.AllCategory;
import com.dohr.complaint.cell.modelClasses.ComplaintModel;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by Shahzaib on 26-Jul-17.
 */

public class AllCatAdapter extends ArrayAdapter<ComplaintModel.AllCategory> implements Filterable {

    public Context context;
    private List<ComplaintModel.AllCategory> mOriginalValues; // Original Values
    private List<ComplaintModel.AllCategory> mDisplayedValues;    // Values to be displayed
    public AllCatAdapter(Context context, List<ComplaintModel.AllCategory> mOriginalValues ) {
        super(context, R.layout.listrow);
        this.context = context;
        this.mOriginalValues = mOriginalValues;
        this.mDisplayedValues = mOriginalValues;
    }

    public class MakeDataHolder
    {
        TextView name;


    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        MakeDataHolder holder ;
        if(convertView==null)
        {
            holder = new MakeDataHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.listrow, parent, false);
            holder.name = convertView.findViewById(R.id.adaptertext);
            convertView.setTag(holder);
        }
        else
        {
            holder=(MakeDataHolder) convertView.getTag();
        }

        holder.name.setText(mDisplayedValues.get(position).getCatName());
        Log.e("data check", "getView: "+mDisplayedValues.get(position).getCatName());
        return convertView;

    }

    @Override
    public int getCount() {
        Log.e( "listsizetesting",""+mDisplayedValues.size() );
        return mDisplayedValues.size();
    }



    @Override
    public long getItemId(int position) {
        return position;
    }



    @Override
    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint,FilterResults results) {

                mDisplayedValues = (ArrayList<ComplaintModel.AllCategory>) results.values; // has the filtered values
                notifyDataSetChanged();  // notifies the data with new filtered values
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();        // Holds the results of a filtering operation in values
                List<ComplaintModel.AllCategory> FilteredArrList = new ArrayList<ComplaintModel.AllCategory>();

                if (mOriginalValues == null) {
                    mOriginalValues = new ArrayList<>(mDisplayedValues); // saves the original data in mOriginalValues
                }

                /********
                 *
                 *  If constraint(CharSequence that is received) is null returns the mOriginalValues(Original) values
                 *  else does the Filtering and returns FilteredArrList(Filtered)
                 *
                 ********/
                if (constraint == null || constraint.length() == 0) {

                    // set the Original result to return
                    results.count = mOriginalValues.size();
                    results.values = mOriginalValues;
                } else {
                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < mOriginalValues.size(); i++) {
                        String data = mOriginalValues.get(i).getCatName();
                        if (data.toLowerCase().startsWith(constraint.toString())) {
                            FilteredArrList.add(new ComplaintModel.AllCategory(mOriginalValues.get(i).getId() ,mOriginalValues.get(i).getCatName()));
                        }
                    }
                    // set the Filtered result to return
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }

    public List<ComplaintModel.AllCategory> getList(){
        return mDisplayedValues;
    }

}

