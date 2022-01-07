package ap4b.project;

public class ThoriumMine extends ResourceGenerator{
    public ThoriumMine(){
        this.quantity = 500.0f;
        this.rawQuantity = 50000000;
        this.speed = 8;
    }
    public ThoriumMine(float q){
        this.quantity = q;
        this.speed = 8;
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
