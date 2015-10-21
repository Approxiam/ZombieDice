
public class ConservativeBot extends Player {

	public String name;
	public int score;
	
	public ConservativeBot(String str){
		this.name = str;
		this.score = 0;
	}
	
	public boolean decide(int brains, int shots, int sc){
		if(shots > 0){
			System.out.println(name + " decided to stop.");
			return false;
		} else {
			System.out.println(name + " decided to roll again.");
			return true;
		}
	}
	
	public String toString(){
		return name + " (Conservative Bot)";
	}
}
