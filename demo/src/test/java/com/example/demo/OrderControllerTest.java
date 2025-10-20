package com.example.demo;


import com.example.demo.domain.Member;
import com.example.demo.domain.Order;
import com.example.demo.dto.OrderDto;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.service.OrderService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    private OrderDto.CreateRequest createRequest;
    private Order order;
    private Member testMember;

    MemberRepository memberRepository;
    BookRepository bookRepository;

    @BeforeEach
    void setup() {
        testMember = memberRepository.findById(1051L).orElseThrow();

        createRequest = new OrderDto.CreateRequest();
        createRequest.setMemberNo(testMember.getMemberNo());
        createRequest.setReceiverAddr("서울시 강남구");
        //createRequest.setOrderDate(LocalDate.now());
        createRequest.setOrderDetailsList(List.of()); // 상세는 빈 리스트

        order = new Order();
        order.setOrderNo(100L);
        order.setMember(testMember);
        order.setReceiverAddr(createRequest.getReceiverAddr());
        order.setOrderStatus("완료");
    }

  // ================= CREATE =================
  @Test
  void testCreateOrder() throws Exception {
      Mockito.when(orderService.createOrder(any(Order.class))).thenReturn(order);

      mockMvc.perform(post("/orders")
                      .contentType(MediaType.APPLICATION_JSON)
                      .content(objectMapper.writeValueAsString(createRequest)))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.orderNo").value(order.getOrderNo()))
              .andExpect(jsonPath("$.receiverAddr").value(order.getReceiverAddr()));

      // DTO → Entity 변환 확인
      ArgumentCaptor<Order> captor = ArgumentCaptor.forClass(Order.class);
      Mockito.verify(orderService, times(1)).createOrder(captor.capture());
      Order captured = captor.getValue();
      assertThat(captured.getReceiverAddr()).isEqualTo(createRequest.getReceiverAddr());
  }

  // ================= READ =================
  @Test
  void testGetOrderById() throws Exception {
      Mockito.when(orderService.findById(100L)).thenReturn(Optional.of(order));

      mockMvc.perform(get("/orders/100"))
              .andExpect(status().isOk())
              .andExpect(jsonPath("$.orderNo").value(order.getOrderNo()))
              .andExpect(jsonPath("$.shippingAddress").value(order.getReceiverAddr()));
  }

  // ================= UPDATE =================
//   @Test
//   void testUpdateOrder() throws Exception {
//       OrderDto.UpdateRequest updateRequest = new OrderDto.UpdateRequest();
//       updateRequest.getReceiverAddr("서울시 서초구");

//       Mockito.when(orderService.updateOrder(eq(100L), any(OrderDto.UpdateRequest.class)))
//              .thenReturn(order);

//       mockMvc.perform(put("/orders/100")
//                       .contentType(MediaType.APPLICATION_JSON)
//                       .content(objectMapper.writeValueAsString(updateRequest)))
//               .andExpect(status().isOk())
//               .andExpect(jsonPath("$.orderNo").value(order.getOrderNo()));

//       ArgumentCaptor<OrderDto.UpdateRequest> captor = ArgumentCaptor.forClass(OrderDto.UpdateRequest.class);
//     //  Mockito.verify(orderService, times(1)).updateOrder(eq(100L), captor.capture());
//       assertThat(captor.getValue().getReceiverAddr()).isEqualTo(updateRequest.getReceiverAddr());
//   }

  // ================= DELETE =================
  @Test
  void testDeleteOrder() throws Exception {
      Mockito.doNothing().when(orderService).deleteOrder(100L);

      mockMvc.perform(delete("/orders/100"))
              .andExpect(status().isOk());

      Mockito.verify(orderService, times(1)).deleteOrder(100L);
  }
}
