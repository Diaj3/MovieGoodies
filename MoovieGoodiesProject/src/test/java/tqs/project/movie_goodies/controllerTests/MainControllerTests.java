package tqs.project.movie_goodies.controllerTests;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import tqs.project.movie_goodies.controller.MainController;
import tqs.project.movie_goodies.services.*;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MainController.class)
public class MainControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private ProductCartService productCartService;

    @MockBean
    private CartService cartService;

    @MockBean
    private MovieTagService movieTagService;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private FilterChainProxy filterChainProxy;

    @Before
    public void setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity())
                .build();
    }

    @Test
    void whenGetIndex_returnNullCart() throws Exception{

        mvc.perform(get("/")
                .contentType("application/json"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("cart", equalTo(null)));

    }

    /*@Test
    void whenGetIndex_returnNotNullCart() throws Exception{

        mvc.perform(get("/")
                .with(user("diogo.andrade@ua.pt").password("pass").roles("CONSUMER")))
                .andExpect(model().attribute("cart", equalTo(null)));

    }*/
}
