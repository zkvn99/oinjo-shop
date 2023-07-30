package kr.idu.OInjo_Shop.dto.Cart;

import kr.idu.OInjo_Shop.dto.Item.ItemFormDTO;
import kr.idu.OInjo_Shop.dto.Member.MemberDTO;
import kr.idu.OInjo_Shop.entity.Cart.CartEntity;
import kr.idu.OInjo_Shop.entity.Cart.CartItemEntity;
import kr.idu.OInjo_Shop.entity.Item.ItemEntity;
import kr.idu.OInjo_Shop.entity.Item.ItemImgEntity;
import lombok.*;

import java.time.LocalDate;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CartItemDTO {
    private Long id;
    private CartEntity cart;
    private ItemEntity product;
    private int count;
    private LocalDate createDate;
    private ItemImgEntity image;

    // Getters and setters

    public static CartItemEntity toCartItemEntity(CartDTO cartDTO, ItemFormDTO item, int count, ItemImgEntity image) {
        CartItemEntity cartItemEntity = new CartItemEntity();
        cartItemEntity.setId(cartDTO.getId());
        cartItemEntity.setCart(CartDTO.toCartEntity(cartDTO));
        cartItemEntity.setProduct(item.createItem());
        cartItemEntity.setCount(count);
        cartItemEntity.setImage(image);
        cartItemEntity.setCreateDate(cartDTO.getCreateDate());

        return cartItemEntity;
    }


    public static CartItemDTO toCartItemDTO(CartEntity cartEntity) {
        CartItemDTO cartItemDTO = new CartItemDTO();
        cartItemDTO.setId(cartEntity.getId());
        cartItemDTO.setCart(CartDTO.toCartEntity(CartDTO.toCartDTO(cartEntity)));
        cartItemDTO.setCount(cartEntity.getCount());
        cartItemDTO.setCreateDate(cartEntity.getCreateDate());

        // product에 해당하는 ItemEntity가 null이 아닐 경우에만 설정
        if (cartEntity.getCartItems() != null) {
            CartItemEntity cartItemEntity = (CartItemEntity) cartEntity.getCartItems();
            ItemEntity itemEntity = cartItemEntity.getProduct();
            cartItemDTO.setProduct(itemEntity);

            // 이미지 정보 설정 (product에 연결된 이미지 정보인지 확인 후 설정)
            if (itemEntity != null) {
                ItemImgEntity imageEntity = cartItemEntity.getImage();
                cartItemDTO.setImage(imageEntity);
            }
        }


        return cartItemDTO;
    }

}
