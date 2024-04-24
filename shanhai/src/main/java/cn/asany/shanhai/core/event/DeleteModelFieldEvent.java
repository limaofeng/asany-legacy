package cn.asany.shanhai.core.event;

import cn.asany.shanhai.core.domain.ModelField;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

public class DeleteModelFieldEvent extends ApplicationEvent {

  public DeleteModelFieldEvent(Long modelId, ModelField field) {
    super(new DeleteModelFieldSource(modelId, field));
  }

  @Data
  @AllArgsConstructor
  public static class DeleteModelFieldSource {
    private Long modelId;
    private ModelField field;
  }
}
