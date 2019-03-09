package com.example.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class GameActivity extends AppCompatActivity {

    String mWord;
    int mFailCounter = 0;
    int mGuessedLetters =0;
    int mPoints = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        setRandomWord();
        Log.d("MYLOG","The word is"+mWord);
    }

    /**
     * Takes the letter entered in editTextLetter
     * @param v (from clicking the button)
     */

    public void introduceLetter(View v){
        EditText myEditText = (EditText) findViewById(R.id.editTextLetter);
        String letter = myEditText.getText().toString();
        myEditText.setText("");

        Log.d("MY LOG", "The letter is "+letter);

        if (letter.length()==1){

            checkLetter(letter.toUpperCase());
        }
        else{
            Toast.makeText(this,"Please Introduce a letter!", Toast.LENGTH_SHORT).show();
            Log.d("MY LOG","Player didn't introduce letter");
        }
    }

    /**
     * Checking if the entered letter is matching the letter in the word or not
     * @param introducedLetter, letter entered by the player
     */

    public void checkLetter(String introducedLetter){

        boolean letterGuessed = false;

        char charIntroduced = introducedLetter.charAt(0);
        for(int i=0;i<mWord.length();i++){
            char charFromWord = mWord.charAt(i);
            if (charFromWord == charIntroduced){
                Log.d("MY LOG","There is one match");
                letterGuessed = true;
                showLettersIndex(i, charIntroduced);

                mGuessedLetters++;
            }
        }
        if (letterGuessed == false){
            letterFailed(Character.toString(charIntroduced));
        }

        if (mGuessedLetters == mWord.length()){
            mPoints++;
            clearScreen();

        }
    }

    public void clearScreen(){
        TextView textViewFailed = (TextView) findViewById(R.id.textView6);
        textViewFailed.setText("");

        mGuessedLetters=0;
        mFailCounter =0;

        LinearLayout layoutLetters = (LinearLayout) findViewById(R.id.layoutLetters);

        for(int i=0; i < layoutLetters.getChildCount();i++){
            TextView currentTextView = (TextView) layoutLetters.getChildAt(i);
            currentTextView.setText("_");
        }
        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        imageView.setImageResource(R.drawable.hangmanid1);
    }




    /**
     * Displaying the letters that have matched at their index
     * @param position
     * @param letterGuessed
     */

    public void showLettersIndex(int position, char letterGuessed){
        LinearLayout layoutLetter = (LinearLayout)findViewById(R.id.layoutLetters);
        TextView textView = (TextView) layoutLetter.getChildAt(position);
        textView.setText(Character.toString(letterGuessed));
    }

    public void letterFailed(String letterFailed){

        TextView textViewFailed = (TextView) findViewById(R.id.textView6);
        String previousFail = textViewFailed.getText().toString();
        textViewFailed.setText(previousFail+letterFailed);

        mFailCounter = mFailCounter+1;
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        if (mFailCounter==1){
            imageView.setImageResource(R.drawable.hangmanid2);
        }
        else if(mFailCounter == 2){
            imageView.setImageResource(R.drawable.hangmanid3);
        }
        else if(mFailCounter == 3){
            imageView.setImageResource(R.drawable.hangmanid4);
        }
        else if(mFailCounter == 4){
            imageView.setImageResource(R.drawable.hangmanid5);
        }
        else if(mFailCounter == 5){
            imageView.setImageResource(R.drawable.hangmanid6);
        }
        else if(mFailCounter == 6){
            imageView.setImageResource(R.drawable.hangmanid7);
        }
        else if(mFailCounter == 7){
            Intent gameOverINtent = new Intent(this, GameOver.class);
            gameOverINtent.putExtra("POINTS_IDENTIFIER", mPoints);
            startActivity(gameOverINtent);
        }
    }


    public void setRandomWord(){

        String words = "JAZZ BUZZ NIKE JUMP JUNK CASH QUIZ FLUX GAZE KICK MUCH LESS THIS THAT WHAT";
        String[] arrayWords = words.split(" ");
        int randomNumber =  (int)(Math.random()*arrayWords.length);
        String randomWord = arrayWords[randomNumber];
        mWord = randomWord;
        Log.d("MYLOG","The word is"+mWord);
        //TODO set a value to mWord
    }
}
