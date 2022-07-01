public class ArrayIntList {
   private int[] elementData;
   private int size;

   public static final int DEFAULT_CAPACITY = 100;
   
   public ArrayIntList() {
      elementData = new int[DEFAULT_CAPACITY];
      size = 0;
   }
   
   public void add(int value) {
      elementData[size] = value;
      size++;
   }
   
   public String toString() {
      if (size == 0) {
         return "[]";
      } else {
         String result = "[" + elementData[0];
         for (int i = 1; i < size; i++) {
            result += ", " + elementData[i];
         }
         result += "]";
         return result;
      }
    }
}