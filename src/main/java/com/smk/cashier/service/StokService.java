package com.smk.cashier.service;

import com.smk.cashier.model.Stok;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class StokService {
    FileReader stokServiceReader;
    FileWriter stokServiceWriter;
    List<Stok> stokList =
            new LinkedList<>();
    private static StokService
            stokService=null;
    private StokService(){

        try {
            stokServiceReader = new
                    FileReader("stok.txt");
            stokServiceWriter = new
                    FileWriter("stok.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static synchronized
    StokService getInstance(){
        if (stokService == null){
            stokService = new StokService();
        }
        return stokService;
    }
    private void readFile(){
        BufferedReader BufferedReader = new BufferedReader(
                stokServiceReader
        );
        List<String> stringList =
                BufferedReader.lines().toList();
        stokList = new LinkedList<>();
        for (String string: stringList){
            stokList.add(
                    parsingLineToStok(string)
            );
        }
    }

    private void writeFile(){
        try {
            stokServiceWriter = new
                    FileWriter("stok.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedWriter bufferedWriter = new BufferedWriter(stokServiceWriter);
        for(int i = 0; i < stokList.size(); i++){
            Stok stok = stokList.get(i);
            StringBuilder sb = new StringBuilder();
            sb.append(stok.getId());
            sb.append("|");
            sb.append(stok.getKodeBarang());
            sb.append("|");
            sb.append(stok.getStokBarang());
            try {
                bufferedWriter.write(sb.toString());
                if (i < stokList.size() - 1){
                    bufferedWriter.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private Stok parsingLineToStok(String string) {
        StringTokenizer st = new StringTokenizer(
                string, "|");
        int id = 0;
        Stok barang = new Stok();
        while (st.hasMoreElements()) {
            if (id == 0) {
                barang.setId(Integer.parseInt(st.nextToken()));
            } else if (id == 1) {
                barang.setKodeBarang(st.nextToken());
            } else if (id == 2) {
                barang.setStokBarang(Integer.parseInt(st.nextToken())
                );
            }
            id++;
        }
        return barang;
    }
    public List<Stok>getStokList(){
        readFile();
        return stokList;
    }
    public void addStok(
            Stok stok){
        stokList.add(stok);
        writeFile();
    }
    public List<Stok>
    findByKode(String kode){
        List<Stok>
                resultList =
                stokList.stream().filter(
                        barang -> barang.getKodeBarang().equals(kode)
                ).toList();
        return resultList;
    }
}
