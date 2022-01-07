package ap4b.project;

public class UraniumMine extends ResourceGenerator{
    public UraniumMine(){
        this.quantity = 1000.0f;
        this.rawQuantity = 10000000;
        this.speed = 6;
    }
    public UraniumMine(float q){
        this.quantity = q;
        this.speed = 6;
    }
    public void generateResource() {
        if (rawQuantity>0) {
            this.quantity += 200.0f;
            --this.rawQuantity;
        }
        else
            System.out.println("La ressource premiere est epuise, pensez à changer de ressource!! ");
    }
    
    public boolean hasRequiredResources(){return false;}
}
