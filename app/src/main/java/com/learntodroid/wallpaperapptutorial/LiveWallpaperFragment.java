package com.learntodroid.wallpaperapptutorial;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class LiveWallpaperFragment extends Fragment {
    private TextView textView;
    public static int type =1; //restauramnt
    public static int hyft;
    WallpaperManager mWM;
    CSVReader parser = new CSVReader();
    private HashMap<String,Integer> ResouseList = new HashMap<String, Integer>(){{
        put("Sakura_Burger", R.raw.sakura_burger);
        put("Macdonald-Nara", R.raw.sakura_burger);
        put("GyozaNoOsho-KintetsuNaraEkiMae", R.raw.gyoza);
        put("Sukiya-KintetsuNaraEkiMae", R.raw.gyoza);
        put("HousekiBako", R.raw.kakigori);
        put("MahorobaDaibutsuPurinHonpo", R.raw.kakigori);
        put("PutimaruCafe", R.raw.soba);
        put("Genkishin-Nara", R.raw.ramen);
        put("TenriSutaminaRamen-KintetsuNaraEkiMae", R.raw.ramen);
        put("Sobakiri-Momoyoduki", R.raw.soba);
    }
    };


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            parser.reader(getContext());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_livewallpaper, container, false);

        view.findViewById(R.id.fragment_livewallpaper_set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
                intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(getActivity(), MyWallpaperService.class));
                startActivity(intent);
            }
        });

        textView = view.findViewById(R.id.text_view);
        SeekBar seekBar = view.findViewById(R.id.seekbar);
        mWM = WallpaperManager.getInstance(getContext());

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
                    }

                    //ツマミがタッチされた時に呼ばれる
                    @Override
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }


                    //ツマミがリリースされた時に呼ばれる
                    @Override
                    public void onStopTrackingTouch(SeekBar seekBar) {
                        List<Integer> select = new ArrayList<Integer>();
                        for(int i =0; i< parser.objects.size(); i++) {

                            if( parser.objects.get(i).HYFT == hyft && parser.objects.get(i).type==type) {
                                select.add(parser.objects.get(i).id);
//                                System.out.println(parser.objects.get(i).jp_name);
//                                System.out.println(parser.objects.get(i).HYFT);
//                                System.out.println(parser.objects.get(i).id);
//                                System.out.println(parser.objects.get(i).type);
                            }
                        }
                        int index = new Random().nextInt(select.size());
                        System.out.println(select.get(index));
                        String title =  parser.objects.get(select.get(index)).en_name;
                        System.out.println(title);
                        try{
                            System.out.println(title);
                            mWM.setResource(ResouseList.get(title));

//                            if(hyft == 1){
//                                mWM.setResource(ResouseList.get(title));
//                                System.out.println(ResouseList.get(title));
////                                mWM.setResource(eval("R.raw."+title));
//                            }
//                            if(hyft == 2){
//                                System.out.println(R.raw.soba);
//                                mWM.setResource(R.raw.soba);
//                            }
//                            if(hyft == 3){
//                                System.out.println(R.raw.burger);
//                                mWM.setResource(R.raw.burger);
//                            }
//                            if(hyft == 4){
//                                System.out.println(R.raw.gyoza);
//                                mWM.setResource(R.raw.gyoza);
//                            }
//                            if(hyft == 5){
//                                System.out.println(R.raw.ramen);
//                                mWM.setResource(R.raw.ramen);
//                            }

//                            Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
//                            intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(getActivity(), MyWallpaperService.class));
//                            startActivity(intent);

                        } catch(IOException e){
                            e.printStackTrace();
                        }
//


                        //}
                        //else{
                        //System.out.println("hyft");
                        //System.out.println(hyft);
                        //}
                    }

                });

        return view;
    }
}
