package ap4b.project;

public class WoodFactory extends ResourceGenerator{
    public WoodFactory(){
        this.quantity = 500.0f;
        this.speed = 5;
    }
    public WoodFactory(float q){
        this.quantity = q;
        this.speed = 5;
    }
    public void generateResource() {
        while (rawQuantity>0) {

            try {
                Thread.sleep(speed * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.quantity += 100.0f;
            this.rawQuantity-=10;
        }
        if(rawQuantity<=0){
            System.out.println("La ressource premiere est epuise, pensez à changer de ressource!! ");
        }
    }
    public boolean hasRequiredResources(){return false;}

}
