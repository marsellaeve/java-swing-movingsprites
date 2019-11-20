package id.ac.its.evelyn.MovingSprites;
import java.lang.Math;

import javax.swing.JFrame;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.Random;
public class Board extends JPanel implements ActionListener {
	private boolean gameover=false;
    private final int ICRAFT_X = 40;
    private final int ICRAFT_Y = 60;
    private final int DELAY = 10;
    private Timer timer;
    private SpaceShip spaceShip;
    List<Asteroid> asteroids;
    
    public Board() {
        initBoard();
    }

    private void initBoard() {

        addKeyListener(new TAdapter());
        setBackground(Color.BLACK);
        setFocusable(true);
    	asteroids = new ArrayList<Asteroid>();

        spaceShip = new SpaceShip(ICRAFT_X, ICRAFT_Y);

        timer = new Timer(DELAY, this);
        timer.start();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);

        Toolkit.getDefaultToolkit().sync();
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        int angka;
        
        if(!gameover)g2d.drawImage(spaceShip.getImage(), spaceShip.getX(),
                spaceShip.getY(), this);
        angka = new Random().nextInt(100);
        List<Missile> missiles = spaceShip.getMissiles();
        if(angka<5 && angka>=0) {
        	int angka_y;
        	angka_y= new Random().nextInt(299)+1;
        	asteroids.add(new Asteroid(399, angka_y));
        }
        for (Asteroid asteroid : asteroids) {    
            g2d.drawImage(asteroid.getImage(), asteroid.getX(),
                    asteroid.getY(), this);
        }
        for (Missile missile : missiles) {
            g2d.drawImage(missile.getImage(), missile.getX(),
                    missile.getY(), this);
        }
    }
    
    private void checkCollision() {
    	List<Missile> missiles = spaceShip.getMissiles();
    	for(Asteroid asteroid : asteroids) {
    		for(Missile missile : missiles) {
    			int a=missile.getX()-asteroid.getX();
    			int b=missile.getY()-asteroid.getY();
    			if(Math.sqrt(a*a+b*b)<10) {
    				missile.setVisible(false);
    				asteroid.setVisible(false);
    				break;
    			}
    		}
    	}
    	boolean tanda=true;
    	for(Asteroid asteroid : asteroids) {
    		int s_X = spaceShip.getX();
        	int s_Y = spaceShip.getY();
        	int a=s_X-asteroid.getX();
			int b=s_Y-asteroid.getY();
			if(Math.sqrt(a*a+b*b)<20) {
				tanda=false;
				spaceShip.setVisible(false);
				asteroid.setVisible(false);
				break;
			}
    	}
    }
    @Override
    public void actionPerformed(ActionEvent e) {
    	checkCollision();
        updateMissiles();
        updateSpaceShip();
        updateAsteroid();
        repaint();
    }

    private void updateMissiles() {

        List<Missile> missiles = spaceShip.getMissiles();

        for (int i = 0; i < missiles.size(); i++) {

            Missile missile = missiles.get(i);

            if (missile.isVisible()) {

                missile.move();
            } else {

                missiles.remove(i);
            }
        }
    }
    private void updateAsteroid() {

        for (int i = 0; i < asteroids.size(); i++) {
            Asteroid asteroid = asteroids.get(i);

            if (asteroid.isVisible()) {

                asteroid.move();
            } else {

                asteroids.remove(i);
            }
        }
    }
    private void updateSpaceShip() {
        spaceShip.move();
        if(spaceShip.isVisible()==false) {
        	gameover=true;
        	repaint();
        	try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	gameover=false;
        	spaceShip.setVisible(true);
        }
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {
            spaceShip.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {
            spaceShip.keyPressed(e);
        }
    }
    
}