package br.jus.tre_pa.seven.domain;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;


public class DateTimeDeserialize extends JsonDeserializer<Date>{


	@Override
	public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		
		
		JsonNode node = p.getCodec().readTree(p);
		
		String dtValue = (String) ((TextNode)node.get("data")).asText();
		String hora = (String) ((TextNode)node.get("hora")).asText();
		
		
		LocalDateTime data = LocalDateTime.parse(dtValue.split("T")[0] + " " + hora, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
		
		
		return Date.from(data.atZone(ZoneId.systemDefault()).toInstant());
	}

}
