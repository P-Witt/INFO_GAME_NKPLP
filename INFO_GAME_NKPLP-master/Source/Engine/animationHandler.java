package Source.Engine;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.AffineTransformOp;

import javax.imageio.ImageIO;

public class animationHandler { // Nachfragen an Piet richten
  
  public String curPlaying;
  
  private BufferedImage spriteSheet, curImage;
  private List<String> animNames = new ArrayList<String>();
  private List<Object[]> animQueue = new ArrayList<Object[]>();
  private List<Vector2> animFrameStamps = new ArrayList<Vector2>();
  private int first,last,curFrame,gridSize, timer;
  private float speed;
  private boolean repeat,flipped, processing = false;
  
  public animationHandler(String spriteSheetURL, int gridSize){   //"spriteSheetURL" ist der relative Pfad der Bilddatei und "gridSize" die größe eines Frames in Pixel.
    this.gridSize = gridSize;
    try {
      spriteSheet = loadImage(spriteSheetURL);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  private BufferedImage loadImage(String path) throws IOException {   //Spritesheet laden
    //return ImageIO.read(getClass().getResource(path));
    return ImageIO.read(new FileInputStream(path));
  }
  
  private BufferedImage grabImage(int col, int row, int width, int height){   //Sprite aus dem Spritesheet lesen
    return spriteSheet.getSubimage(col*gridSize -gridSize, row*gridSize -gridSize, width, height);
  }

  public void createAnimation(String name, int first, int last){  //im Konstruktor jedes Objektes mit Animationen, müssen die Animationen definiert werden mit name(z.B. "laufen","springen","schlafen"), anfangsframe und endframe auf dem Spritesheet
    animNames.add(name);
    animFrameStamps.add(new Vector2(first,last));
  }
  
  public void playAnimation(String name, float speed, boolean repeat, boolean interrupt){ //name = identificator der Animation; speed = geschw. der Animation; repeat = bestimmt, ob die Animation einmal abgespielt wird oder in Schleife gespielt wird; interrupt = falls wahr wird diese animation sofort gespielt (wird nicht am ende, sondern am anfang der Schlange gestellt)
    Object[] animation = new Object[]{                                                  //Aufrufen, um eine Animation in die Warteschlange der Animationen zu stellen.
    name,
    (int)animFrameStamps.get(animNames.indexOf(name)).x,
    (int)animFrameStamps.get(animNames.indexOf(name)).y,
    speed,
    repeat,
    };
    if(interrupt){
      List<Object[]> copy = new ArrayList<Object[]>();
      copy.addAll(animQueue);
      animQueue.clear();
      animQueue.add(animation);
      animQueue.addAll(copy);
      processing = false;
    }else{
      animQueue.add(animation);
    }
  }

  private Vector2 indexToPos(int index){  // die nummerierung in Leserichtung umformatieren zu x und y coordinaten
    int columns = spriteSheet.getWidth() / gridSize;
    int row = 1;
    while(index > columns){
      row++;
      index-=columns;
    }
    return new Vector2(index,row);
  }

  public void tick(){ //die Logik des Animationshandlers. Bitte diese tick() Klasse in denen des zu animirenden Objektes aufrufen.
    if(animQueue.size()>0)
    {
      if(!processing){    //falls gerade keine Animation spielt (und in animQueue etwas drin ist) kann die nächste animation gespielt werden.
        processing = true;
        curPlaying = (String)animQueue.get(0)[0];
        first = (int)animQueue.get(0)[1];
        last = (int)animQueue.get(0)[2];
        speed = (float)animQueue.get(0)[3];
        repeat = (boolean)animQueue.get(0)[4];
        curFrame = first;
        timer =0;
      }
      timer++;
      if(timer > speed * 100){
        timer = 0;
        if(curFrame < last){ curFrame++; }  //falls der letzte Frame ereicht wurde:
        else if(repeat){ curFrame = first; }    //fange von vorne an,
        else{
          animQueue.remove(0);    //oder lösche diese Animation aus der Schlange
          processing = false;
        }
      }
      Vector2 imgPos = indexToPos(curFrame);
      curImage = (flipped)? ImageFlip(grabImage((int)imgPos.x, (int)imgPos.y, gridSize, gridSize)) : grabImage((int)imgPos.x, (int)imgPos.y, gridSize, gridSize); // zeige das Bild gespiegelt, falls "flipped" true ist
    }
  }

  private BufferedImage ImageFlip(BufferedImage img){ //gibt das gegebene Bild gespiegelt zurück
    AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
    tx.translate( -img.getHeight(null),0);
    AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
    return op.filter(img, null);
  }

  public void flip(){flipped = !flipped;}
  public void faceLeft(){flipped = true;}
  public void faceRight(){flipped = false;}

  public void draw(Graphics g,int x, int y, int size){    //dieses draw() muss in denen des zu animirenden Objektes aufrufen werden
    g.drawImage(curImage, x, y, size, size, null);  
  }

  public void draw(Graphics g,int x, int y, int Xoffset, int Yoffset, int size){
    g.drawImage(curImage, x + Xoffset, y + Yoffset, size, size, null);  
  }

  public void draw(Graphics g,int x, int y){
    g.drawImage(curImage, x, y, 100, 100, null);  
  }
} 
