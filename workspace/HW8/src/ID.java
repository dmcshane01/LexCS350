import java.util.Random;


public class ID {

	Random r = new Random();
	private int x;
	private int y;
	
	public ID()
	{
		x = r.nextInt(2000001);
		y = r.nextInt(2000001);
	}
	
	public ID(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	
	public boolean equals(ID comp)
	{
		
		return (comp.x == this.x) && (comp.y == this.y);
	}
	
	
}
