package com.brittneybraxton.android.tenniscounter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    int playerAScore = 0;
    int playerBScore = 0;

    int playerA_wonSets = 0;
    int playerB_wonSets = 0;

    int playerA_wonGames = 0;
    int playerB_wonGames = 0;

    int playerA_scoreboard = 0;
    int playerB_scoreboard = 0;

    //    Index of game along scoreboard [0 0 0 0 0 0]
    int gameRound = 0;
    //    Counting the max of 6 per game.
    int gameNumber = 0;

    //    Index of set out of [0 0 0]
    int setRound = 0;
    //    Counting the max of 3 total.
    int setNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public String checkForSetGameWin(int score) {
        if (score == 0) {
            return "15";
        } else if (score == 15) {
            return "30";
        } else if (score == 30) {
            return "40";
        }
        return "0";
    }

    public void updateWonGames(String playerIdentity) {
        TextView scoreboardTextView;
        String resetScoreboard = "0 0 0 0 0 0";
        // ranges 0 to 6
        gameNumber += 1;

        if (playerIdentity == "A") {
            scoreboardTextView = findViewById(R.id.player_A_scoreBoard);
            String scoreboardArray[] = scoreboardTextView.getText().toString().split(" ");

            playerA_scoreboard += 1;
            playerA_wonGames += 1;
            TextView wonGames = findViewById(R.id.player_A_won_games_int);
            wonGames.setText(String.valueOf(playerA_wonGames));

            if (gameRound == 6) {
                System.out.println(gameRound);
            }
            scoreboardArray[gameRound] = String.valueOf(playerA_scoreboard);
            scoreboardTextView.setText(convertObjectArrayToString(scoreboardArray, " "));
        }
        if (playerIdentity == "B") {
            scoreboardTextView = findViewById(R.id.player_B_scoreBoard);
            String scoreboardArray[] = scoreboardTextView.getText().toString().split(" ");

            playerB_scoreboard += 1;
            playerB_wonGames += 1;
            TextView wonSets = findViewById(R.id.player_B_won_games_int);
            wonSets.setText(String.valueOf(playerB_wonGames));

            scoreboardArray[gameRound] = String.valueOf(playerB_scoreboard);
            scoreboardTextView.setText(convertObjectArrayToString(scoreboardArray, " "));
        }
        if (gameNumber % 6 == 0) {
            gameRound += 1;
            if (gameRound == 6) {
                updateWonSets();
                gameRound = 0;
                scoreboardTextView = findViewById(R.id.player_A_scoreBoard);
                scoreboardTextView.setText(resetScoreboard);
                scoreboardTextView = findViewById(R.id.player_B_scoreBoard);
                scoreboardTextView.setText(resetScoreboard);
            }
            playerA_scoreboard = 0;
            playerB_scoreboard = 0;
        }
    }

    public void updateWonSets() {
        TextView setScoreView;
        if (playerA_wonGames > playerB_wonGames) {
            playerA_wonSets += 1;
            setScoreView = findViewById(R.id.player_A_won_sets_int);
            setScoreView.setText(String.valueOf(playerA_wonSets));
        } else {
            playerB_wonSets += 1;
            setScoreView = findViewById(R.id.player_B_won_sets_int);
            setScoreView.setText(String.valueOf(playerB_wonSets));
        }
        setNumber += 1;
    }

    private static String convertObjectArrayToString(Object[] arr, String delimiter) {
        // https://www.journaldev.com/773/java-string-array-to-string
        StringBuilder sb = new StringBuilder();
        for (Object obj : arr)
            sb.append(obj.toString()).append(delimiter);
        return sb.substring(0, sb.length() - 1);

    }

    public void displayForPlayerA(View view) {
        String gameUpdate = checkForSetGameWin(playerAScore);
        playerAScore = Integer.parseInt(gameUpdate);
        Button buttonText = findViewById(R.id.player_A_point);
        buttonText.setText("" + gameUpdate);
        if (playerAScore == 40) {
            updateWonGames("A");
        }
    }

    public void displayForPlayerB(View view) {
        String gameUpdate = checkForSetGameWin(playerBScore);
        playerBScore = Integer.parseInt(gameUpdate);
        Button buttonText = findViewById(R.id.player_B_point);
        buttonText.setText("" + gameUpdate);
        if (playerBScore == 40) {
            updateWonGames("B");
        }
    }

    public void displayCurrentScore(int score, String playerIdentity) {
        Button buttonText;
        if (playerIdentity == "A") {
            buttonText = findViewById(R.id.player_A_point);
        } else {
            buttonText = findViewById(R.id.player_B_point);
        }
        buttonText.setText("" + score);
    }

    public void resetScores(View view) {
        playerAScore = 0;
        playerBScore = 0;

        playerA_scoreboard = 0;
        playerB_scoreboard = 0;

        playerAScore = 0;
        displayCurrentScore(playerAScore, "A");
        playerBScore = 0;
        displayCurrentScore(playerBScore, "B");

        playerA_wonSets = 0;
        playerB_wonSets = 0;
        playerA_wonGames = 0;
        playerB_wonGames = 0;

        gameRound = 0;
        gameNumber = 0;
        setRound = 0;
        setNumber = 0;

        TextView generalTextView;
        String resetScoreboard = "0 0 0 0 0 0";

        generalTextView = findViewById(R.id.player_A_scoreBoard);
        generalTextView.setText(resetScoreboard);
        generalTextView = findViewById(R.id.player_B_scoreBoard);
        generalTextView.setText(resetScoreboard);

        generalTextView = findViewById(R.id.player_A_won_sets_int);
        generalTextView.setText("0");
        generalTextView = findViewById(R.id.player_B_won_sets_int);
        generalTextView.setText("0");

        generalTextView = findViewById(R.id.player_A_won_games_int);
        generalTextView.setText("0");
        generalTextView = findViewById(R.id.player_B_won_games_int);
        generalTextView.setText("0");
    }
}
