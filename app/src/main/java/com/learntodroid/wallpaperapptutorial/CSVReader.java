package com.learntodroid.wallpaperapptutorial;

import android.content.Context;
import android.content.res.AssetManager;

import androidx.appcompat.view.menu.MenuBuilder;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {

    List<ListData> objects = new ArrayList<ListData>();
        public void reader(Context context) {
            AssetManager assetManager = context.getResources().getAssets();

            //読み込みファイルのインスタンス生成
            InputStream is = null;
            InputStreamReader inputStreamReader = null;
            BufferedReader bufferReader = null;

            try {
                is = assetManager.open("points.csv");
                inputStreamReader = new InputStreamReader(is);
                bufferReader = new BufferedReader(inputStreamReader);
                String line;

                //読み込み行数の管理
                int i = 0;

                //列名を管理する為の配列
                String[] arr = null;

                //1行ずつ読み込みを行う
                while ((line = bufferReader.readLine()) != null) {

                    //カンマ区切りで１つづつ配列に入れる
                    ListData data = new ListData();
                    String[] RowData = line.split(",");




                    //先頭行は列名
                    if (i == 0) {
                        //カンマで分割した内容を配列に格納する
//                        arr = line.split(",");
                        System.out.println("0行目だよ");
                    } else {
                        //CSVの左([0]番目)から順番にセット
                        data.setId(Integer.parseInt(RowData[0]));
                        data.setType(Integer.parseInt(RowData[1]));
                        data.setJp_name(RowData[2]);
                        data.setEn_name(RowData[3]);
                        data.setHYFT(Integer.parseInt(RowData[4]));
                        data.setLatitude(Double.parseDouble(RowData[5]));
                        data.setLongitude(Double.parseDouble(RowData[6]));
                        data.setAddress(RowData[7]);
                        data.setDataURL(RowData[8]);

                        objects.add(data);
//                        //データ内容をコンソールに表示する
//                        System.out.println("-------------------------------");
//
//                        //データ件数を表示
//                        System.out.println("データ" + i + "件目");
//
//                        //カンマで分割した内容を配列に格納する
//                        String[] data = line.split(",");
//
//                        //配列の中身を順位表示する。列数(=列名を格納した配列の要素数)分繰り返す
//                        int colno = 0;
//                        for (String column : arr) {
//                            //System.out.println(column + ":" + data[colno]);
//                            colno++;
                    }
                    //行数のインクリメント
                    i++;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            finally {
                try {
                    bufferReader.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
}