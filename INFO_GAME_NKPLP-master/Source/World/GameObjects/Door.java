package Source.World.GameObjects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.HierarchyBoundsAdapter;

import Source.World.Game;
import Source.Engine.Handler;
import Source.Engine.ID;
import Source.World.GameObject;

public class Door extends GameObject{
  
  int width, height;
  public float TPLocX, TPLocY;
  public int teleportID;
  private int doorFacing; //0 = norden, 1 = osten, 2 = sueden, 3 = westen.
  
  
  
  public Door(float x, float y, ID id, Handler handler, int width, int height, float teleportPosX, float teleportPosY, int teleportID, int doorFacing) {
    super(x, y, id, handler);
    this.width = width;
    this.height = height;
    this.TPLocX = teleportPosX;
    this.TPLocY = teleportPosY;
    this.teleportID = teleportID;
    this.doorFacing = doorFacing;
  }
  
  public int getTeleportID(){
    return teleportID;
  }
  
  public Rectangle getBounds() {
    return new Rectangle((int)x,(int)y,width,height);                         //Grenzen werden HierarchyBoundsAdapter entnommen
  }
  
  public void tick() {
    //collision();
  }
  
  public void teleport(Player player, int door) {
    for (int i = 0;i < handler.objects.size();i++) {         
      if(handler.objects.get(i) instanceof Door){
        Door tempDoor = (Door)handler.objects.get(i);
        if (i != door && tempDoor.teleportID == this.teleportID) {
          float tempX = tempDoor.TPLocX;
          float tempY = tempDoor.TPLocY;
          if (this.doorFacing == 2) {
            tempX -= player.playerLength/2;
          }
          else if (this.doorFacing == 3) {
            tempX -= player.playerLength;
            tempY -= player.playerHeight/2;
          }
          else if (this.doorFacing == 0){
            tempX -= player.playerLength/2;
            tempY -= player.playerHeight;
          }
          else if (this.doorFacing == 1) {
            tempY -= player.playerHeight/2;
          }
          
          player.x = tempX;
          player.y = tempY;
        }
      } 
    }
  }
  
  public void render(Graphics g) {
    g.setColor(Color.magenta);
    g.fillRect((int)x, (int)y, width, height);                                    //Graphische Darstellung
    if (Game.debug) {
      g.fillRect((int)TPLocX, (int)TPLocY, 3, 3);
      g.setColor(Color.WHITE);
      g.drawString(Integer.toString(teleportID), (int)x, (int)y);
    }
  } 
}
