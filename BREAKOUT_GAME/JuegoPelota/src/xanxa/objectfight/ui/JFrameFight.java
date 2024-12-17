package xanxa.objectfight.ui;

import xanxa.objectfight.game.GameManager;
import xanxa.objectfight.game.gameobject.Ball;
import xanxa.objectfight.game.gameobject.Player;
import xanxa.objectfight.game.gameobject.Wall;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import xanxa.objectfight.game.gameobject.GameObject;
import xanxa.objectfight.uiStartLevelEnd.InterfazFinalJuego;
import xanxa.objectfight.uiStartLevelEnd.NivelCompletadoN1;
import xanxa.objectfight.uiStartLevelEnd.NivelCompletadoN2;
import xanxa.objectfight.uiStartLevelEnd.NivelCompletadoN3;
import xanxa.objectfight.uiStartLevelEnd.NivelCompletadoN4;
import xanxa.objectfight.uiStartLevelEnd.NivelCompletadoN5;
import xanxa.objectfight.uiStartLevelEnd.PausarJuego;

public class JFrameFight extends JFrame {
    private final double FPMILLIS = 60.0/1000.0; //60fps
    private final double TIME_DIFF_STANDARD = 1/FPMILLIS;//Constant value.
    private long lastUpdateTime = 0;
    private GameManager manager; // El gameManager
    private int currentLevel =1; // Establecer Nivel por el que empezar 1 a 5
    private static final int MAX_LEVELS = 5; // Establecer máximo de Niveles
    private static final int INITIAL_LIVES = 5; // Cantidad de vidas
    private Image backgroundImage; // Fondo de Pantalla
    private boolean isPaused = false;
    private JDialog pauseDialog; // Dialogo de Pausa
    
    // Constructor
    public JFrameFight() throws HeadlessException {
        super();
        
        // Carga la imagen de fondo
        try {
            backgroundImage = new ImageIcon(getClass().getResource("/EstadosDelJuego/FONDO.jpg")).getImage();
        } catch (Exception e) {
            System.out.println("Error al cargar la imagen de fondo: " + e.getMessage());
            e.printStackTrace();
        }

        JPanel panel = new JPanel() {
            @Override
            public void paint(Graphics g) {
                super.paint(g);
                
                // Dibuja la imagen de fondo
                if (backgroundImage != null) {
                    g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
                }
                
                if (!isPaused) {
                    initGameObjects(this);
                    manager.fixedUpdate();
                    manager.update(g);
                    marcador(g);
                    
                    checkLevelCompletion();
                }
                checkFPSToRepaint();
            }
            
            public void checkFPSToRepaint() {
                long actualTime = System.currentTimeMillis();
                double timeDiff = actualTime - lastUpdateTime;
                lastUpdateTime = actualTime;
                
                if (timeDiff > TIME_DIFF_STANDARD) {
                    repaint();
                } else {
                    double timeToWait = TIME_DIFF_STANDARD-timeDiff;
                    new Thread(){
                        @Override
                        public void run() {
                            super.run();
                            try {
                                Thread.sleep((long)timeToWait);
                            } catch (InterruptedException ex) {
                                Logger.getLogger(JFrameFight.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            repaint();
                        }
                    }.start();
                }
            }
        };
        
        panel.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}
            
            @Override
            public void keyPressed(KeyEvent e) {
                handleKeyPress(e.getKeyCode(), true);
            }
            
            @Override
            public void keyReleased(KeyEvent e) {
                handleKeyPress(e.getKeyCode(), false);
            }
        });
        
        panel.setFocusable(true);
        panel.requestFocusInWindow();
        
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.setUndecorated(true);
        this.setVisible(true);
    }

        private void handleKeyPress(int keyCode, boolean isPressed) {
        if (isPressed && keyCode == KeyEvent.VK_ESCAPE) {
            togglePause();
            return; // Salimos del método después de manejar la pausa
        }

        // Si el juego está pausado, no procesamos otras teclas
        if (isPaused) {
            return;
        }
        switch (currentLevel) {
            case 1:
            case 3:
                if (keyCode == KeyEvent.VK_RIGHT) {
                    manager.rightPushed(isPressed, 1);
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    manager.leftPushed(isPressed, 1);
                }
                break;
            case 2:
            case 4:
            case 5:
                if (keyCode == KeyEvent.VK_RIGHT) {
                    manager.rightPushed(isPressed, 1);
                } else if (keyCode == KeyEvent.VK_LEFT) {
                    manager.leftPushed(isPressed, 1);
                } else if (keyCode == KeyEvent.VK_D) {
                    manager.rightPushed(isPressed, 2);
                } else if (keyCode == KeyEvent.VK_A) {
                    manager.leftPushed(isPressed, 2);
                }
                break;
        }
    }
    private void togglePause() {
        // Establecer la Pausa
        if (!isPaused) {
            pausarPartida();
        } else {
            retomarPartida();
        }
    }
    private void pausarPartida() {
       // Pausar la lógica del juego aquí (detener timers, animaciones, etc.)

        isPaused = true;

        SwingUtilities.invokeLater(() -> {
            pauseDialog = new PausarJuego(this, true);
            pauseDialog.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    retomarPartida();
                }
            });
            pauseDialog.setVisible(true);
        });
    }

    private void retomarPartida() {
        isPaused = false;
        // Reanudar la lógica del juego aquí (reiniciar timers, animaciones, etc.)

        SwingUtilities.invokeLater(() -> {
            if (pauseDialog != null) {
                pauseDialog.dispose();
                pauseDialog = null;
            }
            // Asegúrate de que el panel del juego vuelva a tener el foco
            this.getContentPane().getComponent(0).requestFocusInWindow();
        });
    }
    private void pararNivel() {
        isPaused = true;
    }
    private void pasoAlsiguienteNivel() {
        isPaused = false;
    }
    private void initGameObjects(JPanel panel) {
        // Iniciar el objeto del Juego el GameManager
        if (this.manager == null) {
            this.manager = new GameManager(this);
        }
        if (this.manager.getGameZone() == null) {
            this.manager.setGameZone(new Rectangle(this.getX(), this.getY(), this.getWidth(), this.getHeight()));
            initLevel(panel);
        }
    }
    private void initLevel(JPanel panel) {
        // Init de cada Nivel.
        manager.clearGameObjects(); // Limpieza antes de empezar el nivel.
        switch(currentLevel) {
            case 1:
                initLevelOne(panel);
                break;
            case 2:
                initLevelTwo(panel);
                break;
            case 3:
                initLevelThree(panel);
                break;
            case 4:
                initLevelFour(panel);
                break;
            case 5:
                initLevelFive(panel);
                break;
            default:
                break;
        }
    }
// ##########################                 NIVELES                 ##########################

    /**
     * Inicializa el primer nivel del juego.
     * Configura un solo jugador, una pelota y muros básicos.
     */
    private void initLevelOne(JPanel panel) {
        manager.setDualBarMode(false);
        manager.setPlayerLives(INITIAL_LIVES);
        Player jugador = crearJugadorInferior(panel);
        Ball pelota = crearPelota(panel);
        this.manager.addGameObject(pelota);
        this.manager.addGameObject(jugador);
        crearMurosNivel1(panel);
    }

    /**
     * Inicializa el segundo nivel del juego.
     * Introduce el modo de dos jugadores con barras en la parte superior e inferior.
     */
    private void initLevelTwo(JPanel panel) {
        manager.setDualBarMode(true);
        manager.setPlayerLives(INITIAL_LIVES);
        Player jugador1 = crearJugadorInferior(panel);
        Player jugador2 = crearJugadorSuperior(panel);
        Ball pelota = crearPelota(panel);
        this.manager.addGameObject(pelota);
        this.manager.addGameObject(jugador1);
        this.manager.addGameObject(jugador2);
        crearMurosNivel2(panel);
    }

    /**
     * Inicializa el tercer nivel del juego.
     * Vuelve al modo de un jugador pero introduce dos pelotas que se mueven en direcciones opuestas.
     */
    private void initLevelThree(JPanel panel) {
        manager.setDualBarMode(false);
        manager.setPlayerLives(INITIAL_LIVES);
        Player jugador = crearJugadorInferior(panel);
        Ball pelota1 = crearPelota(panel);
        Ball pelota2 = crearPelota(panel);
        // Posiciona las pelotas en el centro del panel, separadas verticalmente
        double centroX = panel.getWidth() * 0.5;
        double centroY = panel.getHeight() * 0.5;
        double separacion = pelota1.getHeight() * 0.5;
        pelota1.setX(centroX - pelota1.getWidth() * 0.5);
        pelota1.setY(centroY - pelota1.getHeight() - separacion);
        pelota2.setX(centroX - pelota2.getWidth() * 0.5);
        pelota2.setY(centroY + separacion);
        // Establece velocidades iniciales opuestas para las pelotas
        pelota1.setSpeedX(0.0);
        pelota1.setSpeedY(-5.0);
        pelota2.setSpeedX(0.0);
        pelota2.setSpeedY(5.0);
        this.manager.addGameObject(pelota1);
        this.manager.addGameObject(pelota2);
        this.manager.addGameObject(jugador);
        crearMurosNivel3(panel);
    }

    /**
     * Inicializa el cuarto nivel del juego.
     * Vuelve al modo de dos jugadores pero aumenta la velocidad de la pelota.
     */
    private void initLevelFour(JPanel panel) {
        manager.setDualBarMode(true);
        manager.setPlayerLives(INITIAL_LIVES);
        Player jugador1 = crearJugadorInferior(panel);
        Player jugador2 = crearJugadorSuperior(panel);
        Ball pelota = crearPelota(panel);
        // Aumenta la velocidad de la pelota en un 50%
        pelota.setSpeedX(pelota.getSpeedX() * 1.5);
        pelota.setSpeedY(pelota.getSpeedY() * 1.5);
        this.manager.addGameObject(pelota);
        this.manager.addGameObject(jugador1);
        this.manager.addGameObject(jugador2);
        crearMurosNivel4(panel);
    }

    /**
     * Inicializa el quinto nivel del juego.
     * Combina el modo de dos jugadores con dos pelotas moviéndose en direcciones opuestas.
     */
    private void initLevelFive(JPanel panel) {
        manager.setDualBarMode(true);
        manager.setPlayerLives(INITIAL_LIVES);
        Player jugador1 = crearJugadorInferior(panel);
        Player jugador2 = crearJugadorSuperior(panel);
        Ball pelota1 = crearPelota(panel);
        Ball pelota2 = crearPelota(panel);
        // Posiciona las pelotas en el centro del panel, separadas verticalmente
        double centroX = panel.getWidth() * 0.5;
        double centroY = panel.getHeight() * 0.5;
        double separacion = pelota1.getHeight() * 0.5;
        pelota1.setX(centroX - pelota1.getWidth() * 0.5);
        pelota1.setY(centroY - pelota1.getHeight() - separacion);
        pelota2.setX(centroX - pelota2.getWidth() * 0.5);
        pelota2.setY(centroY + separacion);
        // Establece velocidades iniciales opuestas para las pelotas
        pelota1.setSpeedX(0.0);
        pelota1.setSpeedY(-5.0);
        pelota2.setSpeedX(0.0);
        pelota2.setSpeedY(5.0);
        this.manager.addGameObject(pelota1);
        this.manager.addGameObject(pelota2);
        this.manager.addGameObject(jugador1);
        this.manager.addGameObject(jugador2);
        crearMurosNivel5(panel);
    }
// ##########################                  MUROS                 ##########################
    /**
     * Método auxiliar para crear y añadir un muro al juego.
     */
    private void crearYAnadirMuro(double x, double y, double ancho, double alto, int vida, Color color, boolean esIrrompible) {
        Wall muro = new Wall(x, y, ancho, alto, vida, color, esIrrompible);
        muro.setBorderColor(Color.WHITE);
        this.manager.addGameObject(muro);
    }

    /**
     * Crea los muros para el nivel 1.
     * @param panel Panel del juego
     */
    private void crearMurosNivel1(JPanel panel) {
        int anchoPantalla = panel.getWidth();
        int altoPantalla = panel.getHeight();
        int numeroMurosHorizontal = 10;
        double anchoMuro = (double) anchoPantalla / numeroMurosHorizontal;
        double altoMuro = (altoPantalla * 5.0) / 100.0;
        int numFilas = (int)(altoPantalla / 4 / altoMuro);

        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numeroMurosHorizontal; j++) {
                crearYAnadirMuro(j * anchoMuro, i * altoMuro, anchoMuro, altoMuro, 
                                 i / 100 + 1, Color.BLACK, false);
            }
        }
    }

    /**
     * Crea los muros para el nivel 2.
     * @param panel Panel del juego
     */
    private void crearMurosNivel2(JPanel panel) {
        int anchoPantalla = panel.getWidth();
        int altoPantalla = panel.getHeight();
        int numeroMurosHorizontal = 10;
        double anchoMuro = (double) anchoPantalla / numeroMurosHorizontal;
        double altoMuro = (altoPantalla * 4.0) / 100.0;
        int numFilas = 5;
        Random random = new Random();
        Color[] colores = {Color.BLUE, Color.GREEN, Color.ORANGE, Color.RED};

        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numeroMurosHorizontal; j++) {
                if (random.nextDouble() > 0.2) {
                    crearYAnadirMuro(j * anchoMuro, 
                                     altoPantalla / 2 - (numFilas * altoMuro / 2) + i * altoMuro,
                                     anchoMuro, altoMuro, 2,
                                     colores[random.nextInt(colores.length)], false);
                }
            }
        }
    }

    /**
     * Crea los muros para el nivel 3.
     * @param panel Panel del juego
     */
    private void crearMurosNivel3(JPanel panel) {
        int anchoPantalla = panel.getWidth();
        int altoPantalla = panel.getHeight();
        int numeroMurosHorizontal = 10;
        double anchoMuro = (double) anchoPantalla / numeroMurosHorizontal;
        double altoMuro = (altoPantalla * 5.0) / 100.0;
        int numFilas = (int)(altoPantalla / 4 / altoMuro);

        for (int i = 0; i < numFilas; i++) {
            for (int j = 0; j < numeroMurosHorizontal; j++) {
                boolean esIrrompible = (i == 2 && (j == 3 || j == 7)) ||
                                       (i == 0 && j == 0) ||
                                       (i == 0 && j == numeroMurosHorizontal - 1);
                crearYAnadirMuro(j * anchoMuro, i * altoMuro, anchoMuro, altoMuro,
                                 esIrrompible ? Integer.MAX_VALUE : i + 1,
                                 esIrrompible ? Color.DARK_GRAY : Color.GRAY, esIrrompible);
            }
        }
    }

    /**
     * Crea los muros para el nivel 4.
     * @param panel Panel del juego
     */
    private void crearMurosNivel4(JPanel panel) {
        int anchoPantalla = panel.getWidth();
        int altoPantalla = panel.getHeight();
        int numeroMurosHorizontal = 12;
        double anchoMuro = (double) anchoPantalla / numeroMurosHorizontal;
        double altoMuro = (altoPantalla * 4.0) / 100.0;
        int numFilas = 5;

        for (int fila = 0; fila < numFilas; fila++) {
            for (int col = 0; col < numeroMurosHorizontal; col++) {
                boolean esIrrompible = (fila == 2 && (col == 3 || col == 8)) ||
                                       (fila == 0 && (col == 0 || col == numeroMurosHorizontal - 1)) ||
                                       (fila == numFilas - 1 && (col == 0 || col == numeroMurosHorizontal - 1));
                crearYAnadirMuro(col * anchoMuro, (altoPantalla / 3) + (fila * altoMuro * 1.5),
                                 anchoMuro, altoMuro, esIrrompible ? 1 : 3,
                                 esIrrompible ? Color.DARK_GRAY : Color.GRAY, esIrrompible);
            }
        }
    }

    /**
     * Crea los muros para el nivel 5.
     * @param panel Panel del juego
     */
    private void crearMurosNivel5(JPanel panel) {
        int anchoPantalla = panel.getWidth();
        int numeroMurosHorizontal = 14;
        int numeroMurosVertical = 10;
        double anchoMuro = (double) anchoPantalla / numeroMurosHorizontal;
        double altoMuro = 30;
        Random random = new Random();
        Color[] colores = {Color.BLUE, Color.GREEN, Color.ORANGE, Color.RED, Color.MAGENTA};

        int seguridadX = numeroMurosHorizontal / 2 - 1;
        int seguridadYSuperior = numeroMurosVertical / 2 - 1;
        int seguridadYInferior = numeroMurosVertical / 2 + 1;

        for (int fila = 1; fila < numeroMurosVertical - 1; fila++) {
            for (int col = 0; col < numeroMurosHorizontal; col++) {
                if ((col == seguridadX || col == seguridadX + 1) &&
                    (fila == seguridadYSuperior || fila == seguridadYInferior)) {
                    continue;
                }
                if (random.nextDouble() > 0.7) {
                    boolean esIndestructible = random.nextDouble() > 0.8;
                    crearYAnadirMuro(col * anchoMuro, fila * (altoMuro + 10), anchoMuro, altoMuro,
                                     esIndestructible ? Integer.MAX_VALUE : random.nextInt(3) + 1,
                                     esIndestructible ? Color.DARK_GRAY : colores[random.nextInt(colores.length)],
                                     esIndestructible);
                }
            }
        }
    }
    /**
     * Crea una pelota específica para el modo de 1 pelota.
     * @param panel El panel del juego donde se colocará la pelota.
     * @return Una nueva instancia de Ball.
     */
    private Ball crearPelota(JPanel panel) {
        // Calcula las dimensiones de la pelota basadas en porcentajes del panel
        double widthPercent = 3.5;
        double heightPercent = 5;
        double width = (panel.getWidth() * widthPercent) / 100.0;
        double height = (panel.getHeight() * heightPercent) / 100.0;

        // Busca el jugador en los objetos del juego
        Player jugador = null;
        for (GameObject obj : manager.getGameObjects()) {
            if (obj instanceof Player) {
                jugador = (Player) obj;
                break;
            }
        }

        double xPelota;
        double yPelota;
        if (jugador != null) {
            // Coloca la pelota en el centro del jugador
            xPelota = jugador.getX() + (jugador.getWidth() / 2) - (width / 2);
            yPelota = jugador.getY() - height - 1; // 1 píxel por encima del jugador
        } else {
            // Si no se encuentra el jugador, coloca la pelota en el centro de la pantalla
            xPelota = (panel.getWidth() - width) / 2;
            yPelota = (panel.getHeight() - height) / 2;
        }

        // Crea la pelota con las dimensiones y posición calculadas
        Ball pelotita = new Ball(
            xPelota,
            yPelota,
            width,
            height,
            Color.BLACK
        );

        // Inicia con velocidad vertical negativa (hacia arriba)
        pelotita.setSpeedX(0);
        pelotita.setSpeedY(-7.0);

        return pelotita;
    }

    /**
     * Crea un jugador con parámetros específicos.
     * @param panel El panel del juego donde se colocará el jugador.
     * @param color El color del jugador.
     * @param esJugadorSuperior Indica si el jugador debe colocarse en la parte superior.
     * @return Una nueva instancia de Player.
     */
    private Player crearJugador(JPanel panel, Color color, boolean esJugadorSuperior) {
        double anchoJugadorPercent = 15.0;
        double alturaJugadorPercent = 3.0;
        double elevacionPercent = 2.0;

        // Calcula las dimensiones del jugador
        double anchoJugador = (panel.getWidth() * anchoJugadorPercent) / 100.0;
        double alturaJugador = (panel.getHeight() * alturaJugadorPercent) / 100.0;
        double xJugador = (panel.getWidth() - anchoJugador) / 2.0;
        double elevacion = (panel.getHeight() * elevacionPercent) / 100.0;

        // Calcula la posición Y del jugador
        double yJugador = esJugadorSuperior ? elevacion : panel.getHeight() - alturaJugador - elevacion;

        return new Player(xJugador, yJugador, anchoJugador, alturaJugador, color);
    }

    /**
     * Crea un jugador en la parte inferior del panel.
     * @param panel El panel del juego donde se colocará el jugador.
     * @return Una nueva instancia de Player en la parte inferior.
     */
    private Player crearJugadorInferior(JPanel panel) {
        return crearJugador(panel, Color.BLUE, false);
    }

    /**
     * Crea un jugador en la parte superior del panel.
     * @param panel El panel del juego donde se colocará el jugador.
     * @return Una nueva instancia de Player en la parte superior.
     */
    private Player crearJugadorSuperior(JPanel panel) {
        return crearJugador(panel, Color.RED, true);
    }
    // MARCADOR
    public void marcador(Graphics g) {
        g.setColor(Color.red);
        g.setFont(g.getFont().deriveFont(20f));
        
        // Mostrar puntos y nivel
        g.drawString("PUNTOS - " + manager.getPoints() + " | NIVEL - " + currentLevel, 10, 20);
        
        // Opción 1: Mostrar vidas como número
        g.drawString("VIDAS - " + manager.getPlayerLives(), 10, 50);
        
        // Opción 2: Mostrar vidas como iconos
        drawLifeIcons(g);
    }
    // PINTADO DE VIDAS
    private void drawLifeIcons(Graphics g) {
        int lives = manager.getPlayerLives();
        int iconSize = 20;
        int startX = 10;
        int startY = 60;
        int padding = 5;

        for (int i = 0; i < lives; i++) {
            g.setColor(Color.red);
            g.fillOval(startX + (iconSize + padding) * i, startY, iconSize, iconSize);
            g.setColor(Color.white);
            g.drawOval(startX + (iconSize + padding) * i, startY, iconSize, iconSize);
        }
    }
    private void checkLevelCompletion() {
        if (manager.areAllBreakableBricksDestroyed()) {
            // Pasar al siguiente nivel EN CASO DE QUE ESTÉ HASTA LOS INDESTRUCTIBLES VACÍOS
            pararNivel();
            showLevelCompletedDialog();
        }
       
    }
       private void showLevelCompletedDialog() {
        SwingUtilities.invokeLater(() -> {
            JDialog nivelCompletadoDialog = null;
            switch (currentLevel) {
                case 1: nivelCompletadoDialog = new NivelCompletadoN1(this, true); break;
                case 2: nivelCompletadoDialog = new NivelCompletadoN2(this, true); break;
                case 3: nivelCompletadoDialog = new NivelCompletadoN3(this, true); break;
                case 4: nivelCompletadoDialog = new NivelCompletadoN4(this, true); break;
                case 5: nivelCompletadoDialog = new NivelCompletadoN5(this, true); break;

            }
            if (nivelCompletadoDialog != null) {
                nivelCompletadoDialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosed(java.awt.event.WindowEvent e) {
                        startNextLevel();
                    }
                });
                nivelCompletadoDialog.setVisible(true);
            }
        });
    }

    private void startNextLevel() {
        currentLevel++;
        if (currentLevel > MAX_LEVELS) {
            this.dispose();
            SwingUtilities.invokeLater(() -> {
                InterfazFinalJuego interfazFinalJuego = new InterfazFinalJuego(null, true);
                interfazFinalJuego.setVisible(true);
            });
        } else {
            initLevel((JPanel)this.getContentPane().getComponent(0));
            pasoAlsiguienteNivel();

        }
    }

}