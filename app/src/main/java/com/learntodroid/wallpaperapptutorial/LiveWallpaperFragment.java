package com.learntodroid.wallpaperapptutorial;

import android.app.WallpaperManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.Locale;

public class LiveWallpaperFragment extends Fragment {
    private TextView textView;
    public static int hyft;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
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

                        //if(hyft == 5){
                            Intent intent = new Intent(WallpaperManager.ACTION_CHANGE_LIVE_WALLPAPER);
                            intent.putExtra(WallpaperManager.EXTRA_LIVE_WALLPAPER_COMPONENT, new ComponentName(getActivity(), MyWallpaperService.class));
                            startActivity(intent);
                        //}
                        //else{
                        System.out.println(hyft);
                        //}
                    }

                });

        return view;
    }
}
