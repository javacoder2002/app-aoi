package uz.pdp.appapi.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    @NotNull
    private String fullName;

    @NotNull
    private String phoneNumber;

    @NotNull
    private String address;

}
