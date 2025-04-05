package com.example.training.Contorller;

import com.example.training.dto.ReqRes;
import com.example.training.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/signup")
    public ResponseEntity<ReqRes> signup(@RequestBody ReqRes reqRessignup){
        return ResponseEntity.ok(authService.signUp(reqRessignup));
    }
    @PostMapping("/signin")
    public ResponseEntity<ReqRes> signin(@RequestBody ReqRes reqRessignin){
        return ResponseEntity.ok(authService.signIn(reqRessignin));
    }
    @PostMapping("/refresh")
    public ResponseEntity<ReqRes> refresh(@RequestBody ReqRes reqResrefresh){
        return ResponseEntity.ok(authService.refreshToken(reqResrefresh));
    }

    @PostMapping("/logout") // 新添加的注销端点
    public ResponseEntity<ReqRes> logout(@RequestBody ReqRes reqResLogout) {
        ReqRes response = authService.logout(reqResLogout);
        if (response.getStatusCode() == 200) {
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
