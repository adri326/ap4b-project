package ap4b.project;

import javax.swing.*;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Weather {
    /// The number of hours for which it was raining
    private float rainyHours = 0;
    /// The factor used for the number of hours for which it was raining in a day
    private float rainFactor = 8.0f;

    /// The speed of the wind of the current day
    private float windSpeed = 0.0f;
    /// The maximum speed of wind
    private float maxWindSpeed = 200.0f;

    /// The number of sunny hours in a day
    private final float hoursPerDay;

    /// The maximum value for irrigation
    private float minIrrigation = 0.5f;
    /// The minimum value for irrigation
    private float maxIrrigation = 1.0f;

    public Weather() {
        this.hoursPerDay = 12;
    }

    public Weather(float hoursPerDay, float rainFactor, float minIrrigation, float maxIrrigation, float maxWindSpeed) {
        this.hoursPerDay = hoursPerDay;
        this.rainFactor = rainFactor;
        this.minIrrigation = minIrrigation;
        this.maxIrrigation = maxIrrigation;
        this.maxWindSpeed = maxWindSpeed;

        System.out.println("Initialement, la durée totale de la journée avec le soleil est fixée à " + this.hoursPerDay + " heures");
        System.out.println("Et le facteur qui influencent le temps de la pluie est " + this.rainFactor);
        System.out.println("Le facteur qui influence les précipitations est entre " + this.minIrrigation + " et " + this.maxIrrigation);
    }

    // Leaving this here in case we need a different function for getSunFactor()
    private float getSunTime() {
        return 2.0f * (float)Math.acos(0.3f / map(sigmoid(this.rainyHours), 0.5f, 1.0f, 1.0f, 0.5f));
    }

    public float getSunFactor() {
        float res = this.hoursPerDay - this.rainyHours;

        if(this.rainyHours != 0) {
            System.out.println("En raison de la pluie, les heures claires de la journée deviennent maintenant " + getSunTime() + " heures");
        } else {
            System.out.println("Les heures de lumière de la journée sont " + res + " heures");
        }

        if (res < 0.0f) res = 0.0f;

        return res;
    }

    public float getIrrigationFactor() {
        float res = map(sigmoid(this.rainyHours / 2.0f), 0.5f, 1.0f, this.minIrrigation, this.maxIrrigation);
        System.out.println("Les précipitations d'aujourd'hui ont été de " + res + "mm (∈ [" + this.minIrrigation + "; " + this.maxIrrigation + "])");
        return res;
    }

    public float getWindFactor() {
        return this.windSpeed;
    }

    public void updateWeather() {
        // this.hoursPerDay = dt;
        // System.out.println("Après ce changement, les heures de lumière du jour sont " + this.hoursPerDay + " heures");
        Random rd = new Random();

        if (rd.nextBoolean()) { // if it rains today
            this.rainyHours = rd.nextFloat() * rainFactor;
            System.out.println("Le temps de pluie aujourd'hui est " + this.rainyHours + " heures");
        } else {
            this.rainyHours = 0;
        }

        if (rd.nextBoolean()) { // if it is windy today
            this.windSpeed = rd.nextFloat() * windMaxSpeed;
        } else {
            this.windSpeed = 0.0f;
        }
    }
    /*
    public static void main(String[] args){
        Weather weather = new Weather(8,4,200);
        weather.updateWeather(8);

    }*/

    private float sigmoid(float x) {
        // tan¯¹(x*π/2)/π+.5
        return (float)Math.atan(x * Math.PI / 2.0f) / (float)Math.PI + 0.5f;
    }

    private float map(float x, float from_min, float from_max, float to_min, float to_max) {
        return map((x - from_min) / (from_max - from_min), to_min, to_max);
    }

    private float map(float x, float to_min, float to_max) {
        return x * (to_max - to_min) + to_min;
    }

    public String toString() {
        StringBuilder res = new StringBuilder(this.rainyHours > 0.0f ? "Rainy" : "Sunny");
        res.append(" (");
        res.append(this.getSunFactor());
        res.append("h of sun, irrigation: ");
        res.append(this.getIrrigationFactor() * 100.0f);
        res.append("%, wind: ");
        res.append(this.getWindFactor() * 100.0f);
        res.append("%)");
        return res.toString();
    }
}
