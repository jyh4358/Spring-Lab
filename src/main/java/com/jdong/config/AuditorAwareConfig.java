package com.jdong.config;

import java.util.Optional;
import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

@Component
public class AuditorAwareConfig implements AuditorAware<Long> {

  @Override
  public Optional<Long> getCurrentAuditor() {
//    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//    if(null == authentication || !authentication.isAuthenticated()) {
//      return Optional.of(0L);
//    }
    // 사용자 환경에 맞게 로그인한 사용자의 정보를 불러온는 로직 추가
//    CustomUserDetails userDetails = (CustomUserDetails)authentication.getPrincipal();
//
//    return Optional.of(userDetails.getId());

    return Optional.of(1L);
  }

}

