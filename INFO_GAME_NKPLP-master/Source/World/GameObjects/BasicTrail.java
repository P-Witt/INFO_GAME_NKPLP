package Source.World.GameObjects;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import Source.Engine.Handler;
import Source.Engine.ID;
import Source.World.GameObject;

public class BasicTrail extends GameObject {
  
  private float alpha = 1;
  private Color color;
  private int width;
  private int height;
  private float life;
  //life = 0.01 -> 0.1
  
  
  public BasicTrail(int x, int y, ID id, Color color, int width, int height, float life, Handler handler) {
    super(x, y, id, handler);
    this.color = color;
    this.width = width;
    this.height = height;                                                     //Konstruktor
    this.life = life;
  }
  
  public void tick() {
    if(alpha>life) {
      alpha-= life -0.0001f;
    } else {                                                            //TAil wird gel�scht/Gelassen
      handler.removeObject(this);
    } 
  }
  
  public void render(Graphics g) {
    Graphics2D g2d = (Graphics2D) g;
    g2d.setComposite(makeTransparent(alpha));
    g.setColor(color);                                                        //Tail wird gezeichnet
    g.fillRect((int)x, (int)y, width, height);
    g2d.setComposite(makeTransparent(1));
  }
  
  
  
  private AlphaComposite makeTransparent(float alpha) {
    int type = AlphaComposite.SRC_OVER;
    return(AlphaComposite.getInstance(type, alpha));
  }
  
  public Rectangle getBounds() {
    return null;
  }
  
}
