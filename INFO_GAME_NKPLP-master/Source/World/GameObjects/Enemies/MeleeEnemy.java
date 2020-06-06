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

public class MeleeEnemy extends GameObject{
  Rectangle hitBox;
  int hp = 15;
  public MeleeEnemy(int x, int y, ID id, Handler handler) {
    super(x,y,id,handler);
    hitBox = new Rectangle(x,y,16,16);
    }
  public void collision() {
    hitBox.x += velX;
    hitBox.y += velY;  
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
      if(tempObject.getID()==ID.Shot){ 
        if (hitBox.intersects(tempObject.getBounds())){ 
          hitBox.x -= velX;
          hitBox.y -= velY; 
          while (!hitBox.intersects(tempObject.getBounds())){ 
            hitBox.x += Math.signum(velX);
            hitBox.y += Math.signum(velY); 
          } 
          hitBox.x -= Math.signum(velX); 
          hitBox.y -=Math.signum(velY);
          this.hp -= 5;
        } 
      } 
    }
    }
     
    public void checkhp () {
    if (hp<1) {
      handler.removeObject(this);
    } // end of if
    }
  public void render(Graphics g) {
    g.setColor(Color.green);
    g.fillRect((int)x, (int)y, 16, 16);
  }
  public Rectangle getBounds() {
    return new Rectangle((int)x,(int)y,32,32);                                                    //Grenzen kriegen
  }
  public void tick(){
    }
  }
  
