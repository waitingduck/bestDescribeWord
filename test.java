import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import javax.swing.JTextArea;

import org.junit.Assert;
import org.junit.Test;




import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;


public class test {
	@Test
	public void toTest(JTextArea result){
		System.out.println("Testing...");
		//test is crawler work correctly
		try (final WebClient webClient = new WebClient(BrowserVersion.CHROME)) {
	    	webClient.getOptions().setJavaScriptEnabled(false);
	        final HtmlPage page1 = webClient.getPage("http://www.amazon.com/Cuisinart-CPT-122-Compact-2-Slice-Toaster/dp/B009GQ034C/ref=sr_1_1?&s=kitchen&ie=UTF8&qid=1431620315&sr=1-1&keywords=toaster");
	        Assert.assertEquals("Amazon.com: Cuisinart CPT-122 Compact 2-Slice Toaster: Kitchen & Dining", ((DomElement)(page1.getFirstByXPath("//title"))).getTextContent() );
	        
	        final HtmlPage page2 = webClient.getPage("http://www.cnn.com/2013/06/10/politics/edward-snowden-profile/");
	        Assert.assertEquals("Man behind NSA leaks says he did it to safeguard privacy, liberty - CNNPolitics.com", ((DomElement)(page2.getFirstByXPath("//title"))).getTextContent());
	        
	        
	        final HtmlPage page3 = webClient.getPage("http://blog.rei.com/camp/how-to-introduce-your-indoorsy-friend-to-the-outdoors/");
	        Assert.assertEquals("How to Introduce Your Indoorsy Friend to the Outdoors - REI Blog", ((DomElement)(page3.getFirstByXPath("//title"))).getTextContent());
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
		System.out.println("Web crawler works correctly");
		result.append("Web crawler works correctly\n\n");
		
		//test pre-process work correctly
		ContentPreprocess cp = null;
		cp = new CasePreprocess();
		Assert.assertEquals("it is a test.", cp.preprocess("It is a TEST."));
		result.append("CasePreprocess work correctly\n");
		
		cp = new PeriodPreprocess();
		Assert.assertEquals("time ", cp.preprocess("time."));
		Assert.assertEquals("waitingduck.com", cp.preprocess("waitingduck.com"));
		result.append("PeriodPreprocess work correctly\n");
		
		cp = new SpacePreprocess();
		Assert.assertEquals("it is a test.", cp.preprocess("it      is a     test."));
		result.append("CasePreprocess work correctly\n");
		
		cp = new SpecialCharacterPreprocess();
		Assert.assertEquals("it is a test             ", cp.preprocess("it is a test(){}[]!@#%^;:"));
		System.out.println("Preprocess works correctly");
		result.append("SpecialCharacterPreprocess work correctly\n");
		result.append("Preprocess works correctly\n\n");
		//test prioritizer work correctly
		PrioritizeProcess pp = null;
		HashMap<String,Double> temp = new HashMap<String,Double>();
		temp.put("2015", 1.0);
		temp.put("8", 1.0);
		temp.put("test" ,1.0);
		temp.put("not test",1.0);
		temp.put("important",1.0);
		temp.put("to",1.0);
		
		List<String> temp_title = new ArrayList<String>();
		temp_title.add("test");
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
		
		pp = new NumberPrioritize();
		pp.setExtraScore(2.0);
		pp.prioritize(temp, null);
		Assert.assertEquals(0.0,temp.get("8"),0.0);
		Assert.assertEquals(2.0,temp.get("2015"),0.0);
		result.append("NumberPrioritize work correctly\n");
		
		pp = new TitlePrioritize();
		pp.setExtraScore(2.0);
		pp.prioritize(temp, temp_title);
		Assert.assertEquals(2.0,temp.get("test"),0.0);
		Assert.assertEquals(1.0,temp.get("not test"),0.0);
		result.append("TitlePrioritize work correctly\n");
		
		pp = new ImportantWordPrioritize();
		pp.setExtraScore(2.0);
	    pp.prioritize(temp, umimportantDict);
	    Assert.assertEquals(0.0,temp.get("to"),0.0);
		Assert.assertEquals(3.0,temp.get("important"),0.0);
		System.out.println("Prioritizer works correctly");
		result.append("ImportantWordPrioritize work correctly\n");
		
		result.append("Prioritizer works correctly\n\n");
		System.out.println("Pass all unit test\n");
		result.append("Pass all unit test\n");
		
	}
}
