package brightedge_assignment;

import java.util.List;
import java.util.Map;

public class titlePrioritizer implements prioritizeProcess{
	private double extraScore=1.0;
	
	@Override
	public void setExtraScore(double score) {
		extraScore = score;
	}
	
	//if the word or string is in the title or in the anagram of title, it will be considered as important.
	@Override
	public void prioritize(Map<String, Double> map, List<String> list) {
		for(String word:map.keySet()){
			if(list.contains(word)){
				map.put(word, map.get(word)*extraScore);
			}
		}
		
	}

}
