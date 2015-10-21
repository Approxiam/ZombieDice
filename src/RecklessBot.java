
public class RecklessBot extends Player {

	public String name;
	public int score;
	
	public RecklessBot(String str){
		this.name = str;
		this.score = 0;
	}
	
	public boolean decide(int brains, int shots, int sc){
		if(brains > 4){
			System.out.println(name + " decided to stop.");
			return false;
		} else {
			System.out.println(name + " decided to roll again.");
			return true;
		}
	}
	
	public String toString(){
		return name + " (Reckless Bot)";
	}
}
