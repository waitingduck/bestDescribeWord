package brightedge_assignment;

public class spacePreprocess implements contentProcess{
	//remove extra space.
	@Override
	public String preprocess(String text) {
		return text.replaceAll("\\s+", " ");
	}
	
}
