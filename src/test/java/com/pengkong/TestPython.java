package com.pengkong;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class TestPython {

    public static void main(String[] args) throws IOException{
        try {
            System.out.println("start");
            String[] args1=new String[]{"python","H:\\space\\mytest.py"};
            Process pr=Runtime.getRuntime().exec(args1);

            BufferedReader in = new BufferedReader(new InputStreamReader(
                    pr.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            in.close();
            pr.waitFor();
            System.out.println("end");
        } catch (Exception e) {
            e.printStackTrace();
        }}
}
