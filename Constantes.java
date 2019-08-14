/**
 * 
 */
package pacman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.Timer;
/**
 * @author Lady Toledo
 *
 */
public interface Constantes {
		public final Font smallfont = new Font("Helvetica", Font.BOLD, 14);  //MUERE TABLERO
		public final Color dotcolor = new Color(192, 192, 0); //MUERE TABLERO
		public final int blocksize = 24; //24 tamaño del juego  MUEREN TODOS
		public final int nrofblocks = 15; //15, puntitos    *************** MUEREN TODOS
		public final int scrsize = nrofblocks * blocksize ;//nrofblocks * blocksize  MUEREN TODOS
		public final int maxghosts = 12; //12  JUEGO MUERE
		public final int pacmanspeed = 6; //6 velocidad MUERE PACMAN
	    public final int validspeeds[] = {1, 2, 3, 4, 6,8};// MUERE NIVELES
	    public final int maxspeed = 6; //MUERE JUEGO
	   // public final Dimension d = new Dimension(400, 400); //no paso nada
	   // public Color mazecolor  = new Color(0, 0, 255); //color laberinto
	    //public final Timer timer = new Timer(40, null);
	  
}