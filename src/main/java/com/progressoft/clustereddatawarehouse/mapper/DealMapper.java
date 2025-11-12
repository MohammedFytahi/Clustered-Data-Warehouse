package com.progressoft.clustereddatawarehouse.mapper;

import com.progressoft.clustereddatawarehouse.dto.DealRequestDto;
import com.progressoft.clustereddatawarehouse.dto.DealResponseDto;
import com.progressoft.clustereddatawarehouse.model.Deal;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DealMapper {

    Deal toEntity(DealRequestDto dto);

    DealResponseDto toDto(Deal entity);


}
