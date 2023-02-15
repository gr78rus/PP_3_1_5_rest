package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.RoleDaoImp;
import ru.kata.spring.boot_security.demo.dao.UserDaoImp;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {

    private final UserDaoImp userDao;
    private final RoleDaoImp roleDao;

    public UserServiceImp(UserDaoImp userDao, RoleDaoImp roleDao) {
        this.userDao = userDao;
        this.roleDao = roleDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    public List<Role> getAllRoles() {
        return roleDao.getAllRoles();
    }

    @Override
    public User findUserById(long id) {
        return userDao.findUserById(id);
    }

    @Override
    public User findUserByName(String name) {
        return userDao.findUserByName(name);
    }

    @Override
    public Role findRoleById(long id) {
        return roleDao.findRoleById(id);
    }

    @Override
    public Role findRoleByName(String name) {
        return roleDao.findRoleByName(name);
    }

    @Override
    @Transactional
    public void saveUser(User user) {

        if (user.getId() == null) {
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        } else if (!userDao.findUserById(user.getId()).getPassword().equals(user.getPassword())) {
            user.setPassword(BCrypt.hashpw(user.getPassword(), BCrypt.gensalt()));
        }
        List<Role> rolesDB = new ArrayList<>();
        List<String> stringList = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());

        for (String s: stringList) {
            rolesDB.add(findRoleByName(s));
        }

        user.setRoles(rolesDB);

        if (user.getId() == null) {
            userDao.createUser(user);
        } else {
            userDao.updateUser(user);
        }
    }

    @Override
    @Transactional
    public void deleteUser(long id) {
        userDao.deleteUserById(id);
    }

    @Override
    @Transactional
    public void createRole(Role role) {
        roleDao.createRole(role);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = findUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {
        return roles.stream().map(r -> new SimpleGrantedAuthority(r.getName())).collect(Collectors.toList());
    }
}