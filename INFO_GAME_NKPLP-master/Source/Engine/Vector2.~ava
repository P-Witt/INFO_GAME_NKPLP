package Source.Engine;

import Source.World.GameObject;

public class Vector2 {
  
  public float x,y;
  public Vector2(float x,float y) { //generische 2d Vector klasse (Piet)
    this.x=x;
    this.y=y;
  }
  
    public void zero() {
        x = 0;
        y = 0;
    }
    
    public double getLength() {
        return Math.sqrt(x * x + y * y);
    }
    
    public static Vector2 add(Vector2 a, Vector2 b) {
      return new Vector2(a.x+b.x,a.y+b.y);
    }
    
    public static Vector2 subtract(Vector2 a, Vector2 b) {
      return new Vector2(a.x-b.x,a.y-b.y);
    }
    
    public static Vector2 getPos(GameObject g) {
    return new Vector2(g.x,g.y);
    }
    
    public void set(Vector2 a) {
      this.x = a.x;
      this.y = a.y;
    }
    
    public void set(float x, float y) {
      this.x = x;
      this.y = y;
    }
    
    public void normalize() {     //verkleinert den Vector zu 1x1 ohne die Richtung zu verlieren (Piet)
        double magnitude = getLength();
        x /= magnitude;
        y /= magnitude;
    }
}
