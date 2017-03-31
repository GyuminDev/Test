package com.example.simgyumin.hw_gyumin01;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PackageManager packageManager; // 패키지 매니저 선언
    private String[] packageNames; // 패키지 이름 array
    private Drawable[] drawables; // 패키지 이미지 array
    private String[] appNames; // App 이름 array

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        packageManager = this.getPackageManager(); //PackageManager 인스턴스 호출

        //Intent 설정
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_MAIN); // 첫 엑티비티로서 실행
        intent.addCategory(Intent.CATEGORY_LAUNCHER);

        List<ResolveInfo> resolveinfos = packageManager.queryIntentActivities(intent,
                PackageManager.GET_META_DATA); // Intent로 실행 가능한 app목록을 조회하여 List에 집어넣음

        //조회된 app들의 갯수
        int size = resolveinfos.size();

        packageNames = new String[size];
        appNames = new String[size];
        drawables = new Drawable[size];

        for(int i=0; i<size; i++) { // array = packagename
            packageNames[i] = resolveinfos.get(i).activityInfo.applicationInfo.packageName;

            try {
                drawables[i] = packageManager.getApplicationIcon(packageNames[i]); // packagename으로 App icon get
                appNames[i] = getPackageManager().getApplicationLabel(getPackageManager().getApplicationInfo(
                        packageNames[i], PackageManager.GET_UNINSTALLED_PACKAGES)).toString(); //packagename으로 App name get
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
        }

        ListView listView = (ListView)findViewById(R.id.listview1); // ListView 인스턴스 호출

        MyAdapter myAdapter = new MyAdapter(this, drawables, appNames);
        listView.setAdapter(myAdapter);
    }


}

