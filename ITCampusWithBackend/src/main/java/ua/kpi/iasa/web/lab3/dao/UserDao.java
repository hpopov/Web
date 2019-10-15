package ua.kpi.iasa.web.lab3.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.kpi.iasa.web.lab3.model.UserModel;

@Repository
public interface UserDao extends CrudRepository<UserModel, Integer> {

    Optional<UserModel> findByUsername(String username);

    @Query(value = "select u from UserModel u join fetch u.userDetails")
    Optional<UserModel> findByUsernameFetchUserDetailsModel(String username);

    @Query(value = "select u from UserModel u left join fetch u.projects")
    Optional<UserModel> findByUsernameFetchProjects(String username);

    List<UserModel> findAll();
}