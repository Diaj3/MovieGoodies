package tqs.project.movie_goodies.validator;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import tqs.project.movie_goodies.entities.Product;


@Component
public class ProductValidator {

    public Model validate(Product product, Model model) {
        model.addAttribute("valid", true);

        if (product.getName() != null)
            if (product.getName().equals("")) {
                model.addAttribute("error_mame", "Required Field Name!");
                model.addAttribute("valid", false);
            } else if (product.getName().length() < 5 || product.getName().length() > 32) {
                model.addAttribute("error_name", "Size invalid!");
                model.addAttribute("valid", false);
            }

        if (product.getPrice() != null)
            if (product.getPrice() < 0) {
                model.addAttribute("error_price", "Invalid Price!");
                model.addAttribute("valid", false);
            }

        if (product.getImage() != null)
            if (product.getImage().equals("")) {
                model.addAttribute("error_image", "Required Field Image Url!");
                model.addAttribute("valid", false);
            } else if (product.getImage().length() < 15 || product.getImage().length() > 255) {
                model.addAttribute("error_image", "Size invalid!");
                model.addAttribute("valid", false);
            }

        if (product.getQuantity() != null)
            if (product.getQuantity() < 0) {
                model.addAttribute("error_quantity", "Invalid Quantity!");
                model.addAttribute("valid", false);
            }

        if (product.getDescription() != null)
            if (product.getDescription().equals("")) {
                model.addAttribute("error_description", "Required Field Description!");
                model.addAttribute("valid", false);
            } else if (product.getDescription().length() < 5 || product.getDescription().length() > 255) {
                model.addAttribute("error_description", "Size invalid!");
                model.addAttribute("valid", false);
            }

        return model;
    }
}
