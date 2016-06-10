package com.lunaret_seb.hb.lunaret_seb_zoo.stock;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by KEVIN on 10/06/2016.
 */
public class StockCRUD {

    ArrayList<Stock> listStock;

    public StockCRUD() {

         listStock = new ArrayList<Stock>();
        listStock.add(new Stock("Carotte",300,500,1));
        listStock.add(new Stock("Viande",300,1000,2));
        listStock.add(new Stock("Poisson",120,700,3));
    }

    public void delete(Stock stock) {
        delete(listStock.indexOf(stock));
    }

    private void delete(int id) {
        listStock.remove(id);
    }

    public void add(Stock stock) {
        listStock.add(stock);
    }
    public void update(Stock stock, Stock newStock) {
        int index = listStock.indexOf(stock);
        Stock currentStock = listStock.get(index);
        currentStock.setName(newStock.getName());
        currentStock.setQuantities(newStock.getQuantities());
        currentStock.setMaximum(newStock.getMaximum());
        currentStock.setId(newStock.getId());
}
    public Stock retrieve(int id) {
        for (Stock stock:listStock){
            if(stock.getId()==id)
                return stock;
        }
        return null;
        }
public ArrayList<Stock> retrieveAll(){
        return listStock;
        }
}
