package com.example.demo;


import com.example.demo.domain.Member;
import com.example.demo.dto.OrderDetailsDto;
import com.example.demo.dto.OrderDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private com.example.demo.repository.MemberRepository memberRepository;

    @Autowired
    private com.example.demo.repository.BookRepository bookRepository;

    private Member testMember;
    private com.example.demo.domain.Book testBook;

    // @BeforeEach
    // void setup() {
    //     testMember = memberRepository.findById(1000L).orElseThrow();
    //     testBook = bookRepository.findById(1).orElseThrow();
    // }

    // @Test
    // void testCreateOrder() throws Exception {
    //     OrderDto.CreateRequest createDto = new OrderDto.CreateRequest();
    //     createDto.setMemberNo(testMember.getMemberNo());
    //     createDto.setReceiverName("홍길동");
    //     createDto.setReceiverAddr("서울시 강남구");
    //     createDto.setOrderStatus("주문완료");
    //     createDto.setOrderTotal(10000L);
    //     createDto.setUsedPoint(0L);
    //     createDto.setPaymentTotal(10000L);
    //     createDto.setPhone("010-1234-5678");

    //     OrderDetailsDto.CreateRequest detailDto = new OrderDetailsDto.CreateRequest();
    //     detailDto.setBookNo(testBook.getBookNo());
    //     detailDto.setPurchaseQuantity(1L);

    //     createDto.setOrderDetailsList(List.of(detailDto));

    //     mockMvc.perform(post("/orders")
    //                     .contentType(MediaType.APPLICATION_JSON)
    //                     .content(objectMapper.writeValueAsString(createDto)))
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("$.receiverName").value("홍길동"))
    //             .andExpect(jsonPath("$.orderDetailsList[0].bookNo").value(testBook.getBookNo()));
    // }

    // @Test
    // void testUpdateOrder() throws Exception {
    //     // 먼저 주문 생성
    //     OrderDto.CreateRequest createDto = new OrderDto.CreateRequest();
    //     createDto.setMemberNo(testMember.getMemberNo());
    //     createDto.setReceiverName("홍길동");
    //     createDto.setReceiverAddr("서울시 강남구");
    //     createDto.setOrderStatus("주문완료");
    //     createDto.setOrderTotal(10000l);
    //     createDto.setUsedPoint(0L);
    //     createDto.setPaymentTotal(10000l);
    //     createDto.setPhone("010-1234-5678");

    //     OrderDetailsDto.CreateRequest detailDto = new OrderDetailsDto.CreateRequest();
    //     detailDto.setBookNo(testBook.getBookNo());
    //     detailDto.setPurchaseQuantity(1L);
    //     createDto.setOrderDetailsList(List.of(detailDto));

    //     String result = mockMvc.perform(post("/orders")
    //                     .contentType(MediaType.APPLICATION_JSON)
    //                     .content(objectMapper.writeValueAsString(createDto)))
    //             .andReturn().getResponse().getContentAsString();

    //     Long orderNo = objectMapper.readTree(result).get("orderNo").asLong();

    //     // 수정 DTO
    //     OrderDto.UpdateRequest updateDto = new OrderDto.UpdateRequest();
    //     updateDto.setReceiverName("김철수");
    //     updateDto.setReceiverAddr("서울시 서초구");
    //     updateDto.setOrderStatus("배송중");
    //     updateDto.setOrderTotal(10000l);
    //     updateDto.setUsedPoint(0l);
    //     updateDto.setPaymentTotal(10000l);
    //     updateDto.setPhone("010-9876-5432");

    //     mockMvc.perform(put("/orders/{orderNo}", orderNo)
    //                     .contentType(MediaType.APPLICATION_JSON)
    //                     .content(objectMapper.writeValueAsString(updateDto)))
    //             .andExpect(status().isOk())
    //             .andExpect(jsonPath("$.receiverName").value("김철수"))
    //             .andExpect(jsonPath("$.orderStatus").value("배송중"));
    // }

    // @Test
    // void testDeleteOrder() throws Exception {
    //     // 주문 생성
    //     OrderDto.CreateRequest createDto = new OrderDto.CreateRequest();
    //     createDto.setMemberNo(testMember.getMemberNo());
    //     createDto.setReceiverName("홍길동");
    //     createDto.setReceiverAddr("서울시 강남구");
    //     createDto.setOrderStatus("주문완료");
    //     createDto.setOrderTotal(10000l);
    //     createDto.setUsedPoint(0l);
    //     createDto.setPaymentTotal(10000l);
    //     createDto.setPhone("010-1234-5678");

    //     String result = mockMvc.perform(post("/orders")
    //                     .contentType(MediaType.APPLICATION_JSON)
    //                     .content(objectMapper.writeValueAsString(createDto)))
    //             .andReturn().getResponse().getContentAsString();

    //     Long orderNo = objectMapper.readTree(result).get("orderNo").asLong();

    //     // 삭제
    //     mockMvc.perform(delete("/orders/{orderNo}", orderNo))
    //             .andExpect(status().isNoContent());
    // }

    // @Test
    // void testGetOrdersByMember() throws Exception {
    //     mockMvc.perform(get("/orders/member/{memberNo}", testMember.getMemberNo()))
    //             .andExpect(status().isOk());
    // }
}
