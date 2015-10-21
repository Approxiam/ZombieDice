
public class ReasonableBot extends Player {

	public String name;
	public int score;
	
	public ReasonableBot(String str){
		this.name = str;
		this.score = 0;
	}
	
	public boolean decide(int brains, int shots, int sc){
		if(brains == 0){
			System.out.println(name + " decided to roll again.");
			return true;
		}
		if((brains + score > 12) || (shots == 2)) {
			System.out.println(name + " decided to stop.");
			return false;
		} else {
			System.out.println(name + " decided to roll again.");
			return true;
		}
	}
	
	public String toString(){
		return name + " (Reasonable Bot)";
	}
}
