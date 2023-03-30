package athena.api;

import java.util.Arrays;

public class TestAScript {
    public static void main (String[] args) {
        int[] array = {4, 2, 1, 3, 5, 9, 6, 8, 7};

        for (int i = 0; i < array.length - 1; i++) {

            if (array[i] > array[i + 1]) {
                int temp = array[i];
                array[i] = array[i + 1];
                array[i + 1] = temp;
            }

            for (int j = i+1; j < array.length-1; j++) {

            }

        }
        System.out.println(Arrays.toString(array));

    }
}
