package brightedge_assignment;

import java.util.ArrayList;
import java.util.List;

public class preprocess {
	//load all the contentPreprocess 
	public String preprocess(String text){
		List<contentProcess> preprocess_list = new ArrayList<contentProcess>();
		preprocess_list.add(new specialCharacterPreprocess());
		preprocess_list.add(new periodPreprocess());
		preprocess_list.add(new spacePreprocess());
		preprocess_list.add(new casePreprocess());
		for(contentProcess preprocess_rule:preprocess_list){
			text = preprocess_rule.preprocess(text);
		}
		return text;
	}
}
