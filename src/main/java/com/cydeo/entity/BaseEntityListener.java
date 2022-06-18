import org.keycloak.adapters.springsecurity.account.SimpleKeycloakAccount;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

@Component
public class BaseEntityListener extends AuditingEntityListener {
    @PrePersist
    private void onPrePersist(BaseEntity baseEntity){
        String userId = getUserId();
        baseEntity.setInsertDateTime(LocalDateTime.now());
        baseEntity.setLastUpdateDateTime(LocalDateTime.now());
        baseEntity.setInsertUserId(userId);
        baseEntity.setLastUpdateUserId(userId);
    }

    @PreUpdate
    private void onPreUpdate(BaseEntity baseEntity){

        String userId = getUserId();
        baseEntity.setLastUpdateDateTime(LocalDateTime.now());
        baseEntity.setLastUpdateUserId(userId);
    }

    public String getUserId(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SimpleKeycloakAccount details = (SimpleKeycloakAccount) authentication.getDetails();
        return details.getKeycloakSecurityContext().getToken().getSubject();
    }
}
