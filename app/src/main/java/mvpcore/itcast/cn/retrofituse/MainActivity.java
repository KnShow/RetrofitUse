package mvpcore.itcast.cn.retrofituse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {
//    http://v.juhe.cn/weather//index?cityname=%E9%95%BF%E6%B2%99&key=fd0f609b22905a0a56a48d7cf59a558b
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        Retrofit build = new Retrofit.Builder()
                .baseUrl("http://v.juhe.cn/weather/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        //和接口关联起来
        ApiService apiService = build.create(ApiService.class);
        //参数封装
        HashMap<String, Object> params = new HashMap<>();
        params.put("cityname","长沙");
        params.put("key","fd0f609b22905a0a56a48d7cf59a558b");
        apiService.sendRequest(params).enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
