package org.opencv.rps;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ReplayActivity extends Activity {
	
	//new
	DatabaseHelper helper=new DatabaseHelper(this);
	
	private Button playAgainButton;
	
	//new
	private Button saveButton;
	private static int userTotalScore = 0;
	private static int computerTotalScore = 0;

	@Override
    public void onCreate(Bundle savedInstanceState) {         

       super.onCreate(savedInstanceState);  
       Play();
   }
	
	private void Play(){
	
		Bundle b = getIntent().getExtras();
		int fingers = b.getInt("fingers");
        Choice userChoice;
    	switch (fingers){
    	case -1:
			userChoice = Choice.UNKNOWN;
			break;
    		
		case 0:
		case 1:
			userChoice = Choice.ROCK;
			break;

		case 2:
		case 3:
			userChoice = Choice.SCISSORS;
			break;

		default:
			userChoice = Choice.PAPER;
			break;
	}
		
		
	setContentView(R.layout.replay_view);
		
		final ImageView userChoiceView = (ImageView)findViewById(R.id.user_choice);
		userChoiceView.setImageResource(userChoice.getUserDrawable());
		
		int guess = b.getInt("guess");
		Log.d("Preetish", "Received Guess: " + Integer.toString(guess));
		int generated = (guess + 4)%3;
		
		Choice computerChoice = Choice.ROCK;
		switch (generated) {
			case 0: computerChoice = Choice.ROCK; break;
			case 1: computerChoice = Choice.PAPER; break;
			case 2: computerChoice = Choice.SCISSORS; break;
			default:
				computerChoice = Choice.UNKNOWN;
				break;
		}
		
		final ImageView computerChoiceView = (ImageView)findViewById(R.id.computer_choice);
		computerChoiceView.setImageResource(computerChoice.getComputerDrawable());
		
		String result = CalculateResult(userChoice, computerChoice);
		final TextView resultView = (TextView)findViewById(R.id.result);
		resultView.setText(result);
		
		final TextView userTotalScoreView = (TextView)findViewById(R.id.user_total_result);
		userTotalScoreView.setText(" " + Integer.toString(userTotalScore) + "   ");
		final TextView computerTotalScoreView = (TextView)findViewById(R.id.computer_total_result);
		computerTotalScoreView.setText(" " + Integer.toString(computerTotalScore));
		
		playAgainButton = (Button) findViewById(R.id.play_again_button);
		//new
		saveButton = (Button) findViewById(R.id.btn_game_save);
		
		playAgainButton.setOnClickListener(new OnClickListener()
	    {
		  @Override
	      public void onClick(View v)
	      {
			  CallActivity();
	      }
	   });
		
		//new
		saveButton.setOnClickListener(new OnClickListener()
	    {
		  @Override
	      public void onClick(View v)
	      {
			  Intent intent = getIntent();
          	  Bundle bunde = intent.getExtras();  
              
              
              String uname = bunde.getString("uname");  
              String currentStage = bunde.getString("stageChosen");
          	helper.modifyContact(uname, currentStage);
//          	AlertDialog.dialog = new AlertDialog.Builder(this)
//          			.setMessage("Saved successfully!")
//          			.setPositiveButton("OK",null)
//          			.show();
	      }
	   });
	}	
	
	private void CallActivity(){
		Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
        finish();
	}
	
	private String CalculateResult(Choice userChoice, Choice computerChoice){
		
		if (userChoice == Choice.UNKNOWN)
			return "No Choice";		if (userChoice == computerChoice) 
			return "It's a Tie!";		
	
		int module = (userChoice.getValue() - computerChoice.getValue() + 3) % 3;
		if (module == 1){
			userTotalScore++;
			return "You Won!";
		}
			
		computerTotalScore++;
		return "You Lost!";
	}	
}

