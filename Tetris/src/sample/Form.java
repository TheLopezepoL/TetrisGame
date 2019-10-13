package sample;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;


public class Form {
    public Rectangle a;
    public Rectangle b;
    public Rectangle c;
    public Rectangle d;

    private static final Color[] colors= {Color.BLACK, Color.GREEN, Color.PURPLE, Color.BLUE,
                                          Color.YELLOW, Color.ORANGE, Color.RED, Color.GRAY};
    private Color color;
    private short type;
    public short rotation = 1;

    public Form (Rectangle a,Rectangle b,Rectangle c,Rectangle d){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.type = 0;
        setColor();
    }

    public Form (Rectangle a,Rectangle b,Rectangle c,Rectangle d, short type){
        this.a = a;
        this.b = b;
        this.c = c;
        this.d = d;
        this.type = type;
        setColor();
    }

    public void setColor (){
        this.color = colors[type];
        this.a.setFill(color);
        this.b.setFill(color);
        this.c.setFill(color);
        this.d.setFill(color);
    }

    public short getType(){
        return this.type;
    }

    public void rotate(){
        if (rotation != 4)
            this.rotation++;
        else
            this.rotation = 1;
    }
}
