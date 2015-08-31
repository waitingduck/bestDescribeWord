import java.util.HashMap;
import java.util.List;


public interface ResultProcess {
	public void setThreshold(double threshold);
	public List<String> getResult(HashMap<String,Double> map);
}
