package Source.Engine.UI;

import java.awt.Graphics;

import Source.Engine.Handler;
import Source.Engine.ID;

public class exitButton extends button {

    public exitButton(int x, int y, int w, int h, ID id, Handler handler) {
      super(x, y,w,h, id, handler);
      super.txt = "exit";
      super.img = new UIImage(x, y, w, h, id, "Content/UI/Exit.png", handler);
    }
    
    public void tick(){
      super.tick();
    }
    
    public void event(){System.exit(1);}

    public void render(Graphics g){
      super.render(g);
    }
}