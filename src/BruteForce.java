import java.util.ArrayList;
import java.util.Collections;

public class BruteForce {

    private static double minDistance = Double.MAX_VALUE;
    private static int[] minPath;

    public static ArrayList<Pakket> CalculatePath(ArrayList<Pakket> pakketjesOriginal) {
        ArrayList<Pakket> bestPakketjes = (ArrayList<Pakket>) pakketjesOriginal.clone();
        ArrayList<Pakket> currentPakketjes = (ArrayList<Pakket>) pakketjesOriginal.clone();
        boolean foundBetter = true;

        while (foundBetter) {
            foundBetter = false;
            for (int i = 0; i < currentPakketjes.size(); i++) {
                for (int j = i+1; j < currentPakketjes.size(); j++) {
                    // Swap two elements in the list
                    swap(currentPakketjes, i, j);
                    ArrayList<Pakket> newBestPakketjes = findShortest(bestPakketjes, currentPakketjes);
                    if (newBestPakketjes != bestPakketjes) {
                        // Found a better solution, update bestPakketjes
                        bestPakketjes = newBestPakketjes;
                        foundBetter = true;
                    }
                    // Swap the elements back to restore the original order
                    swap(currentPakketjes, i, j);
                }
            }
        }

        return bestPakketjes;
    }

    private static void swap(ArrayList<Pakket> list, int i, int j) {
        Pakket temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    private static ArrayList<Pakket> findShortest(ArrayList<Pakket> bestPakketjes, ArrayList<Pakket> currentPakketjes) {
        ArrayList<Pakket> shortestRoute = null;
        double shortestDistance = Double.MAX_VALUE;

        // Compute distance from (0,0) to first point in each route
        double bestDistance = distance(0, 0, bestPakketjes.get(0).getX(), bestPakketjes.get(0).getY());
        double currentDistance = distance(0, 0, currentPakketjes.get(0).getX(), currentPakketjes.get(0).getY());

        // Add up distances between consecutive points in each route
        for (int i = 1; i < bestPakketjes.size(); i++) {
            bestDistance += distance(bestPakketjes.get(i-1).getX(), bestPakketjes.get(i-1).getY(),
                    bestPakketjes.get(i).getX(), bestPakketjes.get(i).getY());
        }
        for (int i = 1; i < currentPakketjes.size(); i++) {
            currentDistance += distance(currentPakketjes.get(i-1).getX(), currentPakketjes.get(i-1).getY(),
                    currentPakketjes.get(i).getX(), currentPakketjes.get(i).getY());
        }

        // Determine shortest route
        if (bestDistance < currentDistance) {
            shortestRoute = bestPakketjes;
            shortestDistance = bestDistance;
        } else {
            shortestRoute = currentPakketjes;
            shortestDistance = currentDistance;
        }

        return shortestRoute;
    }

    private static double distance(int x1, int y1, int x2, int y2) {
        return Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));
    }


}
