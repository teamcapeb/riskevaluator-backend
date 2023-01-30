 package fr.capeb.backend.riskevaluator.controller.authentication;

 import java.util.HashSet;
 import java.util.List;
 import java.util.Set;
 import java.util.stream.Collectors;

 import javax.validation.Valid;

 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.ResponseEntity;
 import org.springframework.security.authentication.AuthenticationManager;
 import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
 import org.springframework.security.core.Authentication;
 import org.springframework.security.core.context.SecurityContextHolder;
 import org.springframework.security.crypto.password.PasswordEncoder;
 import org.springframework.web.bind.annotation.CrossOrigin;
 import org.springframework.web.bind.annotation.PostMapping;
 import org.springframework.web.bind.annotation.RequestBody;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RestController;

 import fr.capeb.backend.riskevaluator.model.Role;
 import fr.capeb.backend.riskevaluator.model.User;
 import fr.capeb.backend.riskevaluator.model.enumeration.ERole;
 import fr.capeb.backend.riskevaluator.payload.request.LoginRequest;
 import fr.capeb.backend.riskevaluator.payload.request.SignupRequest;
 import fr.capeb.backend.riskevaluator.payload.response.JwtResponse;
 import fr.capeb.backend.riskevaluator.payload.response.MessageResponse;
 import fr.capeb.backend.riskevaluator.repository.RoleRepository;
 import fr.capeb.backend.riskevaluator.repository.UserRepository;
 import fr.capeb.backend.riskevaluator.security.jwt.JwtUtils;
 import fr.capeb.backend.riskevaluator.service.serviceimpl.UserDetailsImpl;

 @RestController
 @CrossOrigin(origins = "* a")
 @RequestMapping("/auth")
 public class AuthController {
 	@Autowired
 	AuthenticationManager authenticationManager;

 	@Autowired
 	UserRepository userRepository;

 	@Autowired
 	RoleRepository roleRepository;

 	@Autowired
 	PasswordEncoder encoder;

 	@Autowired
 	JwtUtils jwtUtils;

 	@PostMapping("/signin")
 	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

 		Authentication authentication = authenticationManager.authenticate(
 				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

 		SecurityContextHolder.getContext().setAuthentication(authentication);
 		String jwt = jwtUtils.generateJwtToken(authentication);
		
 		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
 		List<String> roles = userDetails.getAuthorities().stream()
 				.map(item -> item.getAuthority())
 				.collect(Collectors.toList());

 		return ResponseEntity.ok(new JwtResponse(jwt,
 												 userDetails.getId(),
 												 userDetails.getUsername(),
 												 userDetails.getEmail(),
 												 roles));
 	}

 	@PostMapping("/signup")
 	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
 		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
 			return ResponseEntity
 					.badRequest()
 					.body(new MessageResponse("Error: Username is already taken!"));
 		}

 		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
 			return ResponseEntity
 					.badRequest()
 					.body(new MessageResponse("Error: Email is already in use!"));
 		}

 		User user = User.builder().username(signUpRequest.getUsername())
 				.email(signUpRequest.getEmail())
 				.password(encoder.encode(signUpRequest.getPassword())).build();

 		Set<String> strRoles = signUpRequest.getRole();
 		Set<Role> roles = new HashSet<>();

 		if (strRoles == null) {
 			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
 					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
 			roles.add(userRole);
 		} else {
 			strRoles.forEach(role -> {
 				switch (role) {
 				case "admin":
 					Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
 							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
 					roles.add(adminRole);

 					break;
 				default:
 					Role userRole = roleRepository.findByName(ERole.ROLE_USER)
 							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
 					roles.add(userRole);
 				}
 			});
 		}

 		user.setRoles(roles);
 		userRepository.save(user);

 		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
 	}
 }
