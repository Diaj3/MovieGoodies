package tqs.project.movie_goodies.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import tqs.project.movie_goodies.entities.Customer;
import tqs.project.movie_goodies.entities.Provider;
import tqs.project.movie_goodies.repositories.ProviderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProviderService implements UserDetailsService, Services<Provider> {

    private ProviderRepository providerRepository;

    @Autowired
    public void setProviderRepository(ProviderRepository providerRepository) {
        this.providerRepository = providerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Provider user = findProviderByEmail(email);
        User.UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(user.getProviderId() +"_PROVIDER");
            builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
            builder.roles("PROVIDER");
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return builder.build();
    }

    public Provider findProviderByEmail(String email) {
        return providerRepository.findProviderByEmail(email);
    }

    @Override
    public Iterable<Provider> listAll() {
        return providerRepository.findAll();
    }

    @Override
    public Provider getById(Long id) {
        return providerRepository.findById(id).orElse(null);
    }

    @Override
    public Provider save(Provider provider) {
        return providerRepository.save(provider);
    }

    @Override
    public long count(){
        return providerRepository.count();
    }
}