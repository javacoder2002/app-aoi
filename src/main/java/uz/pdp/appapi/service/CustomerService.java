package uz.pdp.appapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import uz.pdp.appapi.entity.Customer;
import uz.pdp.appapi.payload.ApiResponse;
import uz.pdp.appapi.payload.CustomerDto;
import uz.pdp.appapi.repository.CustomerRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    /**
     * Bu yerda barcha mijozlarni ro'yxatini qaytaramiz.<br>
     * We return a list of all customers here.
     * @return customers
     */
    public List<Customer> getCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers;
    }

    /**
     * Id orqali 1 ta mijozni qaytaramiz.<br>
     * We return one customer by id.
     * @param id
     * @return one customer
     * Agar Id orqali mijoz topilmasa null qaytaramiz
     * if the customer is not found by id, then we return null.
     */
    public Customer getCustomerById(Integer id) {
        Optional<Customer> optionalCustomer = customerRepository.findById(id);

//        if(optionalCustomer.isPresent())
//            return optionalCustomer.get();
//        return null;
        return optionalCustomer.orElse(null);
    }


    /**
     * Bu metod orqali  mijoz qo'shamiz
     * We add the customer by this method.
     * @param customerDto
     * @return ApiResponse
     * Bizga CustomerDto keladi.<br>
     * CustomerDto comes to us.<br>
     * Validetion qo'ydik. <br>
     * We put the validation
     */
    public ApiResponse addCustomer(CustomerDto customerDto) {

        String fullName = customerDto.getFullName();
        String phoneNumber = customerDto.getPhoneNumber();
        String address = customerDto.getAddress();

        if (fullName.isEmpty() || phoneNumber.isEmpty() || address.isEmpty())
            return new ApiResponse(false, "information is not full");

        if (customerRepository.existsByPhoneNumber(phoneNumber))
            return new ApiResponse(false, "this phone number already exist!");

        Customer customer = new Customer();
        customer.setFullName(fullName);
        customer.setPhoneNumber(phoneNumber);
        customer.setAddress(address);

        return new ApiResponse(true, "customer added successfully!");
    }
}
