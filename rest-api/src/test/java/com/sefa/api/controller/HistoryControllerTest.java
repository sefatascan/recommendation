package com.sefa.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sefa.api.dto.response.ProductResponse;
import com.sefa.api.service.HistoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = HistoryController.class )
class HistoryControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HistoryService historyService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void get_history_user_should_200_with_body() throws Exception {
        final String url = "/history/user/{id}";
        final String userId = "user-1";

        ProductResponse response = ProductResponse.builder()
                .userId(userId)
                .products(Collections.emptyList())
                .type(ProductResponse.ResponseType.PERSONALIZED)
                .build();

        when(historyService.getLatestTenProductByUser(userId)).thenReturn(response);

        mockMvc.perform(get(url, userId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(objectMapper.writeValueAsString(response)));
    }

    @Test
    void get_history_user_should_404_given_path_variable_null() throws Exception {
        final String url = "/history/user/{id}";
        final String userId = null;

        mockMvc.perform(get(url, userId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    void delete_history_user_should_200_with_body() throws Exception {
        final String url = "/history/user/{id}";
        final String userId = "user-1";
        final List<String> productIds = List.of("product-1", "product-2");


        doNothing().when(historyService).deleteHistoryByUserIdAndProductId(userId, productIds);

        mockMvc.perform(delete(url, userId)
                        .param("productIds", "product-1")
                        .param("productIds", "product-2")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void delete_history_user_should_500_and_exist_message_given_request_params_null() throws Exception {
        final String url = "/history/user/{id}";
        final String userId = "user-1";

        mockMvc.perform(delete(url, userId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.message").exists());
    }

    @Test
    void delete_history_user_should_404_given_path_variable_null() throws Exception {
        final String url = "/history/user/{id}";
        final String userId = null;

        mockMvc.perform(delete(url, userId)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

}