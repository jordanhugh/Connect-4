package com.example.connect_4;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.connect_4.preGame01;
import com.example.connect_4.R;

import static com.example.connect_4.preGame01.name01;
import static com.example.connect_4.preGame01.name02;

public class SecondActivity extends AppCompatActivity implements View.OnClickListener{
    public static final int BLANK = 0;
    public static final int PLAYER1 = 1;
    public static final int PLAYER2 = 2;
    public static final String[] GAME_TOKENS = {"", "X", "O"};
    public static final int MAX_SEQUENCE = 4;
    public static final int COLUMNS = 7;
    public static final int ROWS = 6;

    private Button[][] buttons = new Button[ROWS][COLUMNS];
    private int player;

    private int player1points = 0;
    private int player2points = 0;

    private TextView textViewPlayer1;
    private TextView textViewPlayer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        textViewPlayer1 = findViewById(R.id.text_view_p1);
        textViewPlayer2 = findViewById(R.id.text_view_p2);


        initialiseGame();
    }

    @Override
    public void onClick(View v) {


        int row, col;
        boolean broke = false;

        for (col = 0; col < COLUMNS; col++) {
            for (row = 0; row < ROWS; row++) {
                if(buttons[row][col].getId() == v.getId()){
                    broke = true;
                    break;
                }
            }
            if (broke){
                break;
            }
        }
        row = dropPiece(col);



        if (checkForWin(row, col)) {
            win();
        }
        else if (checkIfBoardFull()) {
            stalemate();
        }
        else {
            player = (player * 2) % 3;
        }
    }

    private void initialiseGame(){
        player = PLAYER1;
        textViewPlayer1.setText(name01 + ": " + player1points + "      ");
        textViewPlayer2.setText(name02 + ": " + player2points);

        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                String buttonID = "button_" + row + col;
                int resID = getResources().getIdentifier(buttonID, "id", getPackageName());
                buttons[row][col] = findViewById(resID);
                buttons[row][col].setOnClickListener(this);
            }
        }
    }

    private boolean checkIfColumnFull(int col){
        if (buttons[0][col].getText().toString().equals(GAME_TOKENS[BLANK])) {
            return false;
        }
        return true;
    }

    private boolean checkIfBoardFull(){
        for(int col = 0; col < COLUMNS; col++){
            if(!checkIfColumnFull(col))
                return false;
        }
        return true;
    }

    private int dropPiece(int col){
        for(int row = ROWS - 1; row >= 0; row--){
            if (buttons[row][col].getText().toString().equals(GAME_TOKENS[BLANK])){
                buttons[row][col].setText(GAME_TOKENS[player]);
                return row;
            }
        }
        return -1;
    }

    private int sequence(int row, int col, int dx, int dy){
        int x = col + dx;
        int y = row + dy;
        int seq = 0;

        while (x >= 0 && x < COLUMNS && y >= 0 && y < ROWS && buttons[y][x].getText().toString().contains(GAME_TOKENS[player])){
            x += dx;
            y += dy;
            seq++;
        }

        return seq;
    }

    boolean checkForWin(int row, int col){
        int dx, dy;

        for (double theta = 0; theta < Math.PI; theta += Math.PI/4){
            dx = (int)Math.round(Math.sin(theta));
            dy = (int)Math.round(Math.cos(theta));


            int seq = 1 + sequence(row, col, dx, dy) + sequence (row, col, -dx, -dy);
            if (seq >= MAX_SEQUENCE)
                return true;
        }

        return false;
    }

    private void win() {
        if(player == PLAYER1) {
            player1points++;
        }
        else{
            player2points++;
        }
        Toast.makeText(this, "Player " + player + " wins!", Toast.LENGTH_SHORT).show();
        updatePoints();
        resetBoard();
    }

    private void stalemate() {
        Toast.makeText(this, "Draw!", Toast.LENGTH_SHORT).show();
        resetBoard();
    }

    private void updatePoints() {
        textViewPlayer1.setText(name01 + ": " + player1points + "      ");
        textViewPlayer2.setText(name02 + ": " + player2points);
    }

    private void resetBoard() {
        for (int j = 0; j < ROWS; j++) {
            for (int i = 0; i < COLUMNS; i++) {
                buttons[j][i].setText(GAME_TOKENS[BLANK]);
            }
        }

        player = PLAYER1;
    }
}