package org.maatanyy.janggon.hanra;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class HelpActivity extends AppCompatActivity {

    @BindView(R.id.buttoncall1)
    Button buttoncall1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.buttoncall1)
    public void oncall(){
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:01012345678"));
        startActivity(intent);
    }

}