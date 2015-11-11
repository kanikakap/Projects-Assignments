package Project_AI;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.query.ResultSetFormatter;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.util.FileManager;


/**
 * @author kanikakapoor
 *
 */
public class FindMyEvent {

	static String source, destination;
	static ArrayList<String> eventNameAl= new ArrayList<String>();
	static String[] value=null;
	static String[] eventNameArray=null;
	
	/**
	 * @param name
	 */
	static void moodOfUser(String name) {
		Model model = FileManager
				.get()
				.loadModel(
						"/Users/kanikakapoor/Desktop/Protege-AI/myontology/AI_project.owl");
		System.out.println("Here is the list of Events ");

		String queryString = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
				+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
				+ "PREFIX my: <http://www.semanticweb.org/kanikakapoor/ontologies/2015/4/untitled-ontology-18#>"
				+ "\n"
				+ "SELECT ?EventName"
				+ "\n"
				+ "WHERE "
				+ "{ ?bi "
				+ " my:attend ?EventName." + "?bi rdf:type my:" + name + "}";

		Query query = QueryFactory.create(queryString);
		QueryExecution qexec = QueryExecutionFactory.create(query, model);
		ResultSet Results = qexec.execSelect();
		ResultSetFormatter.out(System.out, Results, query);

		// oncampus or off campus
		Scanner inaa = new Scanner(System.in);
		System.out.println("Do you want to attend oncampus event or offcampus");
		System.out.println("Press Number");
		System.out.println("1. OnCampus");
		System.out.println("2. OffCampus");
		int choicelocation = inaa.nextInt();

		System.out.println("Do you want to have Food at Event");
		System.out.println("Press Number");
		System.out.println("1. Yes");
		System.out.println("2. No");
		int choicefood = inaa.nextInt();

		// oncampus and yes foood
		if (choicelocation == 1 && choicefood == 1) {
			String queryString1 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
					+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
					+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
					+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
					+ "PREFIX my: <http://www.semanticweb.org/kanikakapoor/ontologies/2015/4/untitled-ontology-18#>"
					+ "\n"
					+ "SELECT ?EventName ?hasFood"
					+ "\n"
					+ "WHERE "
					+ "{ ?bi my:attend ?EventName."
					+ "?bi rdf:type my:"
					+ name
					+ "."
					+ "?EventName rdf:type my:OnCampus."
					+ "?EventName my:has_Food ?hasFood."
					+ "FILTER(?hasFood = true)}";

			Query query1 = QueryFactory.create(queryString1);
			QueryExecution qexec1 = QueryExecutionFactory.create(query1, model);
			ResultSet Results1 = qexec1.execSelect();
			ResultSetFormatter.out(System.out, Results1, query1);
			 query1 = QueryFactory.create(queryString1);
			 qexec1 = QueryExecutionFactory.create(query1, model);
			 Results1 = qexec1.execSelect();
			while (Results1.hasNext()) {
				QuerySolution qr = Results1.next();
				value = qr.toString().split("#");
				eventNameArray = value[1].split(">");
				eventNameAl.add(eventNameArray[0]);
			}
		}

		// off campus and yes food
		else if (choicelocation == 2 && choicefood == 1) {
			String queryString1 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
					+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
					+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
					+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
					+ "PREFIX my: <http://www.semanticweb.org/kanikakapoor/ontologies/2015/4/untitled-ontology-18#>"
					+ "\n"
					+ "SELECT ?EventName ?hasFood"
					+ "\n"
					+ "WHERE "
					+ "{ ?bi  my:attend ?EventName."
					+ "?bi rdf:type my:"
					+ name
					+ "."
					+ "?EventName rdf:type my:OffCampus."
					+ "?EventName my:has_Food ?hasFood ."
					+ "FILTER(?hasFood = true)}";

			Query query1 = QueryFactory.create(queryString1);
			QueryExecution qexec1 = QueryExecutionFactory.create(query1, model);
			ResultSet Results1 = qexec1.execSelect();
			ResultSetFormatter.out(System.out, Results1, query1);
			query1 = QueryFactory.create(queryString1);
			 qexec1 = QueryExecutionFactory.create(query1, model);
			 Results1 = qexec1.execSelect();
			while (Results1.hasNext()) {
				QuerySolution qr = Results1.next();
				value = qr.toString().split("#");
				eventNameArray = value[1].split(">");
				eventNameAl.add(eventNameArray[0]);
			}
		}

		// oncampus and no food
		else if (choicelocation == 1 && choicefood == 2) {
			String queryString1 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
					+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
					+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
					+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
					+ "PREFIX my: <http://www.semanticweb.org/kanikakapoor/ontologies/2015/4/untitled-ontology-18#>"
					+ "\n"
					+ "SELECT ?EventName ?hasFood"
					+ "\n"
					+ "WHERE "
					+ "{"
					+ "?bi my:attend ?EventName."
					+ "?bi rdf:type my:"
					+ name
					+ "."
					+ "?EventName rdf:type my:OnCampus."
					+ "?EventName my:has_Food ?hasFood ."
					+ "FILTER(?hasFood= false)}";

			Query query1 = QueryFactory.create(queryString1);
			QueryExecution qexec1 = QueryExecutionFactory.create(query1, model);
			ResultSet Results1 = qexec1.execSelect();
			ResultSetFormatter.out(System.out, Results1, query1);
			query1 = QueryFactory.create(queryString1);
			 qexec1 = QueryExecutionFactory.create(query1, model);
			 Results1 = qexec1.execSelect();
			while (Results1.hasNext()) {
				QuerySolution qr = Results1.next();
				value = qr.toString().split("#");
				eventNameArray = value[1].split(">");
				eventNameAl.add(eventNameArray[0]);
			}
		}

		// offcampus and no food
		else if (choicelocation == 2 && choicefood == 1) {
			String queryString1 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
					+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
					+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
					+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
					+ "PREFIX my: <http://www.semanticweb.org/kanikakapoor/ontologies/2015/4/untitled-ontology-18#>"
					+ "\n"
					+ "SELECT ?EventName ?hasFood"
					+ "\n"
					+ "WHERE "
					+ "{ ?bi my:attend ?EventName."
					+ "?bi rdf:type my:"
					+ name
					+ "."
					+ "?EventName rdf:type my:OffCampus."
					+ "?EventName my:has_Food ?hasFood ."
					+ "FILTER(?hasFood = false)}";

			Query query1 = QueryFactory.create(queryString1);
			QueryExecution qexec1 = QueryExecutionFactory.create(query1, model);
			ResultSet Results1 = qexec1.execSelect();
			ResultSetFormatter.out(System.out, Results1, query1);
			query1 = QueryFactory.create(queryString1);
			 qexec1 = QueryExecutionFactory.create(query1, model);
			 Results1 = qexec1.execSelect();
			while (Results1.hasNext()) {
				QuerySolution qr = Results1.next();
				value = qr.toString().split("#");
				eventNameArray = value[1].split(">");
				eventNameAl.add(eventNameArray[0]);
			}
		} else {
			System.out.println("Invalid");
			System.exit(0);
		}
		
		Scanner line1 = new Scanner(System.in);
		System.out.println("Which event you want to attend?");
		System.out.println("Press Number");
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		for (int i = 1; i < eventNameAl.size()+1; i++) {
			System.out.println(""+i+". "+eventNameAl.get(i-1));
			map.put(i-1, eventNameAl.get(i-1));
		}
		int sourceId = line1.nextInt();
		
		String eventName = map.get(sourceId-1);
		 destination =getDestination(eventName);
		System.out.println("Your destination is: "+destination);
		
		
		Scanner line2 = new Scanner(System.in);
		System.out.println("Select heurisitc");
		System.out.println("Press Number");
		System.out.println("1. Straight Line Distance");
		System.out.println("2. Straight Line Distance + Hops ");
		
		int heuristic = line2.nextInt();
		usingAStar(source,destination,heuristic);
		
		
		
	}
	
	/**
	 * @param source
	 * @param destination
	 * @param heuristic
	 */
	public static void usingAStar(String source,String destination,int heuristic){
		
		HashMap<String, Double> mapofSource = new HashMap<String, Double>();
		HashMap<String, Double> node1Map = new HashMap<String, Double>();
		String hops= "0.0,5.0,5.0,4.0,4.0,5.0,4.0,4.0";
		String distances="0.0,50.0,150.0,1050.0,550.0,750.0,2250.0,2450.0";
		String[] hop = hops.split(",");
		String[] distance = hops.split(",");
		String[] heuristicApplied=null;
		if(heuristic == 1)
				{		
			heuristicApplied = distance;
		}
		else if(heuristic == 2){
			heuristicApplied = hop;
		}
		node1Map.put("ErikJonsson", Double.parseDouble(heuristicApplied[0].toString()));
		node1Map.put("GreenHall", Double.parseDouble(heuristicApplied[1].toString()));
		node1Map.put("ClarkCenter", Double.parseDouble(heuristicApplied[2].toString()));
		node1Map.put("JSOM", Double.parseDouble(heuristicApplied[3].toString()));
		node1Map.put("McDermottLibrary", Double.parseDouble(heuristicApplied[4].toString()));
		node1Map.put("ActivityCenter", Double.parseDouble(heuristicApplied[5].toString()));
		node1Map.put("FoundersNorth", Double.parseDouble(heuristicApplied[6].toString()));
		node1Map.put("ATEC", Double.parseDouble(heuristicApplied[7].toString()));
		
		HashMap<String, Double> node2Map = new HashMap<String, Double>();
		 hops= "6.0,0.0,4.0,2.0,3.0,4.0,3.0,5.0";
		 distances="50.0,0.0,250.0,950.0,450.0,850.0,2350.0,2550.0";
		 hop = hops.split(",");
		distance = hops.split(",");
		heuristicApplied=null;
		if(heuristic == 1)
				{		
			heuristicApplied = distance;
		}
		else if(heuristic == 2){
			heuristicApplied = hop;
		}
		node2Map.put("ErikJonsson", Double.parseDouble(heuristicApplied[0].toString()));
		node2Map.put("GreenHall", Double.parseDouble(heuristicApplied[1].toString()));
		node2Map.put("ClarkCenter", Double.parseDouble(heuristicApplied[2].toString()));
		node2Map.put("JSOM", Double.parseDouble(heuristicApplied[3].toString()));
		node2Map.put("McDermottLibrary", Double.parseDouble(heuristicApplied[4].toString()));
		node2Map.put("ActivityCenter", Double.parseDouble(heuristicApplied[5].toString()));
		node2Map.put("FoundersNorth", Double.parseDouble(heuristicApplied[6].toString()));
		node2Map.put("ATEC", Double.parseDouble(heuristicApplied[7].toString()));

		HashMap<String, Double> node3Map = new HashMap<String, Double>();
		hops= "5.0,4.0,0.0,5.0,4.0,5.0,5.0,3.0";
		 distances="150.0,250.0,0.0,1250.0,750.0,550.0,2050.0,2650.0";
		 hop = hops.split(",");
		distance = hops.split(",");
		heuristicApplied=null;
		if(heuristic == 1)
				{		
			heuristicApplied = distance;
		}
		else if(heuristic == 2){
			heuristicApplied = hop;
		}
		node3Map.put("ErikJonsson", Double.parseDouble(heuristicApplied[0].toString()));
		node3Map.put("GreenHall", Double.parseDouble(heuristicApplied[1].toString()));
		node3Map.put("ClarkCenter", Double.parseDouble(heuristicApplied[2].toString()));
		node3Map.put("JSOM", Double.parseDouble(heuristicApplied[3].toString()));
		node3Map.put("McDermottLibrary", Double.parseDouble(heuristicApplied[4].toString()));
		node3Map.put("ActivityCenter",Double.parseDouble(heuristicApplied[5].toString()));
		node3Map.put("FoundersNorth", Double.parseDouble(heuristicApplied[6].toString()));
		node3Map.put("ATEC", Double.parseDouble(heuristicApplied[7].toString()));

		HashMap<String, Double> node4Map = new HashMap<String, Double>();
		hops= "4.0,2.0,5.0,0.0,2.0,4.0,5.0,6.0";
		 distances="1050.0,950.0,1250.0,0.0,1450.0,1850.0,3350.0,3550.0";
		 hop = hops.split(",");
		distance = hops.split(",");
		heuristicApplied=null;
		if(heuristic == 1)
				{		
			heuristicApplied = distance;
		}
		else if(heuristic == 2){
			heuristicApplied = hop;
		}
		node4Map.put("ErikJonsson", Double.parseDouble(heuristicApplied[0].toString()));
		node4Map.put("GreenHall", Double.parseDouble(heuristicApplied[1].toString()));
		node4Map.put("ClarkCenter", Double.parseDouble(heuristicApplied[2].toString()));
		node4Map.put("JSOM",Double.parseDouble(heuristicApplied[3].toString()));
		node4Map.put("McDermottLibrary", Double.parseDouble(heuristicApplied[4].toString()));
		node4Map.put("ActivityCenter", Double.parseDouble(heuristicApplied[5].toString()));
		node4Map.put("FoundersNorth", Double.parseDouble(heuristicApplied[6].toString()));
		node4Map.put("ATEC", Double.parseDouble(heuristicApplied[7].toString()));

		HashMap<String, Double> node5Map = new HashMap<String, Double>();
		hops= "4.0,3.0,4.0,2.0,0.0,4.0,5.0,3.0";
		 distances="550.0,450.0,750.0,1450.0,0.0,1350.0,2150.0,3150.0";
		 hop = hops.split(",");
		distance = hops.split(",");
		heuristicApplied=null;
		if(heuristic == 1)
				{		
			heuristicApplied = distance;
		}
		else if(heuristic == 2){
			heuristicApplied = hop;
		}
		node5Map.put("ErikJonsson", Double.parseDouble(heuristicApplied[0].toString()));
		node5Map.put("GreenHall", Double.parseDouble(heuristicApplied[1].toString()));
		node5Map.put("ClarkCenter", Double.parseDouble(heuristicApplied[2].toString()));
		node5Map.put("JSOM", Double.parseDouble(heuristicApplied[3].toString()));
		node5Map.put("McDermottLibrary", Double.parseDouble(heuristicApplied[4].toString()));
		node5Map.put("ActivityCenter", Double.parseDouble(heuristicApplied[5].toString()));
		node5Map.put("FoundersNorth", Double.parseDouble(heuristicApplied[6].toString()));
		node5Map.put("ATEC", Double.parseDouble(heuristicApplied[7].toString()));

		HashMap<String, Double> node6Map = new HashMap<String, Double>();
		hops= "5.0,4.0,5.0,4.0,4.0,0.0,4.0,3.0";
		 distances="750.0,850.0,550.0,1850.0,1350.0,0.0,1450.0,2450.0";
		 hop = hops.split(",");
		distance = hops.split(",");
		heuristicApplied=null;
		if(heuristic == 1)
				{		
			heuristicApplied = distance;
		}
		else if(heuristic == 2){
			heuristicApplied = hop;
		}
		node6Map.put("ErikJonsson", Double.parseDouble(heuristicApplied[0].toString()));
		node6Map.put("GreenHall", Double.parseDouble(heuristicApplied[1].toString()));
		node6Map.put("ClarkCenter", Double.parseDouble(heuristicApplied[2].toString()));
		node6Map.put("JSOM", Double.parseDouble(heuristicApplied[3].toString()));
		node6Map.put("McDermottLibrary", Double.parseDouble(heuristicApplied[4].toString()));
		node6Map.put("ActivityCenter", Double.parseDouble(heuristicApplied[5].toString()));
		node6Map.put("FoundersNorth", Double.parseDouble(heuristicApplied[6].toString()));
		node6Map.put("ATEC", Double.parseDouble(heuristicApplied[7].toString()));

		HashMap<String, Double> node7Map = new HashMap<String, Double>();
		hops= "4.0,3.0,5.0,5.0,5.0,4.0,0.0,4.0";
		 distances="2250.0,2350.0,2050.0,3350.0,2150.0,1450.0,0.0,950.0";
		 hop = hops.split(",");
		distance = hops.split(",");
		heuristicApplied=null;
		if(heuristic == 1)
				{		
			heuristicApplied = distance;
		}
		else if(heuristic == 2){
			heuristicApplied = hop;
		}
		node7Map.put("ErikJonsson", Double.parseDouble(heuristicApplied[0].toString()));
		node7Map.put("GreenHall", Double.parseDouble(heuristicApplied[1].toString()));
		node7Map.put("ClarkCenter", Double.parseDouble(heuristicApplied[2].toString()));
		node7Map.put("JSOM", Double.parseDouble(heuristicApplied[3].toString()));
		node7Map.put("McDermottLibrary",Double.parseDouble(heuristicApplied[4].toString()));
		node7Map.put("ActivityCenter", Double.parseDouble(heuristicApplied[5].toString()));
		node7Map.put("FoundersNorth",Double.parseDouble(heuristicApplied[6].toString()));
		node7Map.put("ATEC", Double.parseDouble(heuristicApplied[7].toString()));

		HashMap<String, Double> node8Map = new HashMap<String, Double>();
		hops= "4.0,5.0,3.0,6.0,3.0,3.0,4.0,0.0";
		 distances="2450.0,2550.0,2650.0,3550.0,3150.0,2450.0,950.0,0.0";
		 hop = hops.split(",");
		distance = hops.split(",");
		heuristicApplied=null;
		if(heuristic == 1)
				{		
			heuristicApplied = distance;
		}
		else if(heuristic == 2){
			heuristicApplied = hop;
		}
		node8Map.put("ErikJonsson", Double.parseDouble(heuristicApplied[0].toString()));
		node8Map.put("GreenHall", Double.parseDouble(heuristicApplied[1].toString()));
		node8Map.put("ClarkCenter", Double.parseDouble(heuristicApplied[2].toString()));
		node8Map.put("JSOM", Double.parseDouble(heuristicApplied[3].toString()));
		node8Map.put("McDermottLibrary", Double.parseDouble(heuristicApplied[4].toString()));
		node8Map.put("ActivityCenter", Double.parseDouble(heuristicApplied[5].toString()));
		node8Map.put("FoundersNorth", Double.parseDouble(heuristicApplied[6].toString()));
		node8Map.put("ATEC", Double.parseDouble(heuristicApplied[7].toString()));
		
		if(source.equals("ErikJonsson")){
			mapofSource  = node1Map;
		}
		else if(source.equals("GreenHall")){
			mapofSource  = node2Map;
		}
		else if(source.equals("ClarkCenter")){
			mapofSource  = node3Map;
		}
		else if(source.equals("JSOM")){
			mapofSource  = node4Map;
		}
		else if(source.equals("McDermottLibrary")){
			mapofSource  = node5Map;
		}
		else if(source.equals("ActivityCenter")){
			mapofSource  = node6Map;
		}
		else if(source.equals("FoundersNorth")){
			mapofSource  = node7Map;
		}
		else if(source.equals("ATEC")){
			mapofSource  = node8Map;
		}
		
		//Ref: https://raymondrchua.wordpress.com/2013/12/21/a-star-search-algorithm-java-implementation/
		
		Map<String, Node> mappingSourceDestination = new HashMap<String, Node>();
		
		
		//initialize the Graph for N1 to N8 straight line distance
		Node n1 = new Node("ErikJonsson", mapofSource.get("ErikJonsson"));
		Node n2= new Node("GreenHall", mapofSource.get("GreenHall"));
		Node n3= new Node("ClarkCenter",mapofSource.get("ClarkCenter"));
		Node n4 = new Node("JSOM", mapofSource.get("JSOM"));
		Node n5 = new Node("McDermottLibrary", mapofSource.get("McDermottLibrary")); 
		Node n6 = new Node("ActivityCenter", mapofSource.get("ActivityCenter"));
		Node n7 = new Node("FoundersNorth", mapofSource.get("FoundersNorth"));
		Node n8 = new Node("ATEC", mapofSource.get("ATEC"));
		
		mappingSourceDestination.put("ErikJonsson", n1);
		mappingSourceDestination.put("GreenHall", n2);
		mappingSourceDestination.put("ClarkCenter", n3);
		mappingSourceDestination.put("JSOM", n4);
		mappingSourceDestination.put("McDermottLibrary", n5);
		mappingSourceDestination.put("ActivityCenter", n6);
		mappingSourceDestination.put("FoundersNorth", n7);
		mappingSourceDestination.put("ATEC", n8);
	
		//initialize the edges
		
		
		//ErikJonsonn
		n1.adjacencies = new Edge[]
				{
				
				new Edge(n2,100),
				new Edge(n3,200),
				};
		
		//GreenHall
		n2.adjacencies=new Edge[]
				{
				new Edge(n1, 100),
				new Edge(n4, 1000),
				new Edge(n5,500)
				};
		
		//ClarkCenter
		n3.adjacencies=new Edge[]
				{
				new Edge(n1, 200),
				new Edge(n6, 600),
				};
		
		//JSOM
		n4.adjacencies=new Edge[]
				{
				new Edge(n2, 1000),
				new Edge(n5, 2000),
				};
		
		//McDermottLibrary
		n5.adjacencies=new Edge[]
				{
				new Edge(n2, 500),
				new Edge(n4, 2000),
				new Edge(n7,2200)
				};
		
		//ActivityCenter
		n6.adjacencies=new Edge[]
				{
				new Edge(n3, 600),
				new Edge(n7, 1500)
				};
		
		//FoundersNorth
		n7.adjacencies=new Edge[]
				{
				new Edge(n5, 2200),
				new Edge(n8, 1000),
				new Edge(n6,1500)
				};
		
		//ATEC
		n8.adjacencies=new Edge[]
				{
				new Edge(n1, 2500),
				new Edge(n7, 1000)
				};
		
	
		AStarSearchAlgo(mappingSourceDestination.get(source),mappingSourceDestination.get(destination));
		
		
		//print path from n1 to n8
		List<Node> path=printPath(mappingSourceDestination.get(destination));
		System.out.println("Path" +path);
		
	}
	
	public static List<Node> printPath(Node target)
	{
		List<Node> path = new ArrayList<Node>();
		
		for(Node node = target; node!=null ; node=node.parent)
		{
			path.add(node);
		}
		
		Collections.reverse(path);
		
		return path;
	}
	
	public static void AStarSearchAlgo(Node source, Node goal)
	{
		Set<Node> explored = new HashSet<Node>();
		PriorityQueue<Node> queue = new PriorityQueue<Node> (20, new Comparator<Node>(){
			//override compare Method
			
			public int compare(Node i , Node j)
			{
				if(i.f_scores > j.f_scores)
				{
					return 1;
				}
				
				else if( i.f_scores < j.f_scores)
				{
					return -1;
				}
				
				else
				{
					return 0;
				}
			}
		}
		);
		
		//cost from start
		source.g_scores=0;
		
		queue.add(source);
		
		boolean found = false;
		
		while((!queue.isEmpty()) && (!found))
		{
			//the node in having the lowest f_score value
			Node current = queue.poll();
			
			explored.add(current);
			
			//goal found
			if(current.value.equals(goal.value))
			{
				found = true;
			}
			
			//check every child of current node
			for(Edge e : current.adjacencies)
			{
				Node child = e.target;
				double cost = e.cost;
				double temp_g_scores = current.g_scores + cost;
				double temp_f_scores = temp_g_scores + child.h_scores;
				
				/*if child node has been evaluated and 
                the newer f_score is higher, skip*/
				
				if((explored.contains(child)) && (temp_f_scores >= child.f_scores))
				{
					continue;
				}
				
				 /*else if child node is not in queue or 
                newer f_score is lower*/
                
                else if((!queue.contains(child)) || 
                        (temp_f_scores < child.f_scores)){

                        child.parent = current;
                        child.g_scores = temp_g_scores;
                        child.f_scores = temp_f_scores;

                        if(queue.contains(child)){
                                queue.remove(child);
                        }

                        queue.add(child);
			}
		}
	}

}
	
	public static String getDestination(String eventName){
		Model model = FileManager
				.get()
				.loadModel(
						"/Users/kanikakapoor/Desktop/Protege-AI/myontology/AI_project.owl");
		
		String queryString2 = "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n"
				+ "PREFIX owl: <http://www.w3.org/2002/07/owl#>\n"
				+ "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n"
				+ "PREFIX xsd: <http://www.w3.org/2001/XMLSchema#>\n"
				+ "PREFIX my: <http://www.semanticweb.org/kanikakapoor/ontologies/2015/4/untitled-ontology-18#>"
				+ "\n"
				+ "SELECT ?bi "
				+ "\n"
				+ "WHERE "
				+ "{ ?bi my:conducts my:"+ eventName+ "}";
				
		Query query1 = QueryFactory.create(queryString2);
		QueryExecution qexec1 = QueryExecutionFactory.create(query1, model);
		ResultSet Results1 = qexec1.execSelect();
		ResultSetFormatter.out(System.out, Results1, query1);
		query1 = QueryFactory.create(queryString2);
		 qexec1 = QueryExecutionFactory.create(query1, model);
		 Results1 = qexec1.execSelect();
		while (Results1.hasNext()) {
			QuerySolution qr = Results1.next();
			value = qr.toString().split("#");
			eventNameArray = value[1].split(">");
			destination = eventNameArray[0].toString();
		}
		
		
		
		return destination;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		String personName;
		Scanner scannerObject = new Scanner(System.in);

		// Reads a single line from the console
		// and stores into personname variable

		System.out.println("Hey ! How are you doing");
		System.out.println("May I know your name ");
		personName = scannerObject.nextLine();

		Scanner line = new Scanner(System.in);
		System.out.println("Where is your current location?");
		System.out.println("Press Number");
		System.out.println("1. Activity Center");
		System.out.println("2. ATEC");
		System.out.println("3. Clark Center");
		System.out.println("4. Erik Jonsonn");
		System.out.println("5. Founders North");
		System.out.println("6. Greenhall");
		System.out.println("7. JSOM");
		System.out.println("8. McDermott Library");
		int sourceId = line.nextInt();
		
		switch (sourceId) {
		case 1:
			source="ActivityCenter";
			break;
		case 2:
			source="ATEC";
			break;

		case 3:
			source="ClarkCenter";
			break;
		case 4:
			source="ErikJonsonn";
			break;
		case 5:
			source="FoundersNorth";
			break;
		case 6:
			source="GreenHall";
			break;
		case 7:
			source="JSOM";
			break;

		case 8:
			source="McDermottLibrary";
			break;

		default:
			break;
		}

		System.out
				.println("Hi "
						+ personName
						+ " Welcome to 'Find my Event' Application. \n"
						+ "Today I will route you to your favorable event depending upon your mood !");
		

		System.out.println("So How are you feeling today ?");
		System.out.println("Press any number");
		System.out.println("1. Bored ");
		System.out.println("2. Studious ");
		System.out.println("3. Helpful ");
		System.out.println("4. Peaceful ");
		System.out.println("5. Happy ");
		System.out.println("6. Neutral ");
		String str;
		int choice = scannerObject.nextInt();

		switch (choice) {
		case 1:
			str = "Bored";
			moodOfUser(str);
			
			break;

		case 2:
			str = "Studious";
			moodOfUser(str);
			
			break;

		case 3:
			str = "Helpful";
			moodOfUser(str);
			
			break;

		case 4:
			str = "Peaceful";
			moodOfUser(str);
		
			break;

		case 5:
			str = "Happy";
			moodOfUser(str);
		
			break;

		case 6:
			str = "Neutral";
			moodOfUser(str);
			
			break;

		default:
			System.out.println("Invalid");
			System.exit(0);
			break;
		}

		
	}
}
