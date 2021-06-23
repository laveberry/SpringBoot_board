package com.springboot.board.domain.entity;

import lombok.Getter;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/**Auditing: 데이터 조작시 자동으로 날짜수정 JPA
 * */
@Getter
@MappedSuperclass //테이블로 매핑하지않고, 자식 Entitiy에게 매핑정보를 상속하기위한 어노테이션
@EntityListeners(AuditingEntityListener.class) //JPA에서 해당 Entity는 Auditing기능을 사용한다는 것을 알리는 어노테이션
public class TimeEntitiy{

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;

}
