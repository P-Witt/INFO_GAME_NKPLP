package Source.World.GameObjects.Enemies;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import Source.Engine.Handler;
import Source.Engine.ID;
import Source.World.Game;
import Source.World.GameObject;
import Source.World.GameObjects.BasicTrail;

public class SmartEnemy extends GameObject{
  
  Random random = new Random();
  
  private GameObject player;
  
  Rectangle hitBox;
  
  public SmartEnemy(int x, int y, ID id, Handler handler) {
    super(x, y, id, handler);
    for (int i = 0; i < handler.objects.size(); i++) {
      if(handler.objects.get(i).getID()==ID.Player) player = handler.objects.get(i);
    }
    velX=4;
    velY=4;
    hitBox = new Rectangle(x, y, 32, 32);
  }
  
  public Rectangle getBounds() {
    return new Rectangle((int)x,(int)y,32,32);                                                      //Grenzen werden entnommen
  }
  public void tick() {
    hitBox.x = (int)x; 
    hitBox.y = (int)y; 
    collision();
    x+=velX;
    y+=velY;
    float diffX= x-player.getX() -8;
    float diffY= y-player.getY() -8;
    float distance = (float) Math.sqrt(((x-player.getX())*(x-player.getX()))+((y-player.getY())*(y-player.getY())));
    velX = (float) ((-1/distance)*diffX);
    velY = (float) ((-1/distance)*diffY);                                                                       //Normales Verhalten(Hier: Wird dem Spieler gefolgt)
    if(y<=0 || y>=Game.HEIGHT-32) velY *=-1;
    if(x<=0 || x>=Game.WIDTH-32) velX *=-1;
    handler.addObject(new BasicTrail((int)x, (int)y, ID.Trail, Color.orange, 32, 32, 0.08f, handler));
  }
  public void collision() {
    hitBox.x += velX;
    for (int i = 0;i < handler.objects.size();i++) {         
      GameObject tempObject = handler.objects.get(i); 
      if(tempObject.getID()==ID.Wall){ 
        if (hitBox.intersects(tempObject.getBounds())){ 
          hitBox.x -= velX;
          hitBox.y -=velY; 
          while (!hitBox.intersects(tempObject.getBounds())){ 
            hitBox.x += Math.signum(velX);
            hitBox.y += Math.signum(velY); 
          } 
          hitBox.x -= Math.signum(velX);
          hitBox.y -= Math.signum(velY); 
          //velX = 0; 
          x = hitBox.x;
          y = hitBox.y;
        } 
      }
      hitBox.y += velY;  
      if(tempObject.getID()==ID.Shot){ 
        if (hitBox.intersects(tempObject.getBounds())){ 
          hitBox.x -= velX;
          hitBox.y -= velY; 
          while (!hitBox.intersects(tempObject.getBounds())){ 
            hitBox.x += Math.signum(velX); 
          } 
          hitBox.x -= Math.signum(velX); 
          //collision code
          x = random.nextInt(Game.WIDTH - 32);
          y = random.nextInt(Game.HEIGHT - 32);                                                          //Kollision mit Schuss
          int velXr = Game.ranInt(0,1);
          if(velXr == 0) {velX*=-1;}
          int velYr = Game.ranInt(0,1);
          if(velYr == 0) {velX*=-1;}
        } 
      } 
    } 
     

  }
  public void render(Graphics g) {
    g.setColor(Color.orange);
    g.fillRect(hitBox.x, hitBox.y, 32, 32);                                                       //grafische Darstellung
  }
}
