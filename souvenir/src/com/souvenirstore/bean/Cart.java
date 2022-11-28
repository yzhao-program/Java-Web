package com.souvenirstore.bean;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {

    // private Integer totalCount;
    // private BigDecimal totalPrice;

    /**
     * key: souvenir id,
     * value: souvenir data
     */
    private Map<Integer,CartItem> items = new LinkedHashMap<Integer,CartItem>();

    public void addItem(CartItem cartItem) {

        CartItem item = items.get(cartItem.getId());

        // check whether the item already exists in the cart
        if (item == null) {
            // did not add it into the cart before: add the item
            items.put(cartItem.getId(), cartItem);
        } else {
            // the item already exists in the cart: update quantity and subtotal amount
            item.setCount( item.getCount() + 1 );
            item.setTotalPrice( item.getPrice().multiply(new BigDecimal( item.getCount() )) );
        }

    }

    public void deleteItem(Integer id) {
        items.remove(id);
    }

    public void clear() {
        items.clear();
    }

    public void updateCount(Integer id,Integer count) {

        CartItem cartItem = items.get(id);
        // check whether the item already exists in the cart
        if (cartItem != null) {
            cartItem.setCount(count);
            cartItem.setTotalPrice( cartItem.getPrice().multiply(new BigDecimal( cartItem.getCount() )) );
        }
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;

        for (Map.Entry<Integer,CartItem>entry : items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }

        return totalCount;
    }

    public BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);

        for (Map.Entry<Integer,CartItem>entry : items.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }

        return totalPrice;
    }

    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrice=" + getTotalPrice() +
                ", items=" + items +
                '}';
    }
}
