package com.aminenurgynk.mapper;

import com.aminenurgynk.dto.request.RegisterRequestDto;
import com.aminenurgynk.dto.response.RegisterResponseDto;
import com.aminenurgynk.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {

    IAuthMapper INTANCE = Mappers.getMapper(IAuthMapper.class);

    Auth toAuth(RegisterRequestDto dto);

    RegisterResponseDto toRegisterResponseDto(Auth auth);
}
