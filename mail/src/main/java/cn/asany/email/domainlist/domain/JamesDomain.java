package cn.asany.email.domainlist.domain;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.james.core.Domain;
import org.hibernate.Hibernate;
import org.jfantasy.framework.dao.BaseBusEntity;

/**
 * James Domain
 *
 * @author limaofeng
 */
@Getter
@Setter
@RequiredArgsConstructor
@Entity(name = "JamesDomain")
@Table(name = "JAMES_DOMAIN")
public class JamesDomain extends BaseBusEntity {

  /** The name of the domain. column name is chosen to be compatible with the JDBCDomainList. */
  @Id
  @Column(name = "DOMAIN_NAME", nullable = false, length = 100)
  private String name;

  /** 所属组织 */
  @Column(name = "ORGANIZATION_ID")
  private Long organization;

  /**
   * Use this simple constructor to create a new Domain.
   *
   * @param name the name of the Domain
   */
  public JamesDomain(Domain name) {
    this.name = name.asString();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
      return false;
    }
    JamesDomain that = (JamesDomain) o;
    return name != null && Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return getClass().hashCode();
  }
}
