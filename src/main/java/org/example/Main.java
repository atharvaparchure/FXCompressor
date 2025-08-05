package org.example;

import org.example.ShipPackerUnpacker.Packer;
import org.example.ShipPackerUnpacker.UnPacker;

import javax.swing.*;
import javax.swing.border.*;
import javax.sound.sampled.*;
import java.awt.*;
import java.awt.event.*;
import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;
import javafx.embed.swing.JFXPanel;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class Main {

    public static void main(String[] args) {
        new JFXPanel(); // Initialize JavaFX for MediaPlayer
        playMusic("vice_city_intro.mp3");

        JFrame fobj = new JFrame("𝞴𝞊𝞑𝞛𝞘𝞒 𝞹𝞎𝞕𝞊 𝞹𝞒𝞕𝞘𝞕𝞘𝞜");
        fobj.setSize(600, 500);
        fobj.setLayout(null);
        fobj.setUndecorated(true);

        JPanel backgroundPanel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                URL imgUrl = getClass().getResource("/vice_city_background.jpg");
                ImageIcon icon = new ImageIcon(imgUrl);
                g.drawImage(icon.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        backgroundPanel.setBounds(0, 0, 600, 500);
        backgroundPanel.setLayout(null);

        JButton closeBtn = new JButton("✖");
        closeBtn.setBounds(560, 10, 30, 30);
        closeBtn.setForeground(Color.WHITE);
        closeBtn.setBackground(Color.RED);
        closeBtn.setBorder(new LineBorder(Color.BLACK, 2));
        closeBtn.setFocusPainted(false);
        closeBtn.setFont(new Font("Dialog", Font.BOLD, 14));
        closeBtn.addActionListener(e -> System.exit(0));
        backgroundPanel.add(closeBtn);

        JButton bobj1 = createStyledButton("📦 PACK", 200, 140, new Color(255, 105, 180));
        JButton bobj2 = createStyledButton("📁 UNPACK", 200, 220, new Color(0, 191, 255));
        backgroundPanel.add(bobj1);
        backgroundPanel.add(bobj2);

        addSoundEffect(bobj1, "click.mp3");
        addSoundEffect(bobj2, "click.mp3");

        addGlowEffect(bobj1);
        addGlowEffect(bobj2);

        JTextArea terminal = new JTextArea();
        terminal.setBounds(20, 400, 560, 80);
        terminal.setBackground(new Color(0, 0, 0, 180));
        terminal.setForeground(Color.GREEN);
        terminal.setFont(new Font("Consolas", Font.PLAIN, 14));
        terminal.setEditable(false);
        terminal.setBorder(new LineBorder(Color.GREEN));
        backgroundPanel.add(terminal);

        Timer logTimer = new Timer();
        logTimer.scheduleAtFixedRate(new TimerTask() {
            int count = 0;
            public void run() {
                terminal.append("> SYSTEM LOG: Ready for action...\n");
                if (++count > 5) cancel();
            }
        }, 0, 1000);

        JLabel title = new JLabel();
        title.setBounds(150, 30, 300, 50);
        title.setFont(new Font("Segoe UI", Font.BOLD, 30));
        title.setForeground(Color.BLACK);
        backgroundPanel.add(title);

        JLabel dragArea = new JLabel();
        dragArea.setBounds(0, 0, 500, 50);
        backgroundPanel.add(dragArea);

        dragArea.addMouseListener(new MouseAdapter() {
            Point clickPoint;
            public void mousePressed(MouseEvent e) {
                clickPoint = e.getPoint();
            }
        });

        dragArea.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                Point curr = e.getLocationOnScreen();
                fobj.setLocation(curr.x - dragArea.getX() - 10, curr.y - dragArea.getY());
            }
        });

        fobj.add(backgroundPanel);
        fobj.setVisible(true);
        fobj.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Trigger packing/unpacking only on click:
        bobj1.addActionListener(e -> {
            new Packer().Pack(bobj1);
        });

        bobj2.addActionListener(e -> {
            new UnPacker().UnPack(bobj2);
        });
    }

    public static JButton createStyledButton(String text, int x, int y, Color color) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 200, 50);
        btn.setBackground(color);
        btn.setForeground(Color.WHITE);
        btn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        btn.setFocusPainted(false);
        btn.setBorder(new RoundBorder(20));
        btn.setOpaque(true);

        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                btn.setBorder(new CompoundBorder(
                        new LineBorder(new Color(255, 255, 255, 180), 3, true),
                        new EmptyBorder(5, 15, 5, 15)));
                btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
                btn.setBackground(color.brighter());
            }

            public void mouseExited(MouseEvent e) {
                btn.setBorder(new RoundBorder(20));
                btn.setBackground(color);
            }
        });
        return btn;
    }

    public static void addSoundEffect(JButton btn, String soundFileName) {
        btn.addActionListener(e -> {

            try (InputStream is = Main.class.getResourceAsStream("click.mp3" + soundFileName)) {
                if (is == null) {
                    System.out.println("Sound file not found: " + soundFileName);
                    return;
                }
                AudioInputStream audioIn = AudioSystem.getAudioInputStream(new BufferedInputStream(is));
                Clip clip = AudioSystem.getClip();
                clip.open(audioIn);
                clip.start();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
    }

    public static void addGlowEffect(JButton btn) {
        Timer glowTimer = new Timer();
        glowTimer.scheduleAtFixedRate(new TimerTask() {
            boolean glow = false;
            public void run() {
                SwingUtilities.invokeLater(() -> {
                    btn.setBorder(glow ? new LineBorder(Color.CYAN, 3, true) : new LineBorder(Color.MAGENTA, 3, true));
                    glow = !glow;
                });
            }
        }, 0, 700);
    }

    public static void playMusic(String fileName) {
        try {
            URL resource = Main.class.getResource("/" + fileName);
            if (resource == null) {
                System.err.println("File not found in resources: " + fileName);
                return;
            }
            URI uri = resource.toURI();
            Media media = new Media(uri.toString());
            MediaPlayer player = new MediaPlayer(media);
            player.setCycleCount(MediaPlayer.INDEFINITE);
            player.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static class RoundBorder implements Border {
        private final int radius;

        RoundBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        public boolean isBorderOpaque() {
            return true;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}
