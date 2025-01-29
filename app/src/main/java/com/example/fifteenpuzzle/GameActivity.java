package com.example.fifteenpuzzle;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;

import androidx.appcompat.app.AppCompatActivity;


import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends AppCompatActivity {

    private GridLayout gridLayout;
    private Button[] buttons = new Button[16];
    private ArrayList<Integer> buttonOrder = new ArrayList<>();
    private int emptyTileIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        gridLayout = findViewById(R.id.gridLayout);

        // ボタンの配列にボタンを設定
        for (int i = 0; i < 16; i++) {
            String buttonID = "button_" + i;
            int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
            buttons[i] = findViewById(resID);
        }

        // ボタンの順番を管理するリスト
        for (int i = 0; i < 16; i++) {
            buttonOrder.add(i);
        }

        // ボタンにクリックリスナーを設定
        for (int i = 0; i < 16; i++) {
            final int index = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    moveTile(index);
                }
            });
        }

        // 初期化：ゲーム開始時にボタンをシャッフル
        shuffleTiles();
    }

    // ボタンを動かすメソッド
    private void moveTile(int index) {
        // 空のタイルの位置を確認
        emptyTileIndex = findEmptyTile();

        // クリックされたボタンが空のタイルと隣接しているか確認
        if (isAdjacent(index, emptyTileIndex)) {
            // 隣接していれば、ボタンの順番を交換
            Collections.swap(buttonOrder, index, emptyTileIndex);
            updateGrid();
        }
    }

    // 空のタイルを見つけるメソッド
    private int findEmptyTile() {
        return buttonOrder.indexOf(15);  // 15番目が空のタイル
    }

    // ボタンが隣接しているか確認するメソッド
    private boolean isAdjacent(int index1, int index2) {
        int row1 = index1 / 4;
        int col1 = index1 % 4;
        int row2 = index2 / 4;
        int col2 = index2 % 4;

        // 上下左右に隣接していればtrue
        return (Math.abs(row1 - row2) == 1 && col1 == col2) || (Math.abs(col1 - col2) == 1 && row1 == row2);
    }

    // ゲームの状態を更新（ボタンのテキストを変更）
    private void updateGrid() {
        for (int i = 0; i < 16; i++) {
            if (buttonOrder.get(i) == 15) {
                buttons[i].setText("");  // 空のタイルは何も表示しない
            } else {
                buttons[i].setText(String.valueOf(buttonOrder.get(i) + 1));
            }
        }
    }

    // ボタンをランダムにシャッフルするメソッド
    private void shuffleTiles() {
        Collections.shuffle(buttonOrder);
        updateGrid();
    }
}
