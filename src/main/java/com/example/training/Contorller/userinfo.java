//import com.example.training.entity.Customer;
//import com.example.training.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/user_info")
//public class userinfo {
//
//    @Autowired
//    private UserRepository userRepository; // 你的Customer的Repository
//
//    @GetMapping
//    public ResponseEntity<?> getUserInfo() {
//        // 从Spring Security上下文中获取认证对象
//        Customer userDetails = (Customer) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//
//        // 从数据库中获取完整的用户信息（如果需要）
//        String username = String.valueOf(userRepository.findByUsername(userDetails.getUsername()));
//
//        // 检查用户是否存在
//        if (username == null) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//        }
//
//        // 返回用户信息
//        return ResponseEntity.ok(username);
//    }
//}
