package org.maatanyy.janggon.hanra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.button)
    Button button;

    @BindView(R.id.button2)
    Button button2;

    @BindView(R.id.button6)
    Button button6;

    @BindView(R.id.imageView)
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.button)
    public void onmap(){
        Intent intent = new Intent(getApplicationContext(), MapActivity.class);
        startActivity(intent);
    }


    @OnClick(R.id.button2)
    public void oncourse(){
        Intent intent = new Intent(getApplicationContext(), CourseActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.button6)
    public void onresolve(){
        Intent intent = new Intent(getApplicationContext(), ResolveActivity.class);
        startActivity(intent);
    }


}