import java.util.*;

public class RiskThresholdBinary {

    // 🔍 Linear Search - find exact threshold
    public static int linearSearch(int[] arr, int target) {
        int comparisons = 0;
        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                System.out.println("Linear Search → Comparisons: " + comparisons);
                return i;
            }
        }
        System.out.println("Linear Search → Comparisons: " + comparisons);
        return -1;
    }

    // 🔎 Binary Search - find exact or insertion index
    public static int binarySearchInsert(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        int comparisons = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            comparisons++;

            if (arr[mid] == target) {
                System.out.println("Binary Search → Comparisons: " + comparisons);
                return mid;
            } else if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println("Binary Search → Comparisons: " + comparisons);
        return low; // insertion point
    }

    // 🔽 Floor: largest ≤ target
    public static Integer floor(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        Integer result = null;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target) return arr[mid];
            else if (arr[mid] < target) {
                result = arr[mid];
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return result;
    }

    // 🔼 Ceiling: smallest ≥ target
    public static Integer ceiling(int[] arr, int target) {
        int low = 0, high = arr.length - 1;
        Integer result = null;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == target) return arr[mid];
            else if (arr[mid] > target) {
                result = arr[mid];
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return result;
    }

    // ▶️ Main method
    public static void main(String[] args) {
        int[] risks = {10, 25, 50, 100};
        int target = 30;

        // Linear Search
        int linearIndex = linearSearch(risks, target);
        System.out.println("Linear Index: " + linearIndex);

        // Binary Search
        int insertionIndex = binarySearchInsert(risks, target);
        System.out.println("Insertion Index: " + insertionIndex);

        // Floor & Ceiling
        Integer floorVal = floor(risks, target);
        Integer ceilVal = ceiling(risks, target);

        System.out.println("Floor(" + target + "): " + floorVal);
        System.out.println("Ceiling(" + target + "): " + ceilVal);
    }
}