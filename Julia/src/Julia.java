public class Julia {
    private final int WIDTH;
    private final int HEIGHT;
    protected boolean isInSet;
    private static double cX;
    private static double cY;
    private static double zX;
    private static double zY;
    private static int iterator;

    //Konstruktor Julji
    public Julia(double x, double y, int width, int height) {
        this.WIDTH = width;
        this.HEIGHT = height;
        cX = x;
        cY = y;
    }

    //Odwzorowuje współrzędne x, y płótna na obszar zainteresowania
    public void pointMap(double i, double j) {
        zX = (((double) i * 2) / WIDTH) - 1;
        zY = 1 - (((double) j * 2) / HEIGHT) ;

    }

    //znajduje czy odwzorowane współrzędne znajdują się w zbiorze Julii
    public boolean isInSet(int iterations) {

        iterator = 0;
        for (; iterator < iterations; iterator++) {
            double z_new_x = (zX * zX) - (zY * zY) + cX;
            double z_new_y = (2 * zX * zY) + cY;
            zX = z_new_x;
            zY = z_new_y;
            iterator++;

            //zamiast testować POW(Z) > 2 możemy testować POW(Z^2) > 4
            if ((Math.pow(zX, 2) + Math.pow(zY, 2)) > 4) {
                return isInSet = false;
            }
        }
        return isInSet = true;
    }

    //zwraca wartość iteratora w celu obliczenia odpowiedniego koloru
    public int get_iterator_val() {
        return iterator;
    }
}