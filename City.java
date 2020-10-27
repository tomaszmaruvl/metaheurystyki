public class City {

        int index;
        int x;
        int y;
        int supplyDemand;
    
        public City(int index, int x, int y, int supplyDemand) {
            this.index = index;
            this.x = x;
            this.y = y;
            this.supplyDemand = supplyDemand;
        }
    
        public int getIndex() {
            return index;
        }
    
        public void setIndex(int index) {
            this.index = index;
        }
    
        public int getX() {
            return x;
        }
    
        public void setX(int x) {
            this.x = x;
        }
    
        public int getY() {
            return y;
        }
    
        public void setY(int y) {
            this.y = y;
        }
    
        public int getSupplyDemand() {
            return supplyDemand;
        }
    
        public void setSupplyDemand(int supplyDemand) {
            this.supplyDemand = supplyDemand;
        }
    
        public double calculateDistance(City otherCity){
            int x_diff = this.x - otherCity.x;
            int y_diff = this.y - otherCity.y;
            return Math.sqrt(x_diff * x_diff + y_diff * y_diff);
        }
    
}