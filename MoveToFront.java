package Assignment_4;
import java.io.FileInputStream;

public class MoveToFront {
    // apply move-to-front encoding, reading from standard input and writing to standard output
    public static void encode() {
        int[] charAtIndex = new int[256];
        int[] next = new int[256];
        int start = 0;

        for (int i = 0; i < 256; i++) {
            charAtIndex[i] = i;
            next[i] = i + 1;
        }
        while (!BinaryStdIn.isEmpty()) {
            char readChar = BinaryStdIn.readChar(8);
            int num = 0;
            int currentCharIndex = start;
            int prevCharIndex = -1;
            while (charAtIndex[currentCharIndex] != readChar) {
                prevCharIndex = currentCharIndex;
                currentCharIndex = next[currentCharIndex];
                num++;
            }
            BinaryStdOut.write(num, 8);
            // Move readChar to front, and increase the index of the rest

            if (prevCharIndex != -1) {
                next[prevCharIndex] = next[currentCharIndex];
                next[currentCharIndex] = start;
            }
            start = currentCharIndex;
        }
        BinaryStdOut.flush();
    }

    // apply move-to-front decoding, reading from standard input and writing to standard output
    public static void decode() {
        int[] charAtIndex = new int[256];

        for (int i = 0; i < 256; i++)
            charAtIndex[i] = i;
        while (!BinaryStdIn.isEmpty()) {
            int currentCharIndex = BinaryStdIn.readChar(8);
            int currentChar = charAtIndex[currentCharIndex];
            BinaryStdOut.write(currentChar, 8);
            for (int i = currentCharIndex; i > 0; i--)
                charAtIndex[i] = charAtIndex[i - 1];
            charAtIndex[0] = currentChar;
        }
        BinaryStdOut.flush();
    }

    public static void main(String[] args){
        if (args[0].equals("-")) encode(); //apply move-to-front encoding
        else if (args[0].equals("+")) decode(); //apply move-to-front decoding
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}