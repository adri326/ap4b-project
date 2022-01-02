package weather;
import groovy.console.ui.AstBrowser;

import javax.swing.*;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Weather {
    private int rainy = 0;
    private int timeOfDay = 0;
    private boolean israiny = false;
    private float  facteurtime = 0.0f;
    private float facteurrainfall = 0.0f;
    private int rainfall = 0;
    private boolean isday = true;
    public Weather(int timeOfDay, float facteurtime,float facteurrainfall){
        this.timeOfDay = timeOfDay;
        this.facteurtime = facteurtime ;
        this.facteurrainfall = facteurrainfall;
        System.out.println("Initialement, la durée totale de la journée avec le soleil est fixée à "+this.timeOfDay+"heures");
        System.out.println("Et le facteur qui influencent le temps de la pluie est "+this.facteurtime);
        System.out.println("Le facteur qui influence les précipitations est "+this.facteurrainfall);
    }
    public int getSunFactor() {
        // TODO
        this.timeOfDay -= this.rainy;
        if(this.rainy != 0) {
            System.out.println("En raison de la pluie, les heures claires de la journée deviennent maintenant " + this.timeOfDay + "heures");
        }
        else {
            System.out.println("Les heures de lumière de la journée sont "+this.timeOfDay+"heures");
        }
        return (int)Math.cos(this.timeOfDay);
    }

    public int getIrrigationFactor() {
        // TODO: σ(rainy) ?
        if (israiny) {
            this.rainfall = (int)(this.rainy*this.facteurrainfall);
            System.out.println("Les précipitations d'aujourd'hui ont été de "+this.rainfall+"mm");
            return this.rainfall;
        }
        else {
            throw new IllegalStateException();
        }
    }
    public void updateDay(){
        try {
            if (isday) {
                System.out.println("C'est le jour");
                TimeUnit.SECONDS.sleep(4);
                this.isday= false;
                System.out.println("C'est la nuit");
                TimeUnit.SECONDS.sleep(4);
                this.isday = true;
            }
        }catch(Exception e) {
            System.out.println(e);
        }
    }


    public void updateWeather(int dt) {
        this.timeOfDay = dt;
        System.out.println("Après ce changement, les heures de lumière du jour sont "+this.timeOfDay+"heures");
        updateDay();
        Random rd = new Random();
        israiny = rd.nextBoolean();
        if(israiny) {
            this.rainy += (int) (Math.random() * facteurtime);
            getIrrigationFactor();
            System.out.println("Le temps de pluie aujourd'hui est "+this.rainy+"heures");
        }
        else {
            this.rainy = 0;
        }
    }
    /*
    public static void main(String[] args){
        Weather weather = new Weather(8,4,200);
        weather.updateWeather(8);

    }*/
}
