package bsu.rfct.course2.group7.Kaminskaya.varC1;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Main {

    private static double x(double t){
        return 16*Math.pow(Math.sin(t),3);
    }

    private static double y(double t){
        return 13*Math.cos(t) - 5 * Math.sin(2*t) - 2 * Math.sin(3*t) - Math.sin(4*t);
    }

    private static void createData(File file) throws IOException {
        DataOutputStream out = new DataOutputStream(new FileOutputStream(file));


        ArrayList<Double> l = new ArrayList<Double>();

        for (double t = 0; t <= 2 * Math.PI; t+=0.2){
            System.out.println(10*x(t));
            l.add(10*x(t));
            l.add(10*y(t));
        }


        for (Double v : l){
            out.writeDouble(v);
        }

        out.close();
    }

    private static void getData(File file) throws IOException {
        System.out.println("hello");
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        System.out.println("hello");
        String s = in.readUTF();
        System.out.println(s);
        System.out.println("hello");
        in.close();
    }

    private static void transform(File file) throws IOException{
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        String new_name = file.getName().split("\\.")[0] + "_new.bin";
        File new_file = new File(new_name);
        DataOutputStream out = new DataOutputStream(new FileOutputStream(new_file));

        int i = 0;
        double a;
        while(in.available() > 0){
            if (i++%100 == 0){
                out.writeDouble(in.readDouble());
                out.writeDouble(2*in.readDouble());
            }
            else{
                a = in.readDouble();
                a = in.readDouble();
            }
        }
        in.close();
        out.close();
    }

    public static void main(String[] args) throws IOException {

        File file = new File("data_1.bin");


//        createData(file);
//        getData(file);
        transform(file);
        Plot plot = new Plot();
        plot.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        plot.setVisible(true);
    }

}
