package org.maatanyy.janggon.hanra;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;

    @BindView(R.id.button2)
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

    }


    @OnClick(R.id.button)
    void onButtonClicked(){
       Toast.makeText(this,"aaaaaaaa", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.button2)
    void onButton2Clicked(){
        Toast.makeText(this,"11", Toast.LENGTH_SHORT).show();
    }

}