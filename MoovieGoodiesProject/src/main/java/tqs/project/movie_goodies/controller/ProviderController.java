package tqs.project.movie_goodies.controller;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import tqs.project.movie_goodies.entities.*;
import tqs.project.movie_goodies.forms.RegisterForm;
import tqs.project.movie_goodies.services.*;
import tqs.project.movie_goodies.validator.ProductValidator;
import tqs.project.movie_goodies.validator.UserValidator;

import java.security.Principal;


@Controller
@RequestMapping("/provider")
public class ProviderController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ProviderService providerService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private ProductValidator productValidator;

    @Autowired
    private MovieTagService movieTagService;

    @Autowired
    private ProductSaleService productSaleService;

    @Autowired
    private SaleService saleService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(){
        return "login_provider";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register(Model model){
        model.addAttribute("user", new RegisterForm());
        return "register_provider";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registration(@ModelAttribute("user") RegisterForm user, Model model) {
        model = userValidator.validate(user, model, providerService.findProviderByEmail(user.getEmail()));
        Boolean valid = (Boolean) model.getAttribute("valid");
        if (valid == null || !valid) {
            return "register_provider";
        }
        Provider provider = new Provider();
        provider.setFirstName(user.getFirstName());
        provider.setLastName(user.getLastName());
        provider.setEmail(user.getEmail());
        provider.setPassword(user.getPassword());
        providerService.save(provider);
        return "redirect:/provider/login";
    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public String products(Model model, Principal principal){
        Provider provider = providerService.getById(Long.parseLong(principal.getName().split("_")[0]));
        model.addAttribute("title", "List of Products");
        model.addAttribute("products", productService.findByProvider(provider));
        return "products";
    }

    @RequestMapping(value = "/item/{productId}", method = RequestMethod.GET)
    public String item(@PathVariable Long productId, Model model, Principal principal) {
        Provider provider = providerService.getById(Long.parseLong(principal.getName().split("_")[0]));
        Product item = productService.getById(productId);
        if (item == null || item.getProvider() != provider)
            return "redirect:/provider/products";
        model.addAttribute("item", item);
        model.addAttribute("title", "Detail Product");
        model.addAttribute("movieTag", new MovieTag());
        return "item_provider";
    }

    @RequestMapping(value = "/item/{productId}", method = RequestMethod.POST)
    public String item(@PathVariable Long productId, Product item, MovieTag movieTag, Principal principal){
        Provider provider = providerService.getById(Long.parseLong(principal.getName().split("_")[0]));
        Product product = productService.getById(productId);
        if (product == null || product.getProvider() != provider)
            return "redirect:/provider/products";
        if (movieTag.getTag() == null) {
            product.setName(item.getName());
            if (!product.getPrice().equals(item.getPrice()))
                product.setOldPrice(product.getPrice());
            product.setPrice(item.getPrice());
            product.setDescription(item.getDescription());
            product.setQuantity(item.getQuantity());
            product.setDisplay(item.getDisplay());
            productService.save(product);
        } else {
            MovieTag tag = movieTagService.findByTag(movieTag.getTag());
            if (tag == null) {
                tag = new MovieTag();
                tag.setTag(movieTag.getTag().toUpperCase());
            }
            tag.addProduct(product);
            movieTagService.save(tag);
        }
        return "redirect:/provider/item/"+productId;
    }

    @RequestMapping(value = "/sales", method = RequestMethod.GET)
    public String sales(Model model, Principal principal){
        Provider provider = providerService.getById(Long.parseLong(principal.getName().split("_")[0]));
        model.addAttribute("title", "Sales");
        model.addAttribute("products", productService.findByProvider(provider));
        return "sales";
    }

    @RequestMapping(value = "/orders", method = RequestMethod.GET)
    public String orders(Model model, Principal principal){
        Provider provider = providerService.getById(Long.parseLong(principal.getName().split("_")[0]));
        model.addAttribute("title", "Orders");
        model.addAttribute("products", productService.findByProvider(provider));
        return "orders";
    }

    @RequestMapping(value = "/orders/{productId}", method = RequestMethod.POST)
    public String orders(@PathVariable Long productId, Model model, Principal principal){
        Provider provider = providerService.getById(Long.parseLong(principal.getName().split("_")[0]));
        ProductSale productSale = productSaleService.getById(productId);
        if (productSale != null) {
            if (productSale.getProduct().getProvider().equals(provider)) {
                productSale.setDeliver(true);
                productSaleService.save(productSale);
                boolean final_deliver = true;
                for (ProductSale productsSale: productSale.getSale().getProductsSale()) {
                    if (!productsSale.getDeliver()) {
                        final_deliver = false;
                        break;
                    }
                }
                if (final_deliver){
                    Sale sale = productSale.getSale();
                    sale.setDeliver(true);
                    saleService.save(sale);
                }
            }
        }
        return "redirect:/provider/orders";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    public String insert(Model model){
        model.addAttribute("product", new Product());
        model.addAttribute("title", "Insert New Product");
        return "insert";
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public String insert(Product product, Model model, Principal principal){
        model.addAttribute("product", new Product());
        model = productValidator.validate(product, model);
        Boolean valid = (Boolean) model.getAttribute("valid");
        if (valid != null && valid) {
            Provider provider = providerService.getById(Long.parseLong(principal.getName().split("_")[0]));
            product.setProvider(provider);
            productService.save(product);
            return "redirect:/provider/profile";
        } else {
            model.addAttribute("message", "Failed to add Product!");
            model.addAttribute("product", product);
        }
        model.addAttribute("title", "Insert New Product");
        return "insert";
    }

    @RequestMapping(value = "/profile",  method = {RequestMethod.GET, RequestMethod.POST})
    public String profile(Provider provider, Principal principal, Model model){
        Provider user = providerService.getById(Long.parseLong(principal.getName().split("_")[0]));
        if (provider != null)
            updateProfile(provider, user);
        model.addAttribute("provider", user);
        model.addAttribute("title", "Profile");
        return "profile_provider";
    }

    private void updateProfile(Provider provider, Provider user) {
        if (provider.getFirstName() != null)
            if (provider.getFirstName().length() < 5 || provider.getFirstName().length() > 32 && !provider.getFirstName().equals(user.getFirstName()))
                user.setFirstName(provider.getFirstName());
        if (provider.getLastName() != null)
            if (provider.getLastName().length() < 5 || provider.getLastName().length() > 32 && !provider.getLastName().equals(user.getLastName()))
                user.setLastName(provider.getLastName());
        if (provider.getEmail() != null) {
            EmailValidator emailValidator = EmailValidator.getInstance();
            if (emailValidator.isValid(provider.getEmail()) && !provider.getEmail().equals(user.getEmail()))
                if (providerService.findProviderByEmail(provider.getEmail()) == null)
                    user.setEmail(provider.getEmail());
        }
        if (provider.getContact() != null && !provider.getContact().equals("") && !provider.getContact().equals(user.getContact()))
            user.setContact(provider.getContact());
        if (provider.getAddress() != null && !provider.getAddress().equals("") && !provider.getAddress().equals(user.getAddress()))
            user.setAddress(provider.getAddress());
        providerService.save(user);
    }
}
