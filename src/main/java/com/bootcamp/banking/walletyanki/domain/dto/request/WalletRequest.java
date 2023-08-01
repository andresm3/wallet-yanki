package com.bootcamp.banking.walletyanki.domain.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

@Data
public class WalletRequest {
  @NotBlank(message = "Field documentNumber must be required")
  private String documentNumber;
  @NotBlank(message = "Field phone must be required")
  private String phone;
  @NotBlank(message = "Field email must be required")
  private String email;
  @Range(min = 1, max = 2, message = "Field profile must be 1 or 2")
  private int profile;
  private String accountNumber;
}
