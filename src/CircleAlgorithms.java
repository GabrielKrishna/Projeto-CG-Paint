import java.awt.Graphics;
public class CircleAlgorithms {
    public static void simetria(Graphics g, int x, int y, int centerX, int centerY) {
        putPixel(g,x,y,centerX,centerY);
        putPixel(g,x,-y,centerX,centerY);
        putPixel(g,-x,y,centerX,centerY);
        putPixel(g,-x,-y,centerX,centerY);
        putPixel(g,y,x,centerX,centerY);
        putPixel(g,y,-x,centerX,centerY);
        putPixel(g,-y,x,centerX,centerY);
        putPixel(g,-y,-x,centerX,centerY);
    }

    public static void putPixel(Graphics g, int x, int y, int centerX, int centerY) {
        g.drawLine(x+centerX, y+centerY, x+centerX, y+centerY);
    }
/*
    public static void algParam(Graphics g, int raio) {
        int x, y;

        for (int ang = 0; ang < 360; ang++) {
            x = (int) (raio * Math.cos(Math.PI * ang / 180));
            y = (int) (raio * Math.sin(Math.PI * ang / 180));
            putPixel(g, x, y);
        }
    }

    public static void algIncSem(Graphics g, int raio) {
        int x = 0, y = 0;
        double ang = 0.0;

        while (ang < Math.PI / 4) {
            x = (int) (raio * Math.cos(ang));
            y = (int) (raio * Math.sin(ang));
            //putPixel(g,x,y);
            simetria(g, x, y);
            ang = ang + (double) 1 / raio;
        }
    }
*/
    public static void algBres(Graphics g, int raio, int centerX, int centerY) {
        System.out.println("algBres_circ:"+raio);
        int x=0, y=raio, u=1, v=2 *raio-1, e=0;
        while(x<=y) {
            simetria(g,x,y,centerX,centerY);
            x++;
            e = e + u;
            u = u + 2;
            if (v<(2*e)) {y--; e=e-v; v=v-2;}
        }
    }
}