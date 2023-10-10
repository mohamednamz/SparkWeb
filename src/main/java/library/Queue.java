package library;

public class Queue<E> {
    E[] arr = (E[]) new Object[1];
    public int size = 0;
    E headOfQueue = null;

    public E[] insertInQueue(E element) {

        if (size >= arr.length) {
            grow();
        }

        arr[size] = element;

        if (size == 0) {
            headOfQueue = element;
        }
        size++;

        return arr;
    }

    private void grow() {

        E[] newArr = (E[]) new Object[arr.length * 2];

        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    public boolean remove(int index) {
        if (index > arr.length) {
            return false;
        }
        arr[index] = null;
        shiftQueue(index);
        return true;
    }

    private void shiftQueue(int index) {

        E[] newArray = (E[]) new Object[arr.length - 1];

        for (int i = 0; i < newArray.length; i++) {
            if (i < index) {
                newArray[i] = arr[i];
            }
            if (i == index || i > index) {
                newArray[i] = arr[i + 1];
            }
        }
        size--;
        arr = newArray;
    }

    public E dequeue() {

        if (headOfQueue == null) {
            return null;
        }

        for (int i = 0; i < arr.length; i++) {

            if (i == 0) {
                headOfQueue = arr[0];
                arr[0] = null;
            }
            if (i == arr.length - 1) {
                arr[i] = null;
            } else {
                arr[i] = arr[i + 1];
            }
        }
        //TODO unsure whether this breaks the code, sinc size of the queue doesnt actually decrease
        size--;
        return headOfQueue;
    }

    public E removeFromQueue(E e) {
        int x = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == e) {
                arr[i] = null;
                x = i;
                break;
            }
        }
        for (int i = x; i < arr.length; i++)
            if (i != arr.length - 1) {
                arr[i] = arr[i + 1];
            } else {
                arr[i] = null;
            }
        return headOfQueue;
    }

    public E peak() {

        return arr[0];
    }

    public E checkQueue(int i) {

        return arr[i];

    }




}

