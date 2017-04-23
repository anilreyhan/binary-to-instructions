import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class AliAnılREYHAN_150115029 {

    public static void main(String[] args) throws FileNotFoundException {

        Scanner file = new Scanner(new File("input.txt"));
        final String ADD = "0001", AND = "0101", BR = "0000", JMP = "1100", LD = "0010", LDI = "1010", LDR = "0110",
                LEA = "1110", NOT = "1001", ST = "0011", STI = "1011", STR = "0111", RESERVED = "1101";

        while (file.hasNextLine()) {
            String line = file.nextLine();
            if (line.length() != 16) {
                stringIsNot16CharLong();
                continue;
            }

            boolean contains = true;

            for (int i = 0; i < line.length(); i++) {

                if (line.charAt(i) != '0' && line.charAt(i) != '1') {
                    contains = false;
                }
            }
            if (!contains) {
                stringHasInvalidCharacters();
                continue;
            }

            if (isOpCode(line, ADD)) {

                if (line.charAt(10) == '0') {

                    String DR = line.substring(4, 7);
                    String SR1 = line.substring(7, 10);
                    String SR2 = line.substring(13);

                    System.out.println(
                            "ADD R" + binaryToDecimal(DR) + " R" + binaryToDecimal(SR1) + " R" + binaryToDecimal(SR2));

                } else if (line.charAt(10) == '1') {

                    String DR = line.substring(4, 7);
                    String SR1 = line.substring(7, 10);
                    String imm5 = line.substring(11);

                    System.out.println("ADD R" + binaryToDecimal(DR) + " R" + binaryToDecimal(SR1) + " #"
                            + twosComplementBinaryToDecimal(imm5));

                } else {
                    System.err.println("Sorry, an error has been occured!");
                }

            } else if (isOpCode(line, AND)) {

                if (line.charAt(10) == '0') {

                    String DR = line.substring(4, 7);
                    String SR1 = line.substring(7, 10);
                    String SR2 = line.substring(13);

                    System.out.println(
                            "AND R" + binaryToDecimal(DR) + " R" + binaryToDecimal(SR1) + " R" + binaryToDecimal(SR2));

                } else if (line.charAt(10) == '1') {

                    String DR = line.substring(4, 7);
                    String SR1 = line.substring(7, 10);
                    String imm5 = line.substring(11);

                    System.out.println("AND R" + binaryToDecimal(DR) + " R" + binaryToDecimal(SR1) + " #"
                            + twosComplementBinaryToDecimal(imm5));

                } else {
                    System.err.println("Sorry, an error has been occured!");
                }

            } else if (isOpCode(line, BR)) {

                String out = "BR";
                if (line.charAt(4) == '1') {
                    out = out + "n";
                }
                if (line.charAt(5) == '1') {
                    out = out + "z";
                }
                if (line.charAt(6) == '1') {
                    out = out + "p";
                }

                out = out + " x" + binaryToHexadecimal(line.substring(7));

                System.out.println(out);

            } else if (isOpCode(line, JMP)) {

                String out = "JMP";
                String BaseR = line.substring(4, 7);
                out = out + " R" + binaryToDecimal(BaseR);

            } else if (isOpCode(line, LD)) {

                String out = "LD";
                String DR = line.substring(4, 7);
                out = out + binaryToDecimal(DR) + " x" + binaryToHexadecimal(line.substring(7));
                System.out.println(out);

            } else if (isOpCode(line, LDI)) {

                String out = "LDI";
                String DR = line.substring(4, 7);
                out = out + binaryToDecimal(DR) + " x" + binaryToHexadecimal(line.substring(7));
                System.out.println(out);
            } else if (isOpCode(line, LDR)) {

                String out = "LDR";
                String DR = line.substring(4, 7);
                String BaseR = line.substring(7, 10);
                out = out + " R" + binaryToDecimal(DR) + " R" + binaryToDecimal(BaseR) + " #"
                        + twosComplementBinaryToDecimal(line.substring(10));
                System.out.println(out);

            } else if (isOpCode(line, LEA)) {

                String out = "LEA";
                String DR = line.substring(4, 7);
                out = out + " R" + binaryToDecimal(DR) + " x" + binaryToHexadecimal(line.substring(7));
                System.out.println(out);

            } else if (isOpCode(line, NOT)) {

                String out = "NOT";
                String DR = line.substring(4, 7);
                String SR = line.substring(7, 10);
                out = out + binaryToDecimal(DR) + binaryToDecimal(SR);
                System.out.println(out);

            } else if (isOpCode(line, ST)) {

                String out = "ST";
                String SR = line.substring(4, 7);
                out = out + binaryToDecimal(SR) + binaryToHexadecimal(line.substring(7));
                System.out.println(out);

            } else if (isOpCode(line, STI)) {

                String out = "STI";
                String SR = line.substring(4, 7);
                out = out + binaryToDecimal(SR) + binaryToHexadecimal(line.substring(7));
                System.out.println(out);

            } else if (isOpCode(line, STR)) {

                String out = "STR";
                String SR = line.substring(4, 7);
                String BaseR = line.substring(7, 10);
                out = out + " R" + binaryToDecimal(SR) + " R" + binaryToDecimal(BaseR) + " #"
                        + twosComplementBinaryToDecimal(line.substring(10));
                System.out.println(out);

            } else if (isOpCode(line, RESERVED)) {
                System.out.println("reserved");

            } else {
                System.err.println("Sorry, undefined OpCode!");
            }

        }
    }

    public static void stringIsNot16CharLong() {
        System.err.println("Your String line is not 16 character long.");
    }

    public static void stringHasInvalidCharacters() {
        System.err.println("Your String has invalid characters.");
    }

    public static boolean isOpCode(String line, String opcode) {

        if (line.substring(0, 4).equals(opcode)) {
            return true;
        }
        return false;
    }

    public static int binaryToDecimal(String string) {

        return Integer.parseInt(string, 2);
    }

    public static String binaryToHexadecimal(String string) {

        String result = Integer.toHexString(binaryToDecimal(string));

        return result;
    }

    public static int twosComplementBinaryToDecimal(String string) {
        if (string.charAt(0) == '1') {

            String invertedDigits = invertDigits(string);
            int decimal = binaryToDecimal(invertedDigits);
            decimal = (decimal + 1) * (-1);
            return decimal;
        } else {
            return binaryToDecimal(string);
        }

    }

    public static String invertDigits(String string) {
        String a = string;
        a = a.replace('0', 'x');
        a = a.replace('1', '0');
        a = a.replace('x', '1');
        return a;
    }

}
