package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class RoleDaoImp implements RoleDao{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Role> getAllRoles() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }

    @Override
    public Role findRoleById(long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role findRoleByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery(
                "SELECT x FROM Role x WHERE x.name = :name", Role.class);
        query.setParameter("name", name);
        return query.getSingleResult();
    }

    @Override
    public void createRole(Role role) {
        entityManager.persist(role);
    }
}
