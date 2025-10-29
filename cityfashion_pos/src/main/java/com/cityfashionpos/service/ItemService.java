package com.cityfashionpos.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.cityfashionpos.dto.ItemRequestDTO;
import com.cityfashionpos.dto.ItemResponseDTO;
import com.cityfashionpos.entity.ItemEntity;

public interface ItemService {
    ItemEntity saveItem(ItemRequestDTO itemDTO, MultipartFile imageFile);

    ItemEntity updateItem(Long id, ItemRequestDTO itemDTO, MultipartFile imageFile);

    ItemEntity getItemById(Long id);

    ItemEntity getItemByCode(String code);

    void deleteItem(Long id);

    List<ItemResponseDTO> getAllItems();

    List<ItemResponseDTO> updateItemQuantity(Long id, Integer quantity, String transactionType);
}