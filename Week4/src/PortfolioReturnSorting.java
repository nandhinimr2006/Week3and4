import java.util.*;

public class PortfolioReturnSorting {

    // Asset class
    static class Asset {
        String name;
        double returnRate;
        double volatility;

        public Asset(String name, double returnRate, double volatility) {
            this.name = name;
            this.returnRate = returnRate;
            this.volatility = volatility;
        }

        @Override
        public String toString() {
            return name + ":" + returnRate + "% (vol=" + volatility + ")";
        }
    }

    // 🔵 Merge Sort (Ascending, Stable)
    public static void mergeSort(Asset[] arr, int left, int right) {
        if (left >= right) return;

        int mid = (left + right) / 2;
        mergeSort(arr, left, mid);
        mergeSort(arr, mid + 1, right);
        merge(arr, left, mid, right);
    }

    private static void merge(Asset[] arr, int left, int mid, int right) {
        Asset[] temp = new Asset[right - left + 1];

        int i = left, j = mid + 1, k = 0;

        while (i <= mid && j <= right) {
            // Stable: <= keeps original order
            if (arr[i].returnRate <= arr[j].returnRate) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        while (i <= mid) temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (int x = 0; x < temp.length; x++) {
            arr[left + x] = temp[x];
        }
    }

    // 🟢 Quick Sort (Hybrid: Quick + Insertion)
    public static void quickSort(Asset[] arr, int low, int high) {
        if (high - low < 10) {
            insertionSort(arr, low, high);
            return;
        }

        if (low < high) {
            int pivotIndex = medianOfThree(arr, low, high);
            swap(arr, pivotIndex, high);

            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    // Comparator: returnRate DESC, volatility ASC
    private static int compare(Asset a, Asset b) {
        if (a.returnRate != b.returnRate) {
            return Double.compare(a.returnRate, b.returnRate);
        }
        return -Double.compare(a.volatility, b.volatility);
    }

    // Partition (Lomuto)
    private static int partition(Asset[] arr, int low, int high) {
        Asset pivot = arr[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (compare(arr[j], pivot) > 0) { // DESC logic
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, i + 1, high);
        return i + 1;
    }

    // 🔀 Insertion Sort (for small partitions)
    private static void insertionSort(Asset[] arr, int low, int high) {
        for (int i = low + 1; i <= high; i++) {
            Asset key = arr[i];
            int j = i - 1;

            while (j >= low && compare(arr[j], key) < 0) {
                arr[j + 1] = arr[j];
                j--;
            }
            arr[j + 1] = key;
        }
    }

    // 🎯 Pivot Selection: Median of Three
    private static int medianOfThree(Asset[] arr, int low, int high) {
        int mid = (low + high) / 2;

        Asset a = arr[low];
        Asset b = arr[mid];
        Asset c = arr[high];

        if (compare(a, b) > 0) {
            if (compare(b, c) > 0) return mid;
            else if (compare(a, c) > 0) return high;
            else return low;
        } else {
            if (compare(a, c) > 0) return low;
            else if (compare(b, c) > 0) return high;
            else return mid;
        }
    }

    // 🎲 Random Pivot (optional alternative)
    private static int randomPivot(int low, int high) {
        return low + new Random().nextInt(high - low + 1);
    }

    private static void swap(Asset[] arr, int i, int j) {
        Asset temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // ▶️ Main method
    public static void main(String[] args) {

        Asset[] assets = {
                new Asset("AAPL", 12, 5),
                new Asset("TSLA", 8, 9),
                new Asset("GOOG", 15, 4)
        };

        // Merge Sort (Ascending)
        mergeSort(assets, 0, assets.length - 1);
        System.out.println("Merge Sort (Asc): " + Arrays.toString(assets));

        // Quick Sort (Descending + Volatility)
        quickSort(assets, 0, assets.length - 1);
        System.out.println("Quick Sort (Desc): " + Arrays.toString(assets));
    }
}