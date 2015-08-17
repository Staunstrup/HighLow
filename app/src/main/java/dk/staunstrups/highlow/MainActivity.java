package dk.staunstrups.highlow;

import android.app.Activity;
import android.os.Bundle;
//import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	static private TextView OutView, Current;
	static private RadioButton Left, Right;
	private boolean gameover = false;
	private HighLowGame g;
	private GameResult result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		OutView = (TextView) findViewById(R.id.textView1);	
		Current = (TextView) findViewById(R.id.CurrentCard);
		Left = (RadioButton) findViewById(R.id.Left);
		Right = (RadioButton) findViewById(R.id.Right);
		
		g= new HighLowGame();
		result= new GameResult();
		
		Current.setText(g.newGame().res+"\nWill the next card be higher or lower?"); 
		gameover= false;
			
		final OnClickListener radioListener = new OnClickListener() {
			@Override
			public void onClick(View v){
				RadioButton rb = (RadioButton) v;
				char c= rb.getText().charAt(0);
				if (gameover) {
					if (c== 'Y') {  // start new game
						gameover= false;
						Current.setText(g.newGame().res+"\nWill the next card be higher or lower?");
						Left.setText("Higher");
						Right.setText("Lower");		
					} else {
						finish();
					}										
				} else {
					//OutView.append("First char: "+c+" ");
					result= g.gameIteration(c);
					Current.setText(result.res +"\n" );
					if (result.gameover) {
						gameover= true;
						Current.append("Do you want to plan another game?");
						Left.setText("Yes");
						Right.setText("No");
					}
				}
			}
		};

		final RadioButton low = (RadioButton) findViewById(R.id.Left);
		low.setOnClickListener(radioListener);
		
		final RadioButton high = (RadioButton) findViewById(R.id.Right);
		high.setOnClickListener(radioListener);
	}
}
