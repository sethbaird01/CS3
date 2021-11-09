import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

class SierpinskiPanel extends JPanel {
    public SierpinskiPanel() {
        super.setPreferredSize(new Dimension(400, 400));
        super.setBackground(Color.WHITE);
    }

    int[][][] drawTri(Graphics g, int[][] dims) {
        
        // pass 3 dims of triangle to be drawn
        int x1 = dims[0][0]; int y1 = dims[0][1]; //top point (2, 0)
        int x2 = dims[1][0]; int y2 = dims[1][1]; //bottom point (2, 2)
        int x3 = dims[2][0]; int y3 = dims[2][1]; //left point (0, 2)

                    //     ( xy top pt)      ( xy bottom pt)     ( xy left pt)
        int[][] tri1 = {{(x2+x3)/2,(y1)}, {(x2+x3)/2,(y2+y1)/2}, {(x3),(y2+y1)/2}} ; // adjacent triangle to the top (from diagonal line)
        int[][] tri2 = {{(x2+x3)/2, (y2)}, {(x2+x3)/2, (y2+((y2-y1)/2))}, {(x3), (y2+((y2-y1)/2))}} ; // adjacent triangle to the left (from bottom line)
        int[][] tri3 = {{(x1+((x2-x3)/2)), (y1)}, {(x1+((x2-x3)/2)), (y2+y1)/2}, {(x1), (y2+y1)/2}} ; // adjacent triangle to the right (from right line)

        g.drawLine(x1, y1, x2, y2); // top to bottom
        g.drawLine(x2, y2, x3, y3); // bottom to left
        g.drawLine(x1, y1, x3, y3); // horizontal

        // returns the points of next 3 triangles

        int[][][] next = { tri1, tri2, tri3 };
        return next;

    }

    void drawSierpinksi(Graphics g, int[][] dims) {

        int y1 = dims[0][1]; //top y point of current triangle
        int y2 = dims[1][1]; //bottom y point of current triangle

        if (y2 - y1 < 10) {
            return;
        }

        int[][][] triangles = drawTri(g, dims);

        for (int[][] is : triangles) {
            drawSierpinksi(g, is);
        }

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        g.setColor(Color.BLUE);

        int h = getHeight();
        int w = getWidth();

        // ( xy top ) ( xy bottom ) ( xy left )

        int[][] dims = { { w, 0 }, { w, h }, { 0, h } };

        drawSierpinksi(g, dims);
    }
}

public class Sierpinski {

    public static void main(String[] args) {
        JFrame frame = new JFrame("Sierpinski");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(new SierpinskiPanel());
        frame.pack();
        frame.setVisible(true);
    }

}
