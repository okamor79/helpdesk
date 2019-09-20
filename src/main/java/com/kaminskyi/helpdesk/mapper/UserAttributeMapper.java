package com.kaminskyi.helpdesk.mapper;

import com.kaminskyi.helpdesk.entity.Users;
import com.kaminskyi.helpdesk.entity.enums.UserRole;
import org.springframework.ldap.core.AttributesMapper;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;

public class UserAttributeMapper implements AttributesMapper {
    @Override
    public Object mapFromAttributes(Attributes attributes) throws NamingException {
        Users user = new Users();

        Attribute fullName = attributes.get("cn");
        if (fullName != null) {
            user.setFullName((String) fullName.get());
        }

        Attribute login = attributes.get("sAMAccountName");
        if (login != null) {
            user.setLogin((String) login.get());
        }

        Attribute mail = attributes.get("mail");
        if (mail != null) {
            user.setMail((String) mail.get());
        }

        user.setRole(UserRole.ROLE_USER);
        user.setLastActivity(null);

        return user;
    }
}
