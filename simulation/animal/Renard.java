package animal;
import ecosysteme.Elementnaturel;
import terrain.Terrain;
import terrain.Type;
import vegetal.Pin;

public class Renard extends Mammiferes implements Comparable<Renard> {

    public Renard(double tauxReproduction, int sante, int genre, boolean empoisonne) {
        super(tauxReproduction, sante, genre, empoisonne);
    }

    public void deplacerRenard(int i, int j, int direction, Elementnaturel[][] carte) {
        if (direction == 0) {
        } else if (direction == 1 && i != 0) {
            if (carte[i-1][j] instanceof Pin || carte[i-1][j] instanceof Terrain) {
                if (((Terrain) carte[i-1][j]).getType() != Type.DESERT) {
                    marcher(carte, i, j, i-1, j);
                }
            } else if (carte[i-1][j] instanceof Lapin) {
                mangerLapin(carte, i, j, i-1, j);
            } else if (carte[i-1][j] instanceof Renard) {
                if (Math.abs(((Renard) carte[i-1][j]).getGenre() - 1) == ((Renard) carte[i][j]).getGenre()) {
                    if (((Renard) carte[i][j]).getSante() >= 20 && ((Renard) carte[i-1][j]).getSante() >= 20) {
                        ((Renard) carte[i][j]).reproduireRenard(carte, i, j);
                        ((Renard) carte[i][j]).setSante(((Renard) carte[i][j]).getSante() - 10);
                        ((Renard) carte[i-1][j]).setSante(((Renard) carte[i-1][j]).getSante() - 10);
                    }
                } else if (((Renard) carte[i][j]).compareTo((Renard) carte[i-1][j]) >= 0) {
                    ((Renard) carte[i-1][j]).setSante(((Renard) carte[i-1][j]).getSante() - 10);
                } else {
                    ((Renard) carte[i][j]).setSante(((Renard) carte[i-1][j]).getSante() - 10);
                }
            }
        } else if (direction == 2 && j != carte[i].length-1) {
            if (carte[i][j+1] instanceof Pin || carte[i][j+1] instanceof Terrain) {
                if (((Terrain) carte[i][j+1]).getType() != Type.DESERT) {
                    marcher(carte, i, j, i, j+1);
                }
            } else if (carte[i][j+1] instanceof Lapin) {
                mangerLapin(carte, i, j, i, j+1);
            } else if (carte[i][j+1] instanceof Renard) {
                if (Math.abs(((Renard) carte[i][j+1]).getGenre() - 1) == ((Renard) carte[i][j]).getGenre()) {
                    if (((Renard) carte[i][j]).getSante() >= 20 && ((Renard) carte[i][j+1]).getSante() >= 20) {
                        ((Renard) carte[i][j]).reproduireRenard(carte, i, j);
                        ((Renard) carte[i][j]).setSante(((Renard) carte[i][j]).getSante() - 10);
                        ((Renard) carte[i][j+1]).setSante(((Renard) carte[i][j+1]).getSante() - 10);
                    }
                } else if (((Renard) carte[i][j]).compareTo((Renard) carte[i][j+1]) >= 0) {
                    ((Renard) carte[i][j+1]).setSante(((Renard) carte[i][j+1]).getSante() - 10);
                } else {
                    ((Renard) carte[i][j]).setSante(((Renard) carte[i][j+1]).getSante() - 10);
                }
            }
        } else if (direction == 3 && i != carte.length-1) {
            if (carte[i+1][j] instanceof Pin || carte[i+1][j] instanceof Terrain) {
                if (((Terrain) carte[i+1][j]).getType() != Type.DESERT) {
                    marcher(carte, i, j, i+1, j);
                }
            } else if (carte[i+1][j] instanceof Lapin) {
                mangerLapin(carte, i, j, i+1, j);
            } else if (carte[i+1][j] instanceof Renard) {
                if (Math.abs(((Renard) carte[i+1][j]).getGenre() - 1) == ((Renard) carte[i][j]).getGenre()) {
                    if (((Renard) carte[i][j]).getSante() >= 20 && ((Renard) carte[i+1][j]).getSante() >= 20) {
                        ((Renard) carte[i][j]).reproduireRenard(carte, i, j);
                        ((Renard) carte[i][j]).setSante(((Renard) carte[i][j]).getSante() - 10);
                        ((Renard) carte[i+1][j]).setSante(((Renard) carte[i+1][j]).getSante() - 10);
                    }
                } else if (((Renard) carte[i][j]).compareTo((Renard) carte[i+1][j]) >= 0) {
                    ((Renard) carte[i+1][j]).setSante(((Renard) carte[i+1][j]).getSante() - 10);
                } else {
                    ((Renard) carte[i][j]).setSante(((Renard) carte[i+1][j]).getSante() - 10);
                }
            }
        } else if (direction == 4 && j != 0) {
            if (carte[i][j-1] instanceof Pin || carte[i][j-1] instanceof Terrain) {
                if (((Terrain) carte[i][j-1]).getType() != Type.DESERT) {
                    marcher(carte, i, j, i, j-1);
                }
            } else if (carte[i][j-1] instanceof Lapin) {
                mangerLapin(carte, i, j, i, j-1);
            } else if (carte[i][j-1] instanceof Renard) {
                if (Math.abs(((Renard) carte[i][j-1]).getGenre() - 1) == ((Renard) carte[i][j]).getGenre()) {
                    if (((Renard) carte[i][j]).getSante() >= 20 && ((Renard) carte[i][j-1]).getSante() >= 20) {
                        ((Renard) carte[i][j]).reproduireRenard(carte, i, j);
                        ((Renard) carte[i][j]).setSante(((Renard) carte[i][j]).getSante() - 10);
                        ((Renard) carte[i][j-1]).setSante(((Renard) carte[i][j-1]).getSante() - 10);
                    }
                } else if (((Renard) carte[i][j]).compareTo((Renard) carte[i][j-1]) >= 0) {
                    ((Renard) carte[i][j-1]).setSante(((Renard) carte[i][j-1]).getSante() - 10);
                } else {
                    ((Renard) carte[i][j]).setSante(((Renard) carte[i][j-1]).getSante() - 10);
                }
            }
        }
      
        this.setSante(this.getSante() - 1); // Ils perdent 1 point de santé à chaque tour
      
        if (this.isEmpoisonne()) { // Ils perdent 1 point de santé supplémentaire s'ils sont empoisonnés.
            this.setSante(this.getSante() - 1);
        }
      
        if (this.getSante() <= 0) { // Ils meurent s'ils n'ont plus de santé.
            carte[i][j] = new Terrain(Type.FORET, ((double)(Math.random())), ((int)(Math.random()))*1000);
            ((Terrain) carte[i][j]).newStatus();
        }
    } // Fin de la méthode moveRenard

 // Méthode pour que les loups marchent sur le terrain
    public void marcher(Elementnaturel[][] carte, int i, int j, int terrainI, int terrainJ) {
        carte[terrainI][terrainJ] = new Terrain(Type.PLAIN, Math.random(), (int)(Math.random() * 1000)); // Nouveau terrain avec une chance aléatoire de devenir fertile
        ((Terrain) carte[terrainI][terrainJ]).newStatus(); // Le nouveau terrain commence sec
    }

    // Méthode pour manger un lapin
    public void mangerLapin(Elementnaturel[][] carte, int i, int j, int lapinI, int lapinJ) {
        ((Renard) carte[i][j]).setSante(((Renard) carte[i][j]).getSante() + ((Lapin) carte[lapinI][lapinJ]).getSante()); // Le renard gagne de la santé en fonction de la santé du lapin
        carte[lapinI][lapinJ] = new Terrain(Type.PLAIN, Math.random(), (int)(Math.random() * 1000)); // Nouveau terrain avec une chance aléatoire de devenir fertile
        ((Terrain) carte[lapinI][lapinJ]).newStatus(); // Le nouveau terrain commence sec
    }

    // Méthode pour la reproduction des renards
    public void reproduireRenard(Elementnaturel[][] carte, int i, int j) {
        if (Math.random() <= ((Renard) carte[i][j]).getTauxReproduction()) { // Chance de reproduction réussie en fonction de l'entrée de l'utilisateur
            if (carte[i+1][j] instanceof Terrain || carte[i+1][j] instanceof Pin) { // Recherche d'emplacements pour le bébé renard
                carte[i+1][j] = new Renard(this.getTauxReproduction(), this.getSante(), (int) Math.random(), this.isEmpoisonne());
            } else if (carte[i-1][j] instanceof Terrain || carte[i-1][j] instanceof Pin) {
                carte[i-1][j] = new Renard(this.getTauxReproduction(), this.getSante(), (int) Math.random(), this.isEmpoisonne());
            } else if (carte[i][j+1] instanceof Terrain || carte[i][j+1] instanceof Pin) {
                carte[i][j+1] = new Renard(this.getTauxReproduction(), this.getSante(), (int) Math.random(), this.isEmpoisonne());
            } else if (carte[i][j-1] instanceof Terrain || carte[i][j-1] instanceof Pin) {
                carte[i][j-1] = new Renard(this.getTauxReproduction(), this.getSante(), (int) Math.random(), this.isEmpoisonne());
            }
        }
    }

    @Override
    public int compareTo(Renard renard) {
        // TODO Auto-generated method stub
        return 0;
    }
}
