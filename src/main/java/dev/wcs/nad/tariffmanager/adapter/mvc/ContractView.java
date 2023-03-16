package dev.wcs.nad.tariffmanager.adapter.mvc;

import dev.wcs.nad.tariffmanager.adapter.rest.dto.contract.ContractInfoDto;
import dev.wcs.nad.tariffmanager.mapper.mapstruct.EntityToDtoMapper;
import dev.wcs.nad.tariffmanager.persistence.entity.Contract;
import dev.wcs.nad.tariffmanager.service.ContractService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/public/customer/")
public class ContractView {

    private final ContractService contractService;
    private final EntityToDtoMapper entityToDtoMapper; // customerContractsListView

    public ContractView(ContractService contractService, EntityToDtoMapper entityToDtoMapper) {
        this.contractService = contractService;
        this.entityToDtoMapper = entityToDtoMapper;
    }

    @GetMapping("/{custId}/contracts/view")
    public String viewContractsOfCustomer(Model model, @PathVariable Long custId) {
        model.addAttribute("now", new Date().toInstant());

        // Challenge:
        // 1. Read Contracts for User from Service
        // 2. Map Contract objects to ContractInfoDto objects and add to List
        // 3. Redirect to a new page "customerContractsListView"

        List<ContractInfoDto> contractInfoDtos = new ArrayList<>();
        if (custId >= 0) {// todo : better to test if contract exist
            contractInfoDtos.addAll(
                    entityToDtoMapper.contractsToContractInfosDto(contractService.readContractsForUser(custId)));

            model.addAttribute("contracts", contractInfoDtos);
        }

        return "customerContractsListView";
    }
}
