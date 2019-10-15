package ua.kpi.iasa.web.lab3.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import ua.kpi.iasa.web.lab3.model.PersonalInfoModel;

@Repository
public interface PersonalInfoDao extends CrudRepository<PersonalInfoModel, Integer> {

    Optional<PersonalInfoModel> findByUserId(Integer userId);
}
