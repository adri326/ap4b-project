package ap4b.project;

public class Upgrade {
    private int price;
    private String technology;
    private String name;

    public Upgrade(){
        this.price = 0;
        this.technology = "None";
        this.name = " ";
    }
    public Upgrade(int p, String tech, String n ){
        this.price = p;
        this.technology = tech;
        this.name = n;
    }

    public int getPrice() { return this.price;}
    public String getTechnology() { return this.technology;}
    public String getName() { return this.name;}

    public void setPrice(int p){ this.price = p;}
    public void setTechnology(String t){ this.technology = t ;}
    public void setName (String n){ this.name = n;}
}
