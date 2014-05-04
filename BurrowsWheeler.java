package Assignment_4;
import java.util.Arrays;

public class BurrowsWheeler {
    // Reading from standard input and writing to standard output
    public static void encode() {
        String len = BinaryStdIn.readString();
        CircularSuffixArray csa = new CircularSuffixArray(len);

        for (int i = 0; i < csa.length(); i++) {
            if (csa.index(i) == 0) {
                BinaryStdOut.write(i);
                break;
            }
        }

        for (int i = 0; i < csa.length(); i++) {
            int index = csa.index(i) - 1;
            if (index == -1) index = csa.length() - 1;
            BinaryStdOut.write(len.charAt(index), 8);
        }
        BinaryStdOut.flush();
    }

    public static void decode() {
        int cur = BinaryStdIn.readInt();
        String len = BinaryStdIn.readString();
        char[] chars = len.toCharArray();
        char[] sortedChars = len.toCharArray();
        Arrays.sort(sortedChars);

        int[] next = new int[chars.length];
        int[] index = new int[257];

        for (char c : chars)
            index[c + 1]++;
        for (char c = 0; c < 256; c++)
            index[c + 1] += index[c];
        for (int i = 0; i < chars.length; i++) {
            next[index[chars[i]]++] = i;
        }

        for (int i = 0; i < chars.length; i++) {
            BinaryStdOut.write(sortedChars[cur], 8);
            cur = next[cur];
        }
        BinaryStdOut.flush();
    }
 
    public static void main(String[] args) {
        if (args[0].equals("-")) encode(); //apply Burrows-Wheeler encoding
        else if (args[0].equals("+")) decode(); //apply Burrows-Wheeler decoding
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}