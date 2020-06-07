package Source.World.GameObjects.BulletTypes;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import Source.Engine.Handler;
import Source.Engine.ID;
import Source.Engine.Vector2;
import Source.World.GameObject;
import Source.World.GameObjects.BasicTrail;

public class DirectionalShot extends GameObject{  //Schuss in richtung Maus (Piet)
  
  private Vector2 dir = new Vector2(0,0);
  private GameObject owner;
  private float speed, despawnTimer = 10000;
  
  Rectangle hitBox;
  
  public DirectionalShot(GameObject owner, Vector2 target, ID id, Handler handler) {
    super((int)owner.x, (int)owner.y, ID.Shot, handler);
    dir = Vector2.subtract(target, Vector2.getPos(owner));
    speed = 15;
    this.owner = owner;
    dir.normalize();
    hitBox = new Rectangle((int)x, (int)y, 16, 16);
  }

  public DirectionalShot(GameObject owner,Vector2 offset, Vector2 target, ID id, Handler handler) {
    super((int)owner.x + (int)offset.x, (int)owner.y + (int)offset.y, ID.Shot, handler);
    dir = Vector2.subtract(target,Vector2.getPos(owner));
    speed = 15;
    this.owner = owner;
    dir.normalize();
    hitBox = new Rectangle((int)x, (int)y, 16, 16);
  }
  public DirectionalShot(GameObject owner, Vector2 offset, Vector2 target, Vector2 position, ID id, Handler handler){
    super((int)owner.x + (int)offset.x, (int)owner.y + (int)offset.y, ID.Shot, handler);
    dir = target;
    speed = 15;
    this.owner = owner;
    dir.normalize();
    hitBox = new Rectangle((int)x, (int)y, 16, 16);
    }
  public void tick() {
    hitBox.x = (int)x; 
    hitBox.y = (int)y;
    collision();
    x += dir.x * speed;
    y += dir.y * speed;
    handler.addObject(new BasicTrail((int)x+4, (int)y+4, ID.Trail, Color.blue, 8, 8, 0.08f, handler));
    if(despawnTimer < 0){handler.removeObject(this);}
    else{despawnTimer--;}
  }
  
  public void collision() {
    hitBox.x += velX; 
    for (int i = 0;i < handler.objects.size();i++) {         
      GameObject tempObject = handler.objects.get(i); 
      if(tempObject.getID()==ID.Wall || tempObject.getID()==ID.Door){ 
        if (hitBox.intersects(tempObject.getBounds())){ 
          hitBox.x -= velX; 
          while (!hitBox.intersects(tempObject.getBounds())){ 
            hitBox.x += Math.signum(velX); 
          } 
          hitBox.x -= Math.signum(velX);
          handler.removeObject(this); 
          } // end of if 
        } 
      }
     
    
    hitBox.y += velY; 
    for (int i = 0;i < handler.objects.size();i++) {         
      GameObject tempObject = handler.objects.get(i); 
      if(tempObject.getID()==ID.Wall || tempObject.getID()==ID.Door){ 
        if (hitBox.intersects(tempObject.getBounds())){ 
          hitBox.y -= velY; 
          while (!hitBox.intersects(tempObject.getBounds())){ 
            hitBox.y += Math.signum(velY); 
          } 
          hitBox.y -= Math.signum(velY); 
          handler.removeObject(this); 
        } 
      } 
    } 
  }
  public Rectangle getBounds() {
    return hitBox;
    }
  public void render(Graphics g) {
  g.setColor(Color.blue);
    g.fillRect((int)x, (int)y, 16, 16);                                                                   //Darstellung
    }
  
}
