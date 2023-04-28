package Redditclone.Practice.Service;

import Redditclone.Practice.Dto.AuthenticationResponse;
import Redditclone.Practice.Dto.LoginRequest;
import Redditclone.Practice.Dto.RefreshTokenRequest;
import Redditclone.Practice.Dto.RegisterRequest;
import Redditclone.Practice.Exception.SpringRedditException;
import Redditclone.Practice.Model.NotificationEmail;
import Redditclone.Practice.Model.User;
import Redditclone.Practice.Model.VerificationToken;
import Redditclone.Practice.Repository.Userrepository;
import Redditclone.Practice.Repository.VerificationTokenRepository;
import Redditclone.Practice.Security.JwtProvider;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;

    private final Userrepository userrepository;

    private final VerificationTokenRepository verificationTokenRepository;

    private final MailService mailService;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    private final RefreshTokenService refreshTokenService;



    @Transactional
    public void signup(RegisterRequest registerRequest){
        User user=new User();
        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setCreated(LocalDate.now());
        user.setEnabled(false);
        userrepository.save(user);

//        Authentication authenticate = authenticationManager
//                .authenticate(new UsernamePasswordAuthenticationToken(registerRequest.getUsername(),
//                        registerRequest.getPassword()));
//        SecurityContextHolder.getContext().setAuthentication(authenticate);
//        String token=jwtProvider.generateToken(authenticate);

        String token=generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Please activate your account ",user.getEmail(),"thank you for signing up to reddit "+"please click on below url to activate your account : "+"http://localhost:8080/api/auth/accountVerification/"+token));
    }

    private String generateVerificationToken(User user){
        String token= UUID.randomUUID().toString();
        VerificationToken verificationToken=new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationTokenRepository.save(verificationToken);
        return token;

    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken=verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(()->new SpringRedditException("invalid token"));
        fetchUserandEnable(verificationToken.get());
    }

    @Transactional
    private void fetchUserandEnable(VerificationToken verificationToken) {
        String username=verificationToken.getUser().getUsername();
       User user= userrepository.findByUsername(username).orElseThrow(()->new SpringRedditException("user not found with name"+username));
       user.setEnabled(true);
       userrepository.save(user);
    }

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),
                        loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        Set<String> roles = authenticate.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenService.generateRefreshToken().getToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(loginRequest.getUsername())
                .roles(roles)
                .build();
    }

    public AuthenticationResponse refreshToken(RefreshTokenRequest refreshTokenRequest) throws Exception {
        refreshTokenService.validateRefreshToken(refreshTokenRequest.getRefreshToken());
        List<SimpleGrantedAuthority> authorities = refreshTokenRequest.getAuthorities().stream()
                .map(SimpleGrantedAuthority::new)
                .toList();
        String token = jwtProvider.generateTokenWithUserName(refreshTokenRequest.getUsername(), authorities);
        return AuthenticationResponse.builder()
                .authenticationToken(token)
                .refreshToken(refreshTokenRequest.getRefreshToken())
                .expiresAt(Instant.now().plusMillis(jwtProvider.getJwtExpirationInMillis()))
                .username(refreshTokenRequest.getUsername())
                .build();
    }
    @Transactional(readOnly = true)

    public User getCurrentUser() {
        Jwt principal = (Jwt) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return userrepository.findByUsername(principal.getSubject())
                .orElseThrow(() -> new UsernameNotFoundException("User name not found - " + principal.getSubject()));
    }

    public boolean isLoggedIn() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return !(authentication instanceof AnonymousAuthenticationToken) && authentication.isAuthenticated();
    }
}
