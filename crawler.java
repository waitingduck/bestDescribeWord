package brightedge_assignment;

import java.util.List;

import org.junit.Assert;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

public class crawler {
	
	//crawl the target web page, default setting is using chrome. Return the htmlPage.
	public HtmlPage getPage(String URL) throws Exception{
		try(final WebClient webClient = new WebClient(BrowserVersion.CHROME)){
			webClient.getOptions().setJavaScriptEnabled(false);
			webClient.getOptions().setCssEnabled(false);
			final HtmlPage page = webClient.getPage(URL);
			return page;
		}
	}
	
	//return title of htmlPage
	public String getTitle(HtmlPage page){
		return page.getTitleText();
	}

	//return text of htmlPage.
	public String getContent(HtmlPage page) {
		return page.asText();
	}
}

