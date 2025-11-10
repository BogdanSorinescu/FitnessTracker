package com.example.fitTrackBackend.Service;

import com.example.fitTrackBackend.Repo.UserRepo;
import com.example.fitTrackBackend.dto.LoginUserDto;
import com.example.fitTrackBackend.dto.RegisterUserDto;
import com.example.fitTrackBackend.dto.VerifyUserDto;
import jakarta.mail.MessagingException;
import org.springframework.security.authentication.AuthenticationManager;
import com.example.fitTrackBackend.Model.UserAccounts;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
public class AuthenticationService {
    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    private final EmailService emailService;

    public AuthenticationService(
            UserRepo userRepo,
            PasswordEncoder passwordEncoder,
            AuthenticationManager authenticationManager,
            EmailService emailService

    ){
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.emailService = emailService;

    }
    public UserAccounts signup(RegisterUserDto input){
        UserAccounts user = new UserAccounts(
                input.getUsername(),
                passwordEncoder.encode(input.getPassword()),
                input.getEmail());
                user.setVerificationCode(generateVerificationCode());
                user.setVerificationExpired(LocalDateTime.now().plusMinutes(15));
                user.setEnabled(false);
                sendVerificationEmail(user);
                return userRepo.save(user);


    }

    public UserAccounts authenticate(LoginUserDto input){
        UserAccounts user = userRepo.findByEmail(input.getEmail())
                .orElseThrow(()-> new RuntimeException("User doesn't exist"));
        if (!user.isEnabled()){
            throw new RuntimeException("Not yet verified, please verify your account");
        }
        authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                      input.getEmail(),
                      input.getPassword()
              )
        );
        return user;
    }

    public void verifyUser(VerifyUserDto input){
        Optional<UserAccounts> optionalUser = userRepo.findByEmail(input.getEmail());
        if (optionalUser.isPresent()){
            UserAccounts user = optionalUser.get();
            if (user.getVerificationExpired().isBefore(LocalDateTime.now())){
                throw new RuntimeException("Verification code has expired");
            }
            if (user.getVerificationCode().equals(input.getVerificationCode())){
                user.setEnabled(true);
                user.setVerificationCode(null);
                user.setVerificationExpired(null);
                userRepo.save(user);
            }else{
                throw new RuntimeException("Invalid verification code");
            }

            }else{
                throw new RuntimeException("User not found");
        }
    }

    public void resendVerificationCode(String email){
        Optional<UserAccounts> optionalUser = userRepo.findByEmail(email);
        if (optionalUser.isPresent()){
            UserAccounts user = optionalUser.get();
            if (user.isEnabled()){
                throw new RuntimeException("Account is already verified");

            }
            user.setVerificationCode(generateVerificationCode());
            user.setVerificationExpired(LocalDateTime.now().plusHours(1));
            sendVerificationEmail(user);
            userRepo.save(user);
        }else {
            throw new RuntimeException("User count not be found");
        }

    }
    public void sendVerificationEmail(UserAccounts user){
        String subject = "Account verification";
        String verificationCode = user.getVerificationCode();
        String htmlMessage = "<html>"
                + "<body style=\"margin: 0; padding: 0; font-family: -apple-system, BlinkMacSystemFont, 'Segoe UI', Roboto, 'Helvetica Neue', Arial, sans-serif;\">"
                + "<div style=\"background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); padding: 40px 20px; min-height: 100vh;\">"
                + "<div style=\"max-width: 500px; margin: 0 auto;\">"

                // Main Card
                + "<div style=\"background: rgba(255, 255, 255, 0.95); border-radius: 24px; padding: 40px 32px; box-shadow: 0 20px 60px rgba(0, 0, 0, 0.3), 0 0 1px rgba(255, 255, 255, 0.5) inset; backdrop-filter: blur(10px); border: 1px solid rgba(255, 255, 255, 0.2);\">"

                // Header
                + "<div style=\"text-align: center; margin-bottom: 32px;\">"
                + "<h2 style=\"color: #1a1a1a; font-size: 28px; font-weight: 600; margin: 0 0 12px 0; letter-spacing: -0.5px;\">Welcome!</h2>"
                + "<p style=\"color: #666; font-size: 16px; margin: 0; line-height: 1.5;\">Please enter the verification code below to continue</p>"
                + "</div>"

                // Code Card
                + "<div style=\"background: linear-gradient(135deg, #667eea 0%, #764ba2 100%); border-radius: 16px; padding: 32px 24px; text-align: center; box-shadow: 0 10px 30px rgba(102, 126, 234, 0.4); margin-bottom: 24px;\">"
                + "<p style=\"color: rgba(255, 255, 255, 0.9); font-size: 13px; font-weight: 500; margin: 0 0 8px 0; text-transform: uppercase; letter-spacing: 1px;\">Verification Code</p>"
                + "<p style=\"color: #ffffff; font-size: 36px; font-weight: 700; margin: 0; letter-spacing: 8px; font-family: 'Courier New', monospace;\">"
                + verificationCode
                + "</p>"
                + "</div>"

                // Footer Info
                + "<div style=\"text-align: center; padding-top: 24px; border-top: 1px solid rgba(0, 0, 0, 0.06);\">"
                + "<p style=\"color: #999; font-size: 13px; margin: 0; line-height: 1.6;\">This code will expire in 10 minutes<br/>For security, never share this code with anyone</p>"
                + "</div>"
                + "</div>"

                // Bottom Text
                + "<p style=\"text-align: center; color: rgba(255, 255, 255, 0.8); font-size: 12px; margin-top: 24px;\">If you didn't request this code, please ignore this email</p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";
        try {
            emailService.sendVerificationEmail(user.getEmail(), subject, htmlMessage);
        }catch (MessagingException e){
            e.printStackTrace();
        }
    }
    private String generateVerificationCode(){
        Random random = new Random();
        int code = random.nextInt(900000) + 100000;
        return String.valueOf(code);

    }
}
