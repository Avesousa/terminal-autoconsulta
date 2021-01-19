package com.imagosur.terminal_autoconsulta.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imagosur.commons.dao.Dao;
import com.imagosur.terminal_autoconsulta.entity.EquipoEntity;
import com.imagosur.terminal_autoconsulta.request.LoginRequest;
import com.imagosur.terminal_autoconsulta.request.SignupRequest;
import com.imagosur.terminal_autoconsulta.response.JwtResponse;
import com.imagosur.terminal_autoconsulta.response.MessageResponse;
import com.imagosur.terminal_autoconsulta.service.UserDetailsImpl;
import com.imagosur.terminal_autoconsulta.service.UserService;
import com.imagosur.terminal_autoconsulta.specification.UserSpecification;
import com.imagosur.terminal_autoconsulta.utils.JwtUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private UserService userService;
	@Autowired
	private Dao<EquipoEntity, String> userRepository;

	@Autowired
	private PasswordEncoder encoder;

	@Autowired
	private JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		System.out.println(loginRequest);
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getTerminal(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getInactivo(), null));
	}

	@PostMapping("/generar-clave")
	public ResponseEntity<?> generarClave(@Valid @RequestBody SignupRequest signUpRequest) {
		UserSpecification spec = new UserSpecification();
		spec.setUsername(signUpRequest.getTerminal());
		try {
			EquipoEntity terminal = userRepository.getOneOrNull(spec);
			if (terminal == null) {
				return ResponseEntity.badRequest()
						.body(new MessageResponse("Error: no existe una terminal con la descripcion indicada!"));
			}
			terminal.setClave(encoder.encode(signUpRequest.getPassword()));
			userService.update(terminal);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: " + e.getMessage()));
		}
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@GetMapping(path = "/testConnection")
	public ResponseEntity<String> testConnection() {
		return ResponseEntity.ok("Activo");
	}
}
