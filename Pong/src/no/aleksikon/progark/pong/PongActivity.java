package no.aleksikon.progark.pong;

import sheep.game.Game;
import android.os.Bundle;
import android.app.Activity;
import android.view.Display;
import android.view.Menu;

public class PongActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pong);
		
		Game game = new Game(this, null);
		Display display = getWindowManager().getDefaultDisplay();
		
		game.pushState(new GameState(display));
		
		setContentView(game);
	}

}
