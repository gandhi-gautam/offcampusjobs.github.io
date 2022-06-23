package co.offcampusjobs.controller;

import co.offcampusjobs.business.JobBusiness;
import co.offcampusjobs.business.UserBusiness;
import co.offcampusjobs.model.Job;
import co.offcampusjobs.model.JwtResponse;
import co.offcampusjobs.model.User;
import co.offcampusjobs.service.impl.CustomUserDetailsService;
import co.offcampusjobs.util.jwt.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JobBusiness jobBusiness;

    @Autowired
    private UserBusiness userBusiness;

    @PostMapping("/add")
    public ResponseEntity<Job> saveJob(@Valid @RequestBody Job job, BindingResult result) {
        if (result.hasErrors()) {
            System.out.println(result);
            return null;
        }
        return new ResponseEntity<>(jobBusiness.saveNewJob(job), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) {
        if (result.hasErrors()) {
            throw new RuntimeException("User data body is not in valid format");
        }
        try {
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(),
                    user.getPassword()));
        } catch (UsernameNotFoundException e) {
            e.printStackTrace();
            throw new UsernameNotFoundException("Username not found");
        } catch (BadCredentialsException e){
            e.printStackTrace();
            throw new BadCredentialsException("Bad Credentials");
        }
        UserDetails userDetails = this.customUserDetailsService.loadUserByUsername(user.getEmail());
        String token = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }
}
