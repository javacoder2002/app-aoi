package uz.pdp.appapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import uz.pdp.appapi.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    boolean existsByPhoneNumber(String phoneNumber);

}
