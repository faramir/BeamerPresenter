/*
 * Copyright (c) 2015, Marek Nowicki
 * All rights reserved.
 *
 * This file is distributable under the Simplified BSD license. See the terms
 * of the Simplified BSD license in the documentation provided with this file.
 */
package pl.umk.mat.faramir.beamer;

import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.swing.Timer;

/**
 *
 * @author faramir
 */
public class NotesFrame extends javax.swing.JFrame {

    private final MainFrame mainFrame;
    private final ActionListener timerAction;
    private BufferedImage currentSlideImage;
    private BufferedImage nextSlideImage;
    private SlideNote note;
    private long previousStart;

    public NotesFrame(boolean fullscreen, MainFrame mainFrame) {
        if (fullscreen) {
            setBackground(Color.BLACK);
            setUndecorated(true);
            setExtendedState(Frame.MAXIMIZED_BOTH);
            setResizable(false);
//            setAlwaysOnTop(true);
        }
        this.mainFrame = mainFrame;
        initComponents();

        timerAction = (e) -> {
            LocalDateTime now = LocalDateTime.now();//.truncatedTo(ChronoUnit.SECONDS);
            long elapsed = ChronoUnit.SECONDS.between(mainFrame.getStartTime(), now);

            if (note.getSeconds() > 0) {
                long remain = note.getSeconds() - elapsed;
                if (Math.abs(remain) >= 60) {
                    presentationTimeLabel.setText(
                            String.format("<html>%s%02d:%02d <span style='font-size:35%%'>(%+d:%02d)</span></html>",
                                    elapsed < 0 ? "-" : "",
                                    Math.abs(elapsed) / 60,
                                    Math.abs(elapsed) % 60,
                                    remain / 60, Math.abs(remain) % 60));
                } else {
                    presentationTimeLabel.setText(
                            String.format("<html>%s%02d:%02d <span style='font-size:35%%'>(%+d)</span></html>",
                                    elapsed < 0 ? "-" : "",
                                    Math.abs(elapsed) / 60,
                                    Math.abs(elapsed) % 60,
                                    remain));
                }
                if (elapsed > note.getSeconds()) {
                    presentationTimeLabel.setForeground(Color.RED);
                } else if (note.getSeconds() - elapsed <= 1) {
                    presentationTimeLabel.setForeground(Color.ORANGE);
                } else if (note.getSeconds() - elapsed <= 5) {
                    presentationTimeLabel.setForeground(Color.GREEN);
                } else {
                    presentationTimeLabel.setForeground(Color.BLACK);
                }
            } else {
                presentationTimeLabel.setText(String.format("%s%02d:%02d", elapsed < 0 ? "-" : "", Math.abs(elapsed) / 60, Math.abs(elapsed) % 60));
                presentationTimeLabel.setForeground(Color.BLACK);
            }
        };

        Timer timer = new Timer(100, timerAction);
        timer.setInitialDelay(0);
        timer.setRepeats(true);
        timer.start();

        Timer clock = new Timer(100, (e) -> {
            LocalDateTime now = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
            clockLabel.setText(now.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        });
        clock.setInitialDelay(0);
        clock.setRepeats(true);
        clock.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        slidesNotesSplitPane = new javax.swing.JSplitPane();
        slidesSplitPane = new javax.swing.JSplitPane();
        currentSlideBorderPane = new javax.swing.JPanel();
        currentSlidePane = new javax.swing.JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawCurrentSlide(g);
            }
        };
        rightSlideSplitPane = new javax.swing.JPanel();
        clockPane = new javax.swing.JPanel();
        clockLabel = new javax.swing.JLabel();
        nextSlideBorderPane = new javax.swing.JPanel();
        nextSlidePane = new javax.swing.JPanel() {
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawNextSlide(g);
            }
        };
        notesBottomSplitPane = new javax.swing.JPanel();
        notesBorderPane = new javax.swing.JPanel();
        notesScrollPane = new javax.swing.JScrollPane();
        notesTextArea = new javax.swing.JTextArea();
        currentSlideNumberBorderPane = new javax.swing.JPanel();
        currentSlideNumberLabel = new javax.swing.JLabel();
        presentationTimeBorderPane = new javax.swing.JPanel();
        presentationTimeLabel = new javax.swing.JLabel();

        FormListener formListener = new FormListener();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Notatki");
        setLocationByPlatform(true);
        setMinimumSize(new java.awt.Dimension(600, 400));

        slidesNotesSplitPane.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        slidesNotesSplitPane.setOrientation(javax.swing.JSplitPane.VERTICAL_SPLIT);
        slidesNotesSplitPane.setResizeWeight(0.9);

        slidesSplitPane.setResizeWeight(0.7);

        currentSlideBorderPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Aktualny slajd"));
        currentSlideBorderPane.setPreferredSize(new java.awt.Dimension(400, 300));

        currentSlidePane.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout currentSlidePaneLayout = new javax.swing.GroupLayout(currentSlidePane);
        currentSlidePane.setLayout(currentSlidePaneLayout);
        currentSlidePaneLayout.setHorizontalGroup(
            currentSlidePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 448, Short.MAX_VALUE)
        );
        currentSlidePaneLayout.setVerticalGroup(
            currentSlidePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 283, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout currentSlideBorderPaneLayout = new javax.swing.GroupLayout(currentSlideBorderPane);
        currentSlideBorderPane.setLayout(currentSlideBorderPaneLayout);
        currentSlideBorderPaneLayout.setHorizontalGroup(
            currentSlideBorderPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(currentSlidePane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        currentSlideBorderPaneLayout.setVerticalGroup(
            currentSlideBorderPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currentSlideBorderPaneLayout.createSequentialGroup()
                .addComponent(currentSlidePane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        slidesSplitPane.setLeftComponent(currentSlideBorderPane);

        clockPane.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Aktualna godzina", javax.swing.border.TitledBorder.RIGHT, javax.swing.border.TitledBorder.DEFAULT_POSITION));

        clockLabel.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        clockLabel.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        clockLabel.setText("00:00:00");
        clockLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        javax.swing.GroupLayout clockPaneLayout = new javax.swing.GroupLayout(clockPane);
        clockPane.setLayout(clockPaneLayout);
        clockPaneLayout.setHorizontalGroup(
            clockPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(clockLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 330, Short.MAX_VALUE)
        );
        clockPaneLayout.setVerticalGroup(
            clockPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(clockLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        nextSlideBorderPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Następny slajd"));
        nextSlideBorderPane.setPreferredSize(new java.awt.Dimension(340, 255));

        nextSlidePane.setBackground(new java.awt.Color(0, 0, 0));
        nextSlidePane.setPreferredSize(new java.awt.Dimension(300, 225));

        javax.swing.GroupLayout nextSlidePaneLayout = new javax.swing.GroupLayout(nextSlidePane);
        nextSlidePane.setLayout(nextSlidePaneLayout);
        nextSlidePaneLayout.setHorizontalGroup(
            nextSlidePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        nextSlidePaneLayout.setVerticalGroup(
            nextSlidePaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 225, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout nextSlideBorderPaneLayout = new javax.swing.GroupLayout(nextSlideBorderPane);
        nextSlideBorderPane.setLayout(nextSlideBorderPaneLayout);
        nextSlideBorderPaneLayout.setHorizontalGroup(
            nextSlideBorderPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nextSlidePane, javax.swing.GroupLayout.DEFAULT_SIZE, 384, Short.MAX_VALUE)
        );
        nextSlideBorderPaneLayout.setVerticalGroup(
            nextSlideBorderPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(nextSlidePane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout rightSlideSplitPaneLayout = new javax.swing.GroupLayout(rightSlideSplitPane);
        rightSlideSplitPane.setLayout(rightSlideSplitPaneLayout);
        rightSlideSplitPaneLayout.setHorizontalGroup(
            rightSlideSplitPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(clockPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(nextSlideBorderPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
        );
        rightSlideSplitPaneLayout.setVerticalGroup(
            rightSlideSplitPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, rightSlideSplitPaneLayout.createSequentialGroup()
                .addComponent(clockPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nextSlideBorderPane, javax.swing.GroupLayout.DEFAULT_SIZE, 248, Short.MAX_VALUE))
        );

        slidesSplitPane.setRightComponent(rightSlideSplitPane);

        slidesNotesSplitPane.setLeftComponent(slidesSplitPane);

        notesBorderPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Notatki"));

        notesScrollPane.setBorder(null);

        notesTextArea.setEditable(false);
        notesTextArea.setBackground(java.awt.SystemColor.control);
        notesTextArea.setColumns(40);
        notesTextArea.setFont(new java.awt.Font("Monospaced", 0, 14)); // NOI18N
        notesTextArea.setLineWrap(true);
        notesTextArea.setRows(4);
        notesTextArea.setTabSize(4);
        notesTextArea.setText("123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 123456789 ABC");
        notesTextArea.setWrapStyleWord(true);
        notesTextArea.setBorder(null);
        notesTextArea.addMouseWheelListener(formListener);
        notesTextArea.addMouseListener(formListener);
        notesScrollPane.setViewportView(notesTextArea);

        javax.swing.GroupLayout notesBorderPaneLayout = new javax.swing.GroupLayout(notesBorderPane);
        notesBorderPane.setLayout(notesBorderPaneLayout);
        notesBorderPaneLayout.setHorizontalGroup(
            notesBorderPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(notesScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 593, Short.MAX_VALUE)
        );
        notesBorderPaneLayout.setVerticalGroup(
            notesBorderPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(notesScrollPane)
        );

        currentSlideNumberBorderPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Aktualny slajd"));

        currentSlideNumberLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        currentSlideNumberLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        currentSlideNumberLabel.setText("0/0");

        javax.swing.GroupLayout currentSlideNumberBorderPaneLayout = new javax.swing.GroupLayout(currentSlideNumberBorderPane);
        currentSlideNumberBorderPane.setLayout(currentSlideNumberBorderPaneLayout);
        currentSlideNumberBorderPaneLayout.setHorizontalGroup(
            currentSlideNumberBorderPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(currentSlideNumberBorderPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(currentSlideNumberLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        currentSlideNumberBorderPaneLayout.setVerticalGroup(
            currentSlideNumberBorderPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(currentSlideNumberLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        presentationTimeBorderPane.setBorder(javax.swing.BorderFactory.createTitledBorder("Czas"));

        presentationTimeLabel.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        presentationTimeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        presentationTimeLabel.setText("00:00");
        presentationTimeLabel.addMouseWheelListener(formListener);
        presentationTimeLabel.addMouseListener(formListener);

        javax.swing.GroupLayout presentationTimeBorderPaneLayout = new javax.swing.GroupLayout(presentationTimeBorderPane);
        presentationTimeBorderPane.setLayout(presentationTimeBorderPaneLayout);
        presentationTimeBorderPaneLayout.setHorizontalGroup(
            presentationTimeBorderPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(presentationTimeBorderPaneLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(presentationTimeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addContainerGap())
        );
        presentationTimeBorderPaneLayout.setVerticalGroup(
            presentationTimeBorderPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(presentationTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        javax.swing.GroupLayout notesBottomSplitPaneLayout = new javax.swing.GroupLayout(notesBottomSplitPane);
        notesBottomSplitPane.setLayout(notesBottomSplitPaneLayout);
        notesBottomSplitPaneLayout.setHorizontalGroup(
            notesBottomSplitPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notesBottomSplitPaneLayout.createSequentialGroup()
                .addComponent(notesBorderPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(notesBottomSplitPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(currentSlideNumberBorderPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(presentationTimeBorderPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        notesBottomSplitPaneLayout.setVerticalGroup(
            notesBottomSplitPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(notesBottomSplitPaneLayout.createSequentialGroup()
                .addGroup(notesBottomSplitPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(notesBorderPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(notesBottomSplitPaneLayout.createSequentialGroup()
                        .addComponent(currentSlideNumberBorderPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(presentationTimeBorderPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );

        slidesNotesSplitPane.setRightComponent(notesBottomSplitPane);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slidesNotesSplitPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(slidesNotesSplitPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.MouseListener, java.awt.event.MouseWheelListener {
        FormListener() {}
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            if (evt.getSource() == notesTextArea) {
                NotesFrame.this.notesTextAreaMouseClicked(evt);
            }
            else if (evt.getSource() == presentationTimeLabel) {
                NotesFrame.this.presentationTimeLabelMouseClicked(evt);
            }
        }

        public void mouseEntered(java.awt.event.MouseEvent evt) {
        }

        public void mouseExited(java.awt.event.MouseEvent evt) {
        }

        public void mousePressed(java.awt.event.MouseEvent evt) {
        }

        public void mouseReleased(java.awt.event.MouseEvent evt) {
        }

        public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
            if (evt.getSource() == notesTextArea) {
                NotesFrame.this.notesTextAreaMouseWheelMoved(evt);
            }
            else if (evt.getSource() == presentationTimeLabel) {
                NotesFrame.this.presentationTimeLabelMouseWheelMoved(evt);
            }
        }
    }// </editor-fold>//GEN-END:initComponents

    private void notesTextAreaMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_notesTextAreaMouseWheelMoved
        if (evt.isControlDown() && evt.getWheelRotation() != 0) {
            Font font = notesTextArea.getFont();
            notesTextArea.setFont(font.deriveFont(font.getSize2D() + (evt.getWheelRotation() > 0 ? -1 : 1)));
        }
    }//GEN-LAST:event_notesTextAreaMouseWheelMoved

    private void notesTextAreaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_notesTextAreaMouseClicked
        if (evt.isControlDown()) {
            Font font = notesTextArea.getFont();
            notesTextArea.setFont(font.deriveFont(font.getSize2D() + (evt.getButton() == MouseEvent.BUTTON1 ? 1 : -1)));
        }
    }//GEN-LAST:event_notesTextAreaMouseClicked

    private void presentationTimeLabelMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_presentationTimeLabelMouseWheelMoved
        if (evt.isControlDown() && evt.getWheelRotation() != 0) {
            mainFrame.setStartTime(mainFrame.getStartTime().plusSeconds(evt.getWheelRotation() > 0 ? -1 : 1));
            timerAction.actionPerformed(null);
        }
    }//GEN-LAST:event_presentationTimeLabelMouseWheelMoved

    private void presentationTimeLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_presentationTimeLabelMouseClicked
        if (evt.isControlDown()) {
            mainFrame.setStartTime(mainFrame.getStartTime().plusSeconds(evt.getButton() == MouseEvent.BUTTON1 ? 1 : -1));
            timerAction.actionPerformed(null);
        } else if (evt.getClickCount() >= 2) {
            clearTime();
        }
    }//GEN-LAST:event_presentationTimeLabelMouseClicked

    public void changeSlideInfo(int currentSlide, int slidesCount, SlideNote note, long previousStart, BufferedImage currentSlideImage, BufferedImage nextSlideImage) {
        this.currentSlideImage = currentSlideImage;
        this.nextSlideImage = nextSlideImage;
        if (note == null) {
            note = new SlideNote("", 0);
        }
        this.note = note;
        this.previousStart = previousStart;
        currentSlideNumberLabel.setText(String.format("%d/%d", currentSlide, slidesCount));
        notesTextArea.setText(note.getText());
        notesTextArea.setCaretPosition(0);
        currentSlidePane.repaint();
        nextSlidePane.repaint();
        timerAction.actionPerformed(null);
    }

    private void drawSlide(int width, int height, Graphics2D g2, BufferedImage image) {
        width -= 2;
        height -= 2;
        double slideProportion = (double) width / height;
        Rectangle bounds = new Rectangle();

        if (slideProportion <= (double) image.getWidth() / image.getHeight()) {
            bounds.width = width;
            bounds.height = (int) (((double) width / image.getWidth()) * image.getHeight());
            bounds.x = 1;
            bounds.y = Math.max(1, (height - bounds.height) / 2);
        } else {
            bounds.height = height;
            bounds.width = (int) (((double) height / image.getHeight()) * image.getWidth());
            bounds.x = Math.max(1, (width - bounds.width) / 2);
            bounds.y = 1;
        }

        g2.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.drawImage(image, bounds.x, bounds.y, bounds.width, bounds.height, null);
    }

    private void drawNoSlide(int width, int height, Graphics g) {
        g.setColor(Color.RED);
        g.drawLine(0, 0, width, height);
        g.drawLine(0, height, width, 0);
    }

    private void drawCurrentSlide(Graphics g) {
        if (currentSlideImage == null) {
            drawNoSlide(currentSlidePane.getWidth(), currentSlidePane.getHeight(), g);
        } else {
            drawSlide(currentSlidePane.getWidth(), currentSlidePane.getHeight(), (Graphics2D) g, currentSlideImage);

        }
    }

    private void drawNextSlide(Graphics g) {
        if (nextSlideImage == null) {
            drawNoSlide(nextSlidePane.getWidth(), nextSlidePane.getHeight(), g);
        } else {
            drawSlide(nextSlidePane.getWidth(), nextSlidePane.getHeight(), (Graphics2D) g, nextSlideImage);
        }
    }

    protected void clearTime() {
        if (note.getSeconds() <= 0) {
            mainFrame.setStartTime(LocalDateTime.now());
        } else {
            mainFrame.setStartTime(LocalDateTime.now().minusSeconds(previousStart));
        }
        timerAction.actionPerformed(null);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel clockLabel;
    private javax.swing.JPanel clockPane;
    private javax.swing.JPanel currentSlideBorderPane;
    private javax.swing.JPanel currentSlideNumberBorderPane;
    private javax.swing.JLabel currentSlideNumberLabel;
    private javax.swing.JPanel currentSlidePane;
    private javax.swing.JPanel nextSlideBorderPane;
    private javax.swing.JPanel nextSlidePane;
    private javax.swing.JPanel notesBorderPane;
    private javax.swing.JPanel notesBottomSplitPane;
    private javax.swing.JScrollPane notesScrollPane;
    private javax.swing.JTextArea notesTextArea;
    private javax.swing.JPanel presentationTimeBorderPane;
    private javax.swing.JLabel presentationTimeLabel;
    private javax.swing.JPanel rightSlideSplitPane;
    private javax.swing.JSplitPane slidesNotesSplitPane;
    private javax.swing.JSplitPane slidesSplitPane;
    // End of variables declaration//GEN-END:variables
}
