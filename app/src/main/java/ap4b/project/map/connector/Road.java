package ap4b.project;

public class Road extends Connector{
        private int endLevel; //Le niveau final d'une pole electrique
        private Gamestate game;
        private Habitation habitat;
    
        public Road(){
            super();
            this.level = 0;
            this.price = 5;
        }
        public Road(int Nid, int level){
            this.networkID = Nid;
            this.level = level;
            this.price = 5;
        }
        public void updateNetwork(int netID, int level){
            this.networkID = netID;
            this.level = level;
        }
        public boolean transportsResource(ResourceType reType, int endLevel){
            this.endLevel = endLevel;
            habitat.updateGeneration(game);
        }
        public int getPrice(){ return this.price;}
        public boolean getReached(){
            try {
                this.level += 1;
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }
        }

}
