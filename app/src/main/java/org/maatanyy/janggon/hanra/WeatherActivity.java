package org.maatanyy.janggon.hanra;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class WeatherActivity extends AppCompatActivity {
    @BindView(R.id.button9)
    Button button9;

    @BindView(R.id.dateView)
    TextView dateView;

    @BindView(R.id.cityView)
    TextView cityView;

    @BindView(R.id.weatherView)
    TextView weatherView;

    @BindView(R.id.tempView)
    TextView tempView;

   static RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        ButterKnife.bind(this);

        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(getApplicationContext());
        }
    }

    @OnClick(R.id.button9)
    public void onbt9(){
        makeRequest();
    }

    public void makeRequest(){

        String url = "http://api.openweathermap.org/data/2.5/forecast/daily?id=1846266&lang=kr&appid=8367f47f6242e5310c1b27a4ebfd9868";

        StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {

            @SuppressLint("SetTextI18n")
            @Override
            public void onResponse(String response) {

                try {


                    //System의 현재 시간(년,월,일,시,분,초까지)가져오고 Date로 객체화함
                    long now = System.currentTimeMillis();
                    Date date = new Date(now);

                    //년, 월, 일 형식으로. 시,분,초 형식으로 객체화하여 String에 형식대로 넣음
                    SimpleDateFormat simpleDateFormatDay = new SimpleDateFormat("yyyy-MM-dd");
                    SimpleDateFormat simpleDateFormatTime = new SimpleDateFormat("HH:mm:ss");
                    String getDay = simpleDateFormatDay.format(date);
                    String getTime = simpleDateFormatTime.format(date);


                    //getDate에 개행을 포함한 형식들을 넣은 후 dateView에 text설정
                    String getDate = getDay + "\n" + getTime;
                    dateView.setText(getDate);


                    //api로 받은 파일 jsonobject로 새로운 객체 선언
                    JSONObject jsonObject = new JSONObject(response);


                    //도시 키값 받기
                   // String city = jsonObject.getString("name");
                    String city = jsonObject.getString("name");
                    cityView.setText(city);


                    //날씨 키값 받기
                    JSONArray weatherJson = jsonObject.getJSONArray("weather");
                    JSONArray aa = weatherJson;
                    Log.i("aa","메시지");
                    JSONObject weatherObj = weatherJson.getJSONObject(0);
                    String weather = weatherObj.getString("description");
                    weatherView.setText(weather);



                    //기온 키값 받기
                    JSONObject tempK = new JSONObject(jsonObject.getString("main"));
                    //기온 받고 켈빈 온도를 섭씨 온도로 변경
                    double tempDo = (Math.round((tempK.getDouble("temp")-273.15)*100)/100.0);
                    tempView.setText(tempDo +  "°C");




                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();

                return params;
            }

        };

        request.setShouldCache(false);
        requestQueue.add(request);
    }
}