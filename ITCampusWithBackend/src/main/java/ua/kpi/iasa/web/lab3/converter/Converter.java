package ua.kpi.iasa.web.lab3.converter;

public interface Converter<MODEL, DATA> {
	DATA modelToData(MODEL model);
	MODEL dataToModel(DATA data);
}
