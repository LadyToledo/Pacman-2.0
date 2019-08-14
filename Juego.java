/**
 * 
 */
package pacman;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Event;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
/**
 * @author Lady Toledo
 *
 */
public class Juego extends JPanel implements ComponentesDeJuego,Constantes,ActionListener{
	
	private Timer timer; // tengo bloqueado lo que lo usa en tablero, solo lo ocupa juego
	
    private Pacman a;
	private Tablero b;
	private Fantasmas c;
	private Niveles e;

	private Image ii;
    private int pacanimdir = 1; //1
    private final int pacanimdelay = 2; //2
    private final int pacmananimcount = 4; //4
    private int pacanimcount = pacanimdelay;
    
    
	public boolean ingame = false;
	public boolean dying = false; //moribundo
	public int currentspeed = 3; //3, velocidad actual
	public int nrofghosts = 6;
	public int pacmananimpos = 0; //0
	
	public Dimension d;
	public Color mazecolor;
	public int pacsleft, score;
	public int reqdx, reqdy, viewdx, viewdy;
	public int pacmanx, pacmany, pacmandx, pacmandy;
	
    public int[] dx, dy;
    public short[] screendata;
    public int[] ghostx, ghosty, ghostdx, ghostdy, ghostspeed;
   
    public Image ghost;
    public Image pacman1, pacman2up, pacman2left, pacman2right, pacman2down;
    public Image pacman3up, pacman3down, pacman3left, pacman3right;
    public Image pacman4up, pacman4down, pacman4left, pacman4right;

    public void loadImages() {
        ghost = new ImageIcon(getClass().getResource("../images/ghost.gif")).getImage();
        pacman1 = new ImageIcon(getClass().getResource("../images/pacman.gif")).getImage();
        pacman2up = new ImageIcon(getClass().getResource("../images/up1.gif")).getImage();
        pacman3up = new ImageIcon(getClass().getResource("../images/up2.gif")).getImage();
        pacman4up = new ImageIcon(getClass().getResource("../images/up3.gif")).getImage();
        pacman2down = new ImageIcon(getClass().getResource("../images/down1.gif")).getImage();
        pacman3down = new ImageIcon(getClass().getResource("../images/down2.gif")).getImage();
        pacman4down = new ImageIcon(getClass().getResource("../images/down3.gif")).getImage();
        pacman2left = new ImageIcon(getClass().getResource("../images/left1.gif")).getImage();
        pacman3left = new ImageIcon(getClass().getResource("../images/left2.gif")).getImage();
        pacman4left = new ImageIcon(getClass().getResource("../images/left3.gif")).getImage();
        pacman2right = new ImageIcon(getClass().getResource("../images/right1.gif")).getImage();
        pacman3right = new ImageIcon(getClass().getResource("../images/right2.gif")).getImage();
        pacman4right = new ImageIcon(getClass().getResource("../images/right3.gif")).getImage();
    }
    public Juego() {
    	loadImages();
        initVariables();
        addKeyListener(new TAdapter());
        setFocusable(true);
        setBackground(Color.BLACK);
        setDoubleBuffered(true);
    }
    @Override
    public void addNotify() {
        super.addNotify();
        juegoInicial();
    }
	//showIntroScreen -> mostrar pantalla de introduccion
    public void showIntroScreen(Graphics2D g2d) {
        g2d.setColor(new Color(0, 32, 48));
        g2d.fillRect(50, scrsize / 2 - 30, scrsize - 100, 50);
        // g2d.fillRect(50, scrsize / 2 - 30, scrsize - 100, 50);
        g2d.setColor(Color.white);
        g2d.drawRect(50, scrsize / 2 - 30, scrsize - 100, 50);

        String x = "Presiona X para empezar.";
        Font small = new Font("Helvetica", Font.BOLD, 15);
        FontMetrics metr = this.getFontMetrics(small);

        g2d.setColor(Color.white);
        g2d.setFont(small);
        g2d.drawString(x, (scrsize - metr.stringWidth(x)) / 2, scrsize / 2);
    }
	@Override
	public void tablero() {
		
	}
	private void initVariables() {

        screendata = new short[nrofblocks * nrofblocks];
        mazecolor = new Color(0, 0, 255); //color laberinto
        d = new Dimension(400, 400); //no paso nada
        ghostx = new int[maxghosts];
        ghostdx = new int[maxghosts];
        ghosty = new int[maxghosts];
        ghostdy = new int[maxghosts];
        ghostspeed = new int[maxghosts];
        dx = new int[4]; //4
        dy = new int[4]; //4

        timer = new Timer(40, this);
        timer.start();
    }
	public void playGame (Graphics2D g2d)  {
		a=new Pacman();
		c=new Fantasmas();
        if (dying) {
            death();
        } else {
        	a.movePacman();
            a.drawPacman(g2d);
            c.moveGhosts(g2d);
            checkMaze();
        }
    }
	//comprobar laberinto
    private void checkMaze() {
        short i = 0;
        boolean finished = true;

        while (i < nrofblocks * nrofblocks && finished) {
            if ((screendata[i] & 48) != 0) {
                finished = false;
            }
            i++;
        }

        if (finished) {
            score += 50;
            if (nrofghosts < maxghosts) {
                nrofghosts++;
            }
            if (currentspeed < maxspeed) {
                currentspeed++;
            }
            nivelDeInicio();
        }
    }
	 private void death() {//murio
	        pacsleft--;

	        if (pacsleft == 0) {
	            ingame = false;
	        }
	        continuarNivel();
	    }
	@Override
	public void niveles() {
		
	}

	@Override
	public void pacman() {
		
	}
	@Override
	public void fantasmas() {
		
	}
	@Override
	public void juegoInicial() { //init game
		pacsleft = 3; //3, vidas
        score = 0; //puntaje inicial
        nivelDeInicio();
        nrofghosts = 3; //numero de fantasmas
        currentspeed = 3; //velocidad actual
		
	}
	@Override
	public void nivelDeInicio() {
		e=new Niveles();
		e.initLevel();
		
	}
	@Override
	public void continuarNivel() {
		e=new Niveles();
        e.continueLevel();
		
	}
	@Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
	public void doDrawing(Graphics g) {
		b=new Tablero();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.black);
        g2d.fillRect(0, 0, d.width, d.height); //ocupan dimension dx
        b.drawMaze(g2d);
        b.drawScore(g2d);
        doAnim();

        if (ingame) {
            playGame(g2d);
        } else {
            showIntroScreen(g2d);
        }

        g2d.drawImage(ii, 5, 5, this);
        Toolkit.getDefaultToolkit().sync();
        g2d.dispose();
    }
    private void doAnim() {

        pacanimcount--;

        if (pacanimcount <= 0) {
            pacanimcount = pacanimdelay;
            pacmananimpos = pacmananimpos + pacanimdir;

            if (pacmananimpos == (pacmananimcount - 1) || pacmananimpos == 0) {
                pacanimdir = -pacanimdir;
            }
        }
    }

	 /**teclas que controlan el juego */
    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            int key = e.getKeyCode();

            if (ingame) {
                if (key == KeyEvent.VK_LEFT) {
                    reqdx = -1;
                    reqdy = 0;
                } else if (key == KeyEvent.VK_RIGHT) {
                    reqdx = 1;
                    reqdy = 0;
                } else if (key == KeyEvent.VK_UP) {
                    reqdx = 0;
                    reqdy = -1;
                } else if (key == KeyEvent.VK_DOWN) {
                    reqdx = 0;
                    reqdy = 1;
                
                // pensar si dejar esto
                }/** else if (key == KeyEvent.VK_ESCAPE && timer.isRunning()) {
                    ingame = false;
                } else if (key == KeyEvent.VK_PAUSE) {
                    if (timer.isRunning()) {
                        timer.stop();
                    } else {
                        timer.start();
                    }
                }*/
            /**letra con la que empieza el juego*/
            } else {
                if (key == 'x' || key == 'X') {
                    ingame = true;
                    juegoInicial();
                }
            }
        }
      //no se para que sirve
        @Override
        public void keyReleased(KeyEvent e) {
        	/*
            int key = e.getKeyCode();
            
            if (key == Event.LEFT || key == Event.RIGHT
                    || key == Event.UP || key == Event.DOWN) {
                reqdx = 0;
                reqdy = 0;
           
            }*/
        }
    }
    @Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
	
}
