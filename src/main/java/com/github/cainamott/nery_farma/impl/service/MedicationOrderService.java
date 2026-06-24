package com.github.cainamott.nery_farma.impl.service;

import com.github.cainamott.nery_farma.impl.dto.*;
import com.github.cainamott.nery_farma.impl.dto.*;
import com.github.cainamott.nery_farma.impl.pattern.facade.OrderFulfillmentFacade;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class MedicationOrderService {

    private final OrderFulfillmentFacade orderFulfillmentFacade;

    public MedicationOrderDTO createNewOrder(MedicationOrderRequest requestDTO) {
        return orderFulfillmentFacade.submitNewOrder(requestDTO);
    }

    public UpdateOrderResponse receiveCargoMovementUpdate(UpdateOrderRequest updateRequest) {
        return orderFulfillmentFacade.updateOrderCargoStatus(updateRequest);
    }

    public MovementDTO receiveOrder(OrderReceivementRequest receivementRequest) {
        return orderFulfillmentFacade.receiveOrder(receivementRequest);
    }
}
