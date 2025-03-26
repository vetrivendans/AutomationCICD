import java.util.*;

public class MatrixMaxSum {
    public static int flippingMatrix(List<List<Integer>> matrix) {
        int n = matrix.size() / 2; // Half the size of the matrix (2x2 quadrant for a 4x4 matrix)
        System.out.println("Matrix size: " + matrix.size() + ", Half size (n): " + n);

        int maxSum = 0; // This will store the total sum of the top-left quadrant

        // Iterate through each element in the top-left quadrant
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Access mirrored positions from all four quadrants
                int topLeft = matrix.get(i).get(j); // Top-left quadrant (Q1)
                int topRight = matrix.get(i).get(2 * n - j - 1); // Top-right quadrant (Q2)
                int bottomLeft = matrix.get(2 * n - i - 1).get(j); // Bottom-left quadrant (Q3)
                int bottomRight = matrix.get(2 * n - i - 1).get(2 * n - j - 1); // Bottom-right quadrant (Q4)

                // Print the mirrored values for debugging
                System.out.println("Position (" + i + "," + j + "):");
                System.out.println("  Top-left: " + topLeft);
                System.out.println("  Top-right: " + topRight);
                System.out.println("  Bottom-left: " + bottomLeft);
                System.out.println("  Bottom-right: " + bottomRight);

                // Find the maximum value among the four mirrored positions
                int maxValue = Math.max(Math.max(topLeft, topRight), Math.max(bottomLeft, bottomRight));
                System.out.println("  Maximum value chosen: " + maxValue);

                // Add the maximum value to the total sum
                maxSum += maxValue;
                System.out.println("  Updated maxSum: " + maxSum);
            }
        }

        return maxSum; // Return the total sum
    }

    public static void main(String[] args) {
        // Sample Input: 4x4 matrix
        List<List<Integer>> matrix = Arrays.asList(
            Arrays.asList(112, 42, 83, 119),
            Arrays.asList(566, 125, 56, 49),
            Arrays.asList(15, 78, 101, 43),
            Arrays.asList(62, 98, 114, 108)
        );

        // Print the original matrix
        System.out.println("Original Matrix:");
        for (List<Integer> row : matrix) {
            System.out.println(row);
        }

        // Calculate and print the maximum sum
        int result = flippingMatrix(matrix);
        System.out.println("Maximum Sum of Top-Left Quadrant: " + result);
    }
}