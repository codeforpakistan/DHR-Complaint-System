package com.dohr.complaint.cell.javaClasses.Awareness;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.dohr.complaint.cell.R;
import com.dohr.complaint.cell.modelClasses.AwarenessAllCategory;
import com.squareup.picasso.Picasso;

import static com.dohr.complaint.cell.UtilsClasses.Config.BaseUrl;

public class AwarenessDetails extends AppCompatActivity {
    ImageView backbtn,thumbnail_img;
    TextView title_text, description_text;

    CardView cardView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_awareness_details);
        backbtn = findViewById(R.id.backbtn);
        thumbnail_img = findViewById(R.id.thumbnail_img);
        description_text = findViewById(R.id.description_text);
        title_text = findViewById(R.id.title_text);
        //cardView = findViewById(R.id.cardView);
        Bundle data = getIntent().getExtras();
        AwarenessAllCategory allCategory = data.getParcelable("allCat");

              String description_str =allCategory.getDescription();
              String title_str =allCategory.getTitle();

        title_text.setText(title_str);
        description_text.setText(description_str);


        Picasso.with(this).load(allCategory.getImage()).resize(300,300).into(thumbnail_img);



        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AwarenessDetails.this, Awareness.class));
            }
        });
    }
}
