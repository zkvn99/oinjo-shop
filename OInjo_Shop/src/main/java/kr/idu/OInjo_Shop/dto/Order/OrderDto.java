package kr.idu.OInjo_Shop.dto.Order;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderDto {
    private Long itemId;        //상품 아이디

    private int count;
}