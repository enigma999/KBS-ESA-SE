import java.util.ArrayList;

import static java.lang.Math.sqrt;

public class Main {
    public static void main(String[] args) {

        Magazijn testMagazijn = new Magazijn(7
                , 10, 10);



        final long startTimeBruteForce = System.currentTimeMillis();
        ArrayList<Pakket> pathBruteforce = BruteForce.CalculatePath(testMagazijn.getPakketjes());
        final long endTimeBruteForce = System.currentTimeMillis();

        final long startTimeBranchAndBound = System.currentTimeMillis();
        ArrayList<Pakket> pathBranchAndBound = BranchAndBound.CalculatePath(testMagazijn.getPakketjes());
        final long endTimeBranchAndBound = System.currentTimeMillis();

        final long startTimeNearestNeighbor = System.currentTimeMillis();
        ArrayList<Pakket> pathNearestNeighbor = NearestNeighbour.CalculatePath(testMagazijn.getPakketjes());
        final long endTimeNearestNeighbor = System.currentTimeMillis();

        double pathLenghtBruteforce = CalculatePathLength(pathBruteforce);
        double pathLenghtBranchAndBound = CalculatePathLength(pathBranchAndBound);
        double pathLenghtNearestNeighbor = CalculatePathLength(pathNearestNeighbor);
        System.out.println("Bruteforce");
        System.out.println("Total execution time: " + (endTimeBruteForce - startTimeBruteForce));
        System.out.println("total length of the path " + pathLenghtBruteforce);
        System.out.println("BranchAndBound");
        System.out.println("Total execution time: " + (endTimeBranchAndBound - startTimeBranchAndBound));
        System.out.println("total length of the path " + pathLenghtBranchAndBound);
        System.out.println("NearestNeighbor");
        System.out.println("Total execution time: " + (endTimeNearestNeighbor - startTimeNearestNeighbor));
        System.out.println("total length of the path " + pathLenghtNearestNeighbor);
        System.out.println(pathNearestNeighbor.size());


//        System.out.println(testMagazijn.toString());

    }

    public static double CalculatePathLength(ArrayList<Pakket> pakketjes) {
        double lengthOfPath = 0;
//        int firstPakketX = pakketjes.get(1).getX();
//        int firstPakketY = pakketjes.get(1).getX();
//        lengthOfPath = sqrt(firstPakketX * firstPakketY);
        for (int i = 0; i < pakketjes.size() - 1; i++) {
            double DistanceBetween = FindDistance(pakketjes.get(i), pakketjes.get(i + 1));
            lengthOfPath = lengthOfPath + DistanceBetween;

        }
        return lengthOfPath;
    }

    public static double FindDistance(Pakket pakket1, Pakket pakket2) {
        int deltaX = pakket1.getX() - pakket2.getX();
        int deltaY = pakket1.getY() - pakket2.getY();
        double distanceBetween = sqrt(deltaY * deltaY + deltaX * deltaX);
        return distanceBetween;
    }
}