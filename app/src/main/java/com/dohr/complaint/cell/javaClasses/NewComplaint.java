package com.dohr.complaint.cell.javaClasses;

import android.app.Dialog;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.IntRange;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.SearchView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.SearchView;
import com.dohr.complaint.cell.R;
import com.dohr.complaint.cell.RoomSqliteDB.DhrDatabase;
import com.dohr.complaint.cell.javaClasses.AdapterClasses.AllCatAdapter;
import com.dohr.complaint.cell.javaClasses.AdapterClasses.CustomAdapter;
import com.dohr.complaint.cell.javaClasses.AdapterClasses.SubCatAdapter;
import com.dohr.complaint.cell.modelClasses.AllCategory;
import com.dohr.complaint.cell.modelClasses.ComplaintModel;
import com.dohr.complaint.cell.modelClasses.ModelList;
import com.dohr.complaint.cell.modelClasses.SubComplaintModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewComplaint extends AppCompatActivity {

    //CardView card_container3, card_container2, card_container;
    LinearLayout card_container, card_container2, card_container3;
    String catId,catName,subCatId,subCatName,Subject,Details;
    private static final String DATABASE_NAME = "demo_db";
    List<SubComplaintModel.SubCategory> mSubCategories=new ArrayList<>();
    List<ComplaintModel.AllCategory> mAllCategories=new ArrayList<>();;
    private static final String TAG = "Main";
    private DhrDatabase mDatabase;
    AllCatAdapter allCatAdapter;
    SubCatAdapter mSubCatAdapter;
    int count = 0;
    ImageView backbtn,back_bttn;
    ImageView nextbttn;
    private DhrDatabase dhrDatabase;
    //private static final String DATABASE_NAME = "dhr_db";
    EditText ComplaintType,subcomplaint,msubject,details;
  //  String [] complaint_type = {"Children Rights", "Minority Rights","Rights to life, Liberty, Dignity","Women Rights"};
    ArrayList<String> complaintIdArr = new ArrayList<>();
    ArrayList<String> complaintNameArr = new ArrayList<>();
    ArrayList<String> subComplaintIdArr = new ArrayList<>();
    ArrayList<String> subComplaintNameArr = new ArrayList<>();
    String ChildRights,subComplaints;
    String complaintId = "";
    String subcomplaintId = "";
    SharedPreferences editformSharePreferences;
    SharedPreferences.Editor editformEditor;
    Animation LeftSwipe,RightSwipe;
    String msubject_edtxt, detail_edttxt,ComplaintType_edttxt, subcomplaint_edttxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_complaint);
        setUI();
        loadAllCategories();

    }

    /*************************************************************************************************************/

    private void setUI() {
        mDatabase = Room.databaseBuilder(getApplicationContext(),
                DhrDatabase.class, DATABASE_NAME).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
        nextbttn = findViewById(R.id.nextbttn1);
        details = findViewById(R.id.details);
        backbtn = findViewById(R.id.backbtn);
        msubject = findViewById(R.id.subject);
        card_container = findViewById(R.id.card_container);
        card_container2 = findViewById(R.id.card_container2);
        ComplaintType = findViewById(R.id.ComplaintType);
        subcomplaint = findViewById(R.id.subcomplaint);
        card_container3 = findViewById(R.id.card_container3);
        back_bttn = findViewById(R.id.back_bttn);


        LeftSwipe = AnimationUtils.loadAnimation(this, R.anim.left_slide);
        RightSwipe = AnimationUtils.loadAnimation(this, R.anim.right_slide);

        List<ComplaintModel.AllCategory> cat_list =  mDatabase.daoAccess().fetchComplaintList();
        Log.e("datacheck", cat_list.toString());
        Log.e("datacheck",mDatabase.daoAccess().fetchComplaintList().toString());

        editformSharePreferences=getApplicationContext().getSharedPreferences("EditData",MODE_PRIVATE);
        String sh_complaintTypes =editformSharePreferences.getString("ComplaintType","no data");
        final String sh_msubject =editformSharePreferences.getString("msubject","no data");
        String sh_subcomplaint=editformSharePreferences.getString("subcomplaint","no data");
        final String  sh_details=editformSharePreferences.getString("details","no data");
        if(sh_msubject != "no data")
        {
            msubject.setText(sh_msubject);
            ComplaintType.setText(sh_complaintTypes);
            details.setText(sh_details);
            subcomplaint.setText(sh_subcomplaint);
            Log.e("share data", "shareprefrance "+sh_msubject+"  "+sh_complaintTypes+"  "+sh_details+" "+sh_subcomplaint);
            //editformEditor = editformSharePreferences.edit();
            //editformEditor.clear();
            //editformEditor.commit();
        }
        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
                //startActivity(new Intent(NewComplaint.this, Home.class));
            }
        });
        //setting dropbox on spiners here
        ComplaintType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // showComplaintType();
                showAllCategoriesDialog();
            }
        });
        subcomplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showSubCategoriesDialog();
            }
        });
        nextbttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ComplaintType_edttxt = ComplaintType.getText().toString();
                subcomplaint_edttxt = subcomplaint.getText().toString();
                msubject_edtxt = msubject.getText().toString();
                detail_edttxt = details.getText().toString();
                if (TextUtils.isEmpty(ComplaintType_edttxt)){
                    ComplaintType.setError("Enter Complaint Type");
                    ComplaintType.requestFocus();
                }
                else if (TextUtils.isEmpty(subcomplaint_edttxt))
                {
                    subcomplaint.setError("Error sub complaint type");
                    subcomplaint.requestFocus();
                }
                else if (TextUtils.isEmpty(msubject_edtxt)){
                    msubject.setError("Enter Subject");
                    msubject.requestFocus();
                }
                else if (TextUtils.isEmpty(detail_edttxt)){
                    details.setError(("Enter Details"));
                    details.requestFocus();
                }
                else{
                    Intent intent= new Intent(NewComplaint.this, ComplaintAgainst.class);
                    intent.putExtra("ComplaintType", ComplaintType_edttxt);
                    intent.putExtra("subcomplaint", subcomplaint_edttxt);
                    intent.putExtra("Subject", msubject_edtxt);
                    intent.putExtra("Details", detail_edttxt);
                    startActivity(intent);
                }
       }
        });

      /*  back_bttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (count == 2){

                    card_container3.setVisibility(View.GONE);
                    card_container2.setVisibility(View.VISIBLE);
                    card_container2.startAnimation(RightSwipe);
                    count--;
                    Log.e(TAG, "run:"+count );

                }else if(count == 1){
                    card_container2.setVisibility(View.GONE);
                    card_container.setVisibility(View.VISIBLE);
                    card_container.startAnimation(RightSwipe);
                    count--;
                    Log.e(TAG, "run:"+count );
                }
            }
        });*/
    }

    private void loadAllCategories() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    mAllCategories = mDatabase.daoAccess().fetchComplaintList();
                    Log.e(TAG, "list size: "+mAllCategories.size() );
                    Log.e(TAG, "all cat: "+mAllCategories.toString() );

                }catch (Exception e){
                    Log.e(TAG, "exp: "+e.toString() );
                }
            }
        });


    }
    private void showAllCategoriesDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                final Dialog allCategoriesDialog = new Dialog(NewComplaint.this, R.style.dialog_theme);
                allCategoriesDialog.setCancelable(true);
                allCategoriesDialog.setContentView(R.layout.spinner_dialog);
                TextView dialog_title = allCategoriesDialog.findViewById(R.id.dialog_title);
                //add dynamic title to dialog
                dialog_title.setText("Select Category Type");
                SearchView mSearchView = allCategoriesDialog.findViewById(R.id.search_search);
                mSearchView.setVisibility(View.VISIBLE);
                final ListView list = allCategoriesDialog.findViewById(R.id.seize_cat_list);
                allCatAdapter = new AllCatAdapter(NewComplaint.this, mAllCategories);
                Log.e(TAG, "run: "+mAllCategories.toString() );
                list.setAdapter(allCatAdapter);
                allCatAdapter.notifyDataSetChanged();
                list.setTextFilterEnabled(false);
                mSearchView.setIconifiedByDefault(false);
                mSearchView.setSubmitButtonEnabled(false);
                mSearchView.setQueryHint("Search...");
                allCategoriesDialog.show();

                /*************************************************************/
                //add text watcher on search edit text
                mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        if (TextUtils.isEmpty(newText)) {
                            //loadAllCategories();
                            allCatAdapter = new AllCatAdapter(NewComplaint.this, mAllCategories);
                            list.setAdapter(allCatAdapter);
                            allCatAdapter.notifyDataSetChanged();

                        } else {
                            Filter filter = allCatAdapter.getFilter();
                            filter.filter(newText);
                        }
                        return true;
                    }
                });
                //list view item click listener
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        allCategoriesDialog.dismiss();
                        List<ComplaintModel.AllCategory> arrayList = new ArrayList<>();
                        arrayList = allCatAdapter.getList();
                        ComplaintType.setText(""+arrayList.get(i).getCatName());
                        ComplaintType.setError(null);
                        catId = arrayList.get(i).getId();
                        catName = arrayList.get(i).getCatName();

                        if (!catId.equals("") || catId != null){
                            //create part and put in hasmap
                            loadSubCategories(catId);
                            //subcomplaint.setText("");
                            //mMap.put("vechicle_make", createPartFromString(catId));
                            Log.e("catId", catId);
                            Log.e("catName", catName);
                        } else {
                            Toast.makeText(NewComplaint.this, "Select complaint type", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
    private void showSubCategoriesDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                final Dialog subCategoriesDialog = new Dialog(NewComplaint.this, R.style.dialog_theme);
                subCategoriesDialog.setCancelable(true);
                subCategoriesDialog.setContentView(R.layout.spinner_dialog);
                TextView dialog_title = subCategoriesDialog.findViewById(R.id.dialog_title);
                //add dynamic title to dialog
                dialog_title.setText("Select Category Type");
                SearchView mSearchView = subCategoriesDialog.findViewById(R.id.search_search);
                mSearchView.setVisibility(View.VISIBLE);
                final ListView list = subCategoriesDialog.findViewById(R.id.seize_cat_list);
                mSubCatAdapter = new SubCatAdapter(NewComplaint.this, mSubCategories);
                list.setAdapter(mSubCatAdapter);
                mSubCatAdapter.notifyDataSetChanged();
                list.setTextFilterEnabled(false);
                mSearchView.setIconifiedByDefault(false);
                mSearchView.setSubmitButtonEnabled(false);
                mSearchView.setQueryHint("Search...");
                subCategoriesDialog.show();

                /*************************************************************/
                //add text watcher on search edit text
                mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                    @Override
                    public boolean onQueryTextSubmit(String query) {
                        return false;
                    }

                    @Override
                    public boolean onQueryTextChange(String newText) {
                        if (TextUtils.isEmpty(newText)) {
                            loadAllCategories();
                            mSubCatAdapter = new SubCatAdapter(NewComplaint.this, mSubCategories);
                            list.setAdapter(allCatAdapter);
                            mSubCatAdapter.notifyDataSetChanged();

                        } else {
                            Filter filter = allCatAdapter.getFilter();
                            filter.filter(newText);
                        }
                        return true;
                    }
                });

                /*************************************************************/

                //list view item click listener
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                        subCategoriesDialog.dismiss();
                        List<SubComplaintModel.SubCategory> arrayList = new ArrayList<>();
                        arrayList = mSubCatAdapter.getList();
                        subcomplaint.setText(""+arrayList.get(i).getCatName());
                        subcomplaint.setError(null);
                        subCatId = arrayList.get(i).getId();
                        subCatName = arrayList.get(i).getCatName();

                        if (!catId.equals("")){
                            //create part and put in hasmap
                            //mMap.put("vechicle_make", createPartFromString(subCatId));
                            Log.e("subCatId", subCatId);
                            Log.e("subCatName", subCatName);
                        } else {

                        }
                    }
                });
            }
        });

        /*************************************************************/
    }
    /**************************************************************************************************************/
    private void loadSubCategories(final String sub_cat_id) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    mSubCategories = mDatabase.daoAccess().fetchSubComplaintList(sub_cat_id);
                    Log.e(TAG, "list size: "+mSubCategories.size() );
                    Log.e(TAG, "sub cat: "+mSubCategories.toString() );
                }catch (Exception e){
                    Log.e(TAG, "exp: "+e.toString() );
                }
            }
        }).start();

    }
    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to cancel form")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }







}
