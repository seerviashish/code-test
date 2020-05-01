#include <bits/stdc++.h>

using namespace std;

void printArray(int arr[], int n) {
    for (int i = 0; i < n; i++) cout << arr[i] << " ";
    cout << "\n";
}

void heapify(int arr[], int n, int i) {
    int largest = i;
    int l = 2 * i + 1;
    int r = 2 * i + 2;

    cout << "ROOT ==> " << i << endl;
    cout << "LEFT ==> " << l << endl;
    cout << "RIGHT ==> " << r << endl;

    if (l < n && arr[l] > arr[largest]) largest = l;
    if (r < n && arr[r] > arr[largest]) largest = r;
    cout << "Largest update ==> " << largest << endl;

    if (largest != i) {
        swap(arr[i], arr[largest]);
        cout << "arr ==> " << endl;
        printArray(arr, 6);
        heapify(arr, n, largest);
    }
}

void heapSort(int arr[], int n) {
    for (int i = n / 2 - 1; i >= 0; i--) {
        heapify(arr, n, i);
    }

    for (int i = n - 1; i > 0; i--) {
        swap(arr[0], arr[i]);
        heapify(arr, i, 0);
    }
}

int main() {
    int arr[] = {12, 11, 13, 5, 6, 7};
    int n = sizeof(arr) / sizeof(arr[0]);
    heapSort(arr, n);
    cout << "Sorted Array is \n";
    printArray(arr, n);
}