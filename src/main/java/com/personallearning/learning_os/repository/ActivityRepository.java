package com.personallearning.learning_os.repository;

import com.personallearning.learning_os.domain.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
// extends的第一个处理数据库，第二个过滤
public interface ActivityRepository extends JpaRepository<Activity, Long>, JpaSpecificationExecutor<Activity> {  
    // Day1 不需要写任何方法
}
