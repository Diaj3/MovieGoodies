package tqs.project.movie_goodies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tqs.project.movie_goodies.entities.*;
import tqs.project.movie_goodies.services.CustomerService;
import tqs.project.movie_goodies.services.MovieTagService;
import tqs.project.movie_goodies.services.ProductService;
import org.springframework.ui.Model;

import java.security.Principal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Controller
public class MainController {

    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private MovieTagService movieTagService;

    @GetMapping(value="/")
    public String index(Model model, Principal principal) {
        model = getCart(model, principal);
        return "index";
    }

    @RequestMapping(value = "/shop/{type}",  method = {RequestMethod.POST, RequestMethod.GET})
    public String shop(@PathVariable String type, String tag, Model model, Principal principal){
        if (!type.equals("all") && !type.equals("new") && !type.equals("sale")) {
            return "redirect:/shop/all";
        }
        model = getCart(model, principal);
        if (tag == null)
            model.addAttribute("products", productService.listAll());
        else {
            MovieTag movieTag = movieTagService.findByTag(tag);
            if (movieTag != null)
                model.addAttribute("products", movieTag.getProducts());
        }
        model.addAttribute("productSearch", new MovieTag());
        model.addAttribute("title", "Shop");
        model.addAttribute("title2", "Check out all the new Movie Arrivals Collection 2020");
        model.addAttribute("type", type);
        model.addAttribute("day", java.sql.Date.valueOf(LocalDate.now()));
        return "shop";
    }

    @RequestMapping(value = "/item/{productId}", method = RequestMethod.GET)
    public String item(@PathVariable Long productId, Model model, Principal principal) {
        Product item = productService.getById(productId);
        if (item == null)
            return "redirect:/shop/all";
        model = getCart(model, principal);
        Cart cart = (Cart) model.getAttribute("cart");
        model.addAttribute("permission", true);
        if (item.getDisplay()) {
            ProductCart add_product = new ProductCart();
            add_product.setQuantity(0);
            if (cart != null) {
                for (ProductCart product : cart.getProductsCart()) {
                    if (product.getProduct().getProductId().equals(productId)) {
                        add_product.setQuantity(product.getQuantity());
                        break;
                    }
                }
            }
            model.addAttribute("product", add_product);
            if (item.getQuantity() == 0) {
                model.addAttribute("message", "Sold off!");
                model.addAttribute("permission", false);
            } else if (item.getQuantity() < 5) {
                model.addAttribute("message", "Only have " + item.getQuantity() + " units!");
            }
        } else {
            model.addAttribute("message", "Product Unavailable!");
            model.addAttribute("permission", false);
        }
        model.addAttribute("item", item);
        Set<Product> products = new HashSet<>();
        for (MovieTag tag: item.getMovieTags()) {
            products.addAll(tag.getProducts());
        }
        model.addAttribute("products", products);
        model.addAttribute("day", java.sql.Date.valueOf(LocalDate.now()));
        model.addAttribute("title", "Detail Product");
        return "item";
    }

    private Model getCart(Model model, Principal principal) {
        if (principal == null) {
            model.addAttribute("cart", null);
        } else if (principal.getName().split("_")[1].equals("PROVIDER")) {
            model.addAttribute("cart", null);
        } else {
            Customer customer = customerService.getById(Long.parseLong(principal.getName().split("_")[0]));
            model.addAttribute("cart", customer.getCart());
        }
        return model;
    }

}