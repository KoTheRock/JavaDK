public class Main {

    public static <T, U> boolean compareArrays(T[] firstArray, U[] scndArray) {
        if (firstArray.length != scndArray.length) {
            return false;
        }
        for (int i = 0; i < firstArray.length; i++) {
            if(!firstArray[i].equals(scndArray[i])) {
                return false;
            }
        }
        return true;
    }
    public static void main (String[]args){

        Integer[] intArray = {1, 2, 3};
        String[] strArray = {"A", "B", "C"};
        Integer[] intArray2 = {1, 2, 3};

        System.out.println(compareArrays(intArray, intArray2));
        System.out.println(compareArrays(intArray, strArray));
    }
}
