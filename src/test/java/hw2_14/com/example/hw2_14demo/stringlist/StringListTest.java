package hw2_14.com.example.hw2_14demo.stringlist;

import hw2_14.com.example.hw2_14demo.stringlist.exceptions.NullParameterException;
import hw2_14.com.example.hw2_14demo.stringlist.exceptions.OutOfArrayException;
import hw2_14.com.example.hw2_14demo.stringlist.exceptions.OverflowArrayException;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class StringListTest {

    private StringList stringList = new StringListImpl(5);

    @BeforeEach
    private void generateStartCondition() {
        stringList = new StringListImpl(5);
        stringList.add("1");
        stringList.add("2");
        stringList.add("3");
        stringList.add("4");
    }

    @Test
    void add() {
        assertThrows(NullParameterException.class, ()-> stringList.add(null));
        assertEquals("5", stringList.add("5"));
        assertThrows(OverflowArrayException.class, ()->stringList.add("6"));
    }

    @Test
    void testAdd() {
    }

    @Test
    void set() {
    }

    @Test
    void remove() {
        stringList.add("5");
        stringList.remove("2");

        StringList stringList1 = new StringListImpl(4);
        stringList1.add("1");
        stringList1.add("3");
        stringList1.add("4");
        stringList1.add("5");

        assertThat(Arrays.equals(stringList.toArray(), 0, stringList.size(), stringList1.toArray(), 0, stringList1.size())).isTrue();

    }

    @Test
    void testRemove() {
    }

    @Test
    void contains() {
        assertThat(stringList.contains("1")).isTrue();
        assertThat(stringList.contains("10")).isFalse();
    }

    @Test
    void indexOf() {
        assertThat(stringList.indexOf("2")).isEqualTo(1);
        assertThat(stringList.indexOf("1")).isEqualTo(0);
        assertThat(stringList.indexOf("1A")).isEqualTo(-1);
    }

    @Test
    void lastIndexOf() {
        assertThat(stringList.lastIndexOf("2")).isEqualTo(1);
        stringList.add("1");
        assertThat(stringList.lastIndexOf("1")).isEqualTo(4);
        assertThat(stringList.lastIndexOf("10")).isEqualTo(-1);
    }

    @Test
    void get() {
        assertThat(stringList.get(0)).isEqualTo("1");
        assertThat(stringList.get(3)).isEqualTo("4");
        assertThrows(OutOfArrayException.class, ()->stringList.get(10));
    }

    @Test
    void testEquals() {
        StringList stringList1 = new StringListImpl(4);
        stringList1.add("1");
        stringList1.add("2");
        stringList1.add("3");
        stringList1.add("4");

        assertThat(stringList.equals(stringList1)).isTrue();
    }

    @Test
    void size() {
        assertThat(stringList.size()).isEqualTo(4);
    }

    @Test
    void isEmpty() {
        assertThat(stringList.isEmpty()).isFalse();
    }

    @Test
    void clear() {
        stringList.clear();
        assertThat(stringList.size()).isEqualTo(0);
    }


    //Проверен в remove опосредованно
    /*@Test
    void toArray() {

    }*/
}