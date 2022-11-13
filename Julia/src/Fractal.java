import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.*;
import java.util.concurrent.*;

public class Fractal extends JPanel {

    private static final int WIDTH = 800;
    private static final int HEIGHT = 800;
    private static final int THREADS = 800;
    private int iterations;
    private boolean isJulia = false;
    // inicjalizacjia obiekty klasy Julia
    Julia julia = null;

    public static void main(String[] args) {
        //inicjalizacja płutna z JFrame
        JFrame canvas_frame = null;
            //ustawienie pozycji startowej x,y na 0 oraz ilość iteracji na 0
            double start_x = 0;
            double start_y = 0;
            int iterations = 0;
            // tu przypisuje seobie wartości jakie tam chce do obliczeń
            start_x = -0.4;
            start_y = 0.6;
            iterations = 1000;


            canvas_frame = new JFrame("Julia");
            canvas_frame.setContentPane(new Fractal(start_x, start_y, iterations));

        canvas_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        canvas_frame.pack();
        canvas_frame.setLocationRelativeTo(null);
        canvas_frame.setVisible(true);

    }
    //konstruktor fraktala Julji
    public Fractal(double x, double y, int iterations) {

        this.iterations = iterations;
        isJulia = true;
        julia = new Julia(x, y, WIDTH, HEIGHT);
        // wielkość panela
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
    }

    //wywołaj paintComponent z klasy nadrzędnej
    public void paintComponent(Graphics g) {
        //wywołaj paintComponent z klasy nadrzędnej
        super.paintComponent(g);

        try {
            // Usługa ExecutorService automatycznie udostępnia pulę wątków i interfejsów API dla
            // przydzielania zadan.
            ExecutorService executorService = Executors.newFixedThreadPool(THREADS);

            for (int thread = 0; thread < THREADS; thread++) {
                    executorService.submit(new JuliaThread(thread, julia, iterations, (Graphics2D) g, HEIGHT));
            }
            //inicjuje uporządkowane zamknięcie
            executorService.shutdown();
            //blokuje do momentu zakończenia wykonywania wszystkich zadań po żądaniu zamknięcia
            executorService.awaitTermination(7, TimeUnit.SECONDS);

        } catch (Exception err) {
            err.printStackTrace();
        }

    }
}