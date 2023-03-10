import java.util.ArrayList;

public class BruteForce {

    public static ArrayList<Pakket> CalculatePath(ArrayList<Pakket> pakketjesOriginal) {
        ArrayList<Pakket> bestPakketjes = new ArrayList<>(pakketjesOriginal);
        ArrayList<Pakket> currentPakketjes = new ArrayList<>(pakketjesOriginal);
        if(true){

            for (int i = 0; i < currentPakketjes.size(); i++) {
                for (int j = i + 1; j < currentPakketjes.size(); j++) {
                    // Swap two elements in the list
                    swap(currentPakketjes, i, j);
                    double bestLength = Main.CalculatePathLength(bestPakketjes);
                    double currentLength = Main.CalculatePathLength(currentPakketjes);
                    if (currentLength < bestLength) {
                        // Found a better solution, update bestPakketjes
                        bestPakketjes = new ArrayList<>(currentPakketjes);
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
}
