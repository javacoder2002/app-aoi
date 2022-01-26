package uz.pdp.appapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import uz.pdp.appapi.entity.Customer;
import uz.pdp.appapi.payload.ApiResponse;
import uz.pdp.appapi.payload.CustomerDto;
import uz.pdp.appapi.service.CustomerService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    /**
     * Bu yerda barcha mijozlarni ro'yxatini qaytaramiz.<br>
     * We return a list of all customers here.
     * @return customers
     */
    @GetMapping
    public List<Customer> getCustomer(){
        return customerService.getCustomers();
    }

    /**
     * Id orqali 1 ta mijozni qaytaramiz.<br>
     * We return one customer by id.
     * @param id
     * @return one customer
     * Agar Id orqali mijoz topilmasa null qaytaramiz
     * if the customer is not found by id, then we return null.
     */
    @GetMapping("/api/customer/{id}")
    public Customer getCustomerById(@PathVariable Integer id) {
        return customerService.getCustomerById(id);
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
    @PostMapping
    public ApiResponse addCustomer(@RequestBody CustomerDto customerDto){
        return customerService.addCustomer(customerDto);
    }


/*
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }*/

}
