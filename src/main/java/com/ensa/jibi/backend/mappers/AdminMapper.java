package com.ensa.jibi.backend.mappers;

import com.ensa.jibi.backend.domain.dto.AdminDto;
import com.ensa.jibi.backend.domain.entities.Admin;
import com.ensa.jibi.cmi.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper implements Mapper<Admin, AdminDto> {
    private final ModelMapper modelMapper;

    public AdminMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
    @Override
    public AdminDto mapTo(Admin admin) {
        return modelMapper.map(admin, AdminDto.class);
    }

    @Override
    public Admin mapFrom(AdminDto adminDto) {
        return modelMapper.map(adminDto, Admin.class);
    }
}
