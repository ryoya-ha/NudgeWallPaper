package com.learntodroid.wallpaperapptutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class WallpaperActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        System.out.println("WallPaperActivity");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallpaper);
    }
}
