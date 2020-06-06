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
import Source.Engine.Vector2;
import Source.World.GameObjects.BulletTypes.DirectionalShot;


public class RangedEnemy extends GameObject{
  Rectangle hitBox;
  int range = 400;
  int hp = 15;
  float ms;
  int cooldown;
  boolean aggroed = false;
  public RangedEnemy(int x, int y, ID id, Handler handler) {
    super(x,y,id,handler);
    hitBox = new Rectangle(x,y,32,32);
    velX = 1;
    velY = 1;
    Vector2 v = new Vector2(velX,velY);
    ms = (float) v.getLength();
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
          hitBox.x -= Math.signum(velX);
          hitBox.y -= Math.signum(velY); 
          velX = -velX;
          velY = -velY;
        } 
      }
      if(tempObject.getID()==ID.Shot){ 
        if (hitBox.intersects(tempObject.getBounds())){ 

          while (!hitBox.intersects(tempObject.getBounds())){ 
            hitBox.x += Math.signum(velX);
            hitBox.y += Math.signum(velY); 
          } 
          hitBox.x -= Math.signum(velX); 
          hitBox.y -=Math.signum(velY);
          this.hp -= 5;
          handler.removeObject(tempObject);
        } 
      }
    }
    x = hitBox.x;
    y = hitBox.y; 
    }
    public void attackmovement() {
    Vector2 direction = Vector2.directionalvector(new Vector2(Game.player.getX()+40, Game.player.getY()+15),new Vector2(x,y));
    velX= (int)(direction.x*ms);     // Bewegt sich in richtung des Spielers ohner Ruecksicht auf Waende 
    velY=(int) (direction.y*ms);
    }
  public void kite() {
    Vector2 direction = Vector2.directionalvector(new Vector2(Game.player.getX()+40, Game.player.getY()+15),new Vector2(x,y));
    velX = -1*direction.x*ms;
    velY = -1* direction.y*ms;
    }
  public void shootatplayer () {
    //Vector2 v = new Vector2(Game.player.getX()+40,Game.player.getY()+15);
    Vector2 v =  Vector2.directionalvector(new Vector2(Game.player.getX()+40, Game.player.getY()+15),new Vector2(x,y));
    Vector2 offset = v;
    offset.normalize();
    offset.scale(50);
    handler.addObject(new DirectionalShot(this,offset,v,new Vector2 (x,y),ID.Shot,handler));  // ersellte einen neues Schussobjekt in Richting des Spielers
  }   
    public void checkhp () {
    if (hp<1) {
      handler.removeObject(this);
    } // end of if
    }
  public void render(Graphics g) {
    g.setColor(Color.red);
    g.fillRect((int)x, (int)y, 32, 32);
  }
  public Rectangle getBounds() {
    return new Rectangle(hitBox.x,hitBox.y,32,32);                                                    //Grenzen kriegen
  }
  public void sethp (int damage){ 
    this.hp -= damage;     
    }
  public void tick(){ 
    checkhp();

      if ((int)x-range<Game.player.getX()+40 && Game.player.getX() +40<(int)x+range && (int)y-range<Game.player.getY()+15 && Game.player.getY()+15<(int)y+range) {
        if (((int)x-range/2)<Game.player.getX()+40 && Game.player.getX()+40<((int)x+range/2) && ((int)y-range/2)<Game.player.getY() && Game.player.getY()<((int)y+range/2)) {
        aggroed=true;
        if (cooldown==0) {
          shootatplayer();
          cooldown = 60;
        } // end of if 
        else {
          kite();
          cooldown--;
        } // end of if-else 
        } // end of if
      else if (((int)x-50-range/2)<Game.player.getX()+40 && Game.player.getX()+40<((int)x+50+range/2) && ((int)y-50-range/2)<Game.player.getY() && Game.player.getY()<((int)y+50+range/2)) {
         if (cooldown==0) {
          shootatplayer();
          cooldown = 60;
        } // end of if 
        else {
          cooldown--;
        } // end of if-else 
      }
      else {
        attackmovement();
      } // end of if-else
      } // end of if
    else {
      if (aggroed==true) {
        velX=2;
        velY=2;
      } // end of if
    } // end of if-else
    collision();
    }  
  }
  
