package com.example.fifteenpuzzle;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // スタートボタンがクリックされたときに呼ばれるメソッド
    public void startGame(View view) {
        // GameActivity に遷移する Intent を作成
        Intent intent = new Intent(MainActivity.this, GameActivity.class);
        startActivity(intent);  // GameActivity を開始
    }
}
