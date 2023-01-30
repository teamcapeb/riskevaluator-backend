 package fr.capeb.backend.riskevaluator.security.jwt;

 import fr.capeb.backend.riskevaluator.service.serviceimpl.UserDetailsServiceImpl;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
 import org.springframework.security.core.context.SecurityContextHolder;
 import org.springframework.security.core.userdetails.UserDetails;
 import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
 import org.springframework.util.StringUtils;
 import org.springframework.web.filter.OncePerRequestFilter;

 import javax.servlet.FilterChain;
 import javax.servlet.ServletException;
 import javax.servlet.http.HttpServletRequest;
 import javax.servlet.http.HttpServletResponse;
 import java.io.IOException;

 public class AuthTokenFilter extends OncePerRequestFilter {
   @Autowired
   private JwtUtils jwtUtils;

   @Autowired
   private UserDetailsServiceImpl userDetailsService;

   private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

   @Override
   protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
       throws ServletException, IOException {


     response.setHeader("Access-Control-Allow-Origin", "*");
     response.setHeader("Access-Control-Allow-Credentials", "true");
     response.setHeader("Access-Control-Allow-Methods",
             "ACL, CANCELUPLOAD, CHECKIN, CHECKOUT, COPY, DELETE, GET, HEAD, LOCK, MKCALENDAR, MKCOL, MOVE, OPTIONS, POST, PROPFIND, PROPPATCH, PUT, REPORT, SEARCH, UNCHECKOUT, UNLOCK, UPDATE, VERSION-CONTROL");
     response.setHeader("Access-Control-Max-Age", "3600");
     response.setHeader("Access-Control-Allow-Headers",
             "Origin, X-Requested-With, Content-Type, Accept, Key, Authorization");



     try {

       String jwt = parseJwt(request);
       if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
         String username = jwtUtils.getUserNameFromJwtToken(jwt);

         UserDetails userDetails = userDetailsService.loadUserByUsername(username);
         UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null,
             userDetails.getAuthorities());
         authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

         SecurityContextHolder.getContext().setAuthentication(authentication);
       }
     } catch (Exception e) {
       logger.error("Cannot set user authentication: {}", e);
     }

     if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
       response.setStatus(HttpServletResponse.SC_OK);
     } else {
       filterChain.doFilter(request, response);
     }
   }

   private String parseJwt(HttpServletRequest request) {
     String headerAuth = request.getHeader("Authorization");

     if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
       return headerAuth.substring(7, headerAuth.length());
     }

     return null;
   }
 }
