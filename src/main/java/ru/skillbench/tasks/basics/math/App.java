package ru.skillbench.tasks.basics.math;
import java.util.Arrays;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )    {
        System.out.println( "Hello World!" );
        ArrayVectorImpl aVect = new ArrayVectorImpl();
        double[] arr1 = {8, 8, 33, -0.5, 99};
        double[] arr2 = {1, 1, 4, 1, 6, 13};
        ArrayVectorImpl vector1 = new ArrayVectorImpl(arr2);
        ArrayVectorImpl vector2 = new ArrayVectorImpl(arr1);
        double[] ee;
        //  vector1.set(arr2);
        //   System.out.println(vector1.getSize());
        /*
        vector1.set(10, 4444);
        ee = vector1.get();

        for (int i=0; i<11; i++) {
            System.out.println(ee[i]);
        }
        */
        vector2.sortAscending();
        ee = vector2.get();
        for (int i=0; i<5; i++) {
            System.out.println(ee[i]);
        }
    }
}





