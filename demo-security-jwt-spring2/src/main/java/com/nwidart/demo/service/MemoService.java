package com.nwidart.demo.service;

import com.nwidart.demo.entity.Memo;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface MemoService {

  Optional<Memo> findById(Long id);

  Page<Memo> findAll(Pageable page);

  void store(Memo memo);

  void updateById(Long id, Memo memo);

  void removeById(Long id);
}
