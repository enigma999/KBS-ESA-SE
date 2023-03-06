public class Main {
    public static void main(String[] args) {
        magazijn testMagazijn = new magazijn(5,20,20);
        final long startTimeBruteForce = System.currentTimeMillis();
        // your algorithm here
        final long endTimeBruteForce = System.currentTimeMillis();
        final long startTimeBranchAndBound = System.currentTimeMillis();
        // your algorithm here
        final long endTimeBranchAndBound = System.currentTimeMillis();
        final long startTimeNearestNeighbor = System.currentTimeMillis();
        // your algorithm here
        final long endTimeNearestNeighbor = System.currentTimeMillis();

        System.out.println("Total execution time: " + (endTimeBruteForce - startTimeBruteForce));
        System.out.println("Total execution time: " + (endTimeBranchAndBound - startTimeBranchAndBound));
        System.out.println("Total execution time: " + (endTimeNearestNeighbor - startTimeNearestNeighbor));

        System.out.println(testMagazijn.toString());

    }
}