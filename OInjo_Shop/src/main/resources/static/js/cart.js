// 장바구니에 아무 상품이 없으면 장바구니에 상품이 없다는 메시지 출력
document.addEventListener("DOMContentLoaded", function () {
  const cartContent = document.querySelector(".cart-content");
  const cartWrapper = document.querySelector(".cart-wrapper");
  const noCartMessage = document.querySelector(".no-cart");

  if (!cartContent || cartContent.children.length === 0) {
    noCartMessage.classList.remove("hidden");
  } else {
    cartWrapper.classList.remove("hidden");
  }
});

// 장바구니의 상품 수량개수를 수정
const memberId = parseInt(document.querySelector(".member-id").value);
const cartItemId = parseInt(document.querySelector(".cart-item-id").value);
const faMinus = document.querySelector(".fa-minus");
const faPlus = document.querySelector(".fa-plus");
let cartContentCounting = parseInt(
  document.querySelector(".cart-content-counting").textContent
); // 문자열을 숫자로 변환

const requestUrl = `/member/${memberId}/cart/${cartItemId}/update`;

faMinus.addEventListener("click", () => {
  if (cartContentCounting > 1) {
    cartContentCounting--;

    // PUT 메서드를 사용하여 요청을 보냄
    fetch(requestUrl, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        id:memberId,
        itemId:cartItemId,
        count: cartContentCounting,
      }),
    })
      .then((response) => {
        if (response.ok) {
          // 수정 성공
          console.log("수정 성공");
          updateCartContentCount();
        } else {
          // 수정 실패
          console.log("수정 실패");
        }
        console.log(response)
      })
      .catch((error) => {
        console.error("오류 발생:", error);
      });
  }
});

faPlus.addEventListener("click", () => {
  cartContentCounting++;

  // PUT 메서드를 사용하여 요청을 보냄
  fetch(requestUrl, {
    method: "PUT",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify({
        id:memberId,
        itemId:cartItemId,
        count: cartContentCounting,
    }),
  })
    .then((response) => {
      if (response.ok) {
        // 수정 성공
        console.log("수정 성공");
        updateCartContentCount();
      } else {
        // 수정 실패
        console.log("수정 실패");
      }
    })
    .catch((error) => {
      console.error("오류 발생:", error);
    });
});

function updateCartContentCount() {
  const cartContentCountingElement = document.querySelector(
    ".cart-content-counting"
  );
  cartContentCountingElement.textContent = cartContentCounting; // 수정된 수량을 화면에 반영
}
