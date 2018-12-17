package com.dohr.complaint.cell.javaClasses;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.annotation.ColorRes;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.Toolbar;

import com.dohr.complaint.cell.LogInClasses.LogIn;
import com.dohr.complaint.cell.R;
import com.dohr.complaint.cell.javaClasses.Awareness.Awareness;

import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.LOGIN_PREF;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.Name;
import static com.dohr.complaint.cell.UtilsClasses.UserPrefence.UserPref;

public class Home extends AppCompatActivity {
    SharedPreferences loginSharedPreferences;
    SharedPreferences sharedpreferences;
    SharedPreferences.Editor loginEditor;
    SharedPreferences.Editor userEditor;
    android.support.v7.widget.Toolbar toolbar_trans;

    LinearLayout newcomplaint, checkstatus, notification, awareness, announce, contactus;
    Button menu ;
    ImageView more;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        toolbar_trans = findViewById(R.id.toolbar_trans);
        more = findViewById(R.id.more);
        setSupportActionBar(toolbar_trans);
        setTitle("");
        loginSharedPreferences = getApplicationContext()
                .getSharedPreferences(LOGIN_PREF,
                        Context.MODE_PRIVATE);
        sharedpreferences = getSharedPreferences(UserPref,
                Context.MODE_PRIVATE);
        String userName = sharedpreferences.getString(Name,"No data");
        Toast.makeText(this, "Welcome "+userName, Toast.LENGTH_SHORT).show();

        newcomplaint = findViewById(R.id.newcomplaint);
        checkstatus = findViewById(R.id.checkstatus);
        notification = findViewById(R.id.notification);
        awareness = findViewById(R.id.awareness);
        announce = findViewById(R.id.announcement);
        contactus = findViewById(R.id.contactus);
        menu = findViewById(R.id.menu);

     /*   menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showConfirmDialog();
                    }
                });



            }
        });*/
        awareness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, Awareness.class));
            }
        });

        contactus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, ContactUs.class));
            }
        });

        newcomplaint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this, NewComplaint.class));

            }
        });

        notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Notifications.class));
            }
        });

        announce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Home.this,Announcements.class));
            }
        });

    }

    private void showConfirmDialog() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        loginEditor = loginSharedPreferences.edit();
                        userEditor = sharedpreferences.edit();
                        loginEditor.clear();
                        userEditor.clear();
                        loginEditor.commit();
                        userEditor.commit();
                        Intent intent =new Intent(Home.this, LogIn.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }

                })
                .setNegativeButton("No", null)
                .show();
    }

    public void dosomething(View view) {


        startActivity(new Intent(Home.this, ComplaintStatus.class));

    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to Exit?")
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menudialogue,menu);
        return super.onCreateOptionsMenu(menu);

    }


    private void showDialogue() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Dialog previewDilaog = new Dialog(Home.this, R.style.more_dialog_theme);
                previewDilaog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                previewDilaog.setCancelable(true);
                previewDilaog.setContentView(R.layout.aboutus_layout);
                previewDilaog.getWindow().setBackgroundDrawable(new ColorDrawable(0x7f000000));

                LinearLayout aboutus = previewDilaog.findViewById(R.id.aboutus_lay);
                aboutus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //submitform to server
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //dataUploadToServir();
                                startActivity(new Intent(Home.this,AboutUs.class));

                            }
                        });
                    }
                });

                LinearLayout userprofile = previewDilaog.findViewById(R.id.userprofile_lay);
                userprofile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //dataUploadToServir();
                                startActivity(new Intent(Home.this,UserProfile.class));

                            }
                        });

                    }
                });

                LinearLayout logout = previewDilaog.findViewById(R.id.logout_lay);
                logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //dataUploadToServir();
                                showConfirmDialog();

                            }
                        });

                    }
                });

                previewDilaog.show();
                /*((ViewGroup)previewDilaog.getWindow().getDecorView())
                        .getChildAt(0).startAnimation(bottom);*/
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.more_options:
                //findViewById(R.id.bottomsheet).setVisibility(View.VISIBLE);

                final BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(Home.this);
                bottomSheetDialog.setContentView(R.layout.aboutus_layout);
                LinearLayout aboutus = bottomSheetDialog.findViewById(R.id.aboutus_lay);
                aboutus.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //submitform to server
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //dataUploadToServir();
                                startActivity(new Intent(Home.this,AboutUs.class));
                                bottomSheetDialog.dismiss();
                            }
                        });
                    }
                });
                LinearLayout userprofile = bottomSheetDialog.findViewById(R.id.userprofile_lay);
                userprofile.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //dataUploadToServir();
                                startActivity(new Intent(Home.this,UserProfile.class));
                                bottomSheetDialog.dismiss();
                            }
                        });
                    }
                });
                LinearLayout logout = bottomSheetDialog.findViewById(R.id.logout_lay);
                logout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //dataUploadToServir();
                                showConfirmDialog();
                                bottomSheetDialog.dismiss();
                            }
                        });
                    }
                });
                bottomSheetDialog.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
