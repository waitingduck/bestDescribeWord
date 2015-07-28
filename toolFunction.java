package brightedge_assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class toolFunction {
	
	//This function will produce a list of subset of input list.
	public List<String> findSubSet(List<String> list){
		List<String> res = new ArrayList<>();
        res.add("");

        for(String word : list) {
        	List<String> temp = new ArrayList<String>();
            for(String seq : res) {
            	temp.add((seq.equals("")? word:seq+" "+word));
            }
            res.addAll(temp);
        }
        return res;
	}
	
	//This function will generate word_score hashtable. Currently, it will add every single word into the hashtable. If the
	//word sequence of content is a subset of title, it will be put into hashtable also.
	public HashMap<String,Double> generateWordScore(List<String> content_word_list, List<String> title_subset, String title){
		HashMap<String,Double> word_score = new HashMap<String,Double>();
		for(int i=0;i<content_word_list.size();i++){
			String temp = content_word_list.get(i);
			//Put the single word.
			if(word_score.containsKey(temp)){
				word_score.put(temp, word_score.get(temp)+1.0);
			}else{
				word_score.put(temp,1.0);
			}
			
			//Put the subset of title if it appears in content.
			for(int j=1;j<title.split(" ").length && i+j<content_word_list.size();j++){
				temp= temp + " " + content_word_list.get(i+j);
				if(title_subset.contains(temp)){
					if(word_score.containsKey(temp)){
						word_score.put(temp, word_score.get(temp)+1.0);
					}else{
						word_score.put(temp,1.0);
					}
				}else{
					break;
				}
			}
		}
		return word_score;
	}
}
