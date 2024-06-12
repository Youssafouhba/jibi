package com.ensa.jibi.cmi.services.impl;

import com.ensa.jibi.cmi.domain.dto.creanceDto.RechargeDto;
import com.ensa.jibi.cmi.mappers.impl.RechargeMapper;
import com.ensa.jibi.cmi.repositories.RechargeRepository;
import com.ensa.jibi.cmi.services.RechargeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RechargeServiceImpl implements RechargeService {
    private final RechargeRepository rechargeRepository;
    private final RechargeMapper rechargeMapper;

    @Autowired
    public RechargeServiceImpl(RechargeRepository rechargeRepository, RechargeMapper rechargeMapper) {
        this.rechargeRepository = rechargeRepository;
        this.rechargeMapper = rechargeMapper;
    }

    @Override
    public List<RechargeDto> getAllRecharges() {
        return rechargeRepository.findAll().stream()
                .map(rechargeMapper::mapTo)
                .collect(Collectors.toList());
    }

    @Override
    public RechargeDto getRecharge(Long id) {
        return rechargeMapper.mapTo(rechargeRepository.findById(id).orElse(null));
    }

    @Override
    public RechargeDto createRecharge(RechargeDto rechargeDto) {
        RechargeDto createdRechargeDto = rechargeMapper.mapTo(rechargeRepository.save(rechargeMapper.mapFrom(rechargeDto)));
        return createdRechargeDto;
    }

    @Override
    public RechargeDto updateRecharge(Long id, RechargeDto rechargeDto) {
        if (rechargeRepository.existsById(id)) {
            rechargeDto.setId(id);
            RechargeDto updatedRechargeDto = rechargeMapper.mapTo(rechargeRepository.save(rechargeMapper.mapFrom(rechargeDto)));
            return updatedRechargeDto;
        } else {
            return null;
        }
    }

    @Override
    public void deleteRecharge(Long id) {
        rechargeRepository.deleteById(id);
    }
}
