package ua.kpi.iasa.web.lab3.mapping.populator;

import lombok.NonNull;

public interface Populator<Model, Data> {
    Model populate(@NonNull Data data, @NonNull Model model);
}
