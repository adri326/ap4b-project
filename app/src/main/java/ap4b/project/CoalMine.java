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
        while (rawQuantity>0) {

            try {
                Thread.sleep(speed * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.quantity += 20.0f;
            --this.rawQuantity;
        }
        if(rawQuantity<=0){
            System.out.println("La ressource premiere est epuise, pensez à changer de ressource!! ");
        }
    }
    public boolean hasRequiredResources(){return false;}
}
