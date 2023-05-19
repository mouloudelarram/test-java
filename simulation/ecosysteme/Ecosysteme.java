package ecosysteme;
// Imports pour les graphismes
import javax.swing.*;

import animal.Lapin;
import animal.Renard;
import terrain.Terrain;
import terrain.Type;
import vegetal.Pin;

import java.awt.*;
import java.awt.image.*; 
import javax.imageio.*; 

public class Ecosysteme extends JPanel
{ 

  private int maxX,maxY, ratioGrilleEcran;
  private Elementnaturel[][] monde;
  
  public Ecosysteme(Elementnaturel[][] m) 
  { 
    this.monde = m;
    
    maxX = Toolkit.getDefaultToolkit().getScreenSize().width;
    maxY = Toolkit.getDefaultToolkit().getScreenSize().height-35;
    ratioGrilleEcran = maxY / (monde.length+1);  //ratio pour s'adapter à l'écran comme une carte carrée
    
    System.out.println("Taille de la carte : "+monde.length+" par "+monde[0].length + "\nTaille de l'écran : "+ maxX +"x"+maxY+ " Ratio : " + ratioGrilleEcran);
    
    JFrame frame = new JFrame("Carte du monde");
    
   
    
    frame.getContentPane().add(BorderLayout.CENTER, this);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
    frame.setVisible(true);
  }
  
  
  public void actualiser() { 
    this.repaint();
  }
  
  
    public void paintComponent(Graphics g) {        
      //super.repaint();
      
      //Obtention des images
       Image lapin = Toolkit.getDefaultToolkit().getImage("./images/lapin.png");
       Image renard = Toolkit.getDefaultToolkit().getImage("./images/renard.png");
       Image plaine = Toolkit.getDefaultToolkit().getImage("./images/plaine.jpg");
       Image foret = Toolkit.getDefaultToolkit().getImage("./images/foret.png");
       Image desert = Toolkit.getDefaultToolkit().getImage("./images/desert.jpg");
       
      setDoubleBuffered(true); 
      g.setColor(Color.BLACK);
      
      for(int i = 0; i<monde[0].length;i=i+1)
      { 
        for(int j = 0; j<monde.length;j=j+1) 
        { 
          
          if (monde[i][j] instanceof Lapin)    //Ce bloc peut être modifié pour correspondre à des paires personnage-couleur
            g.drawImage(lapin,j*ratioGrilleEcran,i*ratioGrilleEcran,ratioGrilleEcran,ratioGrilleEcran,this);
          else if (monde[i][j] instanceof Renard)
            g.drawImage(renard,j*ratioGrilleEcran,i*ratioGrilleEcran,ratioGrilleEcran,ratioGrilleEcran,this);
          else if (monde[i][j] instanceof Terrain)
        	  if (((Terrain)(monde[i][j])).getType() == Type.FORET)
	          {
        		  g.drawImage(foret,j*ratioGrilleEcran,i*ratioGrilleEcran,ratioGrilleEcran,ratioGrilleEcran,this);
	          } else if (((Terrain)(monde[i][j])).getType() == Type.PLAIN)
	          {
	        	  g.drawImage(plaine,j*ratioGrilleEcran,i*ratioGrilleEcran,ratioGrilleEcran,ratioGrilleEcran,this);
	          }else {
	        	  g.drawImage(desert,j*ratioGrilleEcran,i*ratioGrilleEcran,ratioGrilleEcran,ratioGrilleEcran,this);
	          }
          /*
          else if (monde[i][j] instanceof Pin)
            if (((Pin)(monde[i][j])).getNutrition() >= 8)
          {
            g.drawImage(plaine,j*ratioGrilleEcran,i*ratioGrilleEcran,ratioGrilleEcran,ratioGrilleEcran,this);
          } else if (((Pin)(monde[i][j])).getNutrition() <= 3)
          {
            g.drawImage(desert,j*ratioGrilleEcran,i*ratioGrilleEcran,ratioGrilleEcran,ratioGrilleEcran,this);
          }
          else
          {
            g.drawImage(desert,j*ratioGrilleEcran,i*ratioGrilleEcran,ratioGrilleEcran,ratioGrilleEcran,this);
          }
          */
      }
    }
  }//fin de la classe Ecosysteme
  
} //fin de la classe DisplayGrid
