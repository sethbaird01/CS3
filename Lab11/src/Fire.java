import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
    this is the Controller component
*/

class Fire extends JFrame {

    private FireView view;
    private FireModel model;

    Fire() {
        super("Forest Fire");

        // build the view
        view = new FireView();
        view.setBackground(Color.white);
        Container c = getContentPane();
        c.add(view, BorderLayout.CENTER);

        // build the model
        model = new FireModel(view);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.setSize(570, 640);
        // smokey.show();
        this.setVisible(true);
        model.solve();

    }

    public static void main(String[] args) {
        new Fire();
    }
}
