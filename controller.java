import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.swing.JTextArea;

import com.gargoylesoftware.htmlunit.html.HtmlPage;


public class Controller {
	public void start(String URL, JTextArea resultText, JTextArea topText){
		
		if(URL.length()==0){
			System.out.println("No input detected.");
			resultText.append("No input detected.");
		}
		//process input
		Double threshold = 0.85;
		
		if(URL.equals("test")){
			test t = new test();
			t.toTest(resultText);
		}
		
		//This part will crawl the whole page content
		Crawler c = new Crawler();
		HtmlPage page = null;
		try{
			page = c.getPage(URL);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//pre-process the title and content.
		Preprocess pp = new Preprocess();
		String title = pp.preprocess(c.getTitle(page));
		
		String content = pp.preprocess(c.getContent(page));
		//After pre-process, we split the content to array.For the combination of some word, I chose the substring which appear in the subset to title.
		//split text
		List<String> title_word_list = Arrays.asList(title.split(" "));		
		List<String> content_word_list = Arrays.asList(content.split(" "));

		
		ToolFunction tf = new ToolFunction();
		//find subset
		List<String> title_subset = tf.findSubSet(title_word_list);
        
		HashMap<String,Double> word_score = tf.generateWordScore(content_word_list, title_subset, title);
		
		//prioritize word_score
        Prioritize p = new Prioritize();
        p.prioritize(word_score, title_subset);
        
        //get result
		System.out.println("Words that best describe the article:");
		
		ResultProcess1 rp = new ResultProcess1(threshold);
		
		rp.getResult(word_score, resultText, topText);
	}
}
