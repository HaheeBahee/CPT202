//package com.example.training.Contorller;
//
//import com.example.training.dto.ReqRes;
//import com.example.training.entity.Customer;
//import com.example.training.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//public class Adminuser {
//
//    @Autowired
//    private UserRepository userRepository;
//    @GetMapping("/public/all")
//    public ResponseEntity<Object> getallCustomer(){
//        return ResponseEntity.ok(userRepository.findAll());
//    }
//    @GetMapping("/public/save")
//    public ResponseEntity<Object> signup(RequestBody ReqRes reqRes){
//        Customer customer = new Customer();
//        customer.setUsername(reqRes.getUsername());
//        return ResponseEntity.ok(userRepository.save(customer));
//    }
//    @GetMapping("/user/alone")
//    public ResponseEntity<Object> useralone(){
//        return ResponseEntity.ok("user alone can access this api");
//    }
//    @GetMapping("/admin/both")
//    public ResponseEntity<Object> adminboth(){
//        return ResponseEntity.ok("both admin and user can access this api");
//    }
//
//}
