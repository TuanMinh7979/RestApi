package com.rest.onetomany.exception;

import java.text.MessageFormat;

public class ItemIsAssignedException extends RuntimeException {
    //TỨC LÀ SẢN PHẨM ĐÃ ĐC ĐẶT TRƯỚC NHƯNG CHƯA ĐC THÊM VÀO GIỎ (giống như đặt sân bóng tại thời điểm đặt
    // ta sẽ không thêm vào ngay vì một số lý do có thể ta muốn thống kê thời gian chính xác lúc nhận sân thi đấu
    // nên tạm gán là đã đặt trước , thông tin về thời gian sẽ tạo ra 1 số nghiệp vụ phức tạp)

    //Hay đơn giản ta lưu thông tin ngay lúc đặt sân thì item là duy nhất nên không thể thêm cho cart khác.

    //=> DO VẬY: khi một sản phẩm đã đc đặt trước ta sẽ thông báo là đã bị đặt trước nên không thể thêm vào giỏ

    public ItemIsAssignedException(final Long itemId, final Long cartId) {
        super(MessageFormat.format("item {0} is already assigned to cart{1}", itemId, cartId));
    }
}
