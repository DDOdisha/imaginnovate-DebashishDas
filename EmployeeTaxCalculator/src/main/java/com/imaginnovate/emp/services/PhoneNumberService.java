package com.imaginnovate.emp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imaginnovate.emp.entities.PhoneNumber;
import com.imaginnovate.emp.repositories.PhoneNumberRepository;

import java.util.List;

@Service
public class PhoneNumberService {

    @Autowired
    private PhoneNumberRepository phoneNumberRepository;

    public List<PhoneNumber> getAllPhoneNumbers() {
        return phoneNumberRepository.findAll();
    }

    public PhoneNumber getPhoneNumberById(Long id) {
        return phoneNumberRepository.findById(id).orElse(null);
    }

    public PhoneNumber savePhoneNumber(PhoneNumber phoneNumber) {
        return phoneNumberRepository.save(phoneNumber);
    }

    public void deletePhoneNumber(Long id) {
        phoneNumberRepository.deleteById(id);
    }
}
