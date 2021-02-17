package com.example.bootstrap;


import com.example.domain.Category;
import com.example.domain.Customer;
import com.example.repositories.CategoryRepository;
import com.example.repositories.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Bootstrap implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final CustomerRepository customerRepository;

    public Bootstrap(CategoryRepository categoryRepository, CustomerRepository customerRepository) {
        this.categoryRepository = categoryRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // this is always initialised since data is non-persistent under h2
        loadFruits();
        loadCustomers();
        log.debug("Loaded SRM bootstrap data as h2");
    }

    private void loadCustomers() {
        Customer someBloke = new Customer();
        someBloke.setFirstname("John");
        someBloke.setLastname("Smith");

        Customer someGal = new Customer();
        someGal.setFirstname("Jill");
        someGal.setLastname("Smith");

        Customer someOne = new Customer();
        someOne.setFirstname("Andy");
        someOne.setLastname("Jones");

        Customer someOneElse = new Customer();
        someOneElse.setFirstname("Mary");
        someOneElse.setLastname("Jones");

        customerRepository.save(someBloke);
        customerRepository.save(someGal);
        customerRepository.save(someOne);
        customerRepository.save(someOneElse);

        System.out.println("Customers added: " + customerRepository.count());
    }

    private void loadFruits() {
        Category fruits = new Category();
        fruits.setName("Fruits");

        Category dried = new Category();
        dried.setName("Dried");

        Category fresh = new Category();
        fresh.setName("Fresh");

        Category exotic = new Category();
        exotic.setName("Exotic");

        Category nuts = new Category();
        nuts.setName("Nuts");

        categoryRepository.save(fruits);
        categoryRepository.save(dried);
        categoryRepository.save(fresh);
        categoryRepository.save(exotic);
        categoryRepository.save(nuts);

        System.out.println("Fruits Loaded = " + categoryRepository.count() );
    }
}
