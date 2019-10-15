package ua.kpi.iasa.web.lab3.dao.base;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

import ua.kpi.iasa.web.lab3.model.EnumModel;
import ua.kpi.iasa.web.lab3.model.EnumType;

@NoRepositoryBean
public interface EnumRepository<T extends Enum<T> & EnumType<T>, EModel extends EnumModel<T>>
        extends Repository<EModel, Integer>, SimpleSaveRepository<EModel, Integer> {

    default EModel getOrCreateEnumModelByType(T type) {
        final Optional<EModel> foundEnumModel = findByValue(type);
        if (foundEnumModel.isPresent()) {
            return foundEnumModel.get();
        } else {
            return save(type.toEmptyModel(type));
        }
    }

    Optional<EModel> findByValue(T value);

    List<EModel> findAll();
}
