import java.io.File;

/**
 * @description:
 * @author: Lyq
 */

public class NBody {

    public static double readRadius(String url){
        In in = new In(url);
        in.readInt();
        return in.readDouble();
    }


    public static Planet[] readPlanets(String url){
        In in = new In(url);
        int N = in.readInt();
        double r = in.readDouble();
        Planet[] planets= new Planet[N];
        for(int i = 0;i < N;i++){
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            planets[i] = new Planet(xxPos,yyPos,xxVel,yyVel,mass,imgFileName);
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double r = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        StdDraw.setScale(-r,r);
        StdDraw.picture(0,0,"images/starfield.jpg");
        for(Planet planet: planets){
            planet.draw();
        }

        StdDraw.enableDoubleBuffering();
        int count = 0;
        for(;count < T;count += dt){
            double xForces[] = new double[planets.length];
            double yForces[] = new double[planets.length];
            for(int i = 0;i < planets.length;i++){
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for(int i = 0;i < planets.length;i++){
                planets[i].update(dt,xForces[i],yForces[i]);
            }
            StdDraw.setScale(-r,r);
            StdDraw.picture(0,0,"images/starfield.jpg");
            for(Planet planet: planets){
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", r);
        for (Planet planet : planets) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planet.xxPos, planet.yyPos, planet.xxVel,
                    planet.yyVel, planet.mass, planet.imgFileName);
        }

    }


}
