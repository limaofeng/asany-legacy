package cn.asany.autoconfigure;

import cn.asany.storage.graphql.scalar.FileCoercing;
import graphql.kickstart.servlet.apollo.ApolloScalars;
import graphql.schema.GraphQLScalarType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageAutoConfiguration {

  @Bean
  public GraphQLScalarType uploadScalarDefine() {
    return ApolloScalars.Upload;
  }
  @Bean
  public FileCoercing fileCoercing() {
    return new FileCoercing();
  }

  @Bean
  public GraphQLScalarType fileByScalar() {
    return GraphQLScalarType.newScalar()
      .name("File")
      .description("文件")
      .coercing(fileCoercing())
      .build();
  }

}
