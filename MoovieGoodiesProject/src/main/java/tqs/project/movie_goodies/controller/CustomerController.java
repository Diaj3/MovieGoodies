package tqs.project.movie_goodies.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.apache.commons.validator.routines.EmailValidator;

import tqs.project.movie_goodies.entities.*;
import tqs.project.movie_goodies.forms.CheckoutForm;
import tqs.project.movie_goodies.forms.RegisterForm;
import tqs.project.movie_goodies.forms.UpdateCartForm;
import tqs.project.movie_goodies.services.*;
import tqs.project.movie_goodies.validator.UserValidator;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Set;


@Controller
@RequestMapping("/user")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ProductCartService productCartService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private ProductService productService;

    @Autowired
    private CartService cartService;

    @Autowired
    private SaleService saleService;

    @Autowired
    private ProductSaleService productSaleService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("user", new RegisterForm());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") RegisterForm user, Model model) {
        model = userValidator.validate(user, model, customerService.findCustomerByEmail(user.getEmail()));
        Boolean valid = (Boolean) model.getAttribute("valid");
        if (valid == null || !valid) {
            return "register";
        }
        Customer customer = new Customer();
        customer.setFirstName(user.getFirstName());
        customer.setLastName(user.getLastName());
        customer.setEmail(user.getEmail());
        customer.setPassword(user.getPassword());
        customerService.save(customer);

        Cart cart = new Cart();
        cart.setCustomer(customer);
        cartService.save(cart);

        customer.setCart(cart);
        customerService.save(customer);

        return "redirect:/user/login";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String cart(Principal principal, Model model){
        Customer customer = customerService.getById(Long.parseLong(principal.getName().split("_")[0]));
        UpdateCartForm form = new UpdateCartForm();
        form.setProductsCart(new ArrayList<>(customer.getCart().getProductsCart()));
        model.addAttribute("form", form);
        model.addAttribute("title", "Cart");
        model.addAttribute("cart", customer.getCart());
        return "cart";
    }

    @RequestMapping(value = "/cart", method = RequestMethod.POST)
    public String cart(@ModelAttribute UpdateCartForm form, Principal principal, Model model){
        Customer customer = customerService.getById(Long.parseLong(principal.getName().split("_")[0]));
        for (ProductCart product: form.getProductsCart()) {
            ProductCart productCart = productCartService.getById(product.getProductcartId());
            if (productCart != null) {
                Cart cart = customer.getCart();
                if (productCart.getCart().getCartId().equals(cart.getCartId())) {
                    int quantity = product.getQuantity();
                    if (quantity == 0) {
                        cart.getProductsCart().remove(productCart);
                        cartService.save(cart);
                        productCartService.delete(productCart);
                    } else if (quantity > 0) {
                        if (productCart.getProduct().getQuantity() < quantity) {
                            quantity = productCart.getProduct().getQuantity();
                        }
                        productCart.setQuantity(quantity);
                        productCartService.save(productCart);
                    }
                }
            }
        }
        form = new UpdateCartForm();
        form.setProductsCart(new ArrayList<>(customer.getCart().getProductsCart()));
        model.addAttribute("form", form);
        model.addAttribute("title", "Cart");
        model.addAttribute("cart", customer.getCart());
        return "cart";
    }

    @RequestMapping(value = "/item/{productId}", method = RequestMethod.POST)
    public String item(@PathVariable Long productId, Integer quantity, Principal principal){
        Customer customer = customerService.getById(Long.parseLong(principal.getName().split("_")[0]));
        Product item = productService.getById(productId);
        if (item == null || !item.getDisplay())
            return "redirect:/shop/all";
        Cart cart = customer.getCart();
        ProductCart productCart = null;
        for (ProductCart product : cart.getProductsCart()) {
            if (product.getProduct().getProductId().equals(productId)) {
                productCart = product;
                break;
            }
        }
        if (productCart == null) {
            productCart = new ProductCart();
            productCart.setProduct(item);
            productCart.setCart(cart);
        }
        if (quantity == 0) {
            cart.getProductsCart().remove(productCart);
            cartService.save(cart);
            productCartService.delete(productCart);
        } else if (quantity > 0) {
            if (item.getQuantity() > 0) {
                if (item.getQuantity() < quantity) {
                    quantity = item.getQuantity();
                }
                productCart.setQuantity(quantity);
                productCartService.save(productCart);
            }
        }
        return "redirect:/item/"+productId;
    }

    @RequestMapping(value = "/profile", method = {RequestMethod.GET, RequestMethod.POST})
    public String profile(Customer customer, Principal principal, Model model){
        Customer user = customerService.getById(Long.parseLong(principal.getName().split("_")[0]));
        if (customer != null)
            updateProfile(customer, user);
        model.addAttribute("customer", user);
        model.addAttribute("title", "Profile");
        model.addAttribute("cart", user.getCart());
        return "profile";
    }

    @RequestMapping(value = "/checkout", method = {RequestMethod.GET, RequestMethod.POST})
    public String checkout(CheckoutForm form, Principal principal, Model model, HttpServletRequest request){
        Customer customer = customerService.getById(Long.parseLong(principal.getName().split("_")[0]));
        Cart cart = customer.getCart();
        if (cart.getProductsCart().size() == 0)
            return "redirect:/shop/all";
        if (request.getMethod().equals("POST")){
            Set<ProductCart> productsCart = cart.getProductsCart();
            if (productsCart.size() > 0) {
                Sale sale = new Sale();
                sale.setCustomer(customer);
                sale.setPaymentMethod(form.getPaymentMethod());
                saleService.save(sale);
                for (ProductCart productcart : productsCart) {
                    Product product = productcart.getProduct();
                    ProductSale productSale = new ProductSale();
                    productSale.setProduct(product);
                    productSale.setQuantity(productcart.getQuantity());
                    productSale.setPrice(product.getPrice());
                    productSale.setSale(sale);
                    productSaleService.save(productSale);
                    product.setQuantity(product.getQuantity()-productcart.getQuantity());
                    productService.save(product);
                    cart.setProductsCart(null);
                    cartService.save(cart);
                    productCartService.delete(productcart);
                }
            }
            return "redirect:/user/profile";
        }
        model.addAttribute("customer", customer);
        model.addAttribute("title", "Checkout");
        model.addAttribute("cart", customer.getCart());
        model.addAttribute("form", new CheckoutForm());
        return "checkout";
    }

    private void updateProfile(Customer customer, Customer user) {
        if (customer.getFirstName() != null)
            if (customer.getFirstName().length() < 5 || customer.getFirstName().length() > 32 && !customer.getFirstName().equals(user.getFirstName()))
                user.setFirstName(customer.getFirstName());
        if (customer.getLastName() != null)
            if (customer.getLastName().length() < 5 || customer.getLastName().length() > 32 && !customer.getLastName().equals(user.getLastName()))
                user.setLastName(customer.getLastName());
        if (customer.getEmail() != null) {
            EmailValidator emailValidator = EmailValidator.getInstance();
            if (emailValidator.isValid(customer.getEmail()) && !customer.getEmail().equals(user.getEmail()))
                if (customerService.findCustomerByEmail(customer.getEmail()) == null)
                    user.setEmail(customer.getEmail());
        }
        if (customer.getContact() != null && !customer.getContact().equals("") && !customer.getContact().equals(user.getContact()))
            user.setContact(customer.getContact());
        if (customer.getAddress() != null && !customer.getAddress().equals("") && !customer.getAddress().equals(user.getAddress()))
            user.setAddress(customer.getAddress());
        customerService.save(user);
    }
}
