package com.prmproject.hairsalonbooking.data.model.dataToSend;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.lang.reflect.Field;

public class EndDateValidator implements ConstraintValidator<EndDateValidation, String> {
    private String startDateField;

    @Override
    public void initialize(EndDateValidation constraintAnnotation) {
        this.startDateField = constraintAnnotation.startDate();
    }

    @Override
    public boolean isValid(String endDate, ConstraintValidatorContext context) {
        try {
            // Lấy đối tượng hiện tại (theo cách tương ứng)
            Object object = context.unwrap(javax.validation.Validator.class).getClass().getDeclaredField("target").get(context);
            Field startDateField = object.getClass().getDeclaredField(this.startDateField);
            startDateField.setAccessible(true);
            String startDate = (String) startDateField.get(object);

            // Kiểm tra tính hợp lệ (bạn có thể cần phân tích cú pháp ngày tháng)
            // Giả sử bạn đã chuyển đổi thành đối tượng LocalDate
            return endDate != null && startDate != null && endDate.compareTo(startDate) > 0; // So sánh chuỗi, có thể sử dụng LocalDate để so sánh đúng cách
        } catch (Exception e) {
            return false;
        }
    }
}

