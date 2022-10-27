package hw2_14.com.example.hw2_14demo.stringlist;

import hw2_14.com.example.hw2_14demo.stringlist.exceptions.NotFoundException;
import hw2_14.com.example.hw2_14demo.stringlist.exceptions.NullParameterException;
import hw2_14.com.example.hw2_14demo.stringlist.exceptions.OutOfArrayException;
import hw2_14.com.example.hw2_14demo.stringlist.exceptions.OverflowArrayException;

import java.util.Arrays;

public class StringListImpl implements StringList{
    private int maxSize; // емкость массива максимальная
    private int countElements=0; // текущая длина массива

    /*public String[] getArray() {
        String[] newArray = new String[countElements];
        newArray = Arrays.copyOf(array, countElements);
        return newArray;
    }*/

    private String[] array;
    private final int DEFAULT_SIZE = 100; // максимальный размер массива по умолчанию

    public StringListImpl() {
        this.maxSize = DEFAULT_SIZE;
        generateArray(maxSize);
    }

    public StringListImpl(int maxSize) {
        this.maxSize = maxSize;
        generateArray(maxSize);
    }

    private void generateArray(int size) {
        array = new String[size];
    }

    private boolean testOverflowArray() {
        if (countElements == maxSize) {
            return true;
        } else {
            return false;
        }
    }

    // расширение размера массива с переносом элементов
    private void expandArraySize() {
        int oldMaxSize = maxSize;
        maxSize *= 2;
        String [] newArray = new String[maxSize];
        newArray = Arrays.copyOf(array, oldMaxSize);
        this.array = new String[maxSize];
        this.array = newArray;
    }

    public void printArray() {
        for (int i = 0; i < countElements; i++) {
            System.out.println("array["+i+"] = " + array[i]);
        }
    }

    // Сдвиг элементов на step, начиная с index1 до (не включая) index2
    public void shiftArrayElements(int index1, int index2, int step) {
        if (step < 0) {
            for (int i = index1; i < index2; i++) {
                array[i+step] = array[i];
            }
        }else {
            for (int i = index2 - 1; i >= index1; i--) {
                array[i+step] = array[i];
            }
        }
    }

    public int getCountElements() {
        return countElements;
    }

    private void testOutOfArrayWithException(int index) {
        if (index >= countElements || index < 0) {
            throw new OutOfArrayException("Выход индекса " + index + " за пределы массива");
        }
    }

    @Override
    public String add(String item) {
        if (item == null) {
            throw new NullParameterException("Параметр не должен быть равен null");
        }
        if (testOverflowArray()) {
            throw new OverflowArrayException("Переполнение при попытке добавить элемент");
        }
        array[countElements] = item;
        countElements++;
        return item;
    }

    @Override
    public String add(int index, String item) {
        if (testOverflowArray()) {
            throw new OverflowArrayException("Переполнение при попытке вставить элемент по индексу "+index);
        }
        countElements++;
        for (int i = countElements-1; i > index; i--) {
            array[i] = array[i-1];
        }
        array[index] = item;
        return item;
    }

    @Override
    public String set(int index, String item) {
        if (index > countElements-1) {
            throw new OverflowArrayException("Выход за пределы массива");
        }
        array[index] = item;
        return item;
    }

    @Override
    public String remove(String item) {
        int index = indexOf(item);
        if (index == -1) {
            throw new NotFoundException("Строка <<" + item + ">> не найдена");
        } else {
            shiftArrayElements(index+1, countElements, -1);
            countElements--;
            return item;
        }
    }

    @Override
    public String remove(int index) {
        testOutOfArrayWithException(index);

        shiftArrayElements(index+1, countElements, -1);
        countElements--;
        return array[index];
    }

    @Override
    public boolean contains(String item) {
        if (indexOf(item) == -1) {
            return false;
        }else {
            return true;
        }
    }

    @Override
    public int indexOf(String item) {
        int index = -1;
        for (int i=0; i < countElements; i++) {
            if (array[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index; // -1, если не найдена строка item, или index найденного элемента
    }

    @Override
    public int lastIndexOf(String item) {
        int index = -1;
        for (int i=countElements-1; i > 0; i--) {
            if (array[i].equals(item)) {
                index = i;
                break;
            }
        }
        return index;
    }

    @Override
    public String get(int index) {
        testOutOfArrayWithException(index);
        return array[index];
    }

    @Override
    public boolean equals(StringList otherList) {
        if (otherList == null) {
            throw new NullParameterException("Передан null");
        }
        if (countElements != otherList.size()) {
            return false;
        }
        return Arrays.equals(array, 0, countElements, otherList.toArray(), 0, countElements);
    }

    @Override
    public int size() {
        return countElements;
    }

    @Override
    public boolean isEmpty() {
        if (countElements <= 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void clear() {
        countElements = 0;
    }

    @Override
    public String[] toArray() {
        return Arrays.copyOf(array, countElements);
    }
}
