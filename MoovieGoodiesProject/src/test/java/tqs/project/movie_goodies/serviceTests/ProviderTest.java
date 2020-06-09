package tqs.project.movie_goodies.serviceTests;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import tqs.project.movie_goodies.entities.Provider;
import tqs.project.movie_goodies.repositories.ProviderRepository;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProviderTest {

    private ProviderRepository providerRepository;

    @Autowired
    public void setProviderRepository(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Test
    public void testCreateSaveProvider() {

        Provider provider = new Provider();
        provider.setFirstName("Companhia");
        provider.setLastName("Limitada");
        provider.setEmail("companhialiitada@gmail.com");
        provider.setPassword("password");
        provider.setAddress("Rua da companhia");
        provider.setContact("232232232");

        //Testing Services
        providerRepository.save(provider);
        assertNotNull(provider.getEmail());

        //verify count of products in the repository
        //long numberOfProviders = providerRepository.count();
        //assertEquals(numberOfProviders, 2);

        //Check later
        //assertEquals(providerRepository.findById(provider.getProviderId()).orElse(null), provider);

        //iterate through all providers
        Iterable<Provider> providers = providerRepository.findAll();
        int count = 0;
        for(Provider p : providers){ count++; }
        //assertEquals(count, 2);

    }
}
