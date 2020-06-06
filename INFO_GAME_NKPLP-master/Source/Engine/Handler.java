package Source.Engine;

import java.awt.Graphics;
import java.util.LinkedList;

import Source.World.GameObject;

public class Handler 
{
  public LinkedList<GameObject> objects = new LinkedList<GameObject>();
  public void tick() {
    for(int i = 0; i<objects.size(); i++) {
      GameObject tempObject = objects.get(i);
      tempObject.tick();                                //hier werden alle Tickmethoden getan.
    }
  }
  public void render(Graphics g) {
    for (int i = 0; i < objects.size(); i++) {
      GameObject tempObject = objects.get(i);
      tempObject.render(g);                             // Hier alle rendermethoden
    }   
  }
  public void addObject(GameObject object) {
    this.objects.add(object);
  }
  public void removeObject(GameObject object) {
    this.objects.remove(object);
  }
}

//Der handler ist daf�r da mit den Objekten um zu gehen. Also Hinzuf�gen, Entfernen. Auch Alle Objekte in einer Liste zu haben.
