package xanxa.objectfight.game.gameobject;

import xanxa.objectfight.game.gameobject.GameObject;
import java.awt.Color;
import java.awt.Graphics;

/**
 * Clase Wall (muro) para el juego.
 * @author Manuel
 */
public class Wall extends GameObject {
    int lives;
    int maxLives;
    Color color;
    Color borderColor;
    boolean unbreakable; // Nuevo atributo para muros irrompibles

    // Constructor
    public Wall(double x, double y, double width, double height, int lives, Color color, boolean unbreakable) {
        super(x, y, width, height);
        this.lives = lives;
        this.maxLives = lives;
        this.color = color;
        this.borderColor  = color;
        this.unbreakable = unbreakable; // Asigna el valor al nuevo atributo
    }

    @Override
    public boolean paint(Graphics g) {
        Color tmp = g.getColor();
        g.setColor(color);
        double tmpX = getX();
        double tmpY = getY();
        double width = getWidth();
        double height = getHeight();
        g.fillRect((int)tmpX, (int)tmpY, (int)width, (int)height);
        g.setColor(borderColor);
        g.drawRect((int)tmpX, (int)tmpY, (int)width, (int)height);
        super.paint(g);
        g.setColor(tmp);
        return isAlive();
    }

    @Override
    public boolean isAlive() {
        return lives > 0 || unbreakable; // Un muro irrompible siempre está "vivo"
    }

    /**
     * Este método es llamado cuando la bola toca el muro.
     * Si el muro no es irrompible, reduce el número de vidas y actualiza el color.
     */
    public void touched() {
        if (!unbreakable) { // Solo reduce vidas si no es irrompible
            lives--;
            int alpha = (int)(255f * (float)lives / (float)maxLives);
            if (alpha < 0) alpha = 0;
            else if (alpha > 255) alpha = 255;
            color = new Color(color.getRed(), color.getGreen(), color.getBlue(), alpha);
        }
    }

    // Métodos getter y setter para la propiedad unbreakable
    public boolean isUnbreakable() {
        return unbreakable;
    }

    public void setUnbreakable(boolean unbreakable) {
        this.unbreakable = unbreakable;
    }

    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    @Override
    public String toString() {
        return "Wall [" + ((int)getX()) + ", " + ((int)getY()) + ", " + ((int)getWidth()) + ", " + ((int)getHeight()) + "]";
    }
}
