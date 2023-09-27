package com.smk.cashier.service;

import com.smk.cashier.model.Barang;
import com.smk.cashier.model.Stok;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class StokServiceTest {

        @Test
        @Order(2)
        void getBarangList() {
            List<Stok> barangList = StokService.getInstance().getStokList();
            assertEquals(barangList.size(),3);
        }

        @Test
        @Order(3)
        void findByKode(){
            List<Stok> resultList = StokService.getInstance().findByKode("Laptop");
            assertEquals(resultList.size(),1);
        }

        @org.junit.jupiter.api.Test
        @Order(1)
        void addBarang() {
            Stok laptop = new Stok();
            laptop.setId(1);
            laptop.setKodeBarang("Laptop");
            laptop.setStokBarang(3);
            StokService.getInstance().addStok(laptop);

            Stok mouse = new Stok();
            mouse.setId(2);
            mouse.setKodeBarang("Mouse");
            mouse.setStokBarang(4);
            StokService.getInstance().addStok(mouse);

            Stok laptopGaming = new Stok();
            laptopGaming.setId(3);
            laptopGaming.setKodeBarang("Laptop Gaming");
            laptopGaming.setStokBarang(6);
            StokService.getInstance().addStok(laptopGaming);
        }
}