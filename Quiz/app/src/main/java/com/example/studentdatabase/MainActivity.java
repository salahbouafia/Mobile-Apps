package com.example.studentdatabase;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

class Question{
    public String question;
    public int qID;
    public boolean qAnswer;
    public Drawable picture;
    public boolean answered;

    public Question(String q,int qID,boolean qAnswer,Drawable pic){
        this.question = q;
        this.qID = qID;
        this.qAnswer = qAnswer;
        this.picture = pic;
        this.answered = false;
    }
}

public class MainActivity extends AppCompatActivity {

    int i=-1;
    int scorecount = 0;

    @SuppressLint({"SetTextI18n", "UseCompatLoadingForDrawables"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.constraints_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button True = findViewById(R.id.True);
        Button False = findViewById(R.id.False);
        Button Next = findViewById(R.id.Next);
        Button Previous = findViewById(R.id.Previous);
        ImageView image = findViewById(R.id.image);
        TextView question = findViewById(R.id.question);
        TextView score = findViewById(R.id.score);

        Question q1 = new Question("About 36 and 42 islands make up New York City",0,true,getDrawable(R.drawable.islands));
        Question q2 = new Question("Only one capital exists in South Africa",1,false,getDrawable(R.drawable.saflag));
        Question q3 = new Question("The largest ocean in the world is the Atlantic Ocean",2,false,getDrawable(R.drawable.ocean));
        Question q4 = new Question("The tallest mountain in the world is Mount Everest",3,true,getDrawable(R.drawable.mountain));
        Question q5 = new Question("California is home to the “Desert of Death”",4,false,getDrawable(R.drawable.desert));
        Question q6 = new Question("13,171 miles is how far the Great Wall of China stretches",5,false,getDrawable(R.drawable.greatwall));
        Question q7 = new Question("The Mississippi is the longest river in the world",6,false,getDrawable(R.drawable.river));
        Question q8 = new Question("The 31-mile-long Channel train tunnel connects England and France",7,true,getDrawable(R.drawable.tunnel));
        Question q9 = new Question("The world’s largest island is Greenland",8,true,getDrawable(R.drawable.greenland));
        Question q10 = new Question("South America has more nations than Africa",9,false,getDrawable(R.drawable.southamerica));
        Question q11 = new Question("in all of America, Alaska has the most active volcanoes",10,true,getDrawable(R.drawable.volcano));
        Question q12 = new Question("The world’s longest coastline is in China",11,false,getDrawable(R.drawable.coastline));

        Question[] q = new Question[] {q1,q2,q3,q4,q5,q6,q7,q8,q9,q10,q11,q12};

        image.setImageDrawable(getDrawable(R.drawable.question));
        question.setText("Press Next To Start The Quiz");
        True.setEnabled(false);
        False.setEnabled(false);
        Previous.setEnabled(false);
        
        Next.setOnClickListener(v -> {
            if(i<q.length-1){
                i++;
                question.setText(q[i].question);
                image.setImageDrawable(q[i].picture);
                Previous.setEnabled(true);
            } else if(i == q.length-1){
                Next.setEnabled(false);
            }
            True.setEnabled(true);
            False.setEnabled(true);
        });

        Previous.setOnClickListener(v -> {
            if(i>0){
                i--;
                question.setText(q[i].question);
                image.setImageDrawable(q[i].picture);
                Next.setEnabled(true);
            }else if(i == 0){
                Previous.setEnabled(false);
            }
            True.setEnabled(true);
            False.setEnabled(true);
        });

        True.setOnClickListener(v -> {
            if(q[i].answered){
                question.setText("Question already answered");
                return;
            }
            if(q[i].qAnswer){
                image.setImageDrawable(getDrawable(R.drawable.check));
                scorecount ++;
                score.setText(scorecount + "/12");
            }else{
                image.setImageDrawable(getDrawable(R.drawable.cross));
            }
            q[i].answered = true;
            True.setEnabled(false);
            False.setEnabled(false);
        });

        False.setOnClickListener(v -> {
            if(q[i].answered){
                question.setText("Question already answered");
                return;
            }
            if(!q[i].qAnswer){
                image.setImageDrawable(getDrawable(R.drawable.check));
                scorecount ++;
                score.setText(scorecount + "/12");
            }else{
                image.setImageDrawable(getDrawable(R.drawable.cross));
            }
            q[i].answered = true;
            True.setEnabled(false);
            False.setEnabled(false);
        });

    }
}