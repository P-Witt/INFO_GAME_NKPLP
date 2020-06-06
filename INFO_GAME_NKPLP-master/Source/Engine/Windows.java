package Source.Engine;

import java.awt.Canvas;
import java.awt.Dimension;

import javax.swing.JFrame;

import Source.World.Game;

public class Windows extends Canvas 
{

  private static final long serialVersionUID = 802980368275219408L;
  
  public Windows(int width, int height, String title, Game game) 
  {
    JFrame frame = new JFrame(title);
    frame.setPreferredSize(new Dimension(width, height));
    frame.setMaximumSize(new Dimension(width, height));
    frame.setMinimumSize(new Dimension(width, height));
    
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
    frame.setUndecorated(true);                         // Spezifikationen f�r das Fenster
    frame.setResizable(false);
    frame.setLocationRelativeTo(null);
    frame.add(game);
    frame.setVisible(true);
    game.start();
  }
  
}
