package com.seweryn.schess;

/**
 * Created by sew on 30/01/2016.
 */
public class QuickSort {
    private static int merge(Integer arryToSort[], int left, int right){
        int i = left, j = right;
        int tmp;
        int pivot = arryToSort[(left + right) / 2];

        while (i <= j) {
            while (arryToSort[i] < pivot)
                i++;
            while (arryToSort[j] > pivot)
                j--;
            if (i <= j) {
                tmp = arryToSort[i];
                arryToSort[i] = arryToSort[j];
                arryToSort[j] = tmp;
                i++;
                j--;
            }
        };

        return i;
    }
    public static void quickSort(Integer arryToSort[], int left, int right) {
        int index = merge(arryToSort, left, right);
        if (left < index - 1)
            quickSort(arryToSort, left, index - 1);
        if (index < right)
            quickSort(arryToSort, index, right);
    }
}
