package Package6;

import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 500;
    private JMenuItem pauseMenuItem;
    private JMenuItem resumeMenuItem;
    private JMenuItem addBossMenuItem;
    private JMenuItem deleteBossMenuItem;
    private Field field = new Field();

    public MainFrame() {
        super("Balls");
        this.setSize(700, 500);
        Toolkit kit = Toolkit.getDefaultToolkit();
        this.setLocation((kit.getScreenSize().width - 700) / 2, (kit.getScreenSize().height - 500) / 2);
        this.setExtendedState(6);
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        JMenu ballMenu = new JMenu("Мячи");
        Action addBallAction = new AbstractAction("Добавить мяч") {
            public void actionPerformed(ActionEvent event) {
                MainFrame.this.field.addBall();
                if (!MainFrame.this.pauseMenuItem.isEnabled() && !MainFrame.this.resumeMenuItem.isEnabled()) {
                    MainFrame.this.pauseMenuItem.setEnabled(true);
                }

            }
        };
        menuBar.add(ballMenu);
        ballMenu.add(addBallAction);
        JMenu bossMenu = new JMenu("Блок");
        Action addBossAction = new AbstractAction("Добавить блок") {
            public void actionPerformed(ActionEvent event) {
                MainFrame.this.field.addBoss();
                MainFrame.this.pauseMenuItem.setEnabled(true);
                MainFrame.this.resumeMenuItem.setEnabled(false);
            }
        };
        this.addBossMenuItem = bossMenu.add(addBossAction);
        Action deleteBossAction = new AbstractAction("Удалить блоки") {
            public void actionPerformed(ActionEvent event) {
                MainFrame.this.field.deleteBosses();
            }
        };
        this.deleteBossMenuItem = bossMenu.add(deleteBossAction);
        this.deleteBossMenuItem.setEnabled(true);
        menuBar.add(bossMenu);
        JMenu controlMenu = new JMenu("Управление");
        menuBar.add(controlMenu);
        Action pauseAction = new AbstractAction("Приостановить движение") {
            public void actionPerformed(ActionEvent event) {
                MainFrame.this.field.pause();
                MainFrame.this.pauseMenuItem.setEnabled(false);
                MainFrame.this.resumeMenuItem.setEnabled(true);
            }
        };
        this.pauseMenuItem = controlMenu.add(pauseAction);
        this.pauseMenuItem.setEnabled(false);
        Action resumeAction = new AbstractAction("Возобновить движение") {
            public void actionPerformed(ActionEvent event) {
                MainFrame.this.field.resume();
                MainFrame.this.pauseMenuItem.setEnabled(true);
                MainFrame.this.resumeMenuItem.setEnabled(false);
            }
        };
        this.resumeMenuItem = controlMenu.add(resumeAction);
        this.resumeMenuItem.setEnabled(false);
        this.getContentPane().add(this.field, "Center");
    }

    public static void main(String[] args) {
        MainFrame frame = new MainFrame();
        frame.setDefaultCloseOperation(3);
        frame.setVisible(true);
    }
}

