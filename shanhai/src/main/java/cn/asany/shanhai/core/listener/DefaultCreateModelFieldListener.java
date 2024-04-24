package cn.asany.shanhai.core.listener;

import cn.asany.shanhai.core.event.CreateModelFieldEvent;
import cn.asany.shanhai.core.event.CreateModelFieldEvent.CreateModelFieldSource;
import cn.asany.shanhai.core.support.ModelParser;
import cn.asany.shanhai.core.support.graphql.GraphQLReloadSchemaProvider;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Slf4j
@Component
public class DefaultCreateModelFieldListener implements ApplicationListener<CreateModelFieldEvent> {

  @Autowired private ModelParser modelParser;

  @Autowired private GraphQLReloadSchemaProvider schemaProvider;

  @SneakyThrows
  @Override
  public void onApplicationEvent(CreateModelFieldEvent event) {
    CreateModelFieldSource source = (CreateModelFieldSource) event.getSource();
    StopWatch sw = StopWatchHolder.get();

    modelParser.createModelField(source.getModelId(), source.getField());

    schemaProvider.updateSchema();

    log.info(sw.prettyPrint());
  }
}
