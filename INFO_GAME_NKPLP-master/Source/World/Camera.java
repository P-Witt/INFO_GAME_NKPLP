package Source.World;

public class Camera {
  private float x, y;
  
  public Camera (float x, float y) {
    this.x = x;
    this.y = y;
    }
  
  public void setX(float x) {
    this.x = x;
    }
  
  public void setY(float y) {
    this.y = y;
    }
  
  public float getX() {
    return x;
    }
  
  public float getY() {
    return y;
    }
  
  public void tick(GameObject Player){     //Passt das Bild an den Player an
    x = -Player.getX() + 1920/2;
    y = -Player.getY() + 1080/2;
    }
  }
