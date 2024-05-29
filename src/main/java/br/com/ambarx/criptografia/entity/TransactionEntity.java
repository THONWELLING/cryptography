package br.com.ambarx.criptografia.entity;

import br.com.ambarx.criptografia.service.CryptoService;
import jakarta.persistence.*;

@Entity
@Table(name = "transactions_table")
public class TransactionEntity {
  @Id
  @Column(name = "transaction_id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long transactionId;

  @Column(name = "user_document")
  private String encryptedUserDocument;

  @Column(name = "credit_card_token")
  private String encryptedCreditCardToken;

  @Column(name = "transaction_value")
  private Long transactionValue;

  @Transient // Serve para que o Hibernate reconheça que este não é um dado que será salvo no banco
  private String rawUserDocument;
  @Transient // Serve para que o Hibernate reconheça que este não é um dado que será salvo no banco
  private String rawCreditCardToken;

  public TransactionEntity() {}

  public Long getTransactionId() {
    return transactionId;
  }

  public void setTransactionId(Long transactionId) {
    this.transactionId = transactionId;
  }

  public String getEncryptedUserDocument() {
    return encryptedUserDocument;
  }

  public void setEncryptedUserDocument(String encryptedUserDocument) {
    this.encryptedUserDocument = encryptedUserDocument;
  }

  public String getEncryptedCreditCardToken() {
    return encryptedCreditCardToken;
  }

  public void setEncryptedCreditCardToken(String encryptedCreditCardToken) {
    this.encryptedCreditCardToken = encryptedCreditCardToken;
  }

  public Long getTransactionValue() {
    return transactionValue;
  }

  public void setTransactionValue(Long transactionValue) {
    this.transactionValue = transactionValue;
  }

  public String getRawUserDocument() {
    return rawUserDocument;
  }

  public void setRawUserDocument(String rawUserDocument) {
    this.rawUserDocument = rawUserDocument;
  }

  public String getRawCreditCardToken() {
    return rawCreditCardToken;
  }

  public void setRawCreditCardToken(String rawCreditCardToken) {
    this.rawCreditCardToken = rawCreditCardToken;
  }

}