package br.com.ambarx.criptografia.entity;

import br.com.ambarx.criptografia.service.CryptoService;
import jakarta.persistence.*;

@Entity
@Table(name = "transactions_table")
public class TransactionEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "transaction_id")
  private Long transactionId;
  @Column(name = "user_document")
  private String encryptedUserDocument;
  @Column(name = "credit_card_token")
  private String encryptedCreditCardToken;
  @Column(name = "transaction_value")
  private Long transactionValue;
  @Transient // Serve para que o hibernate reconheça que não é um dado para ser persistido no Banco
  private String rawUserDocument;
  @Transient // Serve para que o hibernate reconheça que não é um dado para ser persistido no Banco
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


  /**A Anotação @PrePersist quer dizer básicamente antes de persistir o(s) dado(s) no banco faça o seguinte:
   * Nesse caso eu vou pegar os dados cru dos campos rawUserDocument e rawCreditCardToken e encryptar usando o CryptoService.encrypt()
   * Isso vai encryptar os dados de texto comum e salvar no campo que vai representar realmente o dado na coluna do DB */
  @PrePersist
  public void prepPersist(){
    this.encryptedUserDocument    = CryptoService.encrypt(rawUserDocument);
    this.encryptedCreditCardToken = CryptoService.encrypt(rawCreditCardToken);
  }

  /**A Anotação @PosLoad quer dizer justo o contrário `quando os dados forem carregados do DB faça`
   * Neste caso eu vou pegar o s dados encryptados dos campos encryptedUserDocument e encryptedCreditCardToken e
   * desencryptar usando CryptoService.decrypt().
   * Isso vai mostrar os dados em sua forma original(Text) nos campos rawUserDocument e rawCreditCardToken para que possam ser exibidos ao usuário
   * */
  @PostLoad
  public void postLoad(){
    this.rawUserDocument    = CryptoService.encrypt(encryptedUserDocument);
    this.rawCreditCardToken = CryptoService.encrypt(encryptedCreditCardToken);
  }


}

