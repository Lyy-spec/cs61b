/**
 * @description:
 * @author: lyq
 */

public class Planet {
    /**
     * 当前x位置
     */
    public double xxPos;
    /**
     * 当前y位置
     */
    public double yyPos;
    /**
     * x方向当前速度
     */
    public double xxVel;
    /**
     * y方向当前速度
     */
    public double yyVel;
    /**
     * 质量
     */
    public double mass;

    public String imgFileName;


    public Planet(double xxPos,double yyPos,double xxVel,double yyVel,double mass,String imgFileName){
        this.xxPos = xxPos;
        this.yyPos = yyPos;
        this.xxVel = xxVel;
        this.yyVel = yyVel;
        this.mass = mass;
        this.imgFileName = imgFileName;
    }

    public Planet(Planet p){
        this.xxPos = p.xxPos;
        this.yyPos = p.yyPos;
        this.xxVel = p.xxVel;
        this.yyVel = p.yyVel;
        this.mass = p.mass;
        this.imgFileName = p.imgFileName;
    }


    public double calcDistance(Planet p){
        double dx = this.xxPos - p.xxPos;
        double dy = this.yyPos - p.yyPos;
        return Math.sqrt(Math.pow(dx,2) + Math.pow(dy,2));
    }


    public double calcForceExertedBy(Planet p){
        double r = calcDistance(p);
        return (6.67e-11 * mass * p.mass) / Math.pow(r,2);
    }


    public double calcForceExertedByX(Planet p){
        double r = calcDistance(p);
        double dx = p.xxPos - xxPos;
        return calcForceExertedBy(p) * dx / r;
    }


    public double calcForceExertedByY(Planet p){
        double r = calcDistance(p);
        double dy = p.yyPos - yyPos;
        return calcForceExertedBy(p) * dy / r;
    }


    public double calcNetForceExertedByX(Planet[] planets){
        double fX = 0;
        for(Planet p: planets){
            if(!this.equals(p)){
                fX += calcForceExertedByX(p);
            }
        }
        return fX;
    }


    public double calcNetForceExertedByY(Planet[] planets){
        double fY = 0;
        for(Planet p: planets){
            if(!this.equals(p)){
                fY += calcForceExertedByY(p);
            }
        }
        return fY;
    }

    public void update(double dt,double fX,double fY){
        double aX = fX / mass;
        double aY = fY / mass;
        xxVel += dt * aX;
        yyVel += dt * aY;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    public void draw(){
        StdDraw.picture(xxPos,yyPos,"images/" + imgFileName);
    }
}