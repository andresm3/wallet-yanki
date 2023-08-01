package com.bootcamp.banking.walletyanki.domain.models;


import com.bootcamp.banking.walletyanki.domain.dto.request.WalletRequest;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.FieldType;

@Document("wallets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Wallet {

  @Id
  @JsonSerialize(using = ToStringSerializer.class)
  private ObjectId id;
  private String documentNumber;
  private String phone;
  private String email;
  private int profile;
  @Field(targetType = FieldType.DECIMAL128)
  private BigDecimal balance;
  @JsonSerialize(using = ToStringSerializer.class)
  private ObjectId idAccount;


  public Wallet(WalletRequest request) {
    documentNumber = request.getDocumentNumber();
    phone = request.getPhone();
    email = request.getEmail();
    profile = request.getProfile();
    balance = BigDecimal.ZERO;
  }

}