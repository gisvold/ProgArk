package no.progark.aleksikon.oving1;

import java.util.ArrayList;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.view.MotionEvent;

import sheep.game.State;
import sheep.graphics.Color;
import sheep.graphics.Font;
import sheep.input.TouchListener;


/**
 * @author Aleks Gisvold
 *
 */
public class GameState extends State implements TouchListener 
{
	private ArrayList<Chopper> choppers = new ArrayList<Chopper>();
	private int canvasHeight, canvasWidth, chopperWidth, chopperHeight;
	private int chopperCount = 3; //Number of choppers on screen
	private Paint paint;
	
	public GameState(Resources resources)
	{
		Bitmap beatMap = BitmapFactory.decodeResource(resources, R.drawable.helisprite);
		
		for(int i = 0; i<chopperCount; i++)
		{
			choppers.add(new Chopper(beatMap, 100, (i*300)+50, 4));
			choppers.get(i).setId(i);
			choppers.get(i).setSpeed(100, 100);
		}
	}
	
	public void draw(Canvas canvas)
	{
		Color c = new Color(254, 0, 254);
		paint = new Paint(c);
		paint.setStyle(Style.FILL);
		canvas.drawPaint(Color.BLACK);
		canvasHeight = canvas.getHeight();
		canvasWidth = canvas.getWidth();
		
		for(int i=0; i<chopperCount; i++)
		{
			choppers.get(i).draw(canvas);
			
			canvas.drawText("Position chopper "+i+": ("+choppers.get(i).getX()+","+choppers.get(i).getY()+")", 0, ((i+1)*20), Font.BLUE_SANS_BOLD_20);
			
			if(choppers.get(i).getDirection()==0)
			{
				canvas.drawText(i+"", choppers.get(i).getX()+65, choppers.get(i).getY()+30, Font.WHITE_SANS_BOLD_20);
			} else if(choppers.get(i).getDirection() == 1)
			{
				canvas.drawText(i+"", choppers.get(i).getX()+95, choppers.get(i).getX()+30, Font.WHITE_SANS_BOLD_20);
			}
		}
	}
	
	public void update(float dt)
	{
		chopperWidth = choppers.get(0).getWidth();
		chopperHeight = choppers.get(0).getHeight();
		
		for(Chopper chopper : choppers)
		{
			if(chopper.getX()>(canvasWidth-chopperWidth) || chopper.getX()<0)
			{
				chopper.setSpeed(-chopper.getSpeed().getX(), chopper.getSpeed().getY());
			}
			if(chopper.getY()>(canvasHeight-chopperHeight) || chopper.getY()<0)
			{
				chopper.setSpeed(chopper.getSpeed().getX(), -chopper.getSpeed().getY());
			}
			for(Chopper chopperCollision : choppers)
			{
				if(!chopperCollision.equals(chopper))
				{
					if(chopperCollision.getHitbox().intersect(chopper.getHitbox()))
					{
						chopper.setSpeed((chopper.getX()-chopperCollision.getX())*5, (chopper.getY()-chopperCollision.getY())*5);	
					}
				}
			}
			
			if((chopper.getSpeed().getX() > 0 && chopper.getDirection() == 0) || (chopper.getSpeed().getX() < 0 && chopper.getDirection() == 1))
			{
				chopper.flip();
			}
			chopper.update(System.currentTimeMillis());
		}
	}
	
	public boolean onTouchMove(MotionEvent event)
	{
		if(event.getX()<(canvasWidth-chopperWidth) && event.getX()>(0+chopperHeight))
		{
			if(event.getY()<(canvasHeight-chopperHeight) && event.getY()>(0+chopperHeight))
			{
				for(Chopper chop : choppers)
				{
					Rect touchRect = new Rect(chop.getHitbox().left+50, chop.getHitbox().top+50, chop.getHitbox().right+50, chop.getHitbox().bottom+50);
					if(touchRect.contains((int) event.getX(), (int) event.getY()))
					{
						chop.setPosition(event.getX(), event.getY());
					}
				}
			return true;
			}
		}
		return false;
	}
}


