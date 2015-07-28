package brightedge_assignment;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class prioritize {
	//load all the prioritizeProcess
	public void prioritize(HashMap<String,Double> word_score,List<String> title_subset){
		//prioritize the word_score using different prioritizer.
		prioritizeProcess p = new importantWordPrioritizer();
		p.setExtraScore(2.0);
		p.prioritize(word_score, null);
		
		p = new titlePrioritizer();
		p.setExtraScore(3.0);
		p.prioritize(word_score, title_subset);
		
		p = new numberPrioritizer();
		p.setExtraScore(1.5);
		p.prioritize(word_score, null);
	}
}
