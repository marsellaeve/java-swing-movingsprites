package id.ac.its.evelyn.MovingSprites;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class ShootingMissilesEx extends JFrame {

    public ShootingMissilesEx() {
        
        initUI();
    }
    
    private void initUI() {
        Board board=new Board();
        add(board);
        
        setSize(400, 300);
        setResizable(false);
        
        setTitle("Shooting missiles");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        
        EventQueue.invokeLater(() -> {
            ShootingMissilesEx ex = new ShootingMissilesEx();
            ex.setVisible(true);
        });
    }
}