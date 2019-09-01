package ua.kpi.iasa.web.lab3.converter;

import java.util.Collection;
import java.util.EnumSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public interface Converter<MODEL, DATA> extends DataToModelConverter<MODEL, DATA> {
	
	DATA modelToData(MODEL model);
	MODEL populateDataToModel(MODEL model, DATA data);
	
	@Override
	default MODEL dataToModel(DATA data) {
		return populateDataToModel(null, data);
	}
	
	static <T_FROM, T_TO> List<T_TO> mapCollectionToList(Function<T_FROM, T_TO> mapper,
			Collection<T_FROM> collection) {
		return collection.stream().map(mapper).collect(Collectors.toList());
	}
	
//	static <T_FROM, T_TO extends Enum<T_TO>> EnumSet<T_TO> mapCollectionToEnumSet(Function<T_FROM, T_TO> mapper,
//			Collection<T_FROM> collection) {
//		 return EnumSet.copyOf(mapCollectionToSet(mapper, collection));
//	}
	
	static <T_FROM, T_TO extends Enum<T_TO>> EnumSet<T_TO> mapCollectionToEnumSet(Function<T_FROM, T_TO> mapper,
			Collection<T_FROM> collection, Class<T_TO> enumClass) {
		return collection.stream().map(mapper).collect(Collectors.toCollection(()-> EnumSet.noneOf(enumClass)));
	}
	
	static <T_FROM, T_TO> Set<T_TO> mapCollectionToSet(Function<T_FROM, T_TO> mapper,
			Collection<T_FROM> collection) {
		return collection.stream().map(mapper).collect(Collectors.toSet());
	}
	
	static <T_FROM, T_TO, T_TO_KEY> Map<T_TO_KEY,T_TO> mapCollectionToMap(Collection<T_FROM> collection,
			Function<T_FROM, T_TO_KEY> keyMapper,
			Function<T_FROM, T_TO> valueMapper) {
		return collection.stream().collect(Collectors.toMap(keyMapper, valueMapper));
	}
		
}
