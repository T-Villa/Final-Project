//Class for data on car for drop down menus in GUI
package util;

import java.util.*;

public class CarData {
	
	public static List<Integer> getAvailableYears(){
		List<Integer> years = new ArrayList<>();
		for(int year = 1990; year <= 2025;year++) {
			years.add(year);
		}
		return years;
	}
	
	public static List<String> getMakes(){
		return List.of("Ferrari","BMW","Chevrolet","Audi","Ford","Lamborghini", "Porsche", "Mercedes-Benz", "Aston Martin", "McLaren");
	}
	
	public static List<String> getModel(String make){
		switch (make) {
		case "Ferrari":
			return List.of("812 Superfast","F8 Tributo","F40", "Roma", "SF90 Stradale");
		case "BMW":
			return List.of("M4","M3","X5M","M540i");
		case "Chevrolet":
			return List.of("Camaro","Corvette");
		case "Audi":
			return List.of("RS7", "RS6", "R8", "Q8", "A8");
		case "Ford":
			return List.of("Mustang", "GT");
		case "Lamborghini":
            return List.of("Aventador", "Huracan", "Urus");
        case "Porsche":
            return List.of("911 Carrera", "Cayenne", "Taycan", "911 GT2","911 GT3 RS");
        case "Mercedes-Benz":
            return List.of("AMG GT", "S-Class", "G-Class", "C63 AMG");
        case "Aston Martin":
            return List.of("DB11", "Vantage", "DBS Superleggera");
        case "McLaren":
            return List.of("720S", "Artura", "P1", "650S");

			
		default:
			return List.of();
			
		}
	}

	private static final Map<String, Map<String, List<String>>> trimLvl= new HashMap<>(); 
	static {
		// Chevrolet
		Map<String, List<String>> chevyModels = new HashMap<>();
			chevyModels.put("Camaro", List.of("1LT","2LT","3LT","LT1","1SS","2SS","ZL1"));
			chevyModels.put("Corvette", List.of("Base","Grand Sport","Z06","ZR1","C6-R"));
		
		trimLvl.put("Chevrolet", chevyModels);
		
		// BMW
        Map<String, List<String>> bmw = new HashMap<>();
	        bmw.put("M4", List.of("Base", "Competition"));
	        bmw.put("M3", List.of("Base", "Competition"));
	        bmw.put("X5M", List.of("Standard", "Competition"));
        
        trimLvl.put("BMW", bmw);
        
     // Ford
        Map<String, List<String>> ford = new HashMap<>();
	        ford.put("Mustang", List.of("EcoBoost", "GT", "GT Premium", "Mach 1","Shelby GT350", "Shelby GT500"));
	        ford.put("GT", List.of("Base", "Heritage Edition"));
	        
        trimLvl.put("Ford", ford);
        
     // Lamborghini
        Map<String, List<String>> lambo = new HashMap<>();
	        lambo.put("Aventador", List.of("S", "SVJ"));
	        lambo.put("Huracan", List.of("EVO", "STO", "Tecnica"));
	        
        trimLvl.put("Lamborghini", lambo);

     // Porsche
        Map<String, List<String>> porsche = new HashMap<>();
	        porsche.put("911 Carrera", List.of("Carrera", "Carrera S", "Carrera 4S", "Turbo", "Turbo S"));
	        porsche.put("Cayenne", List.of("Base", "S", "Turbo", "Turbo GT"));
	        
        trimLvl.put("Porsche", porsche);
        
     // Mercedes-Benz
        Map<String, List<String>> mercedes = new HashMap<>();
	        mercedes.put("AMG GT", List.of("GT", "GT S", "GT R", "GT Black Series"));
	        mercedes.put("G-Class", List.of("G550", "G63 AMG"));
	        
        trimLvl.put("Mercedes-Benz", mercedes);
        
     // Aston Martin
        Map<String, List<String>> aston = new HashMap<>();
	        aston.put("DB11", List.of("V8", "V12"));
	        aston.put("Vantage", List.of("Base", "F1 Edition", "Roadster"));
        
        trimLvl.put("Aston Martin", aston);
        
     // McLaren
        Map<String, List<String>> mclaren = new HashMap<>();
	        mclaren.put("720S", List.of("Base", "Performance", "Luxury"));
	        mclaren.put("650S", List.of("Coupe", "Spider"));
        
        trimLvl.put("McLaren", mclaren);
	}
	public static List<String> getTrim(String make, String model) {
		return trimLvl.getOrDefault(make, Map.of()).getOrDefault(model, List.of());
	}
	public static List<String> getColor(){
		return List.of("Red", "Orange", "Yellow", "Green", "Blue", "Purple", "Black", "White", "Silver", "Grey", "Nardo Grey", "Matte Black");
	}
}
