package brightedge_assignment;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class importantWordPrioritizer implements prioritizeProcess{
	private double extraScore=0.0;
	
	//set prioritize score.
	@Override
	public void setExtraScore(double score) {
		extraScore = score;
	}
	
	
	
	//use list of unimportant word to decide the score. if it is an unimportant word, set its score to zero. 
	//Otherwise, give the extrascore to important word.
	@Override
	public void prioritize(Map<String, Double> map, List<String> list) {
		String[] unimportantWord = {"he","she","this","those","what","where","who","it","when","how","why",
                "what's","it's","he's","she's","a","an","are","is","were","was","many",
                "much","very","as","&","will","not","may","might","with","the","to","and",
                "of","by","i","on","get","you","your","mine","his","her","him","hers",
                "for","that","out","from","in","at","most","about","do","did","have","has",
                "all","after","before","our","or","behind","-","never","shall","should",
                "too","go","take","be","been","went","keep","like","say","said","says",
                "over","some","its","us","we","they","more","makes","no","non","add","minus",
                "up","down","left","right","please","get","got"};
		list = Arrays.asList(unimportantWord);
		for(String s:map.keySet()){
			String[] temp = s.split(" ");
			for(String word:temp){
				if(!list.contains(word)){
					map.put(s,map.get(s)+extraScore);
				}
				else{
					map.put(s, 0.0);
				}
			}
		}
	}
}
