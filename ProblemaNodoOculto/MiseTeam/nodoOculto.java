import java.util.Scanner;

public class nodoOculto {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x1 = sc.nextInt();
        int y1 = sc.nextInt();
        int x2 = sc.nextInt();
        int y2 = sc.nextInt();
        double dist = Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
        int r = 6;

        double areaOverlap = 0;
        double percent;
        if (dist>=12) {
            areaOverlap = 0;
            percent = 0;
            System.out.println(String.format("%.1f%%", percent));
        } else if (dist == 0) {
            areaOverlap = Math.pow(r,2)*Math.PI;
            percent = 100;
            System.out.println(String.format("%.1f%%", percent));
        } else {

            double tamGrid = 100.0;

            int tamGridInt = 100;
            double acc = 0;
            for (int i = -tamGridInt; i < tamGridInt; i++) {
                for (int j = -tamGridInt; j < tamGridInt; j++) {
                    double distC1, distC2;
                    double x1a,y1b,x2a,y2b;
                    x1a = x1 - (10*i/tamGrid);
                    y1b = y1 - (10*j/tamGrid);
                    x2a = x2 - (10*i/tamGrid);
                    y2b = y2 - (10*j/tamGrid);
                    distC1 = Math.sqrt(Math.pow(x1a,2)+ Math.pow(y1b,2));
                    distC2 = Math.sqrt(Math.pow(x2a,2)+ Math.pow(y2b,2));
                    if (distC1 < 6.0 && distC2 < 6.0) {
                        acc++;
                    }

                }
            }

            percent = acc/(2*tamGrid*2*tamGrid)*100;

            System.out.println(String.format("%.4f%%", percent));

        }
    }
}
