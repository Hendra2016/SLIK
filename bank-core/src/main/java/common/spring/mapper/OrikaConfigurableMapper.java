package common.spring.mapper;

import java.util.Map;

import ma.glasnost.orika.Converter;
import ma.glasnost.orika.Mapper;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
@Component
public class OrikaConfigurableMapper extends ConfigurableMapper {

	private ApplicationContext applicationContext;

	private MapperFactory mapperFactory;

	public OrikaConfigurableMapper() {
		super(false);
	}

	@Autowired
	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
		super.init();
	}

	@Override
	protected void configure(MapperFactory factory) {
		super.configure(factory);
		this.mapperFactory = factory;
		addSpringMappers();
		addSpringConverter();
	}

	private void addSpringMappers() {
		@SuppressWarnings("rawtypes")
		final Map<String, Mapper> mappers = applicationContext
				.getBeansOfType(Mapper.class);
		for (final Mapper<?, ?> mapper : mappers.values()) {
			addMapper(mapper);
		}
	}

	private void addMapper(Mapper<?, ?> mapper) {
		this.mapperFactory.registerMapper(mapper);
	}

	private void addSpringConverter() {
		@SuppressWarnings("rawtypes")
		final Map<String, Converter> converters = applicationContext
				.getBeansOfType(Converter.class);
		for (final Converter<?, ?> converter : converters.values()) {
			addConverter(converter);
		}
	}

	private void addConverter(Converter<?, ?> converter) {
		this.mapperFactory.getConverterFactory().registerConverter(converter);
	}

	public MapperFactory getMapperFactory() {
		return mapperFactory;
	}

	public void setMapperFactory(MapperFactory mapperFactory) {
		this.mapperFactory = mapperFactory;
	}

}