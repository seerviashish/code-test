
public class MyQueue {
    Node[] arr;
    int size;
    int currentSize = 0;

    MyQueue(int size) {
        this.arr = new Node[size];
        this.size = size;
    }

    void add(int index, Node value) {
        this.arr[index] = value;
    }

    void add(Node value) {
        this.arr[currentSize++] = value;
        heappifyUp(currentSize - 1);
    }

    Node pop() {
        Node result = arr[0];
        swap(0, currentSize - 1);
        currentSize--;
        heappify(0, currentSize);
        return result;
    }

    void heappify(int head, int size) {
        int min = head;
        int left = 2 * head + 1;
        int right = 2 * head + 2;

        if (left < size && this.arr[left].item < this.arr[min].item)
            min = left;
        if (right < size && this.arr[right].item < this.arr[min].item)
            min = right;

        if (min != head) {
            swap(min, head);
            heappify(min, size);
        }
    }

    void heappifyUp(int current) {
        if (current == 0) {
            return;
        }
        int p = (current - 1) / 2;

        if (arr[p].item > arr[current].item) {
            swap(p, current);
            heappifyUp(p);
        }
    }

    void swap(int p1, int p2) {
        Node temp = this.arr[p1];
        this.arr[p1] = this.arr[p2];
        this.arr[p2] = temp;
    }

    void print() {
        for (int i = 0; i < currentSize; i++) {
            System.out.print(arr[i].item + " ");
        }
        System.out.println();
    }

}