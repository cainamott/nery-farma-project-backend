package com.github.cainamott.nery_farma.impl.pattern.facade;

import com.github.cainamott.nery_farma.impl.client.OrderProcessingClient;
import com.github.cainamott.nery_farma.impl.dto.*;
import com.github.cainamott.nery_farma.impl.enums.MedicationOrderStatus;
import com.github.cainamott.nery_farma.impl.exceptions.MedicationNotFoundException;
import com.github.cainamott.nery_farma.impl.exceptions.OrderNotFoundException;
import com.github.cainamott.nery_farma.impl.exceptions.OrderRejectedException;
import com.github.cainamott.nery_farma.impl.exceptions.OrderStatusTransitionException;
import com.github.cainamott.nery_farma.impl.mappers.MedicationOrderMapper;
import com.github.cainamott.nery_farma.impl.models.Medication;
import com.github.cainamott.nery_farma.impl.models.MedicationOrder;
import com.github.cainamott.nery_farma.impl.repository.MedicationOrderRepository;
import com.github.cainamott.nery_farma.impl.service.MedicationService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.UUID;

@Component
@AllArgsConstructor
public class OrderFulfillmentFacade {

    private final MedicationService medicationService;
    private final MedicationOrderRepository orderRepository;
    private final MedicationOrderMapper orderMapper;
    private final OrderProcessingClient orderProcessingClient;

    public MedicationOrderDTO createNewOrder(MedicationOrderRequest requestDTO) {
        Medication medication = medicationService.findById(requestDTO.medicationId())
                .orElseThrow(MedicationNotFoundException::new);

        MedicationOrderResponse externalResponse = orderProcessingClient.sendOrder(requestDTO);

        if ("REJECTED".equals(externalResponse.status())) {
            throw new OrderRejectedException(externalResponse.message());
        }

        MedicationOrder order = orderMapper.mapToEntity(requestDTO, medication, MedicationOrderStatus.PENDING);
        orderRepository.save(order);

        return orderMapper.mapToDto(order);
    }

    public UpdateOrderResponse updateOrderStatus(UpdateOrderRequest updateRequest) {
        MedicationOrder order = findOrderOrThrow(updateRequest.orderId());

        applyStatusTransition(order, MedicationOrderStatus.fromApi(updateRequest.orderStatus()));
        updateEstimatedDeliveryDate(order);
        order.setUpdatedAt(LocalDate.now());

        return buildUpdateResponse(updateRequest.orderId(), order);
    }

    public MovementDTO receiveOrder(OrderReceivementRequest receivementRequest) {
        return null;
    }

    private void applyStatusTransition(MedicationOrder order, MedicationOrderStatus newStatus) {
        if (MedicationOrderStatus.PENDING.equals(newStatus)) {
            throw new OrderStatusTransitionException("Pedido não pode ser atualizado para pendente!");
        }

        if (MedicationOrderStatus.DONE.equals(order.getStatus())
                && MedicationOrderStatus.APPROVED.equals(newStatus)) {
            throw new OrderStatusTransitionException(
                    "Pedido não pode voltar ao status de aprovado pois já está completo");
        }

        if (MedicationOrderStatus.PENDING.equals(order.getStatus())
                && MedicationOrderStatus.DONE.equals(newStatus)) {
            throw new OrderStatusTransitionException(
                    "Pedido não pode ser concluído pois ainda não foi aceito");
        }

        order.setStatus(newStatus);
    }

    private void updateEstimatedDeliveryDate(MedicationOrder order) {
        if (order.getRequestedQuantity() > 1000) {
            order.setEstimatedDeliveryDate(LocalDate.now().plusMonths(1));
        } else {
            order.setEstimatedDeliveryDate(LocalDate.now().plusWeeks(2));
        }
    }

    private MedicationOrder findOrderOrThrow(UUID orderId) {
        return orderRepository.findById(orderId)
                .orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    private UpdateOrderResponse buildUpdateResponse(UUID orderId, MedicationOrder order) {
        return UpdateOrderResponse.builder()
                .orderId(orderId)
                .newEstimatedDate(order.getEstimatedDeliveryDate())
                .status(order.getStatus().toApi())
                .message("Dados do pedido Atualizados")
                .build();
    }
}
