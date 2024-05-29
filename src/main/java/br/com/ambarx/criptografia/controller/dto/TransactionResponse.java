package br.com.ambarx.criptografia.controller.dto;

import br.com.ambarx.criptografia.entity.TransactionEntity;

public record TransactionResponse (Long id, String userDocument, String creditCardToken, Long value){

  public static TransactionResponse fromEntity(TransactionEntity entity) {
    return new TransactionResponse(
        entity.getTransactionId(),
        entity.getEncryptedUserDocument(),
        entity.getEncryptedCreditCardToken(),
        entity.getTransactionValue()
    );
  }
}
