import java.util.Scanner;

/**
 * Recreating a dice game between two players. 
 * @author Thomas Nguyen
 * @version 1.0
 */
public class LofiProject 
{
	public static void main(String[] args) 
	{
		// Creating a scanner for user input
		Scanner scnr = new Scanner(System.in);
		
		Player playerOne = new Player();
		Player playerTwo = new Player();
		
		// Launching game
		twoPlayers(scnr, playerOne, playerTwo);
	}
	
	
	// Rolling Dice
	/**
	 * Emulates a dice roll
	 * @return
	 */
	public static int rollDice()
	{
		int roll = (int)Math.floor(Math.random() * 6) + 1;
		return roll;
	}
	
	/**
	 * Asks the user if they want to roll or end their turn
	 * @param scnr
	 */
	public static boolean turnMaker(Scanner scnr, Player player)
	{
		int roll;
		int score = 0;
		
		System.out.println("Please press y to roll or q to end your turn.");
		String input = scnr.nextLine();
		
		while (!input.equals("q"))
		{
			// Ensuring that the user inputs either q or y 
			if (!input.equals("y"))
			{
				System.out.println("Please enter either y or q.");
				input = scnr.nextLine();
			}
			else
			{	
				roll = pointTally();
				if (roll == 0)
				{
					System.out.println("Too bad!");
					return false;
				}
				else
				{
					score += roll;
				}
				
				System.out.println("Please press y to roll again or q to end your turn.");
				input = scnr.nextLine();
			}
		}

		player.addScore(score);
		return false;
	}
	
	
	/**
	 * Special rule that 1 ends a player's turn
	 * @return
	 */
	public static int pointTally()
	{
		int roll;
		
		roll = rollDice();
		System.out.println("You rolled a " + roll);
		
		if (roll == 1)
		{
			return 0;
		}
		else
		{
			return roll;
		}
	}
	
	
	public static boolean gameLaunch(Scanner scnr, Player player)
	{
		boolean turn = true;
		
		while (turn)
		{
			turn = turnMaker(scnr, player);
		}

		int scoreOne = player.getScore();

		System.out.println("You have " + scoreOne + " points!");
		
		return false;
	}
	
	
	public static void twoPlayers(Scanner scnr, Player playerOne, Player playerTwo)
	{
		int turn = 0;
		final int DEFAULT_POINTS = 100;
		
		// Introduction, asking for player names
		makeDashes("Hello! Welcome to the game. Before we begin, can I have the names of our competitors?");
		
		System.out.println("What would Player 1 like to be called?");
		playerOne.setName(scnr.nextLine());
		
		System.out.println("What would Player 2 like to be called?");
		playerTwo.setName(scnr.nextLine());
		
		String playerOneName = playerOne.getName();
		String playerTwoName = playerTwo.getName();
		
		System.out.println();
		makeDashes("Alrighty guys! How many points are we going to?");
		
		int points;
	
		// Referenced stack overflow to insist that user input an int
		while(!scnr.hasNextInt())
		{
			System.out.println("Please enter a number.");
			scnr.nextLine();
		}
		points = scnr.nextInt();

		if (points <= 0)
		{
			points = DEFAULT_POINTS;
		}
		
		System.out.println();
		makeDashes("Okay! We are playing to " + points + " points.");
		
		// Starting the game
		while (playerOne.getScore() < points && playerTwo.getScore() < points)
		{
			turn++;

			if (turn % 2 == 0)
			{
				System.out.println();
				System.out.println("It's your turn " + playerTwoName);
				gameLaunch(scnr, playerTwo);
			}
			else
			{
				System.out.println();
				System.out.println("It's your turn " + playerOneName);
				gameLaunch(scnr, playerOne);
			}
		}
		
		
		System.out.println();
		
		if (playerOne.getScore() > playerTwo.getScore())
		{
			makeDashes("Congratulations, " + playerOneName + " won!");
		}
		else
		{
			System.out.println("Congratulations, " + playerTwoName + " won!");
		}
		
		System.out.println("Here are the standings:");
		System.out.println(playerOneName + " has " + playerOne.getScore() + " points");
		System.out.println(playerTwoName + " has " + playerTwo.getScore() + " points");	
	}
	
	public static void makeDashes(String text)
	{
		int numDashes = text.length();
		String dashes = "";
		for (int i = 0; i < numDashes; ++i)
		{
			dashes += "-";
		}
		System.out.println(text);
		System.out.println(dashes);
	}
}
