import java.util.Hashtable;
import java.lang.Math;

public class HashTables {
    public static void findSumPair(int target, int[] array){
        Hashtable<Integer, Boolean> table = new Hashtable<Integer, Boolean>();//construct a hashtable
        for (int i = 0; i < array.length; ++i){//iterate through every element of the array
            //check if the key with number target-array[i] exists in the table
            if (table.containsKey(target - array[i])){//if such key exists, then array[i] and this key is a valid pair
                System.out.println("Pair found: " + array[i] + ", " + (target - array[i]));//print found statement
                return;
            }
            //if the suitable key is not found, then store array[i] as a key in the hashtable
            table.put(array[i], true);
        }
        //if the pairs are not found after the for loop, it means that there is no such pair, so print statement
        System.out.println("Pair not found.");
        return;
    }

    public static int DistinctValues(int[] array){
        int count = 0;//initialize count to count the number of valid keys
        Hashtable<Integer, Boolean> table = new Hashtable<Integer, Boolean>();//construct a hashtable
        for (int i = 0; i < array.length; ++i){//iterate through every element of the array
            //check if the key of abs(array[i]) exists in the table
            if (!table.containsKey(Math.abs(array[i]))){
                table.put(Math.abs(array[i]),true);//if the table doesn't contain a key with the same absolute value as array[i], add array[i]'s absolute value into table
                count++;//increment count
            }
        }
        return count;
    }

    public static void printArr(int[] arr){
        for (int i = 0; i < arr.length; ++i){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void main (String[] args){
        //testing
        int[] nums = { 8, 7, -8, 5, 10, 10 };
        int target = 0;

        System.out.print("Testing array: ");
        printArr(nums);
        System.out.println("Target sum: " + target);
        System.out.println();
        findSumPair(target, nums);
        System.out.println("Number of distinct absolute values:" + DistinctValues(nums));
    }
}
