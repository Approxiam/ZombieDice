import java.util.*;


public class ZombieDice {
	
	public static final List<Player> players = new ArrayList<Player>();
	public static List<Die> cup = new ArrayList<Die>();
	public static List<Die> setCurrent = new ArrayList<Die>();
	public static List<Die> setTemp = new ArrayList<Die>();
	public static Random random = new Random();
	public static Scanner input = new Scanner(System.in);
	public static int recklessWins = 0;
	
	public static void fillCup(){
		cup.clear();
		for(int i = 0; i < 6; i++){
			Die D = new Die(3,2,1,"green"+i);
			cup.add(D);
		}
		for(int i = 0; i < 4; i++){
			Die D = new Die(2,2,2,"yellow"+i);
			cup.add(D);
		}
		for(int i = 0; i < 3; i++){
			Die D = new Die(1,2,3,"red"+i);
			cup.add(D);
		}
	}
	
	public static Die nextDie(){
		Die D = null;
		if(setCurrent.size() > 0){
			D = setCurrent.get(0);
			setCurrent.remove(0);
		} else if(cup.size() > 0) {
			D = cup.get(0);
			cup.remove(0);
		} else {
			fillCup();
			Collections.shuffle(cup, random);
			D = cup.get(0);
			cup.remove(0);
		}
		return D;
	}
	
	public static void playRound(Player p){
		int brains = 0;
		int shots = 0;
		boolean reroll = true;
		while(reroll){
			for(int i = 0; i < 3; i++){
				Die D = nextDie();
				String str = D.roll(random);
				System.out.println("The chosen die is " + D.name + ", after rolling it shows " + str);
				if(str.equals("footsteps")){
					setTemp.add(D);
				}
				if(str.equals("brain")){
					brains++;
				}
				if(str.equals("shot")){
					shots++;
				}
			}
			setCurrent = new ArrayList<Die>(setTemp);
			setTemp.clear();
			System.out.println("You have " + brains + " brains, and you are at " + shots + " shots.\n");
			if(shots > 2){
				System.out.println("Your killing spree has come to an end.\n");
				break;
			}
			reroll = p.decide(brains, shots, p.score);
			if(!reroll){
				p.score += brains;
			}
		}
		System.out.println("Your score is " + p.score + "\n");
	}
	
	public static void playGame(){
		boolean gameOver = false;
		while(!gameOver) {
			System.out.println("A new round begins.");
			for(int i = 0; i < players.size(); i++){
				Player player = players.get(i);
				System.out.println(player + "'s score is " + player.score);
			}
			for(int i = 0; i < players.size(); i++){
				Player player = players.get(i);
				playRound(player);
				if(player.score > 12){
					gameOver = true;
				}
			}
		}
	}
	
	public static void declareWinner(){
		int winnerIndex = 0;
		int maxScore = 0;
		for(int i = 0; i < players.size(); i++){
			Player player = players.get(i);
			System.out.println(player + " has scored " + player.score + " points.");
			if(player.score > maxScore){
				maxScore = player.score;
				winnerIndex = i;
			}
		}
		/*
		if(winnerIndex == 0){
			recklessWins++;
		}
		*/
		Player player = players.get(winnerIndex);
		System.out.println("The winner is " + player);
	}
	
	public static void addPlayers(){
		while(true){
			System.out.println("What kind of player do you want to add?"
					+"\n 1: Live player."
					+"\n 2: Reckless Bot (aims for at least 5 score per round)."
					+"\n 3: Conservative Bot (stops at the first shot)."
					+"\n 4: Reasonable Bot."
					+"\n 0: Finish.");
			int result;
			do {
				while (!input.hasNextInt()) {
					input.next();
				}
				result = input.nextInt();
			} while (!(0 <= result && result <= 4));
			if(result == 0){
				break;
			} else {
				constructPlayer(result);
			}
			
		}
	}
	
	public static void constructPlayer(int id){
		System.out.println("What's the name of this player?");
		String str = input.next();
		switch(id){
			case 1: players.add(new LivePlayer(str));
				break;
			case 2: players.add(new RecklessBot(str));
				break;
			case 3: players.add(new ConservativeBot(str));
				break;
			case 4: players.add(new ReasonableBot(str));
				break;
			default: break;
		}
	}
	
	public static void cleanUp(){
		fillCup();
		setCurrent.clear();
		setTemp.clear();
		for(int i = 0; i < players.size(); i++){
			Player player = players.get(i);
			player.score = 0;
		}
	}
	
	public static void main(String[] args){
		/*
		players.add(new ResonableBot("Steve"));
		players.add(new RecklessBot("Bill"));
		players.add(new ConservativeBot("Paul"));
		*/
		/*
		addPlayers();
		playGame();
		declareWinner();
		*/
		///*
		players.add(new LivePlayer("Test"));
		playGame();
		//*/
		//cleanUp();
		//System.out.println(players.get(0)+ " won " +recklessWins + " times.");
	}

}
