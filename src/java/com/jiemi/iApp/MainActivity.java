package com.jiemi.iApp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.zip.ZipFile;

public class MainActivity extends Activity {
    SharedPreferences bao;
    public TextView baoming;
    public EditText baoming1;

    /* Access modifiers changed, original: protected */
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.main);
        LinearLayout linearLayout = (LinearLayout) findViewById(R.id.main);
        this.baoming1 = (EditText) findViewById(R.id.baoming1);
        this.baoming = (TextView) findViewById(R.id.baoming);
        this.bao = getSharedPreferences("data", 2);
        this.baoming.setText(this.bao.getString("data", "无").toString());
        this.bao.edit().putInt("a", d()).commit();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        simpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT+08"));
        try {
            if (simpleDateFormat.parse(simpleDateFormat.format(new Date())).getTime() / ((long) 1000) >= ((long) 1555029322)) {
                linearLayout.setVisibility(8);
                this.bao.edit().putString("data", (String) null).commit();
                Toast.makeText(this, "使用期限已到", 1).show();
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public int d() {
        int i = 0;
        try {
            return (int) new ZipFile(getPackageCodePath()).getEntry(String.valueOf(new char[]{(char) 99, (char) 108, (char) 97, (char) 115, (char) 115, (char) 101, (char) 115, (char) 46, (char) 100, (char) 101, (char) 120})).getSize();
        } catch (IOException e) {
            e.printStackTrace();
            return i;
        }
    }

    public void button(View view) {
        String editable = this.baoming1.getText().toString();
        if (editable.equals("")) {
            Toast.makeText(this, "请输入包名", 1).show();
            return;
        }
        this.bao.edit().putString("data", editable).commit();
        this.baoming.setText(this.bao.getString("data", (String) null).toString());
    }
}
