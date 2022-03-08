import javax.swing.*;
import java.awt.*;

public class Main extends JFrame{

    private final JLabel label = new JLabel("Hello World");

    private Main(){
        super("Prima Finestra");
        Container c = this.getContentPane();
        c.setLayout(new GridBagLayout());
        c.add(label);
       

        this.setSize(200,200);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args){
        new Main();
    }
}