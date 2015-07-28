package brightedge_assignment;


import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class controller {
	public static void main(String Args[]){
		
		if(Args.length==0){
			System.out.println("No input detected.");
			System.exit(0);
		}
		//process input
		String URL=Args[0];
		Double threshold = 0.85;
		if(Args.length==2){
			try{
				threshold = Double.parseDouble(Args[1]);
			}catch(Exception e){
				System.out.println("Second parameter is not double, threshold will use default setting.");
			}	
		}
		if(threshold<0.0 || threshold>1.0){
			System.out.println("Second parameter is not in the rage of [0.0, 1.0], threshold will use default setting.");
			threshold = 0.9;
		}
		if(URL.equals("test")){
			test t = new test();
			t.toTest();
			System.exit(0);
		}
		
		//This part will crawl the whole page content
		crawler c = new crawler();
		HtmlPage page = null;
		try{
			page = c.getPage(URL);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		//pre-process the title and content.
		preprocess pp = new preprocess();
		String title = pp.preprocess(c.getTitle(page));
		String content = pp.preprocess(c.getContent(page));
		
		//After pre-process, we split the content to array.For the combination of some word, I chose the substring which appear in the subset to title.
		//split text
		List<String> title_word_list = Arrays.asList(title.split(" "));		
		List<String> content_word_list = Arrays.asList(content.split(" "));

		
		toolFunction tf = new toolFunction();
		//find subset
		List<String> title_subset = tf.findSubSet(title_word_list);
        
		HashMap<String,Double> word_score = tf.generateWordScore(content_word_list, title_subset, title);
		
		//prioritize word_score
        prioritize p = new prioritize();
        p.prioritize(word_score, title_subset);
        
        //get result
		System.out.println("Words that best describe the article:");
		resultProcess rp = new resultProcess1();
		rp.setThreshold(threshold);
		List<String> result = rp.getResult(word_score);
		for(String r:result){
        	System.out.println(r);
        }
	}
}
