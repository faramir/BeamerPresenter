/*
 * Copyright (c) 2015, Marek Nowicki
 * All rights reserved.
 *
 * This file is distributable under the Simplified BSD license. See the terms
 * of the Simplified BSD license in the documentation provided with this file.
 */
package pl.umk.mat.faramir.beamer;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowFocusListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Date;
import javax.swing.Timer;

/**
 *
 * @author faramir
 */
public class FullscreenWindow extends Window implements Runnable, MouseMotionListener, WindowFocusListener, MouseListener, MouseWheelListener {

    final private static Cursor invisibleCursor = Toolkit.getDefaultToolkit().createCustomCursor(new BufferedImage(1, 1, BufferedImage.TYPE_INT_ARGB), new Point(0, 0), null);
    final private static BufferedImage emptyImage = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    private BufferStrategy bufferStrategy;
    private Rectangle screenBounds;
    private BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
    private Thread paintThread;
    private Timer timer;
    final private MainFrame mainFrame;

    public FullscreenWindow(MainFrame mainFrame) {
        super(mainFrame);
        this.mainFrame = mainFrame;
        setBackground(Color.BLACK);
//        setUndecorated(true);
//        setExtendedState(Frame.MAXIMIZED_BOTH);
//        setResizable(false);
        setIgnoreRepaint(true);
//        setAlwaysOnTop(true);
    }

    public void createWindow(GraphicsDevice device) {
        device.setFullScreenWindow(this);

        createBufferStrategy(2);
        bufferStrategy = getBufferStrategy();
        screenBounds = getBounds();

        addMouseMotionListener(this);
        addMouseListener(this);
        addMouseWheelListener(this);
        addWindowFocusListener(this);
        timer = new Timer(3000, e -> this.setCursor(invisibleCursor));
        timer.setRepeats(false);
        timer.start();

        paintThread = new Thread(this);
        paintThread.start();
    }

    @Override
    public void run() {
        BufferedImage page = null;
        while (true) {
            synchronized (this) {
                if (page == image) {
                    try {
                        this.wait();
                    } catch (InterruptedException ex) {
                        return;
                    }
                }
                if (image == null) {
                    page = emptyImage;
                } else {
                    page = image;
                }
            }
            double screenProportion = (double) screenBounds.width / screenBounds.height;
            Rectangle bounds = new Rectangle();
            if (screenProportion <= (double) page.getWidth() / page.getHeight()) {
                bounds.width = screenBounds.width;
                bounds.height = Math.round(((float) screenBounds.width / page.getWidth()) * page.getHeight());
                bounds.x = 0;
                bounds.y = (screenBounds.height - bounds.height) / 2;
            } else {
                bounds.height = screenBounds.height;
                bounds.width = Math.round(((float) screenBounds.height / page.getHeight()) * page.getWidth());
                bounds.x = (screenBounds.width - bounds.width) / 2;
                bounds.y = 0;
            }

            do {
                do {
                    Graphics2D g2 = null;
                    try {
                        g2 = (Graphics2D) bufferStrategy.getDrawGraphics();
                        g2.setColor(Color.BLACK);
                        g2.fillRect(0, 0, screenBounds.width, screenBounds.height);

                        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
                        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
//                        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
//                        g2.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
//                        g2.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
//                        g2.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_NORMALIZE);

                        g2.drawImage(page,
                                (screenBounds.width - bounds.width) / 2,
                                (screenBounds.height - bounds.height) / 2,
                                bounds.width, bounds.height,
                                null);
                    } finally {
                        if (g2 != null) {
                            g2.dispose();
                        }
                    }
                } while (bufferStrategy.contentsRestored());

                bufferStrategy.show();

            } while (bufferStrategy.contentsLost());
        }
    }

    @Override
    public void dispose() {
        if (paintThread != null) {
            paintThread.interrupt();
        }
        super.dispose();
    }

    public void drawOnCenter(BufferedImage image) {
        this.image = image;

        synchronized (this) {
            this.notify();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        this.setCursor(Cursor.getDefaultCursor());
        timer.restart();
    }

    @Override
    public void windowGainedFocus(WindowEvent e) {
        synchronized (this) {
            this.notify();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mainFrame.dispatchKeyEvent(new KeyEvent(this, KeyEvent.KEY_PRESSED, new Date().getTime(), 0, KeyEvent.VK_PAGE_DOWN, KeyEvent.CHAR_UNDEFINED, KeyEvent.KEY_LOCATION_UNKNOWN));
        } else {
            mainFrame.dispatchKeyEvent(new KeyEvent(this, KeyEvent.KEY_PRESSED, new Date().getTime(), 0, KeyEvent.VK_PAGE_UP, KeyEvent.CHAR_UNDEFINED, KeyEvent.KEY_LOCATION_UNKNOWN));
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (e.getWheelRotation() > 0) {
            mainFrame.dispatchKeyEvent(new KeyEvent(this, KeyEvent.KEY_PRESSED, new Date().getTime(), 0, KeyEvent.VK_PAGE_DOWN, KeyEvent.CHAR_UNDEFINED, KeyEvent.KEY_LOCATION_UNKNOWN));
        } else if (e.getWheelRotation() < 0) {
            mainFrame.dispatchKeyEvent(new KeyEvent(this, KeyEvent.KEY_PRESSED, new Date().getTime(), 0, KeyEvent.VK_PAGE_UP, KeyEvent.CHAR_UNDEFINED, KeyEvent.KEY_LOCATION_UNKNOWN));
        }
    }

    @Override
    public void windowLostFocus(WindowEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
