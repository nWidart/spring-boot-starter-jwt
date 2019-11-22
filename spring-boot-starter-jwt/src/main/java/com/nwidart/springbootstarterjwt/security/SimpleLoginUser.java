package com.nwidart.springbootstarterjwt.security;

import com.nwidart.springbootstarterjwt.entity.User;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

public class SimpleLoginUser extends org.springframework.security.core.userdetails.User {

  private static final List<GrantedAuthority> USER_ROLES = AuthorityUtils.createAuthorityList("ROLE_USER");
  private static final List<GrantedAuthority> ADMIN_ROLES = AuthorityUtils
      .createAuthorityList("ROLE_ADMIN", "ROLE_USER");
  private User user;

  public SimpleLoginUser(User user) {
    super(user.getName(), user.getPassword(), determineRoles(user.getAdmin()));
    this.user = user;
  }

  private static List<GrantedAuthority> determineRoles(boolean isAdmin) {
    return isAdmin ? ADMIN_ROLES : USER_ROLES;
  }

  public User getUser() {
    return user;
  }
}
