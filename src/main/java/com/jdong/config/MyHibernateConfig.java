package com.jdong.config;

import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * Hibernate 설정 클래스를 정의합니다.
 * 이 클래스는 Hibernate의 물리적인 네이밍 전략에 대한 빈을 제공합니다.
 *
 * <p>
 * 이 설정 클래스를 사용하여 Hibernate가 데이터베이스 테이블과 컬럼 이름을 매핑할 때의
 * 물리적 네이밍 전략을 지정할 수 있습니다.
 * </p>
 *
 * <p>
 * 네이밍 전략으로 {@link PhysicalNamingStrategyStandardImpl}를 사용합니다.
 * 해당 전략은 기본적으로 대소문자를 구분하는 물리적 네이밍 전략을 제공합니다.
 * </p>
 */
@Configuration(proxyBeanMethods = false)
public class MyHibernateConfig {

  @Bean
  PhysicalNamingStrategyStandardImpl caseSensitivePhysicalNamingStrategy() {
    return new PhysicalNamingStrategyStandardImpl();
  }
}
