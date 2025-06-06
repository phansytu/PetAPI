package com.example.Pet.Security;

import com.example.Pet.Entity.Admin;
import com.example.Pet.Entity.User;
import com.example.Pet.Reponsitories.AdminRepo;
import com.example.Pet.Reponsitories.UserRepo;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Component
public class JwtTokenFilter extends OncePerRequestFilter {

    @Autowired private JwtTokenHelper jwtTokenHelper;
    @Autowired private UserRepo userRepo;
    @Autowired private AdminRepo adminRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws ServletException, IOException {
        String header = req.getHeader("Authorization");
        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            if (jwtTokenHelper.validateToken(token)) {
                String email = jwtTokenHelper.getSubjectFromToken(token);
                //ưu tiên kiểm tra admin trước
                Optional<Admin> adminOtp =adminRepo.findByEmail(email);
                if(adminOtp.isPresent()){
                    Admin admin = adminOtp.get();
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(
                            admin, null, List.of(new SimpleGrantedAuthority("ROLE_ADMIN"))

                    );
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }

                String phone = jwtTokenHelper.getPhoneNumberFromToken(token);
                Optional<User> userOpt = userRepo.findByPhoneNumber(phone);
                if (userOpt.isPresent()) {
                    User user = userOpt.get();
                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(user, null, List.of(new SimpleGrantedAuthority("ROLE_CUSTOMER")));
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        }
        chain.doFilter(req, res);
    }
    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        return path.startsWith("/api/auth/");
    }
}
