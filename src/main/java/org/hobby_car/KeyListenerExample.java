package org.hobby_car;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyListenerExample extends JFrame implements KeyListener {

  private JTextArea textArea;

  public KeyListenerExample() {
    // Thiết lập JFrame
    setTitle("Key Listener Example");
    setSize(400, 300);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocationRelativeTo(null);

    // Tạo JTextArea
    textArea = new JTextArea();
    textArea.addKeyListener(this); // Thêm KeyListener vào JTextArea
    textArea.setLineWrap(true);
    textArea.setWrapStyleWord(true);

    // Thêm JScrollPane để cuộn
    JScrollPane scrollPane = new JScrollPane(textArea);
    add(scrollPane);

    setVisible(true);
  }

  public static void main(String[] args) {
    SwingUtilities.invokeLater(KeyListenerExample::new);
  }

  @Override
  public void keyTyped(KeyEvent e) {
    System.out.println("Key Typed: " + e.getKeyChar());
  }

  @Override
  public void keyPressed(KeyEvent e) {
    System.out.println("Key Pressed: " + e.getKeyCode());
  }

  @Override
  public void keyReleased(KeyEvent e) {
    System.out.println("Key Released: " + e.getKeyCode());
  }
}