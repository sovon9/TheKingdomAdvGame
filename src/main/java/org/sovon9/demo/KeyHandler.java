package org.sovon9.demo;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean keyW;
    public boolean keyA;
    public boolean keyS;
    public boolean keyD;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_W)
        {
            keyW = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_A)
        {
            keyA = true;
        }
        else if (e.getKeyCode() == KeyEvent.VK_S) {
            keyS = true;
        }
        else if(e.getKeyCode() == KeyEvent.VK_D)
        {
            keyD = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        if(e.getKeyCode() == KeyEvent.VK_W)
        {
            keyW = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_A)
        {
            keyA = false;
        }
        else if (e.getKeyCode() == KeyEvent.VK_S) {
            keyS = false;
        }
        else if(e.getKeyCode() == KeyEvent.VK_D)
        {
            keyD = false;
        }

    }
}
