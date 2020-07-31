package com.mastercard.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class MasterCardService {

	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	/* HashMap to store the neighbouring cities in a HashSet */
	private Map<String, HashSet<String>> map = new HashMap<>();
	
	/* Will check if the cities are already visited by using a HashMap - visited */
	private Map<String, Boolean> visited = new HashMap<>();

	/* Method to check if there is a connection between origin and destination. */
	public String getConnection(String origin, String destination) {
		
		for (String city : visited.keySet()) {
			visited.put(city, false);
		}
		
		/* recursively calling dfs method for each node. */
		if (dfs(origin, destination, map, visited)) {
			return "yes";
		}

		return "no";
	}

	/*
	 * Searching for the path if the cities are connected (recursively, depth first
	 * search)
	 */
	private boolean dfs(String source, String target, Map<String, HashSet<String>> map, Map<String, Boolean> visited) {
		
		/*
		 * If source and destination are not present in the map then there will be no
		 * route
		 */
		if (!map.containsKey(source) || !map.containsKey(target))
			return false;

		/*
		 * If a city contains the destination in its neighbours HashSet then we have a
		 * path
		 */
		if (map.get(source).contains(target))
			return true;

		/*
		 * If we haven't already visited the node then we visit the node.
		 */		
		if (visited.get(source) == false) {
			//Marking the node as visited.
			visited.put(source, true);
			
			for (String city : map.get(source)) {
				/* If we are able to find the destination then dfs will return true. */
				if (dfs(city, target, map, visited)) {
					return true;
				}
			}
		}
		/* If we do not find destination we will return false in the end. */
		return false;

	}

	
	/* Reading the text file and storing the data in Map. */
	@Bean
	public void readFile() throws IOException {
		BufferedReader b = null;
		try {
			
			/* Reading the file. */
			File f = new File("city.txt");
			b = new BufferedReader(new FileReader(f));
			String readLine = "";
			log.debug("Reading file using Buffered Reader");
			
			while ((readLine = b.readLine()) != null) {
				String[] cityArr = readLine.split(",");
				String[] item = new String[] { cityArr[0].trim(), cityArr[1].trim() };
				
				/* Constructing the graph from file. */
				if (!map.containsKey(item[0])) {
					map.put(item[0], new HashSet<>());
				}
				
				if (!map.containsKey(item[1])) {
					map.put(item[1], new HashSet<>());
				}
				
				map.get(item[0]).add(item[1]);
				map.get(item[1]).add(item[0]);
				
				/* Initializing the visited map with false values for every city. */
				visited.put(item[0], false);
				visited.put(item[1], false);

			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			b.close();
		}

	}
}
