package com.asilo.edd.Config;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import java.io.IOException;

public class LoginSuccessHandler
    implements AuthenticationSuccessHandler {

  @Override
  public void onAuthenticationSuccess(
      HttpServletRequest request,
      HttpServletResponse response,
      Authentication auth)
      throws IOException {

    
String rol = auth.getAuthorities()
      .iterator().next()
      .getAuthority();

    
if (rol.equals("ROLE_ADMIN")) {
      response.sendRedirect(
        "/admin/panel");
    } else {
      response.sendRedirect(
        "/usuario/inicio");
    }
  }
}