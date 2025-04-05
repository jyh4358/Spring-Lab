package com.jdong.config;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * QueryDSL 사용을 위한 설정 클래스입니다.
 *
 * 이 클래스는 Spring Framework의 @Configuration 애노테이션을 통해
 * QueryDSL 관련 빈(bean)을 생성 및 관리합니다.
 *
 * JPAQueryFactory 빈을 생성하여, 이를 통해 QueryDSL을 사용할 수 있도록 구성합니다.
 */
@Configuration
public class QuerydslConfig {

  @PersistenceContext
  private EntityManager entityManager;

  @Bean
  public JPAQueryFactory queryFactory() {
    return new JPAQueryFactory(entityManager);
  }
}
