import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

public class Detection {

	BackgroundChange bgc;
	JFrame j;
	
	public Detection() {
		bgc = new BackgroundChange();
		j = new JFrame();
		
		j.setTitle("Prank");
		j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		j.setVisible(true);
		j.addKeyListener( new KeyListener() {
				
			@Override
			public void keyPressed(KeyEvent e) {
			
				Point p = MouseInfo.getPointerInfo().getLocation();
				
				int x = (int) p.getX();
				int y = (int) p.getY();
				System.out.println(x + " " + y);
				// 1145 20
				// 1280 0
				
				if (e.getKeyCode() == KeyEvent.BUTTON1_DOWN_MASK
						&& x >= 1143 && y <= 26) {
					doIt();
				}
				
			}
	
			@Override
			public void keyReleased(KeyEvent e) {
			}
	
			@Override
			public void keyTyped(KeyEvent e) {
			}
		});
	};
	
	public static void main(String[] args) {
		
		//Detection d = new Detection();
		Point p = MouseInfo.getPointerInfo().getLocation();
		
		int x = (int) p.getX();
		int y = (int) p.getY();
		System.out.println(x + " " + y);
	}
	
	public void doIt() {
		bgc.changeDefault();
	}

	
	
}
