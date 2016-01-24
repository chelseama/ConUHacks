import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HtmlParser {

	public static void main(String[] args) throws InterruptedException {

		Document doc;
		Document doc2;
		String webUrl = "http://www.curryupnow.com/";
		
		System.out.println("Beggining Scan of Website...");
		System.out.println("If you get Status 429 error, wait few minutes and try again\n");

		try {

			doc = Jsoup.connect(webUrl).get();
//Page title
			String title = doc.title();
			System.out.println("title: " + title);

//Links directly accessible from HomePage
			Elements links = doc.select("a[href]");

			TotalSitesVisited.totalSitesVisited++;
			int i = 0;
			ArrayList<Node> LevelOneNodes = new ArrayList<Node>();

			for (Element link : links) {

				// get the value from href attribute
				if (!link.text().isEmpty() && i < 3) {
					// Create nodes for graph (For now based on first 3 results)
					LevelOneNodes.add(new Node(link.text(), link.attr("href")));
					System.out.println("\n(" + (i+1) + ")" + "link : " + link.attr("href"));
					System.out.println("(" + (i+1) + ")" + "text : " + link.text());
				}
				i++;

			}

			//Normally we would scan through all the level one nodes, but for demo purposes we will only scan through one of them
			//for (Node node : LevelOneNodes) {
				//node.frequency++;
				//node.popularity = node.frequency / TotalSitesVisited.totalSitesVisited;

				TimeUnit.MILLISECONDS.sleep(1000);
				
				// Crawl through link
				try {
					
					//doc2 = Jsoup.connect(webUrl + node.url.substring(1)).get();
					//For demo we have hardcoded the url of the menu page
					doc2 = Jsoup.connect("http://www.hollandhousebarandrefuge.com/menu-marquee/#dinner").get();

					// get page title
					String title2 = doc2.title();
					System.out.println("\ntitle : " + title2 + "\n");

					Elements texts = doc2.select("div");
			
					ArrayList<String> al = new ArrayList<>();
					for (Element div : texts){
						if(!div.text().isEmpty()){
						al.add(div.text());
						}
					}
					
					// Removing Duplicates from the HTML code
					//Set<String> hs = new HashSet<>();
					//hs.addAll(al);
					//al.clear();
					//al.addAll(hs);
					
					//Initialize JSON object
					JSONObject obj = new JSONObject();

					//initialize hashmap
					HashMap<String,Integer> extractedWords = new HashMap<String,Integer>();
					
						String[] output = helperMethods.parseArrayList(al);
						

						System.out.println("\nPrinting an example of words we extracted from the 'MENU' page, after scanning \nMaximum of 500 words will be stored\n" );
						for(int i1 = 0; i1 < output.length; i1++){
							
							System.out.println((i1+1) + " : " + output[i1]);
				
						//Hash map for keeping count of reoccuring words
							for (int j = 0; j<500; j++) {
								if(extractedWords.containsValue(output[i1])){
									extractedWords.replace(output[i1], extractedWords.get(output[i1]), extractedWords.get(output[i1]));
								}
								else{
									extractedWords.put(output[j], 1);
								}
							}
						
					}
						obj.putAll(extractedWords);
						
						System.out.println("\n\nVery early JSON file example\n");
						System.out.println(obj);
						
				} catch (IOException e) {
					e.printStackTrace();
				}
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	


}
