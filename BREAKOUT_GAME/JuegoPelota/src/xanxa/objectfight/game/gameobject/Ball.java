package xanxa.objectfight.game.gameobject;

import xanxa.objectfight.game.colliders.Collider;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Ball extends GameObject {
    private Color color;
    private boolean alive = true;
    private static final double MAX_SPEED = 5.0;
    private double speedX = -1, speedY = -1;

    public Ball(double x, double y, double width, double height, Color color) {
        super(x, y, width, height);
        this.color = color;
    }

    @Override
    public boolean paint(Graphics g) {
        Color previousColor = g.getColor();
        g.setColor(color);
        double tmpX = col.getX();
        double tmpY = col.getY();
        double width = col.getWidth();
        double height = col.getHeight();
        g.fillOval((int)tmpX, (int)tmpY, (int)width, (int)height);
        
        //g.drawString("Ball   x "+getX()+", y "+getY(), 10, 270);
        //g.drawString("CollBl x "+col.getX()+", y "+col.getY(), 10, 300);
        
        g.setColor(previousColor);
        super.paint(g);
        return isAlive();
    }

    @Override
    public boolean behaviour() {
        boolean result = super.behaviour();
        double x = getX() + speedX;
        double y = getY() + speedY;
        updatePosition(x, y);
        return result;
    }

    @Override
    public boolean isAlive() {
        return alive;
    }

    public boolean goAway(GameObject block) {
        Rectangle ballBounds = this.getCollider().getRectangle();
        Rectangle blockBounds = block.getCollider().getRectangle();
        
        if (ballBounds.intersects(blockBounds)) {
            // Calcular la penetración
            double overlapLeft = blockBounds.getMaxX() - ballBounds.getMinX();
            double overlapRight = ballBounds.getMaxX() - blockBounds.getMinX();
            double overlapTop = blockBounds.getMaxY() - ballBounds.getMinY();
            double overlapBottom = ballBounds.getMaxY() - blockBounds.getMinY();

            // Encontrar la menor penetración
            double minOverlapX = Math.min(overlapLeft, overlapRight);
            double minOverlapY = Math.min(overlapTop, overlapBottom);

            // Resolver la colisión
            if (minOverlapX < minOverlapY) {
                // Colisión horizontal
                speedX = -speedX; // Invertir la dirección horizontal
                if (overlapLeft < overlapRight) {
                    setX(blockBounds.getMaxX());
                } else {
                    setX(blockBounds.getMinX() - getWidth());
                }
            } else {
                // Colisión vertical
                speedY = -speedY; // Invertir la dirección vertical
                if (overlapTop < overlapBottom) {
                    setY(blockBounds.getMaxY());
                } else {
                    setY(blockBounds.getMinY() - getHeight());
                }
            }

            // Asegurar que la velocidad no exceda el máximo
           speedX = Math.signum(speedX) * Math.min(Math.abs(speedX), MAX_SPEED);
           speedY = Math.signum(speedY) * Math.min(Math.abs(speedY), MAX_SPEED);
            return true;
        }
        return false;
    }

    public double getSpeedX() {
        return speedX;
    }

    public void setSpeedX(double speedX) {
        this.speedX = Math.min(Math.abs(speedX), MAX_SPEED) * Math.signum(speedX);
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setSpeedY(double speedY) {
       this.speedY = Math.min(Math.abs(speedY), MAX_SPEED) * Math.signum(speedY);
    }

    @Override
    public String toString() {
        return "Ball ["+((int)getX())+", "+((int)getY())+", "+((int)getWidth())+", "+((int)getHeight())+"]";
    }
}