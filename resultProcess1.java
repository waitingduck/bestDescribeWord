package brightedge_assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class resultProcess1 implements resultProcess{

	private double threshold = 0.0;
	
	@Override
	public void setThreshold(double threshold) {
		this.threshold = threshold;
	}
    
	//To preprocess period. Replace period that come with a space to space. 
	//For example, “time. “ will become “time  ”,”Amazon.com” will still be “Amazon.com”
	@Override
	public List<String> getResult(HashMap<String, Double> map) {
		double max = 0;
		double min = Double.MAX_VALUE;
		
		for(String s:map.keySet()){
			max = Math.max(max, map.get(s));
			min = Math.min(min, map.get(s));
		}
		double thresholdScore = ((max-min)*(1-threshold))+min;
		List<String> result = new ArrayList<String>();
		for(String s:map.keySet()){
			if(map.get(s)>=thresholdScore){
				result.add(s);
			}
		}
		
		return result;
		
	}
	
}
