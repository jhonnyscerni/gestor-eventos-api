package br.jus.tre_pa.seven.domain;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DateTimeSerialize extends JsonSerializer<Date> {

	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		LocalDateTime dataTrecho = value.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
		
		gen.writeStartObject();
		gen.writeStringField("data", dataTrecho.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")).toString());
		gen.writeStringField("hora", dataTrecho.format(DateTimeFormatter.ofPattern("HH:mm")).toString());

		gen.writeEndObject();

	}

}
