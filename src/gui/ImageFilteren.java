package gui;
/*
 * ImageFilteraar works to put a so-called Instagram filter on top of the image, in order to personalise the image
 *  * taken from johnbtheperson (youtube), adapted to work in our upload image
 */

import java.awt.*;
import java.awt.image.*;
import model.*;

import java.awt.event.*;

/**
 * @author Team D
 * @description ImageFilteraar works to put a so-called Instagram filter on top
 *              of the image, in order to personalise the image
 * @see UpLoadImage, which hosts the UploadProcedure method that calls this
 *      class.
 */
public class ImageFilteren extends Frame implements WindowListener, ActionListener { // extends Frame was het eerst

	private Choice mode = new Choice();
	private Button appb = new Button("Apply");
	private Button saveb = new Button("Save");
	private GraphDisplay gd1;
	private GraphDisplay gd2;
	private GraphDisplay gd3;
	private ImageDisplay id;
	private BufferedImage product = null;

	/*
	 * load in the picture, make the panel
	 */
	public void imageFilteraar(BufferedImage image) {

		gd1 = new GraphDisplay(); // graph displays
		gd2 = new GraphDisplay();
		gd3 = new GraphDisplay();
		id = new ImageDisplay(gd1, gd2, gd3); // image display
		setLayout(new BorderLayout());
		add(id, BorderLayout.CENTER); // image display

		Panel leftp = new Panel(new GridLayout(3, 1)); // left panel
		leftp.add(gd1);
		leftp.add(gd2);
		leftp.add(gd3);
		Panel leftside = new Panel(new BorderLayout());
		leftside.add(leftp, BorderLayout.CENTER);
		Panel bottomp = new Panel(new GridLayout(1, 2));
		bottomp.add(appb); // apply button
		bottomp.add(saveb); // save button

		saveb.addActionListener(this); // action listeners
		appb.addActionListener(this);
		mode.add("RGB"); // add choice RGB (red green blue) or HSB (hue, saturation, brightness)
		mode.add("HSB");
		leftside.add(mode, BorderLayout.NORTH);
		leftside.add(bottomp, BorderLayout.SOUTH);
		add(leftside, BorderLayout.WEST);

		id.open(image); // open imagedisplay

		setSize(600, 400);
		setVisible(true);
		addWindowListener(this);
	}

	public void windowOpened(WindowEvent e) {
	}

	public void windowClosed(WindowEvent e) {
		((Frame) e.getSource()).dispose();
	}

	public void windowClosing(WindowEvent e) {
		((Frame) e.getSource()).dispose();
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == appb) { // when applied
			id.setMode(mode.getSelectedIndex());
			id.repaint();

		} else if (e.getSource() == saveb) { // when 'saved'
			id.setMode(mode.getSelectedIndex());
			BufferedImage product = id.render();

			((AddDogPanel) MainFrame.getMainFrame().getAddDogPanel()).setOri(product);
			((EditDogPanel) MainFrame.getMainFrame().getEditDogPanel()).setOri(product);
			dispose();
		}
	}

	// Setter for the product, which is rendered in ImageFilteren when 'saved' by
	// pushing the 'save button'.
	public void setProduct(BufferedImage product) {
		this.product = product;
	}

	// getter for the product
	public BufferedImage getProduct() {
		return product;
	}

	class GraphDisplay extends Canvas implements MouseListener, MouseMotionListener, ComponentListener {

		private static final long serialVersionUID = 1L;
		private BufferedImage screen;
		private int[] px;
		private int[] py;
		private boolean down = false;

		public Dimension getMinimumSize() {
			return new Dimension(50, 50);
		}

		public Dimension getMaximumSize() {
			return new Dimension(1000, 1000);
		}

		public Dimension getPreferredSize() {
			return new Dimension(200, 200);
		}

		public void refresh() {
			Graphics2D g = (Graphics2D) screen.getGraphics();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(Color.black);
			g.fillRect(0, 0, getWidth(), getHeight());
			g.setColor(new Color(100, 100, 100));
			for (int i = 0; i < 8; i++) {
				g.drawLine(getWidth() * i / 8, 0, getWidth() * i / 8, getHeight());
				g.drawLine(0, getHeight() * i / 8, getWidth(), getHeight() * i / 8);
			}
			for (int i = 0; i < px.length - 1; i++) {
				g.setColor(Color.white);
				g.drawLine(px[i], py[i], px[i + 1], py[i + 1]);
				g.fillOval(px[i] - 3, py[i] - 3, 6, 6);
			}
			g.fillOval(px[px.length - 1] - 3, py[py.length - 1] - 3, 6, 6);
			repaint(0, 0, getWidth(), getHeight());
		}

		public void update(Graphics g) {
			paint(g);
		}

		public void paint(Graphics g) {
			g.drawImage(screen, 0, 0, this);
		}

		public GraphDisplay() {
			px = new int[2];
			py = new int[2];
			addMouseListener(this);
			addMouseMotionListener(this);
			addComponentListener(this);
		}

		public void mouseClicked(MouseEvent e) {
		}

		public void mousePressed(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON1) {
				click(e);
				down = true;
			} else {
				px = new int[2];
				py = new int[2];
				px[0] = 0;
				py[0] = getHeight();
				px[1] = getWidth();
				py[1] = 0;
				refresh();
			}
		}

		public void click(MouseEvent e) {
			boolean found = false;
			for (int i = 1; i < px.length - 1; i++) {
				if (e.getX() > px[i] - 6 && e.getX() < px[i] + 6) {
					if (true) {
						py[i] = e.getY();
						if (down) {
							px[i] = e.getX();
						}
					}
					found = true;
				}
			}
			if (!found) {
				int[] npx = new int[px.length + 1];
				int[] npy = new int[px.length + 1];
				int i = 0;
				while (e.getX() > px[i]) {
					npx[i] = px[i];
					npy[i] = py[i];
					i++;
				}
				npx[i] = e.getX();
				npy[i] = e.getY();
				i++;
				while (i < npx.length) {
					npx[i] = px[i - 1];
					npy[i] = py[i - 1];
					i++;
				}
				px = npx;
				py = npy;
			}
			refresh();
		}

		public void mouseReleased(MouseEvent e) {
			down = false;
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent e) {
		}

		public void mouseMoved(MouseEvent e) {
		}

		public void mouseDragged(MouseEvent e) {
			click(e);
		}

		public void componentHidden(ComponentEvent e) {
		}

		public void componentShown(ComponentEvent e) {
		}

		public void componentResized(ComponentEvent e) {
			screen = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
			px[0] = 0;
			py[0] = getHeight();
			px[1] = getWidth();
			py[1] = 0;
			refresh();
		}

		public void componentMoved(ComponentEvent e) {
		}

		public float evaluate(float inpt) {
			inpt *= 0.99f;
			inpt += 0.005f;
			int i = 0;
			while ((float) px[i] / (float) getWidth() < inpt) {
				i++;
			}
			float outpt = (float) (getHeight() - py[i - 1]) / (float) getHeight()
					+ ((float) ((getHeight() - py[i]) - (getHeight() - py[i - 1])) / (float) getHeight())
							/ ((float) ((px[i]) - (px[i - 1])) / (float) getWidth())
							* (inpt - ((float) px[i - 1] / (float) getWidth()));
			return outpt;
		}
	}

	class ImageDisplay extends Canvas {
		private static final long serialVersionUID = 1L;
		private BufferedImage img;
		private GraphDisplay gd1;
		private GraphDisplay gd2;
		private GraphDisplay gd3;
		private int mode = 0;

		public void setMode(int m) {
			mode = m;
		}

		public Dimension getMinimumSize() {
			return new Dimension(50, 50);
		}

		public Dimension getMaximumSize() {
			return new Dimension(1000, 1000);
		}

		public Dimension getPreferredSize() {
			return new Dimension(200, 200);
		}

		public BufferedImage render() {
			BufferedImage img2 = new BufferedImage(img.getWidth(), img.getHeight(), BufferedImage.TYPE_INT_RGB);
			Graphics g = img2.getGraphics();
			for (int y = 0; y < img2.getHeight(); y++) {
				for (int x = 0; x < img2.getWidth(); x++) {
					Color tc = new Color(img.getRGB(x, y));
					float tr = gd1.evaluate((float) tc.getRed() / 255f);
					float tg = gd2.evaluate((float) tc.getGreen() / 255f);
					float tb = gd3.evaluate((float) tc.getBlue() / 255f);
					float[] vals = Color.RGBtoHSB(tc.getRed(), tc.getGreen(), tc.getBlue(), null);
					boolean rgb = (mode == 0);
					Color newc;
					if (rgb == true) {
						newc = new Color((int) (tr * 255f), (int) (tg * 255f), (int) (tb * 255f));
					} else {
						newc = new Color(
								Color.HSBtoRGB(gd1.evaluate(vals[0]), gd2.evaluate(vals[1]), gd3.evaluate(vals[2])));
					}
					g.setColor(newc);
					g.fillRect(x, y, 1, 1);
				}
			}
			return img2;
		}

		public void update(Graphics g) {
			paint(g);
		}

		public void paint(Graphics g) {
			for (int y = 0; y < getHeight(); y++) {
				for (int x = 0; x < getWidth(); x++) {
					Color tc = new Color(
							img.getRGB(x * img.getWidth() / getWidth(), y * img.getHeight() / getHeight()));
					float tr = gd1.evaluate((float) tc.getRed() / 255f);
					float tg = gd2.evaluate((float) tc.getGreen() / 255f);
					float tb = gd3.evaluate((float) tc.getBlue() / 255f);
					float[] vals = Color.RGBtoHSB(tc.getRed(), tc.getGreen(), tc.getBlue(), null);
					boolean rgb = (mode == 0);
					Color newc;
					if (rgb == true) {
						newc = new Color((int) (tr * 255f), (int) (tg * 255f), (int) (tb * 255f));
					} else {
						newc = new Color(
								Color.HSBtoRGB(gd1.evaluate(vals[0]), gd2.evaluate(vals[1]), gd3.evaluate(vals[2])));
					}
					g.setColor(newc);
					g.fillRect(x, y, 1, 1);
				}
			}
		}

		public ImageDisplay(GraphDisplay gda, GraphDisplay gdb, GraphDisplay gdc) {
			gd1 = gda;
			gd2 = gdb;
			gd3 = gdc;
		}

		// sets the image and repaints the filter application.
		public void open(BufferedImage img) {
			this.img = img;
			repaint(0, 0, getWidth(), getHeight());
		}
	}
}
