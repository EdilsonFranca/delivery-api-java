package br.com.delivery.app.Controller;

import br.com.delivery.app.Dto.LoginDTO;
import br.com.delivery.app.Security.UserDetailsImpl;
import br.com.delivery.app.Security.jwt.JwtUtils;
import br.com.delivery.app.Dto.JwtDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth/signin")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginDTO login) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(login.getEmail(), login.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        return ResponseEntity.ok(new JwtDTO(jwt, userDetails.getUsername(), userDetails.getEmail()));
    }
}

