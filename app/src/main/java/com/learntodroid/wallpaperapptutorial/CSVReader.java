package com.learntodroid.wallpaperapptutorial;

import android.content.Context;
import android.content.res.AssetManager;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class CSVReader {
        public static void reader(Context context) {
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

                    //先頭行は列名
                    if (i == 0) {
                        //カンマで分割した内容を配列に格納する
                        arr = line.split(",");
                    } else {
                        //データ内容をコンソールに表示する
                        System.out.println("-------------------------------");

                        //データ件数を表示
                        System.out.println("データ" + i + "件目");

                        //カンマで分割した内容を配列に格納する
                        String[] data = line.split(",");

                        //配列の中身を順位表示する。列数(=列名を格納した配列の要素数)分繰り返す
                        int colno = 0;
                        for (String column : arr) {
                            System.out.println(column + ":" + data[colno]);
                            colno++;
                        }
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