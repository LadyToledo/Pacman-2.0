/**
 * 
 */
package pacman;

import java.awt.Graphics2D;
/**
 * @author Lady Toledo
 *
 */
@SuppressWarnings("serial")
public class Pacman extends Tablero implements Constantes {
	public void drawPacman(Graphics2D g2d) {
		if (viewdx == -1) {//-1
			drawPacnanLeft(g2d);
		} else if (viewdx == 1) {//1
			drawPacmanRight(g2d);
		} else if (viewdy == -1) { //-1
			drawPacmanUp(g2d);
		} else {
			drawPacmanDown(g2d);
		}
	}
	public void drawPacmanUp(Graphics2D g2d) {
		//movimienos hacia arriba del pacman
		switch (pacmananimpos) {
		case 1:
			g2d.drawImage(pacman2up, pacmanx + 1, pacmany + 1, this);

			break;
		case 2:
			g2d.drawImage(pacman3up, pacmanx + 1, pacmany + 1, this);
			break;
		case 3:
			g2d.drawImage(pacman4up, pacmanx + 1, pacmany + 1, this);
			break;
		default:
			g2d.drawImage(pacman1, pacmanx + 1, pacmany + 1, this);
			break;
		}
	}

	public void drawPacmanDown(Graphics2D g2d) {
		//movimientos hacia abajo del pacman
		switch (pacmananimpos) {
		case 1:
			g2d.drawImage(pacman2down, pacmanx + 1, pacmany + 1, this);
			break;
		case 2:
			g2d.drawImage(pacman3down, pacmanx + 1, pacmany + 1, this);
			break;
		case 3:
			g2d.drawImage(pacman4down, pacmanx + 1, pacmany + 1, this);
			break;
		default:
			g2d.drawImage(pacman1, pacmanx + 1, pacmany + 1, this);
			break;
		}
	}

	public void drawPacnanLeft(Graphics2D g2d) {
		//movimientos a la izquierda del pacman
		switch (pacmananimpos) {
		case 1:
			g2d.drawImage(pacman2left, pacmanx + 1, pacmany + 1, this);
			break;
		case 2:
			g2d.drawImage(pacman3left, pacmanx + 1, pacmany + 1, this);
			break;
		case 3:
			g2d.drawImage(pacman4left, pacmanx + 1, pacmany + 1, this);
			break;
		default:
			g2d.drawImage(pacman1, pacmanx + 1, pacmany + 1, this);
			break;
		}
	}

	public void drawPacmanRight(Graphics2D g2d) {
		//movimientos a la derecha del pacman
		switch (pacmananimpos) {
		case 1:
			g2d.drawImage(pacman2right, pacmanx + 1, pacmany + 1, this);
			break;
		case 2:
			g2d.drawImage(pacman3right, pacmanx + 1, pacmany + 1, this);
			break;
		case 3:
			g2d.drawImage(pacman4right, pacmanx + 1, pacmany + 1, this);
			break;
		default:
			g2d.drawImage(pacman1, pacmanx + 1, pacmany + 1, this);
			break;
		}
	}
	public void movePacman() {

		int pos;
		short ch;

		if (reqdx == -pacmandx && reqdy == -pacmandy) {
			pacmandx = reqdx;
			pacmandy = reqdy;
			viewdx = pacmandx;
			viewdy = pacmandy;
		}

		if (pacmanx % blocksize == 0 && pacmany % blocksize == 0) {
			pos = pacmanx / blocksize + nrofblocks * (pacmany / blocksize);
			ch = screendata[pos];

			if ((ch & 16) != 0) {
				screendata[pos] = (short) (ch & 15);
				score++;
			}

			if (reqdx != 0 || reqdy != 0) {
				if (!((reqdx == -1 && reqdy == 0 && (ch & 1) != 0)
						|| (reqdx == 1 && reqdy == 0 && (ch & 4) != 0)
						|| (reqdx == 0 && reqdy == -1 && (ch & 2) != 0)
						|| (reqdx == 0 && reqdy == 1 && (ch & 8) != 0))) {
					pacmandx = reqdx;
					pacmandy = reqdy;
					viewdx = pacmandx;
					viewdy = pacmandy;
				}
			}

			// Verificación de limites

			if ((pacmandx == -1 && pacmandy == 0 && (ch & 1) != 0)
					|| (pacmandx == 1 && pacmandy == 0 && (ch & 4) != 0)
					|| (pacmandx == 0 && pacmandy == -1 && (ch & 2) != 0)
					|| (pacmandx == 0 && pacmandy == 1 && (ch & 8) != 0)) {
				pacmandx = 0;
				pacmandy = 0;
			}
		}
		pacmanx = pacmanx + pacmanspeed * pacmandx;
		pacmany = pacmany + pacmanspeed * pacmandy;
	}
}
