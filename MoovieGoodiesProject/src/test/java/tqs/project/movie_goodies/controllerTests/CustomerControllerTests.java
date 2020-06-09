package tqs.project.movie_goodies.controllerTests;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import tqs.project.movie_goodies.controller.CustomerController;
import tqs.project.movie_goodies.services.*;
import tqs.project.movie_goodies.validator.UserValidator;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(CustomerController.class)
public class CustomerControllerTests {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private CustomerService customerService;

    @MockBean
    private ProductCartService productCartService;

    @MockBean
    private UserValidator customerValidator;

    @MockBean
    private ProductService productService;

    @MockBean
    private CartService cartService;

    @MockBean
    private SaleService saleService;

    @MockBean
    private ProductSaleService productSaleService;

    /*@Autowired
    private FilterChainProxy filterChainProxy;

    @Before
    public void setUp() {
        String email="danielpinto@email.com";
        String password="password";
        String firstName="Daniel";
        String lastName="Pinto";
        String address="address";
        String contact="1111111111";

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setAddress(address);
        customer.setContact(contact);

        customerService.save(customer);
        mvc = MockMvcBuilders.webAppContextSetup(context)
                .apply(springSecurity(filterChainProxy))
                .build();
    }*/

    /*
    @Test
    void d(){
        String email="danielpinto@email.com";
        String password="password";
        String firstName="Daniel";
        String lastName="Pinto";
        String address="address";
        String contact="1111111111";

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setAddress(address);
        customer.setContact(contact);

        customerService.save(customer);
    }*/

    @Test
    void getLoginPage() throws Exception{
        mvc.perform(get("/user/login")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    @Test
    void getRegisterPage() throws Exception{
        mvc.perform(get("/user/register")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }

    /*@Test
    void getCartPage() throws Exception{
        String email="danielpinto@email.com";
        String password="password";
        String firstName="Daniel";
        String lastName="Pinto";
        String address="address";
        String contact="1111111111";

        Customer customer = new Customer();
        customer.setFirstName(firstName);
        customer.setLastName(lastName);
        customer.setEmail(email);
        customer.setPassword(password);
        customer.setAddress(address);
        customer.setContact(contact);

        customerService.save(customer);

        Authentication authToken = new UsernamePasswordAuthenticationToken(customer.getEmail(), customer.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authToken);
        mvc.perform(get("/user/cart")
                .contentType("application/json"))
                .andExpect(status().isOk());
    }
    */
    /*@Test
    void getProfilePage() throws Exception{
        System.out.println("bru");
        //when(SecurityContextHolder.getContext().getAuthentication().isAuthenticated()).thenReturn(true);
        System.out.println("bruh");
        mvc.perform(get("/user/profile")
                .contentType("application/json"))
                .andExpect(status().isOk());
        System.out.println("bruv");
    }*/

    /*@Test
    void whenFormNotValid_ReturnRegisterPage() throws Exception{
        CustomerRegister form = new CustomerRegister();
        form.setEmail("email@email.com");
        form.setFirstName("Daniel");
        form.setLastName("Pinto");
        form.setPassword("password1");
        form.setMatchingPassword("password2");

        mvc.perform(post("/user/register")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(form)))
                .andExpect(status().isOk())
                .andExpect(model().attribute("valid", equalTo(null)));
    }*/
}