package com.app.campionsl.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = false)
public class MatchResultConverter implements AttributeConverter<MatchResultDTO, String> {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(MatchResultDTO matchResultDTO) {
        if (matchResultDTO == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsString(matchResultDTO);
        }catch (JsonProcessingException e){
            throw new IllegalArgumentException("Error serializando ResultadoDTO", e);
        }
    }

    @Override
    public MatchResultDTO convertToEntityAttribute(String dbData) {
        if (dbData == null || dbData.isEmpty()) {
            return null;
        }
        try {
            return objectMapper.readValue(dbData, MatchResultDTO.class);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Error deserializando ResultadoDTO", e);
        }
    }
}
