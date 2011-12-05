package com.acmetelecom.utils;

import java.io.*;

/**
 * Created by IntelliJ IDEA.
 * User: The0s
 * Date: 04/12/11
 * Time: 01:47
 * To change this template use File | Settings | File Templates.
 */

//TODO do it maybe with FIT
public class FilePrinter {
    final private String fileName = "test.txt";
    private FileInputStream fStream;
    private FileOutputStream out;
    private FileWriter out2;

    private static FilePrinter instance;

    static {
        try {
        	instance = new FilePrinter();
            
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    private FilePrinter() throws IOException {
        out = new FileOutputStream(fileName);
        fStream = new FileInputStream(fileName);
    }

    public static FilePrinter getInstance() {
        return instance;
    }

    private void createAndWrite(String number, String totalBill) {
        PrintStream prt;
        try {
            //out = new FileOutputStream(fileName);
            prt = new PrintStream(out);
            prt.println(number + "," + totalBill);
            prt.close();
        } catch (Exception e) {
            System.out.println("Write error");
        }
    }

    public void writeToTestFile(String number, String totalBill) {
        File f = new File(this.fileName);
        if (f.exists()) {
            appendToFile(number, totalBill);
        } else {
            createAndWrite(number, totalBill);
        }
    }

    private void appendToFile(String number, String totalBill) {
        try {
            out2 = new FileWriter(fileName, true);
            out2.write(number + "," + totalBill + "\n");
            out2.close();
        } catch (IOException e) {
            System.out.println("Error appending to file " + fileName);
        }
    }

    public String readFile(String number) {
        try {
            String[] temp;

            BufferedReader in = new BufferedReader(new InputStreamReader(fStream));
            while (in.ready()) {
                temp = in.readLine().split(",");
                if (temp[0].equals(number)) {
                    return temp[1];
                }
            }
            in.close();

        } catch (IOException e) {
            System.out.println("File input error");
        }
        return "Null";
    }

    public void deleteFile() {
        File f = new File(fileName);
        if (f.exists()) {
            f.delete();
        }
    }
}
