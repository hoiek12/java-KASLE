package test;

public class question {

	public int code;
	public float x,y;
	public float velocity;
	public boolean iscorrect=false;
	
	
	public question(int code,float x,float y,float velocity)
	{
		this.code=code;
		this.x=x;
		this.y=y;
		this.velocity=velocity;
	}
	public question(int code)
	{
		this.code=code;
	}
	
	
	
	public void movedown()
	{
		y=y-velocity;
	}
}
