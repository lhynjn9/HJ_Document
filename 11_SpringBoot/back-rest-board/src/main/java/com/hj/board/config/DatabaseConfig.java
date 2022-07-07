// 5. 데이터베이스 관련 환경 설정

package com.hj.board.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.hj.board.model.dao")
public class DatabaseConfig {}
