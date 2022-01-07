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
      if (rawQuantity>0) {
          this.quantity += 100.0f;
          this.rawQuantity-=10;
        }
        else
            System.out.println("La resource premiere est epuise, pensez à changer de resource!! ");
    }
    
    public boolean hasRequiredResources(){return false;}

}
