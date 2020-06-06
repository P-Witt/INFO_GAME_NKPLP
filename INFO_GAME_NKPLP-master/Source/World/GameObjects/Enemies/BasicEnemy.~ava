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

public class BasicEnemy extends GameObject{
  
  boolean alive = true;
  Random random = new Random();
  
  Rectangle hitBox;
  
  public BasicEnemy(int x, int y, ID id, Handler handler) {
    super(x, y, id, handler);
    velX=2;                   //TODO
    velY=2;
    hitBox = new Rectangle(x, y, 32, 32);
  }
  
  public Rectangle getBounds() {
    return new Rectangle((int)x,(int)y,32,32);                                                    //Grenzen kriegen
  }
  public void tick() {
    hitBox.x = (int)x; 
    hitBox.y = (int)y;
    collision();
    x+=velX;
    y+=velY;
    if(y<=0 || y>=Game.HEIGHT-32) velY *=-1;
    if(x<=0 || x>=Game.WIDTH-32) velX *=-1;
    handler.addObject(new BasicTrail((int)x, (int)y, ID.Trail, Color.green, 32, 32, 0.08f, handler));
  }
  public void render(Graphics g) {
    if(alive) {
      g.setColor(Color.green);
    }
    else {                                                            //arbe und gr��e setzzen
      g.setColor(Color.red);
    }
    g.fillRect((int)x, (int)y, 16, 16);
  }
  public void collision() {
    hitBox.x += velX;
    hitBox.y += velY; 
    for (int i = 0;i < handler.objects.size();i++) {         
      GameObject tempObject = handler.objects.get(i); 
      if(tempObject.getID()==ID.Wall){ 
        if (hitBox.intersects(tempObject.getBounds())){
        while (!hitBox.intersects(tempObject.getBounds())){ 
            hitBox.x += Math.signum(velX);
            hitBox.y += Math.signum(velY);
          }  
          hitBox.x -= velX;
          hitBox.y -= velY;
          velY *=-1;
          velX *= -1; 
          x = hitBox.x;
          System.out.println("jgbshdbh"); 
        } 
      }
      if(tempObject.getID()==ID.Shot){ 
        if (hitBox.intersects(tempObject.getBounds())){ 
          hitBox.x -= velX; 
          while (!hitBox.intersects(tempObject.getBounds())){ 
            hitBox.x += Math.signum(velX);
            hitBox.y += Math.signum(velY); 
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
}
