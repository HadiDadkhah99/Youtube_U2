import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends JFrame {

    private JPanel background;
    private List<Snow> snows = new ArrayList<>();

    public Main() {

        setBounds(200, 200, 400, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(null);


        background = new JPanel();
        background.setBounds(0, 0, 400, 400);
        background.setBackground(Color.BLACK);
        background.setOpaque(true);
        background.setLayout(null);

        add(background);

        for (int i = 0; i <20 ; i++) {
            Snow snow = new Snow(new Random().nextInt(400), new Random().nextInt(50) - 50, new Random().nextInt(5) + 1);
            background.add(snow);
            snows.add(snow);
        }



        //thread
        new Thread(() -> {

            while (true) {

                update();

                //delay
                try {
                    Thread.sleep(20);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

        }).start();


    }

    public void update() {

        for (Snow s : snows) {
            s.updateOBJ();

            if (s.y > 400) {
                s.setXY(new Random().nextInt(400), new Random().nextInt(50) - 50);
                s.setSpeed(new Random().nextInt(5) + 1);
            }

        }

    }


    public static void main(String[] args) {

        new Main();

    }

    public static class Snow extends JComponent {

        public int x, y, speed;

        //size of snows
        public int SIZE = 12;

        public Snow(int x, int y, int speed) {
            this.x = x;
            this.y = y;
            this.speed = speed;

            setBounds(x, y, SIZE, SIZE);
            setLayout(null);
            setOpaque(true);
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.WHITE);
            g2.fillOval(SIZE / 2, SIZE / 2, SIZE / 2, SIZE / 2);

        }

        public void setXY(int x, int y) {
            this.x = x;
            this.y = y;
            setLocation(x, y);
            //this is for linux (Graphic card) !!!
            Toolkit.getDefaultToolkit().sync();
        }

        public void setSpeed(int speed) {
            this.speed = speed;
        }

        public void updateOBJ() {

            y += speed;
            setXY(x, y);

        }


    }


}
