package com.github.cainamott.nery_farma.impl.service;

import com.github.cainamott.nery_farma.impl.client.OrderProcessingClient;
import com.github.cainamott.nery_farma.impl.dto.*;
import com.github.cainamott.nery_farma.impl.enums.MedicationOrderStatus;
import com.github.cainamott.nery_farma.impl.mappers.MedicationOrderMapper;
import com.github.cainamott.nery_farma.impl.models.Medication;
import com.github.cainamott.nery_farma.impl.models.MedicationOrder;
import com.github.cainamott.nery_farma.impl.repository.MedicationOrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MedicationOrderService {

    private final MedicationService medicationService;
    private final MedicationOrderRepository orderRepository;
    private final MedicationOrderMapper orderMapper;
    private final OrderProcessingClient orderProcessingClient;

    public MedicationOrderDTO createNewOrder(MedicationOrderRequest requestDTO) {

        Optional<Medication> medication = medicationService.findById(requestDTO.medicationId());

        if (medication.isEmpty()) {
            throw new RuntimeException("Medicamento não encontrado na base de dados!");
        }

        // calls requester to wire mock

        MedicationOrderResponse medicationOrderResponse = orderProcessingClient.sendOrder(requestDTO);

        if(medicationOrderResponse.status().equals("REJECTED")) {

            throw new RuntimeException("Pedido Rejeitado! " + medicationOrderResponse.message());
        }

        var medicationOrder = orderMapper.mapToEntity(requestDTO, medication.get(), MedicationOrderStatus.PENDING);
        orderRepository.save(medicationOrder);
        return orderMapper.mapToDto(medicationOrder);

        // No fluxo normal, salva a order como pendente de aprovação e depois espera por uma response de outro enpoint
        // que poderia mudar o status ou um proprio webhook da nossa aplicacao. Para testes é apenas feito um POST para
        // o endpoint de update simulando a resposta de outro serviço

    }

    public UpdateOrderResponse receiveCargoMovementUpdate(UpdateOrderRequest updateRequest) {

        MedicationOrder medicationOrder = validateOrder(updateRequest.orderId());

        updateOrderStatus(medicationOrder, MedicationOrderStatus.fromApi(updateRequest.orderStatus()));
        updateEstimatedDeliveryDate(medicationOrder);
        medicationOrder.setUpdatedAt(LocalDate.now());

        return UpdateOrderResponse
                .builder()
                .orderId(updateRequest.orderId())
                .newEstimatedDate(medicationOrder.getEstimatedDeliveryDate())
                .status(medicationOrder.getStatus().toApi())
                .message("Dados do pedido Atualizados")
                .build();
        // request de update de cargo com a situação atual e qual a atualização
        // basicamente update em banco
    }

    private void updateOrderStatus(MedicationOrder order, MedicationOrderStatus status) {

        if(status.equals(MedicationOrderStatus.PENDING)) {
            throw new RuntimeException("Pedido não pode ser atualizado para pendente!");
        } else if (order.getStatus().equals(MedicationOrderStatus.DONE) && status.equals(MedicationOrderStatus.APPROVED)) {
            throw new RuntimeException("Pedido não pode voltar ao status de aprovado pois já está completo");
        } else if (order.getStatus().equals(MedicationOrderStatus.PENDING) && status.equals(MedicationOrderStatus.DONE) ) {
            throw new RuntimeException("Pedido não pode ser concluído pois ainda não foi aceito");
        }
        order.setStatus(status);
    }

    private void updateEstimatedDeliveryDate(MedicationOrder order){

        // lógica simples de update apenas para utilizar nos testes, se + 1000 itens requeridos, aumenta 1 mes,
        // se menos de 1000, aumenta 2 semanas

        if(order.getRequestedQuantity() > 1000) {
             order.setEstimatedDeliveryDate(LocalDate.now().plusMonths(1)); // + 1 mes
        } else {
            order.setEstimatedDeliveryDate(LocalDate.now().plusWeeks(2)); // 2 semanas
        }
    }

    private MedicationOrder validateOrder(UUID orderId) {
        MedicationOrder order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado: " + orderId));

        return order;
    }

    public MovementDTO receiveOrder(OrderReceivementRequest receivementRequest) {

        // recebe request

        // buscar pela order da request

        // checar o estado da order (se estiver ok vai para o prox passo)

        // update para concluido

        // gera entrada de estoque com estado intermediario esperando conferencia

        return null;
    }

    private Optional<MedicationOrder> findByOrderId(UUID id) {

        return orderRepository.findById(id);
    }


}
