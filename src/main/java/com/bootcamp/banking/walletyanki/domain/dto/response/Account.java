package com.bootcamp.banking.walletyanki.domain.dto.response;

import java.math.BigDecimal;
import java.util.List;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Data
public class Account {

  private String id;
  private String debitCard;
  private String number;
  private int position;
  private Client client;
  private TypeAccount typeAccount;
  private List<String> holders;
  private List<String> signatories;
  @Field(targetType = FieldType.DECIMAL128)
  private BigDecimal balance;
  private boolean status;
}
