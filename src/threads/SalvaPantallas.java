package threads;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

import gui.VentanaPrincipal;

public class SalvaPantallas extends JFrame {
    private static final long serialVersionUID = 1L;
    private int x = 50, y = 50; // Posición inicial del círculo
    private int dx = 2, dy = 2; // Dirección del movimiento del círculo
    private final int RADIUS = 30; // Radio del círculo

    public SalvaPantallas() {
        setTitle("Salvapantallas");
        setSize(1200,750);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        //al hacer clic, cerrar el salvapantallas y volver a la ventana principal
        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dispose(); // Cierra el salvapantallas
                VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
                ventanaPrincipal.setVisible(true); // Muestra la ventana principal
            }
        });

        //inicia el hilo que mueve el círculo
        Thread screensaverThread = new Thread(new ScreensaverRunnable());
        screensaverThread.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(Color.BLUE); //azul pero se pondrá el logo
        g.fillOval(x - RADIUS, y - RADIUS, 2 * RADIUS, 2 * RADIUS); 
    }

    // Clase interna para manejar la animación
    private class ScreensaverRunnable implements Runnable {
        @Override
        public void run() {
            while (true) {
                x += dx;
                y += dy;

                //cambiar la dirección si toca los bordes
                if (x + RADIUS >= getWidth() || x - RADIUS <= 0) {
                    dx = -dx;
                }
                if (y + RADIUS >= getHeight() || y - RADIUS <= 0) {
                    dy = -dy;
                }

                repaint();

                try {
                    Thread.sleep(10); //pausa 10 milisegundos
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
