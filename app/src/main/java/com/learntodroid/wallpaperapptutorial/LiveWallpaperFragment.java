package com.learntodroid.wallpaperapptutorial;

import android.annotation.SuppressLint;
import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONObject;

public class LiveWallpaperFragment extends Fragment {
    private String API_KEY;
    private TextView textView;
    private TextView textView2;
    public static int type = 1;
    public static int hyft;
    Response response;
    WallpaperManager mWM;
    CSVReader parser = new CSVReader();

    private HashMap<String,Integer> ResouseList = new HashMap<String, Integer>(){{
        //レストラン
        put("Genkishin-Nara", R.raw.genkishin_nara);
        put("TenriSutaminaRamen-KintetsuNaraEkiMae", R.raw.ramen);

        put("GyozaNoOsho-KintetsuNaraEkiMae", R.raw.gyoza);
        put("Sukiya-KintetsuNaraEkiMae", R.raw.sukiya);

        put("Macdonald-Nara", R.raw.macdonald);
        put("Sakura_Burger", R.raw.sakura_burger);

        put("PutimaruCafe", R.raw.cafe);
        put("Sobakiri-Momoyoduki", R.raw.soba);

        put("HousekiBako", R.raw.kakigori);
        put("MahorobaDaibutsuPurinHonpo", R.raw.pudding);

        //観光地
//        put("Nara Park", R.raw.nara_park);
//        put("Mount Wakakusa", R.raw.wakakusayama);
//
//        put("Nara Women's University", R.raw.nara_jyoshi);
//        put("Heijo Palace Remains", R.raw.heijyo);
//
//        put("Kofuku-ji ", R.raw.kofukuji);
//        put("Sarusawa Pond", R.raw.sarusawa);
//
//        put("Hokke-ji", R.raw.hokkeji);
//        put("Kasuga-taisha", R.raw.kasugataisha);
//
//        put("Todai-ji", R.raw.todaiji);
//        put("Himuro Shrine", R.raw.himuro);
    }

    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            parser.reader(getContext());
            API_KEY = getResources().getString(R.string.OpenWeatherMap);
            System.out.println(API_KEY);

        final OkHttpClient client = new OkHttpClient();

        final Request request = new Request.Builder()
//                .url("http://www.mocky.io/v2/573336c90f0000902cead88d")
//                .url("https://zipcloud.ibsnet.co.jp/api/search?zipcode=7830060")
                  .url("https://api.openweathermap.org/data/2.5/onecall?lat=34.684458&lon=135.827505&units=metric&lang=ja&appid="+API_KEY)
                .build();

        AsyncTask<Void, Void, String> asyncTask = new AsyncTask<Void, Void, String>() {
            @SuppressLint("StaticFieldLeak")
            @Override
            protected String doInBackground(Void... params) {
                try {
                    response = client.newCall(request).execute();

                    if (!response.isSuccessful()) {

                        System.out.println("fail");
                        return null;
                    }
//                    System.out.println(response.body().string());
                    System.out.println("a");
                    String str = response.body().string();
                    System.out.println(str);
                    JSONObject jsonObject = new JSONObject(str);
                    System.out.println("b");

                    JSONArray jsonarray = jsonObject.getJSONArray("hourly");
                    System.out.println(jsonarray);
                    Double current_hyft = jsonarray.getJSONObject(1).getDouble("feels_like");
//                    Double current_lat = jsonObject.getDouble("lat");
                    System.out.println(current_hyft);
                    return response.body().string();
                } catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if (s != null) {
                    System.out.println(s);

                    textView.setText(s);
                }
            }
        };
        asyncTask.execute();
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_livewallpaper, container, false);

//        view.findViewById(R.id.fragment_livewallpaper_set).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
//                intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(getActivity(), MyWallpaperService.class));
//                startActivity(intent);
//            }
//        });

        textView = view.findViewById(R.id.text_view);
        textView2 = view.findViewById(R.id.text_view2);

        SeekBar seekBar = view.findViewById(R.id.seekbar);
        mWM = WallpaperManager.getInstance(getContext());

        Button button2 = view.findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    System.out.println("Button OK");
                }
            }
        );

        // 初期値
        seekBar.setProgress(1);
        // 最大値
        seekBar.setMin(1);
        seekBar.setMax(5);
        seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    //ツマミがドラッグされると呼ばれる
                    @Override
                    public void onProgressChanged(
                            SeekBar seekBar, int progress, boolean fromUser) {
                        // 68 % のようにフォーマト、 progressがパーセンテージの値
                        // この場合、Locale.USが汎用的に推奨される
                        //String str = String.format(Locale.US, "%d %%",progress);
                        String str = String.format(Locale.US, "%d",progress);
                        textView.setText(str);
                        //System.out.println(str);
                        hyft = progress;

//                        String str2 = String.format(Locale.US, "%d",hyft);
//                        textView2.setText(str2);
                    }

                    //ツマミがタッチされた時に呼ばれる
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    //ツマミがリリースされた時に呼ばれる

                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {


                        List<Integer> select = new ArrayList<Integer>();

                        //String url = "http://zip.cgis.biz/xml/zip.php?zn=1700011";
                        OkHttpClient client = new OkHttpClient();

//                        try {
//                            System.out.println("try");
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }

                        for(int i =0; i< parser.objects.size(); i++) {

                            if( parser.objects.get(i).HYFT == hyft && parser.objects.get(i).type==type) {
                                select.add(parser.objects.get(i).id);
//                                System.out.println(parser.objects.get(i).jp_name);
                            }
                        }
                        int index = new Random().nextInt(select.size());
                        //System.out.println(select.get(index));
                        String title =  parser.objects.get(select.get(index)).en_name;
                        System.out.println(title);
                        String str2 = String.format(Locale.US, "%s",title);
                        textView2.setText(str2);

                        try{
                            System.out.println(title);
                            mWM.setResource(ResouseList.get(title));

//                            Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
//                            intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(getActivity(), MyWallpaperService.class));
//                            startActivity(intent);

                        } catch(IOException e){
                            e.printStackTrace();
                        }
                        //System.out.println(hyft);
                    }

                });

        return view;
    }
}
