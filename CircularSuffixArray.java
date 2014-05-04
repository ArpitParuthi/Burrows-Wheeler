package Assignment_4;
public class CircularSuffixArray {
    private String inputString;
    private int[] index;

    public CircularSuffixArray(String s)  // circular suffix array of s
    {
        inputString = s;
        index = new int[s.length()];
        for (int i = 0; i < index.length; i++)
            index[i] = i;
        Sort(0, index.length - 1, 0);
    }

    private void StartingFrom(int i) {
        for (int j = i; j < i + inputString.length(); j++)
            System.out.print(inputString.charAt(j % inputString.length()));
        System.out.println();
    }

    private void Sort(int min, int max, int offset) {
        if (min >= max|| offset >= inputString.length()) return;
        int low = min, high = max;
        char a = getCharAt(min, offset);
        int i = min + 1;
        while (i <= high) {
            char b = getCharAt(i, offset);
            if (b < a) exchange(low++, i++);
            else if (b > a) exchange(i, high--);
            else i++;
        }
        Sort(min, low-1, offset);
        if (low!=high) Sort(low, high, offset+1);
        Sort(high+1, max, offset);
    }

    private void exchange(int i, int j) {
        int temp = index[i];
        index[i] = index[j];
        index[j] = temp;
    }

    private char getCharAt(int position, int offset) {
        return inputString.charAt((index[position] + offset) % inputString.length());
    }

    public int length()       // length of s            
    {
        return index.length;
    }

    public int index(int i) //returns index of ith sorted suffix
    {
        return index[i];
    }
}
