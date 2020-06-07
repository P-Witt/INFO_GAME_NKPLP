package Source.World;

import java.util.Random;

import Source.Engine.Handler;
import Source.Engine.ID;
import Source.Engine.UI.HUD;
import Source.World.GameObjects.Enemies.*;

public class Spawn {
  private Handler handler;
  private HUD hud;
  private Random random=new Random();
  
  private int scoreKeep=0;
  public Spawn(Handler handler, HUD hud) {
    this.handler = handler;
    this.hud = hud;
    
  }
  
  public void tick() {
    scoreKeep++;
    if (scoreKeep>=150) {
      scoreKeep = 0;
      hud.setLevel(hud.getLevel()+1);
      for (int i = 2; i < 4; i++) {
        if(hud.getLevel()==i) {
          handler.addObject(new BasicEnemy(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), ID.BasicEnemy, handler));
        }
      }
      for (int i = 4; i < 7; i++) {
        if(hud.getLevel()==i) {
          handler.addObject(new FastEnemy(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), ID.FastEnemy, handler));                     //Je nach dem wie viel Zeit vergangen ist werden neue Gegner gespawnt
        }
      }
      for (int i = 7; i < 8; i++) {
        if(hud.getLevel()==i) {
          handler.addObject(new SmartEnemy(random.nextInt(Game.WIDTH), random.nextInt(Game.HEIGHT), ID.SmartEnemy, handler));
        }
      }
    }
  }
  
  
}
