package tqs.project.movie_goodies.forms;

import tqs.project.movie_goodies.entities.ProductCart;

import java.util.ArrayList;

public class UpdateCartForm {
    private ArrayList<ProductCart> productsCart;

    public ArrayList<ProductCart> getProductsCart() {
        return productsCart;
    }

    public void setProductsCart(ArrayList<ProductCart> productsCart) {
        this.productsCart = productsCart;
    }

    @Override
    public String toString() {
        return "UpdateCartForm{" +
                "productsCart=" + productsCart +
                '}';
    }
}
