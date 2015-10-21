import java.util.Random;

public class Die {

	public final String[] sides = new String[6];
	public final String name;
	
	public Die(int brains, int footsteps, int shots, String str){
		this.name = str;
		for(int i = 0; i < 6; i++){
			if(i < brains){
				sides[i] = "brain";
			} else if(i < brains + footsteps){
				sides[i] = "footsteps";
			} else {
				sides[i] = "shot";
			}
		}
	}
	
	public String roll(Random random){
		int index = random.nextInt(6);
		return sides[index];
	}
	
}
