//Not currently being used, can we changed into a tree structure later
public class Node {

	public final String name;
	public final String url;
	public int frequency;
	public double popularity;
    
    public Node(String name, String url) {
      this.name = name;
      this.url = url;
      this.frequency = 0;
      this.popularity = 0;
	
}
}