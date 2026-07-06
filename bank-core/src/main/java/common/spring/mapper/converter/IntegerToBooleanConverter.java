package common.spring.mapper.converter;

import ma.glasnost.orika.MappingContext;
import ma.glasnost.orika.converter.BidirectionalConverter;
import ma.glasnost.orika.metadata.Type;
import org.springframework.stereotype.Component;

@Component
public class IntegerToBooleanConverter extends BidirectionalConverter<Integer, Boolean> {

    @Override
    public Boolean convertTo(Integer source, Type<Boolean> destinationType, MappingContext mappingContext) {
        if (source == null) {
            return null;
        }
        return source != 0;
    }

    @Override
    public Integer convertFrom(Boolean source, Type<Integer> destinationType, MappingContext mappingContext) {
        if (source == null) {
            return null;
        }
        return source ? 1 : 0;
    }
}