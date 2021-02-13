package org.maatanyy.janggon.hanra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class CourseActivity extends AppCompatActivity {

    FrameLayout container;

    GwanFragment gwanFragment;
    SungFragment sungFragment;

    @BindView(R.id.buttonsung)
    Button buttonsung;

    @BindView(R.id.buttongwan)
    Button buttongwan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);
        ButterKnife.bind(this);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

       gwanFragment = new GwanFragment();
       sungFragment = new SungFragment();
    }

    @OnClick(R.id.buttonsung)
    public void onsung(){
        sungFragment = new SungFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container,sungFragment).commit();
    }


    @OnClick(R.id.buttongwan)
    public void ongwan(){
        gwanFragment = new GwanFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.container,gwanFragment).commit();
    }


}