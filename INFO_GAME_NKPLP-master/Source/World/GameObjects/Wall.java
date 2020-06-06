package Source.World.GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.HierarchyBoundsAdapter;

import Source.Engine.Handler;
import Source.Engine.ID;
import Source.World.GameObject;

public class Wall extends GameObject{

  int width, height;
  public Wall(float x, float y, ID id, Handler handler, int width, int height) {
    super(x, y, id, handler);
    this.width = width;
    this.height = height;
  }
  public Rectangle getBounds() {
    return new Rectangle((int)x,(int)y,width,height);                         //Grenzen werden HierarchyBoundsAdapter entnommen
  }
  public void tick() {
    //collision();
  }
  public void render(Graphics g) {
    g.setColor(Color.gray);
    g.fillRect((int)x, (int)y, width, height);                                    //Graphische Darstellung
  } 
}
