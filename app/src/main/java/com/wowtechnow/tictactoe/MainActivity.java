package com.wowtechnow.tictactoe;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView Player1,Player2;
    private String PlayerName1 = "Player :" , PlayerName2 = "Player :";
    private  Dialog Name;
    private boolean player1Turn = true;
    private int player1Points = 0 , player2Points = 0 , roundCount = 0;
    private Button[][] button = new Button[3][3];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Tic Tac Toe");
        setSupportActionBar(toolbar);
        Player1  = findViewById(R.id.tvPlayer1);
        Player2 = findViewById(R.id.tvPlayer2);
        PlayerNameDialog();
        Button reset = findViewById(R.id.btnReset);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetGame();
                PlayerNameDialog();
            }
        });
        for(int i = 0; i < 3 ; i++){
            for(int j = 0; j < 3 ; j++){
                String buttonID = "button_"+ i + j;
                int resID = getResources().getIdentifier(buttonID,"id",getPackageName());
                button[i][j] = findViewById(resID);
                button[i][j].setOnClickListener(this);
            }
        }
    }

    private void PlayerNameDialog(){
        Name = new Dialog(this);
        Name.setContentView(R.layout.dialog_player);
        Name.setCanceledOnTouchOutside(false);
        final EditText Player_X_Name , Player_O_Name;
        Player_O_Name = Name.findViewById(R.id.etPlayer_O_Name);
        Player_X_Name = Name.findViewById(R.id.etPlayer_X_Name);
        Name.findViewById(R.id.btnPlay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Player_O_Name.getText().toString().isEmpty() || Player_X_Name.getText().toString().isEmpty()){
                    Player_O_Name.setError("Please Enter Player Name");
                    Player_X_Name.setError("Please Enter Player Name");
                    Player_X_Name.requestFocus();
                    Player_O_Name.requestFocus();
                    return;
                }
                else{
                    PlayerName1 = Player_X_Name.getText().toString() + ":" ;
                    PlayerName2 = Player_O_Name.getText().toString() + ":" ;
                    Player1.setText(PlayerName1);
                    Player2.setText(PlayerName2);
                    Name.dismiss();
                }
            }
        });
        Name.show();
    }

    private void resetGame() {
        player1Points = 0;
        player2Points = 0;
        UpdatePointText();
        resetBoard();
    }

    @Override
    public void onClick(View v){
        if(!((Button)v).getText().toString().equals("")){
            return;
        }

        if(player1Turn){
            ((Button)v).setText("X");
        }else{
            ((Button)v).setText("O");
        }
        roundCount++;
        if(CheckForWin()){
            if(player1Turn){
                player1Win(v);
            }else{
                player2Wins(v);
            }
        }else if(roundCount == 9){
            Draw(v);
        }else{
            player1Turn = !player1Turn;
        }
    }

    private void Draw(View v) {
      Snackbar snackbar = Snackbar.make(v , "Draw !!" , Snackbar.LENGTH_LONG);
      snackbar.setBackgroundTint(getResources().getColor(R.color.colorSnackBar));
      snackbar.setTextColor(getResources().getColor(R.color.colorDark));
      snackbar.show();
      resetBoard();
    }

    private void player2Wins(View v){
        player2Points++;
        Snackbar snackbar = Snackbar.make(v , "Player 2 Wins !!" , Snackbar.LENGTH_LONG);
        snackbar.setBackgroundTint(getResources().getColor(R.color.colorSnackBar));
        snackbar.setTextColor(getResources().getColor(R.color.colorDark));
        snackbar.show();
        UpdatePointText();
        resetBoard();
    }

    private void player1Win(View v){
        player1Points++;
        Snackbar snackbar = Snackbar.make(v , "Player 1 Wins !!" , Snackbar.LENGTH_LONG);
        snackbar.setBackgroundTint(getResources().getColor(R.color.colorSnackBar));
        snackbar.setTextColor(getResources().getColor(R.color.colorDark));
        snackbar.show();
        UpdatePointText();
        resetBoard();
    }

    private void resetBoard() {
        for(int i = 0; i < 3 ; i++){
            for(int j = 0; j < 3 ; j++){
                button[i][j].setText("");
            }
        }
        roundCount = 0;
        player1Turn = true;
    }

    private void UpdatePointText() {
        Player1.setText(new StringBuilder(PlayerName1).append(player1Points));
        Player2.setText(new StringBuilder(PlayerName2).append(player2Points));
    }

    private boolean CheckForWin(){
        String[][] field = new String[3][3];

        for(int i = 0; i < 3 ; i++){
            for(int j = 0; j < 3 ; j++){
               field[i][j] = button[i][j].getText().toString();
            }
        }
        for(int i = 0; i < 3 ; i++){
            if (field[i][0].equals(field[i][1])
                    && field[i][0].equals(field[i][2])
                    && !field[i][0].equals("")) {
                return true;
            }
        }
        for(int i = 0; i < 3 ; i++){
            if (field[0][i].equals(field[1][i])
                    && field[0][i].equals(field[2][i])
                    && !field[0][i].equals("")) {
                return true;
            }
        }
        if(field[0][0].equals(field[1][1])
                && field[0][0].equals(field[2][2])
                && !field[0][0].equals("")){
            return true;
        }

        if(field[0][2].equals(field[1][1])
                && field[0][2].equals(field[2][0])
                && !field[0][2].equals("")){
            return true;
        }

        return false;
    }

}
