package Game.Zombies;

public class Blood {
    private double x, y;
    long endTime;
     public Blood(double x, double y, long bloodTime) {
        this.x = x;
        this.y = y;
        this.endTime = System.currentTimeMillis() + bloodTime;
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
   public boolean isEnd() {return System.currentTimeMillis() > endTime;}

}
