package com.dohr.complaint.cell.javaClasses;

import android.Manifest;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.dohr.complaint.cell.R;

import java.util.List;

import javax.security.auth.login.LoginException;

public class ContactUs extends AppCompatActivity {
    int REQUEST_PHONE_CALL;
    Button callbutton, websiteclick, map_google, social_links, email_link, directorycheck;
    ImageView gohome;
    private static final int requestphone = 0x1;
    Animation bottom,RightSwipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        int number = 123;
        gohome = findViewById(R.id.gohome);
        callbutton = findViewById(R.id.buttoncall);
        websiteclick = findViewById(R.id.websiteclick);
        map_google = findViewById(R.id.map_google);
        social_links = findViewById(R.id.social_links);
        email_link = findViewById(R.id.email_link);
        directorycheck = findViewById(R.id.directorycheck);

        //bottom = AnimationUtils.loadAnimation(this, R.anim.left_slide);


        directorycheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContactUs.this, Directory.class));
            }
        });

        email_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "abc@gmail.com", null));
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject");
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body");
                startActivity(Intent.createChooser(emailIntent, "Send email..."));
            }
        });

        social_links.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogue();
            }
        });

        map_google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContactUs.this, MapsActivity.class));
            }
        });

        gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContactUs.this, Home.class));
            }
        });

        callbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:+92 9217203"));
                startActivity(intent);
            }
        });

        /*callbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder;
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    builder = new AlertDialog.Builder(ContactUs.this, android.R.style.Theme_Material_Dialog_Alert);
                } else {
                    builder = new AlertDialog.Builder(ContactUs.this);
                }
                builder.setTitle("Call")
                        .setMessage("Select sim1 or sim2")
                        .setPositiveButton("SIM 2", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                CallDoulSimIntent(ContactUs.this, 123, 1);
                            }
                        })
                        .setNegativeButton("SIM 1", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                CallDoulSimIntent(ContactUs.this, 123, 0);
                            }
                        })
                        .setIcon(android.R.drawable.ic_menu_call)
                        .show();

            }
        });*/
        websiteclick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ContactUs.this, AdvanceWebviewClass.class));
            }
        });

    }


    public void dosomething(View view) {


        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:+92 9217203"));

        if (ActivityCompat.checkSelfPermission(ContactUs.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        //Toast.makeText(ContactUs.this, "callagain", Toast.LENGTH_SHORT).show();
        startActivity(callIntent);
    }


    private void showDialogue() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Dialog previewDilaog = new Dialog(ContactUs.this, R.style.dialog_theme);
                previewDilaog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                previewDilaog.setCancelable(true);
                previewDilaog.setContentView(R.layout.social_dialogue);
                previewDilaog.getWindow().setBackgroundDrawable(new ColorDrawable(0x7f000000));

                ImageView facebook = previewDilaog.findViewById(R.id.facebook);
                facebook.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //submitform to server
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //dataUploadToServir();
                                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/dhrkp/"));
                                startActivity(myIntent);

                            }
                        });
                    }
                });

                ImageView twitter = previewDilaog.findViewById(R.id.twitter);
                twitter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //dataUploadToServir();
                                Intent myIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/DhrKpk"));
                                startActivity(myIntent);

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


    private void CallDoulSimIntent(Context context, int number, int simSlot) {


        Intent intent = new Intent(Intent.ACTION_CALL).setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        intent.putExtra("simSlot", simSlot);

        intent.setData(Uri.parse("tel:" + number));


        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ContactUs.this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_PHONE_CALL);
        } else {
            context.startActivity(intent);
        }

    }
}