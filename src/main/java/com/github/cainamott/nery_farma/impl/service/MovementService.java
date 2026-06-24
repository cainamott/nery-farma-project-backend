package com.github.cainamott.nery_farma.impl.service;

import com.github.cainamott.nery_farma.impl.dto.BatchDTO;
import com.github.cainamott.nery_farma.impl.dto.StockDispatchDTO;
import com.github.cainamott.nery_farma.impl.dto.StockEntryDTO;
import com.github.cainamott.nery_farma.impl.pattern.facade.StockManagementFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MovementService {

    private final StockManagementFacade stockManagementFacade;

    public BatchDTO stockEntry(StockEntryDTO entryDTO) {
        return stockManagementFacade.registerStockEntry(entryDTO);
    }

    public BatchDTO stockDispatch(StockDispatchDTO dispatchDTO) {
        return stockManagementFacade.registerStockDispatch(dispatchDTO);
    }
}
