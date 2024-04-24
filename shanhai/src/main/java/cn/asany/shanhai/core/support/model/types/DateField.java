package cn.asany.shanhai.core.support.model.types;

import cn.asany.shanhai.core.domain.ModelFieldMetadata;
import cn.asany.shanhai.core.support.model.FieldType;
import java.sql.Timestamp;
import java.util.Date;
import lombok.Data;
import org.springframework.stereotype.Component;

/**
 * 日期类型
 *
 * @author limaofeng
 */
@Data
@Component
public class DateField implements FieldType<Date, Timestamp> {
  private String id = "Date";
  private String name = "日期";
  private String javaType = Date.class.getName();
  private String graphQLType = "Date";

  private String description;

  private FieldTypeFamily family;

  public DateField() {}

  public DateField(FieldTypeFamily family, String id, String name, String description) {
    this.id = id;
    this.name = name;
    this.family = family;
    this.description = description;
  }

  @Override
  public String getJavaType(ModelFieldMetadata metadata) {
    return this.javaType;
  }

  @Override
  public String getGraphQLType() {
    return graphQLType;
  }

  @Override
  public Date convertToEntityAttribute(Timestamp dbData) {
    return new Date(dbData.getTime());
  }
}
