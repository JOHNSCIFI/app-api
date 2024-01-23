package uz.muydinovs.appApi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {
    @NotNull(message = "Input full name")
    private String fullName;
    @NotNull(message = "Input phone number")
    private String phoneNumber;
    @NotNull(message = "Input address")
    private String address;
}
