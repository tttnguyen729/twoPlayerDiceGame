
public class Player 
{
	private int score = 0;
	private String name = "NoName";
	
	public void addScore(int score)
	{
		this.score += score;
	}
	
	public int getScore()
	{
		return score;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
}
