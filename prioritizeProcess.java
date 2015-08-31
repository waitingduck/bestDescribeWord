import java.util.List;
import java.util.Map;


public interface PrioritizeProcess {
	public void setExtraScore(double score);
	public void prioritize(Map<String,Double> map, List<String> list);
}
