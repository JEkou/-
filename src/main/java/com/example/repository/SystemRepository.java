package com.example.repository;
import java.util.List;

import com.example.entity.Config;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author USER
 */
@Repository
public interface SystemRepository extends JpaRepository<Config,Long> {

    /**
     * 获取启用的配置
     * @param isDelete 是否删除(启用)
     * @return 配置列表
     */
    List<Config> getByIsDelete(int isDelete);

}
