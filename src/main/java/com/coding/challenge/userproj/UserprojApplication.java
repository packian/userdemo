package com.coding.challenge.userproj;

import com.coding.challenge.userproj.model.UserAddrDetails;
import com.coding.challenge.userproj.model.UserDetails;
import com.coding.challenge.userproj.repositories.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@EnableCircuitBreaker
public class UserprojApplication implements CommandLineRunner {
    @Autowired
    private UserDetailsRepository userDetailsRepository;

    public static void main(String[] args) {
        SpringApplication.run(UserprojApplication.class, args);
    }

    @Override
    public void run(String... strings) {

        List<UserDetails> usersDetails = new ArrayList<>();
        usersDetails.add(new UserDetails("Mrs", "Tommy", "John", "Female", new UserAddrDetails("King St", "Sydney", "NSW", "2848")));
        usersDetails.add(new UserDetails("Mrs", "Grace", "Xavier", "Female", new UserAddrDetails("Carrot St", "Sydney", "NSW", "2877")));
        usersDetails.add(new UserDetails("Mr", "Shine", "Tom", "Male", new UserAddrDetails("Root St", "Sydney", "nsw", "2199")));
        usersDetails.add(new UserDetails("Mrs", "Williams", "Tim", "Female", new UserAddrDetails("Grg St", "Sydney", "NSW", "2766")));
        usersDetails.add(new UserDetails("Mr", "George", "Paul", "Male", new UserAddrDetails("Martin St", "Sydney", "NSW", "2888")));
        userDetailsRepository.saveAll(usersDetails);
    }
}
