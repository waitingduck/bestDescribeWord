import java.util.List;
import java.util.Map;

public class NumberPrioritize implements PrioritizeProcess{
	
	private double extraScore = 1.0;
	
	@Override
	public void setExtraScore(double score) {
		this.extraScore = score;
	}
	
	private double raiseScore(double originalScore){
		return originalScore * extraScore;
	}

	//number between 1000~2050 is usually year, it will be considered important. Otherwise, it unimportant.
	@Override
	public void prioritize(Map<String, Double> map, List<String> list) {
		for(String s:map.keySet()){
			try {  
			    double d = Double.parseDouble(s);
			    if(d>=1000 && d<=2050){
			    	map.put(s,raiseScore(map.get(s)));
			    }else{
			    	map.put(s, 0.0);
			    }
			}catch(NumberFormatException e){  
			    continue;  
			}  
		}
		
	}

}
