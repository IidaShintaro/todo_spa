package com.sample.todo.service;

import com.sample.todo.entity.StatusEntity;
import com.sample.todo.repository.StatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class StatusService {

    private final StatusRepository repository;

    // ステータスMap作成メソッド
    public Map<Long, String> statusMap() {

        // ステータスマスタ全件取得
        List<StatusEntity> statusList = repository.statusAll();

        // ステータスMap作成
        Map<Long, String> statusMap = new HashMap<>();
        for  (StatusEntity status : statusList) {
            statusMap.put(status.getId(), status.getStatusName());
        }

        return  statusMap;
    }
}
