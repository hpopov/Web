package ua.kpi.iasa.web.lab3.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ua.kpi.iasa.web.lab3.model.UserModel;

@Repository
public interface UserDao extends CrudRepository<UserModel, Integer> {

    Optional<UserModel> findByUsername(String username);

    @Query(value = "select u from UserModel u left join fetch u.userDetails where u.username = :username")
    Optional<UserModel> findByUsernameFetchUserDetailsModel(@Param(value = "username") String username);

    @Query(value = "select u from UserModel u left join fetch u.projects where u.username = :username")
    Optional<UserModel> findByUsernameFetchProjects(String username);

    List<UserModel> findAll();
}