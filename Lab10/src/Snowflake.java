import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

class SnowFlakePanel extends JPanel {
    public SnowFlakePanel() {
        super.setPreferredSize(new Dimension(400, 400));
        super.setBackground(Color.WHITE);
    }

    int[][] drawStar(Graphics g, int x, int y, int scale) {
        // pass x and y of center

        int[][] out = new int[7][2];
        int iter = 0;

        int least = x;
        if (y < x) {
            least = y;
        }

        least = (int) (least * scale);

        for (int a = 0; a <= 360; a += 60) {
            double angle = Math.toRadians(a);

            g.drawLine(x, y, x + (int) (scale * Math.cos(angle)), y + (int) (scale * Math.sin(angle)));

            out[iter][0] = x + (int) (scale * Math.cos(angle));
            out[iter][1] = y + (int) (scale * Math.sin(angle));
            iter++;
        }

        // center point
        // g.setColor(Color.red);
        // g.drawOval(x-2, y-2, 4, 4);

        return out;
    }

    void drawFlake(Graphics g, int x, int y, int scale) {

        if (scale < 5) {
            return;
        }

        int[][] endpoints = drawStar(g, x, y, scale);

        for (int[] is : endpoints) {
            drawFlake(g, is[0], is[1], (int) (scale * 0.3));
        }
    }

    void drawBlizzard(Graphics g) {
        final double flakeSizeScalar = 0.08;
        final double flakeCountScalar = 0.00007;

        int width = getWidth();
        int height = getHeight();
        int numFlakes = (int) (width * height * flakeCountScalar);
        Random r = new Random();

        int least = height;
        if (width < height) {
            least = width;
        }

        System.out.println("flakes: " + numFlakes);

        for (int i = 0; i < numFlakes; i++) {
            int x = r.nextInt(width);
            int y = r.nextInt(height);
            int scale = (int) (flakeSizeScalar * least * r.nextDouble());
            // restrict color to blue hues
            float rand = 0.5f + (0.75f - 0.5f) * r.nextFloat();
            Color c = Color.getHSBColor(rand, 1, (float) (0.9));

            g.setColor(c);
            drawFlake(g, x, y, scale);
        }

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        // g.setColor(Color.BLUE);
        // int wCtr = getWidth() / 2;
        // int hCtr = getHeight() / 2;
        // drawFlake(g, wCtr, hCtr, (int) (wCtr*0.5));

        drawBlizzard(g);
    }
}

public class Snowflake {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Snowflake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SnowFlakePanel());
        frame.pack();
        frame.setVisible(true);
    }

}
