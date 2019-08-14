/**
 * 
 */
package pacman;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
/**
 * @author Lady Toledo
 *
 */
@SuppressWarnings("serial")
public class Tablero extends MegaTablero implements Constantes{
		public Tablero() {
			super();
		}
		Graphics g;
        Graphics2D g2d = (Graphics2D) g;
		public void drawMaze(Graphics2D g2d) { //dibujar laberinto
			
	        short i = 0;
	        int x, y;

	        for (y = 0;  y < scrsize; y += blocksize) { 
	            for (x = 0; x < scrsize; x += blocksize) {

	                g2d.setColor(mazecolor);
	                g2d.setStroke(new BasicStroke(2)); //
	                
	                // sreen data -> datos de pantalla
	               //blocksize -> tamaño del bloque
	                
	                //lineas derecha
	                if ((screendata[i] & 1) != 0) {
	                    g2d.drawLine(x, y, x, y + blocksize - 1);
	                }  
	                //linea de abajo
	                if ((screendata[i] & 2) != 0) {
	                    g2d.drawLine(x,y, x + blocksize - 1, y);
	                }
					//linea izquierda
	                if ((screendata[i] & 4) != 0) {
	                    g2d.drawLine(x + blocksize - 1, y, x + blocksize - 1,
	                            y + blocksize - 1);
	                }
	                //linea de arriba
	                if ((screendata[i] & 8) != 0) {
	                    g2d.drawLine(x, y + blocksize - 1, x + blocksize - 1,
	                            y + blocksize - 1);
	                }
					
	                if ((screendata[i] & 16) != 0) {
	                	//dot color -> color de punto
	                    g2d.setColor(dotcolor);
	                    g2d.fillRect(x + 11, y + 11, 2, 2);//x + 11, y + 11, 2, 2
	                }
					
	                i++;
	            }
	        }
		}
		
	  //configuracion puntaje
	    public void drawScore(Graphics2D g) {
	        int i;
	        String s;
	        g.setFont(smallfont); 
	        g.setColor(new Color(255, 0, 0));
	        s = "Score: " + score;
	        g.drawString(s, scrsize / 2 + 96, scrsize + 16);
	        for (i = 0; i < pacsleft; i++) {
	            g.drawImage(pacman3left, i * 28 + 8, scrsize + 1, this);
	        }
	    }
	    public void drawGhost(Graphics2D g2d, int x, int y) {
	    	g2d.drawImage(ghost, x, y, this);
	    }
}
