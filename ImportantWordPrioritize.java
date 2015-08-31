import java.util.List;
import java.util.Map;


public class ImportantWordPrioritize implements PrioritizeProcess{
	private double extraScore;
	
	public ImportantWordPrioritize(){
		this(0.0);
	}
	
	public ImportantWordPrioritize(double score){
		extraScore = score;
	}
	
	//set prioritize score.
	@Override
	public void setExtraScore(double score) {
		extraScore = score;
	}
	
	private double getScore(double originalScore){
		return originalScore + extraScore;
	}

	//use list of unimportant word to decide the score. if it is an unimportant word, set its score to zero. 
	//Otherwise, give the extrascore to important word.
	@Override
	public void prioritize(Map<String, Double> map, List<String> dictionary) {
		for(String s:map.keySet()){
			String[] temp = s.split(" ");
			for(String word:temp){
				if(!dictionary.contains(word)){
					map.put(s,getScore(map.get(s)));
				}
				else{
					map.put(s, 0.0);
				}
			}
		}
	}
}