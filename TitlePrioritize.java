import java.util.List;
import java.util.Map;

public class TitlePrioritize implements PrioritizeProcess{
	private double extraScore=1.0;
	
	@Override
	public void setExtraScore(double score) {
		extraScore = score;
	}
	
	private double raiseScore(double originalScore){
		return originalScore * extraScore;
	}
	
	//if the word or string is in the title or in the subset of title, 
	//it will be considered as important word.
	@Override
	public void prioritize(Map<String, Double> map, List<String> list) {
		for(String word:map.keySet()){
			if(list.contains(word)){
				map.put(word,raiseScore(map.get(word)));
			}
		}
		
	}

}
