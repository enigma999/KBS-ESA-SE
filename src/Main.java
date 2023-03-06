import java.util.ArrayList;

import static java.lang.Math.sqrt;

public class Main {
    public static void main(String[] args) {
        Magazijn testMagazijn = new Magazijn(5, 20, 20);


        final long startTimeBruteForce = System.currentTimeMillis();
        ArrayList<Pakket> pathBruteforce = BruteForce.CalculatePathLength(testMagazijn.getPakketjes());
        final long endTimeBruteForce = System.currentTimeMillis();

        final long startTimeBranchAndBound = System.currentTimeMillis();
        ArrayList<Pakket> pathBranchAndBound = BranchAndBound.CalculatePathLength(testMagazijn.getPakketjes());
        final long endTimeBranchAndBound = System.currentTimeMillis();

        final long startTimeNearestNeighbor = System.currentTimeMillis();
        ArrayList<Pakket> pathNearestNeighbor = NearestNeighbour.CalculatePathLength(testMagazijn.getPakketjes());
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


        System.out.println(testMagazijn.toString());

    }

    public static double CalculatePathLength(ArrayList<Pakket> pakketjes) {
        double lengthOfPath = 0;
        for (int i = 0; i < pakketjes.size() - 1; i++) {
            int startX = pakketjes.get(i).x;
            int startY = pakketjes.get(i).y;
            int endX = pakketjes.get(i + 1).x;
            int endY = pakketjes.get(i + 1).y;
            int deltaX = startX + endX;
            int deltaY = startY + endY;
            double deltaDirectline = sqrt(deltaY * deltaY + deltaX * deltaX);
            lengthOfPath = lengthOfPath+deltaDirectline;

        }
        return lengthOfPath;
    }
}