import java.awt.Graphics;

public class LineAlgorithms {

    private static void putPixel(Graphics g, int x, int y) {
        g.drawLine(x, y, x, y);
    }

    public static void algAnalitic(Graphics g, int xi, int yi, int xf, int yf) {
        float m, b, dy, dx;
        dy = yf - yi;
        dx = xf - xi;

        m = (float) dy / dx;

        b = (float) (yi - m * xi);

        for (int x = xi; x <= xf; x++) {
            int y = (int) (m * x + b);
            putPixel(g, x, y);
        }
    }

    public static void algDDA(Graphics g, int xi, int yi, int xf, int yf) {

        int steps;
        float x = xi, y = yi, incX, incY;

        int dx = xf - xi;
        int dy = yf - yi;

        if (Math.abs(dx) > Math.abs(dy)) {
            steps = Math.abs(dx);
            incX = 1;
            incY = (float) dy / dx;
        } else {
            steps = Math.abs(dy);
            incY = 1;
            incX = (float) dx / dy;
        }

        System.out.println(steps + "-" + dx + "-" + dy + "-" + incX + "-" + incY);

        for (int i = 0; i < steps; i++) {
            x = x + incX;
            y = y + incY;
            putPixel(g, Math.round(x), Math.round(y));
        }
    }

    public static void algBres(Graphics g, int xi, int yi, int xf, int yf) {

        int x = xi, y = yi, d = 0, dx = xf - xi, dy = yf - yi, c, m, incX = 1, incY = 1;

        if (dx < 0) {
            incX = -1;
            dx = -dx;
        }
        if (dy < 0) {
            incY = -1;
            dy = -dy;
        }
        System.out.println(dx + "-" + dy + "-" + incX + "-" + incY);

        if (dy <= dx) {
            c = 2 * dx;
            m = 2 * dy;
            System.out.println(c + "-" + m);
            for (; ; ) {
                putPixel(g, x, y);
                if (x == xf) break;
                x += incX;
                d += m;
                if (d >= dx) {
                    y += incY;
                    d -= c;
                }
                System.out.println(x + "-" + y);
            }
        } else {
            c = 2 * dy;
            m = 2 * dx;
            System.out.println(c + "-" + m);
            for (; ; ) {
                putPixel(g, x, y);
                if (y == yf) break;
                y += incY;
                d += m;
                if (d >= dy) {
                    x += incX;
                    d -= c;
                }
                System.out.println(x + "-" + y);
            }

        }
    }
}