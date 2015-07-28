package brightedge_assignment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class test {
	
	@Test
	public void toTest(){
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
		
		//test pre-process work correctly
		contentProcess cp = null;
		cp = new casePreprocess();
		Assert.assertEquals("it is a test.", cp.preprocess("It is a TEST."));
		
		cp = new periodPreprocess();
		Assert.assertEquals("time ", cp.preprocess("time."));
		Assert.assertEquals("waitingduck.com", cp.preprocess("waitingduck.com"));
		
		cp = new spacePreprocess();
		Assert.assertEquals("it is a test.", cp.preprocess("it      is a     test."));
		
		cp = new specialCharacterPreprocess();
		Assert.assertEquals("it is a test             ", cp.preprocess("it is a test(){}[]!@#%^;:"));
		System.out.println("Preprocess works correctly");
		
		//test prioritizer work correctly
		prioritizeProcess pp = null;
		HashMap<String,Double> temp = new HashMap<String,Double>();
		temp.put("2015", 1.0);
		temp.put("8", 1.0);
		temp.put("test" ,1.0);
		temp.put("not test",1.0);
		temp.put("important",1.0);
		temp.put("to",1.0);
		
		List<String> temp_title = new ArrayList<String>();
		temp_title.add("test");
		
		pp = new numberPrioritizer();
		pp.setExtraScore(2.0);
		pp.prioritize(temp, null);
		Assert.assertEquals(0.0,temp.get("8"),0.0);
		Assert.assertEquals(2.0,temp.get("2015"),0.0);
		
		pp = new titlePrioritizer();
		pp.setExtraScore(2.0);
		pp.prioritize(temp, temp_title);
		Assert.assertEquals(2.0,temp.get("test"),0.0);
		Assert.assertEquals(1.0,temp.get("not test"),0.0);
		
		pp = new importantWordPrioritizer();
		pp.setExtraScore(2.0);
	    pp.prioritize(temp, null);
	    Assert.assertEquals(0.0,temp.get("to"),0.0);
		Assert.assertEquals(3.0,temp.get("important"),0.0);
		System.out.println("Prioritizer works correctly");
		System.out.println("Pass all unit test");
		
	}
}
