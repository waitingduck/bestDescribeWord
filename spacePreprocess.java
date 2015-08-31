
public class SpacePreprocess implements ContentPreprocess{
	//remove extra space.
	@Override
	public String preprocess(String text) {
		return text.replaceAll("\\s+", " ");
	}
}

