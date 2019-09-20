package com.kaminskyi.helpdesk.mapper;

import com.kaminskyi.helpdesk.entity.CustomUserDetails;
import com.kaminskyi.helpdesk.entity.Users;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.ldap.userdetails.LdapUserDetailsMapper;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import java.util.Collection;

public class CustomUserMapper extends LdapUserDetailsMapper implements AttributesMapper {

    @Override
    public Object mapFromAttributes(Attributes attributes) throws NamingException {
        Users user = new Users();
        Attribute login = attributes.get("sAMAccountName");
        if (login != null) {
            user.setLogin((String) login.get());
        }

        return user;
    }

    @Override
    public CustomUserDetails mapUserFromContext(DirContextOperations ctx, String username, Collection<? extends GrantedAuthority> authorities) {

        UserDetails details = super.mapUserFromContext(ctx, username, authorities);

        CustomUserDetails customUserDetails = new CustomUserDetails();
        customUserDetails.setMail(ctx.getStringAttribute("mail"));
        customUserDetails.setFullName(ctx.getStringAttribute("cn"));
        customUserDetails.setLogin(ctx.getStringAttribute("sAMAccountName"));
        customUserDetails.setDepartament(ctx.getStringAttribute("department"));
        customUserDetails.setPosition(ctx.getStringAttribute("title"));
        customUserDetails.setPhoneNumber(ctx.getStringAttribute("telephoneNumber"));
        return customUserDetails;
    }
}
