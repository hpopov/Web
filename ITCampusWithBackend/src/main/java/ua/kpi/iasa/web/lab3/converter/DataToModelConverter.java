package ua.kpi.iasa.web.lab3.converter;

public interface DataToModelConverter<MODEL, DATA> {

	MODEL dataToModel(DATA data);
}
