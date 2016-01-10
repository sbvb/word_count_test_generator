/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package word_count_test_generator;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;

/**
 *
 * @author sbvb
 */
public class Main {

//    // If running on Java 6 or older, use `new Random()` on RHS here
//    Random rnd = ThreadLocalRandom.current();
//    for (int i = ar.length - 1; i > 0; i--)
//    {
//      int index = rnd.nextInt(i + 1);
//      // Simple swap
//      int a = ar[index];
//      ar[index] = ar[i];
//      ar[i] = a;
//    }
    public static void ramdomizeList(List<OutputPair> list) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int k = list.size() - 1; k > 0; k--) {
            int index = rnd.nextInt(k + 1);
            // Simple swap
            int a = list.get(index).i;
            list.get(index).i = list.get(k).i;
            list.get(k).i = a;
        }
    }

    public static void ramdomizeVector(int list[]) {
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = ThreadLocalRandom.current();
        for (int k = list.length - 1; k > 0; k--) {
            int index = rnd.nextInt(k + 1);
            // Simple swap
            int a = list[index];
            list[index] = list[k];
            list[k] = a;
        }
    }

    public static void spash() {
        System.out.println("=================================================================");
        System.out.println("https://github.com/sbvb/word_count_test_generator, by sbVB");
        System.out.println("usage: $ java -jar word_count_test_generator.jar output_folder max_nummber max_per_file");
        System.out.println("=================================================================");
    }

    void old_code() {

        // fake parameters begin
        String output_folder = "";
        int max_number = 10;
        int max_per_file = 10;
        String prefix = "a";
        // fake parameters end

        int strings_per_line = 10;

        int k = 0;

        List<OutputPair> list = new LinkedList<OutputPair>();
//        list.add(new OutputPair(1, "a1"));
//        list.add(new OutputPair(2, "a2"));
//        list.add(new OutputPair(3, "a3"));
//        list.add(new OutputPair(4, "a4"));
//        list.add(new OutputPair(5, "a5"));
//        list.add(new OutputPair(6, "a6"));

        for (int n = 1; n <= max_number; n++) {
            for (int i = 0; i < n; i++) {
                String str = prefix;
                str += n;
                list.add(new OutputPair(k++, str));
            }
        }

//        Collections.sort(list, new Comparator<OutputPair>() {
//            public int compare(OutputPair x, OutputPair y) {
//                return x.i > y.i ? 1 : -1;
//            }
//        });
        ramdomizeList(list);

//        System.out.println(list.toString().replaceAll(",", "\n"));
//        System.out.println(list.toString());
//        for (int i = 0; i < list.size(); i++) {
//            int z = list.get(i).i;
//            System.out.println(list.get(z).str);
//        }
        // open file
        String filePrefix = "fileOut";
        String fileSufix = ".txt";
        int fileCount = 1;
        int outputCount = 0;

        boolean stop = false;

        while (!stop) {

            String fileCountStr = String.format("%08d", fileCount);
            //        System.out.println("DEBUG: " + fileCountStr);
            String fileName = output_folder + filePrefix + fileCountStr + fileSufix;
            System.out.println("DEBUG: fileName=" + fileName);

            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileName), "utf-8"))) {

                // each file has a maximum of max_per_file strings
                for (int count = 0; count < max_per_file; count++) {
                    for (int count_per_line = 0; count_per_line < strings_per_line; count_per_line++) {
                        if (outputCount >= list.size()) {
                            stop = true;
                            break;
                        }
                        int idx = list.get(outputCount++).i;
                        String strOut = list.get(idx).str;
//                        System.out.println("DEBUG: strOut=" + strOut + ", outputCount=" + outputCount + ", idx=" + idx);
                        writer.write(strOut + " ");
                    }
                    writer.write("\n");
                }
                writer.close();
                fileCount++;
            } catch (IOException ex) {
                System.out.println("Error: could not open file ");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    static int fsum(int i) {
        if (i == 1) {
            return 1;
        }
        return i + fsum(i - 1);
    }

    static int fsumReverse(int i) {
        int k = 1;
        int z;
        do {
            z = fsum(k);
            k++;
        } while (z < i);
        return k - 1;
    }

    public static void main(String[] args) {
        // print splash screen
        spash();

        // get args
        String output_folder = args[0];
        int max_number = Integer.parseInt(args[1]);
        int max_per_file = Integer.parseInt(args[2]);

        int strings_per_line = 10;

        boolean debug = true;

        if (debug) {
            System.out.println("output_folder=" + output_folder);
            System.out.println("max_number=" + max_number);
            System.out.println("max_per_file=" + max_per_file);
            System.out.println("words generated=" + fsum(max_number));
        }

        String prefix = "a";

        Assert.assertTrue(fsum(1) == 1);
        Assert.assertTrue(fsum(2) == 3);
        Assert.assertTrue(fsum(3) == 6);
        Assert.assertTrue(fsum(4) == 10);
        Assert.assertTrue(fsum(5) == 15);

        Assert.assertTrue(fsumReverse(1) == 1);
        Assert.assertTrue(fsumReverse(2) == 2);
        Assert.assertTrue(fsumReverse(3) == 2);
        Assert.assertTrue(fsumReverse(4) == 3);
        Assert.assertTrue(fsumReverse(5) == 3);
        Assert.assertTrue(fsumReverse(6) == 3);
        Assert.assertTrue(fsumReverse(7) == 4);
        Assert.assertTrue(fsumReverse(8) == 4);
        Assert.assertTrue(fsumReverse(9) == 4);
        Assert.assertTrue(fsumReverse(10) == 4);
        Assert.assertTrue(fsumReverse(11) == 5);
        Assert.assertTrue(fsumReverse(12) == 5);
        Assert.assertTrue(fsumReverse(13) == 5);
        Assert.assertTrue(fsumReverse(14) == 5);
        Assert.assertTrue(fsumReverse(15) == 5);

//        for (int i = 1; i <=15; i++) {
//            System.out.println("DEBUG i=" + i + ", fsumReverse(i)=" + fsumReverse(i)); 
//        }
        int fsum_max_number = fsum(max_number);
        int list[] = new int[fsum_max_number];
        for (int i = 0; i < fsum_max_number; i++) {
            list[i] = i + 1;
        }

        ramdomizeVector(list);

//        for (int i = 0; i < fsum_max_number; i++) {
////            System.out.println("DEBUG i=" + i + ", list(i)=" + fsumReverse(list[i]));
//            System.out.println(fsumReverse(list[i]));
//        }

        // open file
        String filePrefix = "fileOut";
        String fileSufix = ".txt";
        int fileCount = 1;
        int outputCount = 0;

        boolean stop = false;

        while (!stop) {

            String fileCountStr = String.format("%08d", fileCount);
            //        System.out.println("DEBUG: " + fileCountStr);
            String fileName = output_folder + filePrefix + fileCountStr + fileSufix;
            System.out.println("DEBUG: fileName=" + fileName);

            try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(fileName), "utf-8"))) {

                // each file has a maximum of max_per_file strings
                for (int count = 0; count < max_per_file; count++) {
                    for (int count_per_line = 0; count_per_line < strings_per_line; count_per_line++) {
                        if (outputCount >= list.length) {
                            stop = true;
                            break;
                        }
                        String strOut = prefix + fsumReverse(list[outputCount++]);
//                        System.out.println("DEBUG: strOut=" + strOut + ", outputCount=" + outputCount + ", idx=" + idx);
                        writer.write(strOut + " ");
                    }
                    writer.write("\n");
                }
                writer.close();
                fileCount++;
            } catch (IOException ex) {
                System.out.println("Error: could not open file ");
                Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
}


