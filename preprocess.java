import java.util.ArrayList;
import java.util.List;



public class Preprocess {
	//load all the contentPreprocess 
		public String preprocess(String text){
			List<ContentPreprocess> preprocess_list = new ArrayList<ContentPreprocess>();
			preprocess_list.add(new SpecialCharacterPreprocess());
			preprocess_list.add(new PeriodPreprocess());
			preprocess_list.add(new SpacePreprocess());
			preprocess_list.add(new CasePreprocess());
			for(ContentPreprocess preprocess_rule:preprocess_list){
				text = preprocess_rule.preprocess(text);
			}
			return text;
		}
}
