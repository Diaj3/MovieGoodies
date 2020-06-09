package tqs.project.movie_goodies.controllerTests;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import tqs.project.movie_goodies.controller.ProviderController;
import tqs.project.movie_goodies.services.*;
import tqs.project.movie_goodies.validator.ProductValidator;
import tqs.project.movie_goodies.validator.UserValidator;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(ProviderController.class)
public class ProviderControllerTests {
    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProviderService providerService;

    @MockBean
    private UserValidator userValidator;

    @MockBean
    private ProductValidator productValidator;

    @MockBean
    private MovieTagService movieTagService;

    @MockBean
    private ProductSaleService productSaleService;

    @MockBean
    private SaleService saleService;

    /*@Test
    void getProductsPage() throws Exception{
        mvc.perform(get("/provider/products")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }*/

    @Test
    void getLoginPage() throws Exception{
        mvc.perform(get("/provider/login")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void getRegisterPage() throws Exception{
        mvc.perform(get("/provider/register")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void getInsertPage() throws Exception{
        mvc.perform(get("/provider/insert")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }
    /*
    @Test
    void postInsertPage() throws Exception{
        Provider provider = new Provider();
        provider.setFirstName("Diogo");
        provider.setLastName("Andrade");
        provider.setEmail("diogo.andrade@ua.pt");
        provider.setPassword("pass");
        providerService.save(provider);

        Product product = new Product();
        product.setName("Star Wars sweater");
        product.setPrice(45.0);
        product.setDescription("Example of Marvel Mug");
        product.setImage("/images/product1.jpg");
        product.setQuantity(6);
        product.setType("clothes");
        product.setProvider(provider);
        productService.save(product);

        MovieTag movieTag = new MovieTag();
        movieTag.setTag("MARVEL".toUpperCase());
        movieTagService.save(movieTag);

        movieTag.addProduct(product);
        movieTagService.save(movieTag);

        mvc.perform(post("/provider/insert")
                //.param("name", product.getName())
                .param("price", String.valueOf(product.getPrice()))
                .param("description", product.getDescription())
                .param("image", product.getImage())
                .param("quantity", String.valueOf(product.getQuantity()))
                .param("type", product.getType())
                .contentType(MediaType.APPLICATION_FORM_URLENCODED))
                .andExpect(status().isOk())
                //.andExpect(model().attribute("product", hasProperty("name", is(product.getName()))))
                .andExpect(model().attribute("product", hasProperty("price", is(product.getPrice()))))
                .andExpect(model().attribute("product", hasProperty("description", is(product.getDescription()))))
                .andExpect(model().attribute("product", hasProperty("image", is(product.getImage()))))
                .andExpect(model().attribute("product", hasProperty("quantity", is(product.getQuantity()))))
                .andExpect(model().attribute("product", hasProperty("type", is(product.getType()))));
    }*/
}
