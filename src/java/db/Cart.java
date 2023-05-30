/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class Cart {
    private List<Item> items;

    public Cart() {
        this.items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    
    public Item getItemById(int id){
        for(Item item : items) {
            if(item.getProduct().getId() == id)
                return item;
        }
        return null;
    }
    
    public int getQuantityById(int id) {
        return getItemById(id).getQuantity();
    }
    
    
    public void addItem(Item item) {
        if(getItemById(item.getProduct().getId())!=null) {
            Item m = getItemById(item.getProduct().getId());
            m.setQuantity(m.getQuantity() + item.getQuantity());
        } else {
            items.add(item);
        }        
    }
    
    public void removeItem(int id) {
        if(getItemById(id) != null) {
            items.remove(getItemById(id));
        }
    }
    
    public double getTotalMoney() {
        double t= 0;
        for(Item item : items) {
            t += item.getQuantity() * item.getPrice();
        }
        return t;
    }
    
}
