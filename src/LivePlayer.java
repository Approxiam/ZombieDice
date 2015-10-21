import java.util.Scanner;

public class LivePlayer extends Player {

	public String name;
	public int score;
	public final Scanner input = new Scanner(System.in);
	
	public LivePlayer(String str){
		this.name = str;
		this.score = 0;
	}
	
	public boolean decide(int brains, int shots, int sc){
		int scr = sc+brains;
		System.out.println("Score is " + sc);
		System.out.println(
				name
				+ ", do you want to roll again?"
				+ "\n 1: Yes"
				+ "\n 0: No (your score would be "
				+ scr
				+ ")"
				);
		while (!input.hasNextInt()) {
			input.next();
		}
		int answer = input.nextInt();
		if(answer == 0){
			return false;
		} else {
			return true;
		}
	}
	
	public String toString(){
		return name;
	}
}
