package ua.kpi.iasa.web.lab3.mapping.converter;

import lombok.NonNull;

public interface ModelToDataConverter<Model, Data> {
	Data modelToData(@NonNull Model model);
}
