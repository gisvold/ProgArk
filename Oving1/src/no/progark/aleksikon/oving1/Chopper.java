package no.progark.aleksikon.oving1;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.DisplayMetrics;
import sheep.game.Sprite;



/**
 * Heli object
 * @author Aleks Gisvold
 *
 */
public class Chopper extends Sprite {
	
	private Bitmap bitmap;
	private int frameNr, currentFrame, framePeriod;
	private int spriteWidth, spriteHeight;
	private int x, y;
	private long frameTicker;
	private Rect hitbox;
	private int id, direction = 0;
	
	/**
	 * Constructor that sets the frame and coordinates, also initializing animation variables
	 * 
	 */
	public Chopper(Bitmap bitmap, int x, int y, int frameCount)
	{
		this.bitmap = bitmap;
		this.x = x;
		this.y = y;
		currentFrame = 0;
		frameNr = frameCount;
		framePeriod = 100;
		spriteWidth = bitmap.getWidth() / frameCount;
		spriteHeight = bitmap.getHeight();
		hitbox = new Rect(0, 0, spriteWidth, spriteHeight);
		this.setShape(spriteWidth, spriteHeight);
		frameTicker = 01;
	}
	
	public void update(long dt)
	{
		if(dt > frameTicker + framePeriod)
		{
			frameTicker = dt;
			//increment frame, in order to update
			currentFrame++;
			if(currentFrame >= frameNr)
			{
				currentFrame = 0;
			}
		}
		//define the rectangle in order to draw sprite
		this.hitbox.left = currentFrame * spriteWidth;
		this.hitbox.right = this.hitbox.left + spriteWidth;
	}
	
	@Override
	public void draw(Canvas canvas)
	{
		setX(getX() + getSpeed().getX()/100);
		setY(getY() + getSpeed().getY()/100);
		
		Rect destRect = new Rect((int) getX(), (int) getY(), (int) getX() + spriteWidth, (int) getY() + spriteHeight);
				canvas.drawBitmap(bitmap, hitbox, destRect, null);
	}
	
	/**
	 * Set the new x position
	 */
	public void setY(float f)
	{
		this.y = (int) f;
	}
	
	public void setX(float f)
	{
		this.x = (int) f;
	}
	
	public void setPosition(float x, float y)
	{
		this.x = (int) x;
		this.y = (int) y;
	}
	
	public float getX()
	{
		return this.x;
	}
	
	public float getY()
	{
		return this.y;
	}
	
	/**
	 * Returning chopper width
	 */
	public int getWidth()
	{
		return spriteWidth;
	}
	
	/**
	 * Returning chopper height
	 */
	public int getHeight()
	{
		return spriteHeight;
	}
	
	/**
	 * Returning the chopper ID as an integer
	 */
	public int getId()
	{
		return id;
	}
	
	/**
	 * Sets the chopper ID
	 */
	public void setId(int id)
	{
		this.id = id;
	}
	
	/**
	 * A method for changing the way the sprite is pointing, to make it look correct
	 */
	public void flip()
	{
		Matrix m = new Matrix();
		m.preScale(-1, 1);
		Bitmap turnMap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), m, false);
		turnMap.setDensity(DisplayMetrics.DENSITY_DEFAULT);
		bitmap = new BitmapDrawable(turnMap).getBitmap();
		
		if(direction == 0)
		{
			direction = 1;
		} else if (direction == 1)
		{
			direction = 0;
		}
	}
	
	public Rect getHitbox()
	{
		return new Rect((int)getX(), (int)getY(), (int)getX() + spriteWidth, (int)getY() + spriteHeight);
	}
	
	/**
	 * A method that returns the facing direction of the chopper, where 0 is heading left and 1 is heading right
	 */
	public int getDirection()
	{
		return this.direction;
	}
}

