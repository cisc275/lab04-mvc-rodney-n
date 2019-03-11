//Rodney Nickles | February 24, 2019 | CISC275

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Animation extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final int frameCount = 10;
	int picNum = 0;
	BufferedImage[] pics;
	int xloc = 0;
	int yloc = 0;
	final int xIncr = 8;
	final int yIncr = 2;
	int switchX = 0;
	int switchY = 0;
	final static int frameWidth = 500;
	final static int frameHeight = 300;
	final static int imgWidth = 165;
	final static int imgHeight = 165;

	public void paint(Graphics g) {
		picNum = (picNum + 1) % frameCount;

		if ((switchX <= frameWidth) & (switchY <= frameHeight)) {
			g.drawImage(pics[picNum + 50], xloc += xIncr, yloc += yIncr, Color.gray, this);
			if (xloc >= frameWidth - imgWidth)
				switchX = frameWidth + 1;

			if (yloc >= frameHeight - imgHeight)
				switchY = frameHeight + 1;
		}

		if ((switchX >= frameWidth) & (switchY <= frameHeight)) {
			g.drawImage(pics[picNum + 60], xloc -= xIncr, yloc += yIncr, Color.gray, this);
			if (xloc <= 0)
				switchX = 0;
			if (yloc >= frameHeight - imgHeight) 
				switchY = frameHeight + 1;
		}

		if ((switchX <= frameWidth) & (switchY >= frameHeight)) {
			g.drawImage(pics[picNum + 20], xloc += xIncr, yloc -= yIncr, Color.gray, this);
			if (xloc >= frameWidth - imgWidth) 
				switchX = frameWidth + 1;
			if (yloc <= 0) 
				switchY = 0;
		}

		if ((switchX >= frameWidth) & (switchY >= frameHeight)) {
			g.drawImage(pics[picNum + 30], xloc -= xIncr, yloc -= yIncr, Color.gray, this);
			if (xloc <= 0) 
				switchX = 0;
			if (yloc <= 0) 
				switchY = 0;
		}
	}

	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new Animation());
		frame.setBackground(Color.gray);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(frameWidth, frameHeight);
		frame.setVisible(true);
		for (int i = 0; i < 1000; i++) {
			frame.repaint();
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public Animation() {
		ArrayList<BufferedImage> buffAList = new ArrayList<BufferedImage>();
		ArrayList<File> images = createImages();
		pics = new BufferedImage[80];
		for (File i : images) {
			try {
				buffAList.add(ImageIO.read(new File("" + i + "")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		int frameIncr = 0;
		for (int k = 0; k < 8; k++) {
			for (int j = 0; j < frameCount; j++) {
				pics[j + frameIncr] = buffAList.get(k).getSubimage(imgWidth * j, 0, imgWidth, imgHeight);
				}
			frameIncr+=10;
		}
	}
	
	private ArrayList<File> createImages() {
		File path = new File("orcImages");
		ArrayList<File> imageCollection = new ArrayList<File>();
		for (File imgFile : path.listFiles()) {
			imageCollection.add(imgFile);
		}
		return imageCollection;
	}
}