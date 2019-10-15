package ua.kpi.iasa.web.lab3.mapping.converter;

import lombok.NonNull;

public interface DataToModelConverter<Model, Data> {
    Model dataToModel(@NonNull Data data);
}
