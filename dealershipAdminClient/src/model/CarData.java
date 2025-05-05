//Class for data on car for drop down menus in GUI
package model;

import java.util.*;

public class CarData {
	
	public static List<Integer> getAvailableYears(){
		return List.of(1990,1991,1992,1993,1994,1995,1996,1997,1998,1999,2000,2001,2002,2003,2004,2005,2006,2007,2008,2009,2010,2011,2012,2013,2014,2015,2016,2017,2018,2019,2020,2021,2022,2023,2024,2025);
	}
	
	public static List<String> getMakes(){
		return List.of("Ferrari","BMW","Chevorlet","Audi","Ford");
	}
	
	public static List<String> getModel(String make){
		switch (make) {
		case "Ferrari":
			return List.of("812 Superfast","F8 Tributo","F40");
		case "BMW":
			return List.of("M4","M3","X5M","M540i");
		case "Chevorlet":
			return List.of("Camaro","Corvette");
		case "Audi":
			return List.of("RS8","R8","Q7","A4","RS6");
		case "Ford":
			return List.of("Mustang","GT");
	//CONTINUE CASES FOR OTHER BRANDS AND ADD MORE CARS
			
		default:
			return List.of();
			
		}
	}
	//ADD MORE TRIMS FOR CARS
	private static final Map<String, Map<String, List<String>>> trimLvl= new HashMap<>(); 
	static {
		//CHEVORLET
		Map<String, List<String>> chevyModels = new HashMap<>();
		chevyModels.put("Camaro", List.of("1LT","2LT","3LT","LT1","1SS","2SS","ZL1"));
		chevyModels.put("Corvette", List.of("Base","Grand Sport","Z06","ZR1","C6-R"));
		
		trimLvl.put("Chevorlet", chevyModels);
	}
	public static List<String> getTrim(String make, String model) {
		return trimLvl.getOrDefault(make, Map.of()).getOrDefault(model, List.of());
	}
	public static List<String> getColor(){
		return List.of("Red","Orange","Yellow","Green","Blue","Purple");
	}
}
