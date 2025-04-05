//package com.example.training.Contorller;
//
//import com.example.training.entity.Customer;
//import com.example.training.service.CustomerService;
//import jakarta.annotation.Resource;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api")
//public class CustomerController {
//    @Resource
//    private CustomerService customerService;
//
//    @PostMapping("/add")
//    public Customer addcustomer(@RequestBody Customer customer){
//        return customerService.addcustomer(customer);
//    }
//    @DeleteMapping("/delete{id}")
//    public void deletecustomer(@PathVariable("id") int id){
//       customerService.deletecustomer(id);
//    }
//    @PutMapping("/changepassword{id}")
//    public Customer changepassword(@RequestBody Customer customer){
//       return customerService.changepassword(customer);
//
//    }
//    @PutMapping("/changeemail{id}{email}")
//    public Customer changeemail(@RequestBody Customer customer){
//       return customerService.changeemail(customer);
//    }
//
//
//}
