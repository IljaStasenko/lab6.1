package Package6;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Field extends JPanel {
    private boolean paused;
    private ArrayList<BouncingBall> balls = new ArrayList(10);
    private Timer repaintTimer = new Timer(10, new ActionListener() {
        public void actionPerformed(ActionEvent ev) {
            Field.this.repaint();
        }
    });
    private static int arrayH = 450;
    private static int arrayW = 450;
    private boolean paused1;
    private boolean resumeLol;
    private boolean bossFlag = false;
    private Block[][] ArrayOfBoss;

    public static int getArrayH() {
        return arrayH;
    }

    public static int getArrayW() {
        return arrayW;
    }

    public Block[][] getArrayOfBoss() {
        return this.ArrayOfBoss;
    }

    public void deleteBoss(int x, int y) {
        this.ArrayOfBoss[x][y] = null;
    }

    public void addBoss() {
        Block boss;
        do {
            boss = new Block(this);
        } while(this.ArrayOfBoss[boss.getX()][boss.getY()] != null);

        this.ArrayOfBoss[boss.getX()][boss.getY()] = boss;
    }

    public void deleteBosses() {
        for(int i = 0; i < arrayH; ++i) {
            for(int j = 0; j < arrayW; ++j) {
                if (this.ArrayOfBoss[i][j] != null) {
                    this.ArrayOfBoss[i][j] = null;
                }
            }
        }

    }

    public Field() {
        this.ArrayOfBoss = new Block[arrayH][arrayW];
        this.setBackground(Color.WHITE);
        this.repaintTimer.start();
    }

    public void addBall() {
        this.balls.add(new BouncingBall(this));
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D canvas = (Graphics2D)g;
        Iterator var4 = this.balls.iterator();

        while(var4.hasNext()) {
            BouncingBall ball = (BouncingBall)var4.next();
            ball.paint(canvas);
        }

        for(int i = 0; i < arrayH; ++i) {
            for(int j = 0; j < arrayW; ++j) {
                if (this.ArrayOfBoss[i][j] != null) {
                    this.ArrayOfBoss[i][j].paint(canvas);
                }
            }
        }

    }

    public synchronized void pause() {
        this.paused1 = true;
        this.resumeLol = false;
        this.paused = true;
    }

    public synchronized void canMove(BouncingBall ball) throws InterruptedException {
        if (this.paused) {
            ball.getColor();
            this.wait();
        }

    }

    public synchronized void canMove(Block ball) throws InterruptedException {
        if (this.paused) {
            this.wait();
        }

    }

    public synchronized void resume() {
        this.paused1 = false;
        this.paused = false;
        this.notifyAll();
    }
}
