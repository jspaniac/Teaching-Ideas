// Class ArrayIntList can be used to store a list of integers.
//
// Smaller version than what we learned in class since we only need a few functions

public class ArrayIntList {
   private int[] elementData;
   private int size;
   
   public static final int DEFAULT_CAPACITY = 100;
   
   // post: constructs an empty list of default capacity
   public ArrayIntList() {
      elementData = new int[DEFAULT_CAPACITY];
      // TODO: Insert some values that you might not want someone else to see. 
      //       Feel free to fully trust the safety of our implementation, we clear after! ;)
      this.clear();
   }
   
   // post: creates a comma-separated, bracketed version of the list
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
   
   // pre : size() < capacity (throws IllegalStateException if not)
   // post: appends the given value to the end of the list
   public void add(int value) {
      checkCapacity(size + 1);
      elementData[size] = value;
      size++;
   }

   // post: removes all values from the current list
   public void clear() {
      size = 0;
   }
   
   // post: checks that the underlying array has the given capacity,
   //       throwing an IllegalStateException if it does not
   private void checkCapacity(int capacity) {
      if (capacity > elementData.length) {
         throw new IllegalStateException("would exceed list capacity");
      }
   }
}