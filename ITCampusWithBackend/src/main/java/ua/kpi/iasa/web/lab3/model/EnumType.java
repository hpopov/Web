package ua.kpi.iasa.web.lab3.model;

public interface EnumType<T extends Enum<T> & EnumType<T>> {

    <M extends EnumModel<T>> M toEmptyModel(T type);
}
