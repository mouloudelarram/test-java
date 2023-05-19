package animal;

import ecosysteme.Elementnaturel;
import terrain.Terrain;
import terrain.Type;
import vegetal.Pin;

public class Lapin extends Mammiferes {

    public Lapin(double breedRate, int health, int gender, boolean poisoned) {
        super(breedRate, health, gender, poisoned);
    }

    // Méthode permettant au lapin de se déplacer et d'interagir avec d'autres éléments naturels
    public void deplacerLapin(int i, int j, int directionDeplacement, Elementnaturel[][] carte) {
        if (directionDeplacement == 0) {
        } else if (directionDeplacement == 1 && i != 0) {
            if (carte[i - 1][j] instanceof Pin) {
                mangerPlante(carte, i, j, i - 1, j);
            } else if (carte[i - 1][j] instanceof Terrain) {
                if (((Terrain) carte[i - 1][j]).getType() != Type.DESERT) {
                    marcher(carte, i, j, i - 1, j);
                }
            } else if (carte[i - 1][j] instanceof Lapin && Math.abs(((Lapin) carte[i - 1][j]).getGenre() - 1) == ((Lapin) carte[i][j]).getGenre()) {
                if (((Lapin) carte[i][j]).getSante() >= 20 && ((Lapin) carte[i - 1][j]).getSante() >= 20) {
                    ((Lapin) carte[i][j]).reproduireLapin(carte, i, j);
                    ((Lapin) carte[i][j]).setSante(((Lapin) carte[i][j]).getSante() - 10);
                    ((Lapin) carte[i - 1][j]).setSante(((Lapin) carte[i - 1][j]).getSante() - 10);
                }
            }
        } else if (directionDeplacement == 2 && j != carte[i].length - 1) {
            if (carte[i][j + 1] instanceof Pin) {
                mangerPlante(carte, i, j, i, j + 1);
            } else if (carte[i][j + 1] instanceof Terrain) {
                if (((Terrain) carte[i][j + 1]).getType() != Type.DESERT) {
                    marcher(carte, i, j, i, j + 1);
                }
            } else if (carte[i][j + 1] instanceof Lapin && Math.abs(((Lapin) carte[i][j + 1]).getGenre() - 1) == ((Lapin) carte[i][j]).getGenre()) {
                if (((Lapin) carte[i][j]).getSante() >= 20 && ((Lapin) carte[i][j + 1]).getSante() >= 20) {
                    ((Lapin) carte[i][j]).reproduireLapin(carte, i, j);
                    ((Lapin) carte[i][j]).setSante(((Lapin) carte[i][j]).getSante() - 10);
                    ((Lapin) carte[i][j + 1]).setSante(((Lapin) carte[i][j + 1]).getSante() - 10);
                }
            }
        } else if (directionDeplacement == 3 && i != carte.length - 1) {
            if (carte[i + 1][j] instanceof Pin) {
                mangerPlante(carte, i, j, i + 1, j);
            } else if (carte[i + 1][j] instanceof Terrain) {
                if (((Terrain) carte[i + 1][j]).getType() != Type.DESERT) {
                    marcher(carte, i, j, i + 1, j);
                }
            } else if (carte[i + 1][j] instanceof Lapin && Math.abs(((Lapin) carte[i + 1][j]).getGenre() - 1) == ((Lapin) carte[i][j]).getGenre()) {
                if (((Lapin) carte[i][j]).getSante() >= 20 && ((Lapin) carte[i + 1][j]).getSante() >= 20) {
                    ((Lapin) carte[i][j]).reproduireLapin(carte, i, j);
                    ((Lapin) carte[i][j]).setSante(((Lapin) carte[i][j]).getSante() - 10);
                    ((Lapin) carte[i + 1][j]).setSante(((Lapin) carte[i + 1][j]).getSante() - 10);
                }
            }
        } else if (directionDeplacement == 4 && j != 0) {
            if (carte[i][j - 1] instanceof Pin) {
                if (((Terrain) carte[i][j - 1]).getType() != Type.DESERT) {
                    mangerPlante(carte, i, j, i, j - 1);
                }
            } else if (carte[i][j - 1] instanceof Terrain) {
                if (((Terrain) carte[i][j - 1]).getType() != Type.DESERT) {
                    marcher(carte, i, j, i, j - 1);
                }
            } else if (carte[i][j - 1] instanceof Lapin && Math.abs(((Lapin) carte[i][j - 1]).getGenre() - 1) == ((Lapin) carte[i][j]).getGenre()) {
                if (((Lapin) carte[i][j]).getSante() >= 20 && ((Lapin) carte[i][j - 1]).getSante() >= 20) {
                    ((Lapin) carte[i][j]).reproduireLapin(carte, i, j);
                    ((Lapin) carte[i][j]).setSante(((Lapin) carte[i][j]).getSante() - 10);
                    ((Lapin) carte[i][j - 1]).setSante(((Lapin) carte[i][j - 1]).getSante() - 10);
                }
            }
        }

        this.setSante(this.getSante() - 1); // Perd de la santé à chaque tour

        if (this.isEmpoisonne()) // Perd davantage de santé s'il est empoisonné
        {
            this.setSante(this.getSante() - 1);
        }

        if (this.getSante() <= 0) // Meurt s'il n'a plus de santé
        {
            carte[i][j] = new Terrain(Type.FORET, ((double) (Math.random())), ((int) (Math.random())) * 1000);
            ;
            ((Terrain) (carte[i][j])).newStatus();
        }
    }//Fin deplacerLapin

    // Méthode pour marcher sur un terrain
    public void marcher(Elementnaturel[][] carte, int i, int j, int TerrainI, int TerrainJ) {
        carte[i][j] = new Terrain(Type.PLAIN, ((double) (Math.random())), ((int) (Math.random())) * 1000); // Chance aléatoire de devenir fertile
        carte[TerrainI][TerrainJ] = new Lapin(this.getTauxReproduction(), this.getSante(), this.getGenre(), this.isEmpoisonne()); // Le lapin se déplace
    }//Fin marcher

    // Méthode pour manger des plantes
    public void mangerPlante(Elementnaturel[][] carte, int i, int j, int planteI, int planteJ) {
        ((Lapin) carte[i][j]).setSante(((Lapin) carte[i][j]).getSante() + ((Pin) carte[planteI][planteJ]).getNutrition()); // Le lapin gagne de la santé en fonction de la nutrition de la plante
        carte[planteI][planteJ] = new Terrain(Type.PLAIN, ((double) (Math.random())), ((int) (Math.random())) * 1000); // La plante est maintenant morte et un terrain sec est à sa place
        ((Terrain) (carte[planteI][planteJ])).newStatus();
    }//Fin mangerPlante

    // Méthode pour faire se reproduire le lapin
    public void reproduireLapin(Elementnaturel[][] carte, int i, int j) {
        if (((double) (Math.random())) <= ((Lapin) carte[i][j]).getTauxReproduction()) // Chance que la reproduction soit réussie en fonction de l'entrée de l'utilisateur
        {
            if (carte[i + 1][j] instanceof Terrain || carte[i + 1][j] instanceof Pin) // Recherche d'un emplacement pour le bébé lapin
            {
                carte[i + 1][j] = new Lapin(this.getTauxReproduction(), this.getSante(), (int) Math.random(), this.isEmpoisonne());
            } else if (carte[i - 1][j] instanceof Terrain || carte[i - 1][j] instanceof Pin) {
                carte[i - 1][j] = new Lapin(this.getTauxReproduction(), this.getSante(), (int) Math.random(), this.isEmpoisonne());
            } else if (carte[i][j + 1] instanceof Terrain || carte[i][j + 1] instanceof Pin) {
                carte[i][j + 1] = new Lapin(this.getTauxReproduction(), this.getSante(), (int) Math.random(), this.isEmpoisonne());
            } else if (carte[i][j - 1] instanceof Terrain || carte[i][j - 1] instanceof Pin) {
                carte[i][j - 1] = new Lapin(this.getTauxReproduction(), this.getSante(), (int) Math.random(), this.isEmpoisonne());
            }
        }
    }//Fin reproduireLapin
}
