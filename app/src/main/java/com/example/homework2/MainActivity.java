package com.example.homework2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    Button computeGPABtn;
    EditText class1Et;
    EditText class2Et;
    EditText class3Et;
    EditText class4Et;
    EditText class5Et;
    TextView GPAtv;
    TextView gpaLabel;
    ConstraintLayout mainLayout;
    int colorStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Linking UI controls to backend
        computeGPABtn = findViewById(R.id.computeGPABtn);
        class1Et = findViewById(R.id.editText1);
        class2Et = findViewById(R.id.editText2);
        class3Et = findViewById(R.id.editText3);
        class4Et = findViewById(R.id.editText4);
        class5Et = findViewById(R.id.editText5);
        GPAtv = findViewById(R.id.gpaTv);
        gpaLabel = findViewById(R.id.gpaLabel);
        mainLayout = findViewById(R.id.main_layout);
        colorStart = getResources().getColor(R.color.colorPrimaryDark);
    }

    public void computeButtonClicked(View view) {
        try
        {
            //Convert each user entry from string to double.
            //Grades should only range from 0-100, throw exception if not.
            double classOne = Double.parseDouble(class1Et.getText().toString());
            if(classOne > 100 || classOne < 0){
                class1Et.setTextColor(Color.parseColor("#ff6768"));
                throw new IllegalArgumentException();
            }
            else{
                class1Et.setTextColor(Color.parseColor("#ffffff"));
            }
            double classTwo = Double.parseDouble(class2Et.getText().toString());
            if(classTwo > 100 || classTwo < 0){
                class2Et.setTextColor(Color.parseColor("#ff6768"));
                throw new IllegalArgumentException();
            }
            else{
                class2Et.setTextColor(Color.parseColor("#ffffff"));
            }
            double classThree = Double.parseDouble(class3Et.getText().toString());
            if(classThree > 100 || classThree < 0){
                class3Et.setTextColor(Color.parseColor("#ff6768"));
                throw new IllegalArgumentException();
            }
            else{
                class3Et.setTextColor(Color.parseColor("#ffffff"));
            }
            double classFour = Double.parseDouble(class4Et.getText().toString());
            if(classFour > 100 || classFour < 0){
                class4Et.setTextColor(Color.parseColor("#ff6768"));
                throw new IllegalArgumentException();
            }
            else{
                class4Et.setTextColor(Color.parseColor("#ffffff"));
            }
            double classFive = Double.parseDouble(class5Et.getText().toString());
            if(classFive > 100 || classFive < 0){
                class5Et.setTextColor(Color.parseColor("#ff6768"));
                throw new IllegalArgumentException();
            }
            else{
                class5Et.setTextColor(Color.parseColor("#ffffff"));
            }

            //Calculate average of all entries to get GPA
            double GPA = (classOne + classTwo + classThree + classFour + classFive)/5;
            GPA = Math.round(GPA *100.0)/100.0;
            String formattedGPA = String.format("%.2f", GPA);
            GPAtv.setText(formattedGPA);

            //Hide Compute GPA button for 5 seconds after GPA is calculated
            computeGPABtn.setVisibility(View.GONE);
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    computeGPABtn.setVisibility(View.VISIBLE);
                }
            }, 5000);

            changeBackground(GPA);

        } catch (Exception e)
        {
            //Display errors found popup to user.
            Toast toast = Toast.makeText(getApplicationContext(), "All fields must be filled out and within the range 0-100!", Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL,0,27599   );
            toast.show();
        }
    }
    public void changeBackground(double GPA)
    {
        gpaLabel.setVisibility(View.VISIBLE);
        //Change background according to calculated GPA.
        // 80-100 - Green
        // 61-80  - Yellow
        // <61    - Red
        if(GPA >= 80)
        {
            int colorEnd = 0xFF0E5C00;
            ValueAnimator colorAnimation = ObjectAnimator.ofInt(mainLayout,"backgroundColor",colorStart,colorEnd);
            colorAnimation.setDuration(2500);
            colorAnimation.setEvaluator(new ArgbEvaluator());
            colorAnimation.start();
            colorStart = colorEnd;
            //mainLayout.setBackgroundColor(Color.parseColor("#0E5C00"));
        }
        else if(GPA >= 61)
        {
            int colorEnd = 0xFFD9A300;
            ValueAnimator colorAnimation = ObjectAnimator.ofInt(mainLayout,"backgroundColor",colorStart,colorEnd);
            colorAnimation.setDuration(2500);
            colorAnimation.setEvaluator(new ArgbEvaluator());
            colorAnimation.start();
            colorStart = colorEnd;
            //mainLayout.setBackgroundColor(Color.parseColor("#D9A300"));

        }
        else
        {
            int colorEnd = 0xFFA10000;
            ValueAnimator colorAnimation = ObjectAnimator.ofInt(mainLayout,"backgroundColor",colorStart,colorEnd);
            colorAnimation.setDuration(2500);
            colorAnimation.setEvaluator(new ArgbEvaluator());
            colorAnimation.start();
            colorStart = colorEnd;
            //mainLayout.setBackgroundColor(Color.parseColor("#A10000"));
        }
    }
}
