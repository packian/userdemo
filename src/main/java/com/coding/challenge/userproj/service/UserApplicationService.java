package com.coding.challenge.userproj.service;

import com.coding.challenge.userproj.model.UserAddrDetails;
import com.coding.challenge.userproj.model.UserDetails;
import com.coding.challenge.userproj.repositories.UserDetailsRepository;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserApplicationService {

    @Autowired
    private UserDetailsRepository userDetailsRepository;

    @Transactional(readOnly = true)
    @HystrixCommand(fallbackMethod = "fallbackGetUserDetails")
    public UserDetails fetchUserDetails(int id) {
        return userDetailsRepository.findById(id).get();
    }

    public UserDetails fallbackGetUserDetails(int id, Throwable hystrixCommand) {
        return new UserDetails("fallback data", "fallback data", "fallback data", "fallback data", new UserAddrDetails("fallback data", "fallback data", "fallback data", "fallback data"));
    }

    @Transactional
            (rollbackFor = Exception.class)
    @HystrixCommand(fallbackMethod = "fallbackPutUserDetails")
    public UserDetails updateUserDetails(int id, UserDetails userDetails) {
        return userDetailsRepository.findById(id).map(userDetail -> {
            userDetail.setTitle(userDetails.getTitle());
            userDetail.setFirstn(userDetails.getFirstn());
            userDetail.setGender(userDetails.getGender());
            userDetail.setLastname(userDetails.getLastname());
            userDetail.getUserAddrDetails().setStreet(userDetails.getUserAddrDetails().getStreet());
            userDetail.getUserAddrDetails().setCity(userDetails.getUserAddrDetails().getCity());
            userDetail.getUserAddrDetails().setState(userDetails.getUserAddrDetails().getState());
            userDetail.getUserAddrDetails().setPostcode(userDetails.getUserAddrDetails().getPostcode());
            return userDetailsRepository.save(userDetail);
        }).get();
    }

    public UserDetails fallbackPutUserDetails(int id, UserDetails userDetails, Throwable hystrixCommand) {
        return new UserDetails("fallback data", "fallback data", "fallback data", "fallback data", new UserAddrDetails("fallback data", "fallback data", "fallback data", "fallback data"));
    }
}
