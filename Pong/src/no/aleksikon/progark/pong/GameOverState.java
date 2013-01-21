package no.aleksikon.progark.pong;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Typeface;
import android.view.Display;
import android.view.MotionEvent;
import sheep.game.State;
import sheep.game.World;
import sheep.graphics.Color;
import sheep.graphics.Font;
import sheep.input.TouchListener;


/**
 * @author Aleks Gisvold
 *
 */
public class GameOverState extends State implements TouchListener{

	private Paint paint;
	private World world = new World();
	private Font font;
	private int winner;
	private Display display;
	
	/**
	 * Constructor. Add the player-paddles and the ball to the canvas
	 */
	public GameOverState(int winner, Display display)
	{
		this.winner = winner;
		this.display = display;
		Color col = new Color(0,0,0);
		paint = new Paint(col);
		paint.setStyle(Style.FILL);
		font = new Font(255, 255, 255, 30, Typeface.SERIF, Typeface.NORMAL);
	}
	
	
	public void draw(Canvas canvas)
	{
		canvas.drawPaint(paint);
		font.setTextAlign(Align.CENTER);
		canvas.drawText("Game Over", getGame().getWidth()/2, getGame().getHeight()/2, font);
		canvas.drawText("Player "+winner+ "won!", getGame().getWidth()/2, (getGame().getHeight()/2)+30, font);
		canvas.drawText("Touch the screen to start a new game", getGame().getWidth()/2, (getGame().getHeight()/2)+100, font);
		
		world.draw(canvas);
	}
	
	public void update(float dt)
	{
		world.update(dt);
	}
	
	public boolean onTouchMove(MotionEvent event)
	{
		getGame().popState();
		getGame().pushState(new GameState(display));
		return true;
	}
}
