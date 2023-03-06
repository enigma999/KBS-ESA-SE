import javax.print.attribute.standard.PrinterMakeAndModel;
import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class magazijn {
    ArrayList<pakket> pakketjes = new ArrayList<pakket>();
    int MaxX;
    int MaxY;

    public magazijn(int aantalPakketjes, int maxX, int maxY) {

        for(int i=0;i<aantalPakketjes;i++) {
            pakket pakket=new pakket();
            pakket.setNumber(i);
            int X = ThreadLocalRandom.current().nextInt(0, maxX + 1);
            int Y = ThreadLocalRandom.current().nextInt(0, maxY + 1);
            pakket.setX(X);
            pakket.setY(Y);
            pakketjes.add(pakket);
        }
        MaxX = maxX;
        MaxY = maxY;
    }


    @Override
    public String toString() {
        return "magazijn{" +
                "pakketjes=" + pakketjes +
                ", MaxX=" + MaxX +
                ", MaxY=" + MaxY +
                '}';
    }
}
