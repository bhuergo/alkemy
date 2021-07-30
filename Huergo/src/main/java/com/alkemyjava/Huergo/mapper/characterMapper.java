package com.alkemyjava.Huergo.mapper;

import com.alkemyjava.Huergo.entities.Character;
import com.alkemyjava.Huergo.entities.CharacterDTO;

public class characterMapper {

    public CharacterDTO toDTO(Character character) {
        CharacterDTO cDto = new CharacterDTO();
        cDto.setImage(character.getImage());
        cDto.setName(character.getName());
        return cDto;
    }
}
