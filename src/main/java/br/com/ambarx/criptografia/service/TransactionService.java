package br.com.ambarx.criptografia.service;

import br.com.ambarx.criptografia.controller.dto.CreateTransactionRequest;
import br.com.ambarx.criptografia.controller.dto.TransactionResponse;
import br.com.ambarx.criptografia.entity.TransactionEntity;
import br.com.ambarx.criptografia.repository.TransactionRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
  private final TransactionRepository repository;

  public TransactionService(TransactionRepository repository) {
    this.repository = repository;
  }

  public void create(CreateTransactionRequest request){
    var entity = new TransactionEntity();
    entity.setRawCreditCardToken(request.creditCardToken());
    entity.setRawUserDocument(request.userDocument());
    entity.setTransactionValue(request.value());

    repository.save(entity);
  }

  public Page<TransactionResponse> listAll(int page, int pageSize){
    var content = repository.findAll(PageRequest.of(page, pageSize));
    return content.map(TransactionResponse::fromEntity);
  }
}
