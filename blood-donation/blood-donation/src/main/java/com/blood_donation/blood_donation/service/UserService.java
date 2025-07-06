package com.blood_donation.blood_donation.service;

import com.blood_donation.blood_donation.dto.UserRegistrationDto;
import com.blood_donation.blood_donation.entity.User;

public interface UserService {
    User registerNewUser(UserRegistrationDto registrationDto);
}