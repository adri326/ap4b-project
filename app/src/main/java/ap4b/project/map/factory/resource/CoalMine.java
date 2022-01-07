package ap4b.project;

public class CoalMine extends ResourceGenerator{

    public CoalMine(){
        this.quantity = 1000.0f;
        this.rawQuantity = 10000000;
        this.speed = 6;
    }
    public CoalMine(float q){
        this.quantity = q;
        this.speed = 6;
    }
    public void generateResource() {
        if(rawQuantity>0) {
            this.quantity += 20.0f;
            --this.rawQuantity;
        }
        else
            System.out.println("La ressource premiere est epuise, pensez à changer de ressource!! ");
    
        public boolean hasRequiredResources(){return false;}
}
