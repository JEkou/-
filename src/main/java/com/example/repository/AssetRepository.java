package com.example.repository;

import com.example.entity.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 祥玉
 */
@Repository
public interface AssetRepository extends JpaRepository<Asset,Long> {
    /**
     * 查找所有没有发送的数据报
     * @param isSend 是否发送
     * @param isDelete 是否删除
     * @return 没有删除且没有发送的数据
     */
    List<Asset> getByIsSendAndIsDelete(int isSend,int isDelete);

    /**
     * 查找没有发送成功的数据报数量
     * @param isSend 是否发送
     * @param isDelete 是否被删除
     * @return 没有被删除且没有发送的数量
     */
    int countAllByIsSendAndIsDelete(int isSend,int isDelete);
}



