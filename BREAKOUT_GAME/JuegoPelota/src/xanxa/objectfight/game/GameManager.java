package xanxa.objectfight.game;

import xanxa.objectfight.game.gameobject.Ball;
import xanxa.objectfight.game.gameobject.GameObject;
import xanxa.objectfight.game.gameobject.Player;
import xanxa.objectfight.game.gameobject.Wall;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import javax.swing.SwingUtilities;
import xanxa.objectfight.ui.JFrameFight;
import xanxa.objectfight.uiStartLevelEnd.InterfazMuerte;

/**
 * Clase que gestiona el estado y la lógica del juego.
 */
public class GameManager {
    // Lista de objetos del juego
    List<GameObject> gameObjects;
    
    // Puntuación del jugador
    int points = 0;
    
    // Área de juego
    Rectangle gameZone;
    
    // Variables para controlar el movimiento del jugador
    double rightPushed = 0;
    double leftPushed = 0;
    
    // Vidas del jugador
    private int playerLives;
    
    // Número de paredes y bolas en el juego
    private int wallsNumber = 1;
    private int ballsNumber = 1;
    
    // Banderas para modos de juego especiales
    private boolean isTwoPlayerMode = false;
    private boolean isDualBarMode = false;
    
    // Referencia al frame principal del juego
    private JFrameFight mainFrame;

    /**
     * Constructor de GameManager.
     * @param mainFrame El frame principal del juego.
     */
    public GameManager(JFrameFight mainFrame) {
        super();
        this.gameObjects = new ArrayList<GameObject>();
        this.mainFrame = mainFrame;
    }

    /**
     * Obtiene la zona de juego.
     * @return Rectangle que representa la zona de juego.
     */
    public Rectangle getGameZone() {
        return gameZone;
    }

    /**
     * Establece la zona de juego.
     * @param gameZone Nueva zona de juego.
     */
    public void setGameZone(Rectangle gameZone) {
        this.gameZone = gameZone;
    }

    /**
     * Añade un objeto al juego.
     * @param gameObject Objeto a añadir.
     */
    public void addGameObject(GameObject gameObject) {
        this.gameObjects.add(gameObject);
    }

    /**
     * Actualiza el estado del movimiento hacia la derecha.
     * @param pushed True si se presiona la tecla derecha, false en caso contrario.
     */
    public void rightPushed(boolean pushed) {
        rightPushed = pushed ? 1 : 0;
    }

    /**
     * Actualiza el estado del movimiento hacia la izquierda.
     * @param pushed True si se presiona la tecla izquierda, false en caso contrario.
     */
    public void leftPushed(boolean pushed) {
        leftPushed = pushed ? 1 : 0;
    }

    /**
     * Obtiene el estado de vida del jugador.
     * @return Número de vidas del jugador.
     */
    public int getPlayerIsAlive() {
        return playerLives;
    }

    /**
     * Obtiene el número de paredes en el juego.
     * @return Número de paredes.
     */
    public int getWallsNumber() {
        return wallsNumber;
    }

    /**
     * Obtiene el número de bolas en el juego.
     * @return Número de bolas.
     */
    public int getBallsNumber() {
        return ballsNumber;
    }

    /**
     * Obtiene la puntuación actual.
     * @return Puntuación actual.
     */
    public int getPoints() {
        return points;
    }

    /**
     * Obtiene la lista de objetos del juego.
     * @return Lista de objetos del juego.
     */
    public List<GameObject> getGameObjects() {
        return this.gameObjects;
    }

    /**
     * Actualiza el estado del movimiento hacia la derecha para un jugador específico.
     * @param pushed True si se presiona la tecla derecha, false en caso contrario.
     * @param playerNumber Número del jugador (1 o 2).
     */
    public void rightPushed(boolean pushed, int playerNumber) {
        if (playerNumber == 1) {
            rightPushed = pushed ? 1 : 0;
        }
    }

    /**
     * Actualiza el estado del movimiento hacia la izquierda para un jugador específico.
     * @param pushed True si se presiona la tecla izquierda, false en caso contrario.
     * @param playerNumber Número del jugador (1 o 2).
     */
    public void leftPushed(boolean pushed, int playerNumber) {
        if (playerNumber == 1) {
            leftPushed = pushed ? 1 : 0;
        }
    }

    /**
     * Establece el modo de dos jugadores.
     * @param isTwoPlayerMode True para activar el modo de dos jugadores, false para desactivarlo.
     */
    public void setTwoPlayerMode(boolean isTwoPlayerMode) {
        this.isTwoPlayerMode = isTwoPlayerMode;
    }

    /**
     * Establece el modo de barra dual.
     * @param isDualBarMode True para activar el modo de barra dual, false para desactivarlo.
     */
    public void setDualBarMode(boolean isDualBarMode) {
        this.isDualBarMode = isDualBarMode;
    }

    /**
     * Establece el número de vidas del jugador.
     * @param lives Número de vidas a establecer.
     */
    public void setPlayerLives(int lives) {
        this.playerLives = lives;
    }

    /**
     * Obtiene el número de vidas del jugador.
     * @return Número de vidas del jugador.
     */
    public int getPlayerLives() {
        return this.playerLives;
    }

    /**
     * Maneja la pérdida de una vida del jugador.
     */
    private void handleLifeLoss() {
        playerLives--;
        System.out.println("Jugador perdió una vida. Vidas restantes: " + playerLives);
        
        if (playerLives <= 0) {
            endGame();
        } else {
            resetPositions();
        }
    }

    /**
     * Actualiza y dibuja todos los objetos del juego.
     * @param g Objeto Graphics para dibujar.
     */
    public void update(Graphics g) {
        for (GameObject enemy : gameObjects) {
            enemy.paint(g);
        }
    }
    /**
     * Actualiza el estado del juego en cada frame.
     */
    public void fixedUpdate() {
        boolean isInside = true;
        List<Player> bars = new ArrayList<>();
        wallsNumber = 0;
        ballsNumber = 0;
        Rectangle gameZone = getGameZone();
        boolean needReset = false;

        // Iterar sobre todos los objetos del juego
        for (int i = gameObjects.size() - 1; i >= 0; i--) {
            GameObject actual = gameObjects.get(i);
            actual.behaviour();
            List<GameObject> collisions = GameObject.collision(actual, gameObjects);
            boolean isInsideTmp = solveCollision(actual, collisions);

            if (!isInsideTmp) {
                isInside = false;
            }

            // Manejar comportamientos específicos según el tipo de objeto
            if (actual instanceof Player) {
                Player bar = (Player) actual;
                bar.updateSpeed(rightPushed, leftPushed);
                bloquearSalidaJugador(bar);
                bars.add(bar);
            } else if (actual instanceof Ball) {
                Ball ball = (Ball) actual;
                boolean ballHitTop = ball.getY() <= 0;
                boolean ballHitBottom = ball.getY() + ball.getHeight() >= gameZone.getHeight();
                if (ballHitBottom || (isDualBarMode && ballHitTop)) {
                    needReset = true;
                }
                ballsNumber++;
            } else if (actual instanceof Wall) {
                wallsNumber++;
            }

            // Eliminar objetos que ya no están vivos
            if (!actual.isAlive()) {
                gameObjects.remove(i);
            }
        }

        // Manejar la pérdida de vida y reinicio si es necesario
        if (needReset) {
            handleLifeLoss();
            if (playerLives > 0) {
                resetPositions();
            }
        }
    }

    /**
     * Finaliza el juego y muestra la interfaz de muerte.
     */
    public void endGame() {
        System.out.println("Game Over");
        // Ocultar el JFrameFight en lugar de cerrarlo
        if (mainFrame != null) {
            mainFrame.setVisible(false);
        }
        // Abrir la InterfazMuerte
        SwingUtilities.invokeLater(() -> {
            InterfazMuerte interfazMuerte = new InterfazMuerte(null, true);
            interfazMuerte.setVisible(true);
        });
    }

    /**
     * Reinicia las posiciones de las bolas y jugadores.
     */
    private void resetPositions() {
        Rectangle gameZone = getGameZone();
        List<Ball> balls = new ArrayList<>();

        // Recolectar todas las bolas
        for (GameObject obj : gameObjects) {
            if (obj instanceof Ball) {
                balls.add((Ball) obj);
            }
        }

        // Reiniciar posición de las bolas
        for (int i = 0; i < balls.size(); i++) {
            resetBallPosition(balls.get(i), i, balls.size());
        }

        // Reiniciar posición del jugador o jugadores
        for (GameObject obj : gameObjects) {
            if (obj instanceof Player) {
                Player player = (Player) obj;
                if (isDualBarMode) {
                    if (player.getY() < gameZone.getHeight() / 2) {
                        // Barra superior
                        player.setY(0);
                    } else {
                        // Barra inferior
                        player.setY(gameZone.getHeight() - player.getHeight());
                    }
                } else {
                    // Modo de una sola barra
                    player.setY(gameZone.getHeight() - player.getHeight());
                }
                player.setX(gameZone.getWidth() / 2 - player.getWidth() / 2);
            }
        }

        // Pausa breve para que el jugador se prepare
        try {
            Thread.sleep(1000); // Pausa de 1 segundo
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Reinicia la posición de una bola específica.
     * @param ball La bola a reiniciar.
     * @param index El índice de la bola.
     * @param totalBalls El número total de bolas.
     */
    private void resetBallPosition(Ball ball, int index, int totalBalls) {
        // Calcular el centro del área de juego
        double centerX = gameZone.getWidth() / 2;
        double centerY = gameZone.getHeight() / 2;

        // Establecer velocidades iniciales
        double speed = 5; // Velocidad fija
        ball.setSpeedX(0); // Sin movimiento horizontal inicial
        ball.setSpeedY(speed); // Movimiento hacia abajo

        // Definir la separación vertical entre las bolas
        double separacion = 100; // Puedes ajustar este valor según necesites

        // Colocar las bolas en X
        ball.setX(centerX - ball.getWidth() / 2);

        // Colocar las bolas en Y con separación
        if (totalBalls == 2) {
            if (index == 0) {
                // Primera bola
                ball.setSpeedY(-speed); // Primera bola va hacia arriba
                ball.setY(centerY - ball.getHeight() - separacion);
            } else {
                // Segunda bola
                ball.setSpeedY(speed);  // Segunda bola va hacia abajo
                ball.setY(centerY - ball.getHeight());
            }
        } else {
            // Si hay una sola bola o más de dos, todas empiezan en el centro
            ball.setY(centerY - ball.getHeight());
        }
    }

    /**
     * Resuelve las colisiones entre objetos.
     * @param actual El objeto actual.
     * @param collided Lista de objetos con los que colisiona.
     * @return true si el objeto está dentro de los límites, false en caso contrario.
     */
    private boolean solveCollision(GameObject actual, List<GameObject> collided) {
        boolean inside = true;
        if (actual instanceof Ball) {
            Ball ball = (Ball)actual;
            for (GameObject gameObject : collided) {
                if (gameObject instanceof Wall) {
                    Wall block = (Wall)gameObject;
                    if (ball.goAway(block)) {
                        block.touched();
                        if(!block.isUnbreakable()){
                            points++;
                        }
                        System.out.println("Points "+points);
                    }
                } else if (gameObject instanceof Player) {
                    Player player = (Player)gameObject;
                    if (ball.goAway(player)) {
                        double speedXBall = ball.getSpeedX();
                        double speedXPlayer = player.getSpeedX();
                        double newSpeedX = speedXBall + (speedXPlayer * 0.5); // Ajusta el factor de influencia según sea necesario
                        ball.setSpeedX(newSpeedX);
                    }
                }
            }
            inside = checkBallInside(ball);
        }
        return inside;
    }

    /**
     * Verifica si la bola está dentro de los límites del juego.
     * @param ball La bola a verificar.
     * @return true si la bola está dentro de los límites, false en caso contrario.
     */
    private boolean checkBallInside(Ball ball) {
        boolean isInside = true;
        double ballX = ball.getX();
        double ballY = ball.getY();
        double ballWidth = ball.getWidth();
        double ballHeight = ball.getHeight();

        if (ballX < gameZone.getX()) {
            ball.setX(gameZone.getX());
            ball.setSpeedX(Math.abs(ball.getSpeedX()));
        } else if (ballX + ballWidth > gameZone.getX() + gameZone.getWidth()) {
            ball.setX(gameZone.getX() + gameZone.getWidth() - ballWidth);
            ball.setSpeedX(-Math.abs(ball.getSpeedX()));
        }

        if (ballY < gameZone.getY()) {
            ball.setY(gameZone.getY());
            ball.setSpeedY(Math.abs(ball.getSpeedY()));
        } else if (ballY + ballHeight > gameZone.getY() + gameZone.getHeight()) {
            ball.setY(gameZone.getY() + gameZone.getHeight() - ballHeight);
            ball.setSpeedY(-Math.abs(ball.getSpeedY()));
            isInside = false; // La bola ha tocado el fondo
        }

        return isInside;
    }

    /**
     * Verifica si todos los bloques rompibles han sido destruidos.
     * @return true si todos los bloques rompibles han sido destruidos, false en caso contrario.
     */
    public boolean areAllBreakableBricksDestroyed() {
        for (GameObject obj : gameObjects) {
            if (obj instanceof Wall) {
                Wall wall = (Wall) obj;
                if (!wall.isUnbreakable() && wall.isAlive()) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Impide que el jugador salga de los límites del juego.
     * @param player El jugador a bloquear.
     */
    private void bloquearSalidaJugador(Player player) {
        double playerX = player.getX();
        double playerWidth = player.getWidth();
        if (playerX < 0) {
            player.setX(0);
        } else if (playerX + playerWidth > gameZone.getWidth()) {
            player.setX(gameZone.getWidth() - playerWidth);
        }
    }

    /**
     * Verifica si todos los bloques han sido destruidos.
     * @return true si todos los bloques han sido destruidos, false en caso contrario.
     */
    public boolean areAllBricksDestroyed() {
        for (GameObject obj : gameObjects) {
            if (obj instanceof Wall) {
                return false; // Si encuentra al menos un muro, retorna falso
            }
        }
        return true; // Si no encuentra ningún muro, retorna verdadero
    }

    /**
     * Limpia todos los objetos del juego y reinicia las variables.
     */
    public void clearGameObjects() {
        this.gameObjects.clear();
        this.points = 0;
        this.playerLives = 1;
        this.wallsNumber = 1;
        this.ballsNumber = 1;
    }
} 