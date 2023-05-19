package vegetal;
import ecosysteme.Elementnaturel;
import terrain.Terrain;

public class Pin extends Arbres 
{
  
  private int nutrition;
  private boolean poisoned;
  
  //Constructeur
  public Pin (double tauxReproduction, int nutrition, boolean poisoned) 
  {
    super(tauxReproduction);
    this.nutrition = nutrition;
    this.poisoned = poisoned;
  }
  
  public int getNutrition() 
  {
    return this.nutrition;
  }
  
  public boolean getPoisoned()
  {
    return this.poisoned;
  }
  
  
  //==================== Méthodes ====================
  
  //Méthode pour régénérer la nutrition de la plante jusqu'à un maximum donné
  public void regenerer(int maxNutritionPlante) {
    if (this.nutrition < maxNutritionPlante) {
      this.nutrition += 1;
    }
  }//Fin de regenerer
  
  //Méthode pour reproduire la plante. Elles se reproduisent de manière asexuée et ne poussent que sur un sol fertile. Elle se reproduit également dans plusieurs directions à la fois.
  public void seReproduire(int i, int j, Elementnaturel[][] map) 
  {
    if (i != 0 && map[i-1][j] instanceof Terrain && ((Terrain)map[i-1][j]).getStatus()) //Vérification de la présence d'un sol fertile
    {
      map[i-1][j] = new Pin(this.getTauxReproduction(), 0, this.getPoisoned());
    } 
    if (j != map[i].length-1 && map[i][j+1] instanceof Terrain && ((Terrain)map[i][j+1]).getStatus()) 
    {
      map[i][j+1] = new Pin(this.getTauxReproduction(), 0, this.getPoisoned());
    }
    if (i != map.length-1 && map[i+1][j] instanceof Terrain && ((Terrain)map[i+1][j]).getStatus())  
    {
      map[i+1][j] = new Pin(this.getTauxReproduction(), 0, this.getPoisoned());
    }
    if (j != 0 && map[i][j-1] instanceof Terrain && ((Terrain)map[i][j-1]).getStatus()) 
    { 
      map[i][j-1] = new Pin(this.getTauxReproduction(), 0, this.getPoisoned());
    }
  }//Fin de seReproduire
  
}//Fin de la classe
