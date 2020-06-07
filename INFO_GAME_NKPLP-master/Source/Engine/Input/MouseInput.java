package Source.Engine.Input;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import Source.Engine.Handler;
import Source.Engine.ID;
import Source.Engine.Vector2;
import Source.World.Game;
import Source.World.GameObjects.Player;
import Source.World.GameObjects.BulletTypes.DirectionalShot;

public class MouseInput extends MouseAdapter {

  private Handler handler;
  
  public MouseInput(Handler handler) {
    this.handler = handler;
  }
  
  public void mousePressed(MouseEvent e) {
    if(Player.itemPicked[1] && Game.curState == Game.states.play)handler.addObject(new DirectionalShot(Game.player,new Vector2(0, Game.player.hitBox.height / 2),new Vector2(e.getX() - Game.cam.getX(),e.getY() - Game.cam.getY()),ID.Shot,handler));
    Game.leftMousePressed = true;
  }

  public void mouseReleased(MouseEvent e) {
    Game.leftMousePressed = false;
  }  
}
