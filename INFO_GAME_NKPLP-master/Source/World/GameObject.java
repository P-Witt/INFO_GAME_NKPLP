package Source.World;

import java.awt.Graphics;
import java.awt.Rectangle;

import Source.Engine.Direction;
import Source.Engine.Handler;
import Source.Engine.ID;
import Source.World.GameObjects.BulletTypes.Shot;
import Source.World.GameObjects.Player;

public abstract class GameObject 
{
  protected ID id;
  protected float velX, velY;
  public float x,y;
  public boolean dash;
  public Handler handler;
  
  
  public GameObject(float x, float y, ID id, Handler handler) 
  {
    this.x = x;
    this.y = y;
    this.id = id;
    this.handler = handler;
  }
  
  public abstract void tick();
  public abstract void render(Graphics g);
  
  public abstract Rectangle getBounds();
    
  public void setX(int x) {
    this.x = x; 
  }
  
  public void setY(int y) {
    this.y = y;
  }

  public void setID(ID id) {
    this.id = id;
  }
  
  public void setVelX(int velX) {
    this.velX = velX;
  }
  
  public void setVelY(int velY) {
    this.velY = velY;
  }
  
  public float getX() {
    return x;
  }
  
  public float getY() {
    return y;
  }
  
  public void teleport(Player player, int door){
    
  }

  public ID getID() {
    return id;
  }
  
  public float getVelX() {
    return velX;
  }
  
  public float getVelY() {
    return velY;
  }
  
  public void shoot(Direction direction) {
    handler.addObject(new Shot((int) x,(int) y, direction, ID.Shot, handler));
  }
}

// Hier von werden alle Spielobjekte abgeleitet. Und hier werden alle Variabeln, die sie gemein haben(x,y, GeschwindigketX, GeschwindigketY, ID und Handler) festgelegt. AUch die dazu geh�rigen Methoden(get- und Set-methoden).
