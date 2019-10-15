package ua.kpi.iasa.web.lab3.mapping.converter;

import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface Converter<MODEL, DATA>
        extends DataToModelConverter<MODEL, DATA>, ModelToDataConverter<MODEL, DATA> {


    static <T_FROM, T_TO> List<T_TO> mapToList(Function<T_FROM, T_TO> mapper, Iterable<T_FROM> iterable) {
        return ConverterSupport.streamOf(iterable).map(mapper).collect(Collectors.toList());
    }

    static <T_FROM, T_TO extends Enum<T_TO>> EnumSet<T_TO> mapToEnumSet(Function<T_FROM, T_TO> mapper,
            Iterable<T_FROM> iterable, Class<T_TO> enumClass) {
        return ConverterSupport.streamOf(iterable).map(mapper)
                .collect(Collectors.toCollection(() -> EnumSet.noneOf(enumClass)));
    }

    static <T_FROM, T_TO> Set<T_TO> mapToSet(Function<T_FROM, T_TO> mapper, Iterable<T_FROM> iterable) {
        return ConverterSupport.streamOf(iterable).map(mapper).collect(Collectors.toSet());
    }

    static <T_FROM, T_TO, T_TO_KEY> Map<T_TO_KEY, T_TO> mapToMap(Iterable<T_FROM> iterable,
            Function<T_FROM, T_TO_KEY> keyMapper, Function<T_FROM, T_TO> valueMapper) {
        return ConverterSupport.streamOf(iterable).collect(Collectors.toMap(keyMapper, valueMapper));
    }
}
