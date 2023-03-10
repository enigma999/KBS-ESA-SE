import java.util.ArrayList;

import static java.lang.Math.sqrt;

public class Main {
    public static void main(String[] args) {
        int totalWinsBaB = 0;
        int totalWinsNN = 0;
        int totalWinsBF = 0;


        for (int run = 0; run < 100; run++) {

            Magazijn testMagazijn = new Magazijn(8
                    , 10, 10);
//            System.out.println(CalculatePathLength(testMagazijn.getPakketjes()));


            final long startTimeBruteForce = System.currentTimeMillis();
            ArrayList<Pakket> pathBruteforce = BruteForce.CalculatePath(testMagazijn.getPakketjes());
            final long endTimeBruteForce = System.currentTimeMillis();
            double pathLengthBruteforce = CalculatePathLength(pathBruteforce);
//            System.out.println("Bruteforce");
//            System.out.println("Total execution time: " + (endTimeBruteForce - startTimeBruteForce));
//            System.out.println("total length of the path " + pathLengthBruteforce);

            final long startTimeBranchAndBound = System.currentTimeMillis();
            ArrayList<Pakket> pathBranchAndBound = BranchAndBound.CalculatePath(testMagazijn.getPakketjes());
            final long endTimeBranchAndBound = System.currentTimeMillis();
            double pathLengthBranchAndBound = CalculatePathLength(pathBranchAndBound);
//            System.out.println("BranchAndBound");
//            System.out.println("Total execution time: " + (endTimeBranchAndBound - startTimeBranchAndBound));
//            System.out.println("total length of the path " + pathLengthBranchAndBound);

            final long startTimeNearestNeighbor = System.currentTimeMillis();
            ArrayList<Pakket> pathNearestNeighbor = NearestNeighbour.CalculatePath(testMagazijn.getPakketjes());
            final long endTimeNearestNeighbor = System.currentTimeMillis();
            double pathLengthNearestNeighbor = CalculatePathLength(pathNearestNeighbor);
//            System.out.println("NearestNeighbor");
//            System.out.println("Total execution time: " + (endTimeNearestNeighbor - startTimeNearestNeighbor));
//            System.out.println("total length of the path " + pathLengthNearestNeighbor);
            String shortestAlg = "";

            if (pathLengthBruteforce <= pathLengthBranchAndBound && pathLengthBruteforce <= pathLengthNearestNeighbor) {
                shortestAlg = "BF";
                totalWinsBF++;
            } else if (pathLengthBranchAndBound <= pathLengthNearestNeighbor) {
                shortestAlg = "BAB";
                totalWinsBaB++;
            } else {
                shortestAlg = "NN";
                totalWinsNN++;
            }
            System.out.println("Length BF: " + pathLengthBruteforce + "BnB: " + pathLengthBranchAndBound + "NN: " + pathLengthNearestNeighbor + "shortest: " + shortestAlg);
            System.out.println("size BF: " + pathBruteforce.size() + "BnB: " + pathBranchAndBound.size() + "NN: " + pathNearestNeighbor.size() + "shortest: " + shortestAlg);
            System.out.println(pathBruteforce.get(0).getX());
            System.out.println(pathBruteforce.get(0).getY());
            System.out.println(pathBruteforce.get(2).getX());
            System.out.println(pathBruteforce.get(2).getY());


//            System.out.println(pathNearestNeighbor.size());
        }
        System.out.println("BAB" + totalWinsBaB + "NN" + totalWinsNN);


//        System.out.println(testMagazijn.toString());

    }

    public static double CalculatePathLength(ArrayList<Pakket> pakketjes) {
        double lengthOfPath = 0;
        int firstPakketX = pakketjes.get(0).getX();
        int firstPakketY = pakketjes.get(0).getY();
        lengthOfPath = sqrt(firstPakketX * firstPakketX + firstPakketY * firstPakketY);
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