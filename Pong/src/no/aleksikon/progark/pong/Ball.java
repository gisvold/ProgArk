package no.aleksikon.progark.pong;

import sheep.game.Sprite;
import sheep.graphics.Image;

public class Ball extends Sprite 
{
	private Image image;
	private int spriteWidth, spriteHeight;
	static private Ball BallInstance = null;
	
	protected Ball(Image img)
	{
		super(img);
		this.image = img;
		spriteWidth = (int) image.getWidth();
		spriteHeight = (int) image.getHeight();
	}
	
	public static Ball instance(Image instanceImg)
	{
		if(null == BallInstance)
		{
			BallInstance = new Ball(instanceImg);
		}
		return BallInstance;
	}
	
	public int getWidth()
	{
		return spriteWidth;
	}
	
	public int getHeight()
	{
		return spriteHeight;
	}
}
