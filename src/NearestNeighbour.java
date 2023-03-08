import java.util.ArrayList;

public class NearestNeighbour {
    private static int startX = 0;
    private static int startY = 0;
    public static ArrayList<Pakket> CalculatePath(ArrayList<Pakket> pakketjesOriginal) {
        ArrayList<Pakket> pakketjes = (ArrayList<Pakket>) pakketjesOriginal.clone();
        ArrayList<Pakket> path = new ArrayList<>();
        int length = pakketjes.size();
        for (int i = 0; i < length; i++) {
            Pakket pakket = getClosest(pakketjes);
            path.add(pakket);
            pakketjes.remove(pakket);
            startX = pakket.getX();
            startY = pakket.getY();
        }

        return path;
    }

    private static Pakket getClosest(ArrayList<Pakket> pakketjes) {
        double afstand = 1000000000;
        Pakket closestPacket = pakketjes.get(0);

        for (int i = 0; i < pakketjes.size(); i++) {

            int x = pakketjes.get(i).getX();
            int y = pakketjes.get(i).getY();

            if (getAfstand(x,y) < afstand) {
                closestPacket = pakketjes.get(i);
                afstand = getAfstand(x,y);
            }
        }

        return closestPacket;
    }

    private static double getAfstand(int x, int y) {
        int distanceX = Math.abs(x-startX);
        int distanceY = Math.abs(y-startY);
        double afstand = Math.sqrt(Math.pow(distanceX,2) + Math.pow(distanceY,2));
        return afstand;
    }

    public static void main(String[] args) {
        Magazijn magazijn = new Magazijn(5,5,5);
        System.out.println(magazijn.getPakketjes());

        for (int i = 0; i < magazijn.getPakketjes().size(); i++) {
            System.out.println("Nummer: " + magazijn.getPakketjes().get(i).getNumber());
            System.out.println(magazijn.getPakketjes().get(i).getX());
            System.out.println(magazijn.getPakketjes().get(i).getY());
            System.out.println(getAfstand(magazijn.getPakketjes().get(i).getX(), magazijn.getPakketjes().get(i).getY()) + "\n");

        }
        long startTime = System.nanoTime();
        ArrayList<Pakket> pakketjes = CalculatePath(magazijn.getPakketjes());
        long endTime = System.nanoTime();
        long duration = (endTime-startTime);
        System.out.println("Time: " + duration + " nanoseconden");

        for (int i = 0; i < pakketjes.size(); i++) {
            System.out.println(pakketjes.get(i).getNumber());
        }

    }
}

