package ap4b.project;

public class Weather {
    private float rainy = 0.0f;
    private float timeOfDay = 0.0f;

    public float getSunFactor() {
        // TODO
        return (float)Math.cos(this.timeOfDay);
    }

    public float getIrrigationFactor() {
        // TODO: σ(rainy) ?
        return rainy;
    }

    public void updateWeather(float dt) {
        // TODO
    }
}
