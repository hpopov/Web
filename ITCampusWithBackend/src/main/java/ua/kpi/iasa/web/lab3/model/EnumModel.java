package ua.kpi.iasa.web.lab3.model;

public interface EnumModel<T extends Enum<T> & EnumType<T>> {
    Integer getPk();

    T getValue();

    void setPk(Integer Pk);

    void setValue(T value);
}
