import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

import animal.Animal;
import animal.Lapin;
import animal.Renard;
import ecosysteme.Ecosysteme;
import ecosysteme.Elementnaturel;
import terrain.Terrain;
import terrain.Type;
import vegetal.Pin;

class Main {
  static double tauxReproductionSol = 1.0;
  static double chancePlanteEmpoisonnee = 1.0;
  static double tauxReproductionPlante = 1.0;
  static double tauxReproductionLapin = 1.0;
  static double tauxReproductionRenard = 0.5;
  
  static int nutritionMaxPlante = 10;
  static int santeInitialeLapin = 40;
  static int santeInitialeRenard = 1;
  
  static int nombrePlantes = 1;
  static int nombreLapins = 1;
  static int nombreRenards = 1;
  
  public static void main(String[] args) {
	  
	  String fileName;
	  for (int i = 0; i <= 5 ; i++) {
	  
		  fileName = "instance"+i+".txt"; 
		  System.out.println(fileName);
		  try {
		      // Créer un objet BufferedReader pour lire le fichier
		      BufferedReader reader = new BufferedReader(new FileReader(fileName));
		
		      String line;
		      while ((line = reader.readLine()) != null) {
		          // Vérifier si la ligne contient une valeur numérique
		          if (line.matches(".*=\\s*(-?\\d+(\\.\\d+)?).*")) {
		              String[] parts = line.split("=");
		              String variableName = parts[0].trim();
		              String numericValueString = parts[1].replaceAll("[^\\d.]", "").trim();
		              double numericValue = Double.parseDouble(numericValueString);
		              if (variableName.equals("tauxReproductionSol"))
		            	  tauxReproductionSol = numericValue;
		              else if (variableName.equals("chancePlanteEmpoisonnee"))
		            	  chancePlanteEmpoisonnee = numericValue;
		              else if (variableName.equals("tauxReproductionPlante"))
		            	  tauxReproductionPlante = numericValue;
		              else if (variableName.equals("tauxReproductionLapin"))
		            	  tauxReproductionLapin = numericValue;
		              else if (variableName.equals("tauxReproductionRenard"))
		            	  tauxReproductionRenard = numericValue;
		              else if (variableName.equals("nutritionMaxPlante"))
		            	  nutritionMaxPlante = (int)numericValue;
		              else if (variableName.equals("santeInitialeRenard"))
		            	  santeInitialeRenard = (int)numericValue;
		              else if (variableName.equals("nombrePlantes"))
		            	  nombrePlantes = (int)numericValue;
		              else if (variableName.equals("nombreLapins"))
		            	  nombreLapins = (int)numericValue;
		              else if (variableName.equals("nombreRenards"))
		            	  nombreRenards = (int)numericValue;
		              System.out.println(variableName + " = " + numericValue);
		          }
		          
		      }
		      // Fermer le BufferedReader
		      reader.close();
		  } catch (IOException e) {
		      System.out.println("Une erreur s'est produite lors de la lecture du fichier.");
		      e.printStackTrace();
		  }
		  
		  // creation et initialisation de la carte
		  initialiserCarte();
	  }
	  
  }
  public static void initialiserCarte() {
	  int dimensionCarte = 10;
	    int t = 0, i, j;
	    Elementnaturel carte[][] = new Elementnaturel[dimensionCarte][dimensionCarte];
	    Ecosysteme grille = new Ecosysteme(carte);
	    
	    // Initialisation de la carte
	    for (i = 0; i < carte[0].length; i++) {
	      for (j = 0; j < carte.length; j++) {
	        if (i < carte.length / 2) {
	          carte[i][j] = new Terrain(Type.FORET, tauxReproductionSol, 1000);
	        } else {
	          carte[i][j] = new Terrain(Type.PLAIN, tauxReproductionSol, 100);
	        }
	      }
	    }
	    
	    grille.actualiser();
	    System.out.println("Initialisation de la carte en deux parties terminée");
	    try { Thread.sleep(2000); } catch (Exception e) { System.out.println(e); };
	    	
	    while (t++ <= carte[0].length * carte.length) {
	      int random = ((int) (Math.random() * 2));
	      int randomGender = ((int) (Math.random()));
	      i = ((int) (Math.random() * carte[0].length));
	      j = ((int) (Math.random() * carte.length));
	      
	      boolean estEmpoisonnee;
	      
	      if (Math.random() < chancePlanteEmpoisonnee) {
	        estEmpoisonnee = true;
	      } else {
	        estEmpoisonnee = false;
	      }
	      
	      if (! (carte[i][j] instanceof Animal)) {
	    	  if (random != 0) {
	    		  carte[i][j] = new Lapin(tauxReproductionLapin, santeInitialeLapin, randomGender, false);
		      } else /*if (random == 1) */{
		    	  carte[i][j] = new Renard(tauxReproductionRenard, santeInitialeRenard, randomGender, false);
		      }
	      }   
	      
	      grille.actualiser();
	      
	      try { Thread.sleep(100); } catch (Exception e) { System.out.println(e); };
	    }
	    //grille.actualiser();
	    System.out.println("Initialisation de la carte par des êtres vivants terminée");
	    try { Thread.sleep(2000); } catch (Exception e) { System.out.println(e); };
	    // Boucle jusqu'à ce que le renard ou le lapin disparaisse
	    while (nombreRenards != 0 && nombreLapins != 0) {
	      deplacerCarte(carte);
	      try { Thread.sleep(500); } catch (Exception e) { System.out.println(e); };
	      grille.actualiser();
	    }
	    
	    // Indique à l'utilisateur quels organismes sont morts
	    if (nombrePlantes == 0) {
	      System.out.println("Les plantes sont éteintes !");
	    }
	    if (nombreLapins == 0) {
	      System.out.println("Les lapins sont éteints !");
	    }
	    if (nombreRenards == 0) {
	      System.out.println("Les renards sont éteints !");
	    }
  }
  public static void deplacerCarte(Elementnaturel[][] carte) {
    for (int i = 0; i < carte[0].length; i++) {
      for (int j = 0; j < carte.length; j++) {
        int directionDeplacement = ((int) (Math.random() * 5));
        double spawn = (double) (Math.random());
        
        if (carte[i][j] instanceof Lapin) {
          ((Lapin) carte[i][j]).deplacerLapin(i, j, directionDeplacement, carte);
        } else if (carte[i][j] instanceof Renard) {
          ((Renard) carte[i][j]).deplacerRenard(i, j, directionDeplacement, carte);
        }
        /*
        else if (carte[i][j] instanceof Pin) {
          ((Pin) carte[i][j]).regenerer(nutritionMaxPlante);
          if (spawn <= ((Pin) carte[i][j]).getTauxReproduction()) {
            ((Pin) carte[i][j]).seReproduirePlante(i, j, carte);
          }
        }
        *
        else if (carte[i][j] instanceof Terrain) {
          if (((Terrain) carte[i][j]).getType() != Type.DESERT) {
            if (((Terrain) carte[i][j]).estVide()) {
              ((Terrain) carte[i][j]).nouvelEtat();
            }
          }
        }
        */
      }
    }
    
    nombrePlantes = 0;
    nombreLapins = 0;
    nombreRenards = 0;
    
    for (int i = 0; i < carte[0].length; i++) {
      for (int j = 0; j < carte.length; j++) {
        if (carte[i][j] instanceof Pin) {
          nombrePlantes++;
        } else if (carte[i][j] instanceof Lapin) {
          nombreLapins++;
        } else if (carte[i][j] instanceof Renard) {
          nombreRenards++;
        }
      }
    }
  }

}
