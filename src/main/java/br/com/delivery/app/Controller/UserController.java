package br.com.delivery.app.Controller;

import br.com.delivery.app.Dto.UserDTO;
import br.com.delivery.app.Model.User;
import br.com.delivery.app.Repository.UserRepository;
import br.com.delivery.app.Service.UserService;
import br.com.delivery.app.Dto.DtoFormError;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> registerUser(@Valid @RequestBody UserDTO userDto) {

        if (userRepository.existsByEmail(userDto.getEmail())) {
            String mensagem = "Já existe um usuário com o email informado.!";
            DtoFormError error = new DtoFormError("error", mensagem);
            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        userDto.setPassword(encoder.encode(userDto.getPassword()));
        User user = new User();
        BeanUtils.copyProperties(userDto, user);

        userService.save(user);

        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }
}
