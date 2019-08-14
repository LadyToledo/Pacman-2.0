/**
 * 
 */
package pacman;

/**
 * @author Lady Toledo
 *
 */
import java.awt.EventQueue;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class PlayGame extends JFrame {

    public PlayGame() {
        initUI();
    }
    
    private void initUI() {
    	//add(new Tablero());
        add(new MegaTablero());
    	//add(new Juego());
    	//add(new Game());
    	
        setTitle("PACMAN");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(380, 420);
        setLocationRelativeTo(null);
        setVisible(true);        
    }

	/**
	 * @param args
	 */
    public static void main(String[] args) {

       EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
            	PlayGame n = new PlayGame();
                n.setVisible(true);
            }
        });
    }
}