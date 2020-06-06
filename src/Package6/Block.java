package Package6;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.geom.GeneralPath;
import java.util.Random;

public class Block {
    private static final int minLength = 50;
    private static final int maxLength = 200;
    private static final int minWidth = 50;
    private static final int maxWidth = 200;
    private Field field;
    private int length;
    private int width;
    private Color color;
    private int prochnost = 8;
    public int flagNow;
    public int flagNext;
    private int x;
    private int y;
    Random r;

    public int getLength() {
        return this.length;
    }

    public int getWidth() {
        return this.width;
    }

    public int getProchnost() {
        return this.prochnost;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Block(Field field) {
        this.flagNow = this.prochnost;
        this.flagNext = 9;
        this.r = new Random();
        this.field = field;
        this.length = this.r.nextInt(150) + 50;
        this.width = this.r.nextInt(150) + 50;
        this.color = new Color((float)Math.random(), (float)Math.random(), (float)Math.random());
        this.x = this.r.nextInt(Field.getArrayH());
        this.y = this.r.nextInt(Field.getArrayW());
    }

    public void paint(Graphics2D canvas) {
        canvas.setColor(this.color);
        canvas.setPaint(this.color);
        GeneralPath kirp = new GeneralPath();
        kirp.moveTo((float)this.x, (float)this.y);
        kirp.lineTo((float)this.x, (float)(this.y + this.width));
        kirp.lineTo((float)(this.x + this.length), (float)(this.y + this.width));
        kirp.lineTo((float)(this.x + this.length), (float)this.y);
        kirp.closePath();
        canvas.draw(kirp);
        canvas.fill(kirp);
        canvas.setColor(Color.BLACK);
        Font myFont = new Font("Broadway", 1, 24);
        canvas.setFont(myFont);
        if (this.flagNow == this.flagNext) {
            --this.flagNext;
            --this.prochnost;
        }

        String s = String.valueOf(this.prochnost);
        canvas.drawString(s, this.x + 10, this.y + 20);
    }
}
