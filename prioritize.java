import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;


public class Prioritize {
	//load all the prioritizeProcess
		public void prioritize(HashMap<String,Double> word_score,List<String> title_subset){
			//prioritize the word_score using different prioritizer.
			List<String> umimportantDict = new ArrayList<String>();
			try {
				Scanner txtReader = new Scanner(new File("src\\dict\\umimportant.txt"));
				while(txtReader.hasNextLine()){
					umimportantDict.add(txtReader.nextLine());
				}
				txtReader.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			PrioritizeProcess p = new ImportantWordPrioritize();
			p.setExtraScore(2.0);
			p.prioritize(word_score, umimportantDict);
			
			p = new TitlePrioritize();
			p.setExtraScore(3.0);
			p.prioritize(word_score, title_subset);
			
			p = new NumberPrioritize();
			p.setExtraScore(1.5);
			p.prioritize(word_score, null);
		}
}
