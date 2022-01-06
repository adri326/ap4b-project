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
        while (rawQuantity>0) {

            try {
                Thread.sleep(speed * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.quantity += 200.0f;
            --this.rawQuantity;
        }
        if(rawQuantity<=0){
            System.out.println("La ressource premiere est epuise, pensez à changer de ressource!! ");
        }
    }
    public boolean hasRequiredResources(){return false;}
}
