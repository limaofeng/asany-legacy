package cn.asany.storage.graphql.scalar;

import cn.asany.storage.api.FileObject;
import cn.asany.storage.dto.SimpleFileObject;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.jfantasy.framework.error.ValidationException;
import org.jfantasy.framework.util.common.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

/**
 * @author limaofeng
 * @version V1.0
 * @date 2022/7/28 9:12 9:12
 */
@Slf4j
public class FileCoercing implements Coercing<FileObject, Object> {


  @Autowired protected Environment environment;

  @Override
  public Object serialize(@NotNull Object input) throws CoercingSerializeException {
    if (!(input instanceof SimpleFileObject)) {
      return input;
    }
    SimpleFileObject fileObject = ((SimpleFileObject) input);
    if (StringUtil.isBlank(fileObject.getUrl())) {
      fileObject.setUrl(
        environment.getProperty("STORAGE_BASE_URL") + "/preview/" + fileObject.getId());
    }
    return input;
  }

  @NotNull
  @Override
  public FileObject parseValue(@NotNull Object input) throws CoercingParseValueException {
//    if (input instanceof StringValue) {
//      String value = ((StringValue) input).getValue();
//      IdUtils.FileKey fileKey = IdUtils.parseKey(value);
//      FileDetail detail = fileKey.getFile();
//      return SimpleFileObject.builder()
//        .id(value)
//        .directory(false)
//        .name(detail.getName())
//        .size(detail.getSize())
//        .lastModified(detail.getLastModified())
//        .path(detail.getPath())
//        .url(environment.getProperty("STORAGE_BASE_URL") + "/preview/" + value)
//        .build();
//    } else if (input instanceof String) {
//      IdUtils.FileKey fileKey = IdUtils.parseKey((String) input);
//      FileDetail detail = fileKey.getFile();
//      return SimpleFileObject.builder()
//        .id((String) input)
//        .directory(false)
//        .name(detail.getName())
//        .size(detail.getSize())
//        .mimeType(detail.getMimeType())
//        .lastModified(detail.getLastModified())
//        .path(detail.getPath())
//        .url(environment.getProperty("STORAGE_BASE_URL") + "/preview/" + input)
//        .build();
//    }
    throw new ValidationException(String.format("不支持的类型 %s", input.getClass()));
  }

  @NotNull
  @Override
  public FileObject parseLiteral(@NotNull Object input) throws CoercingParseLiteralException {
    return parseValue(input);
  }
}